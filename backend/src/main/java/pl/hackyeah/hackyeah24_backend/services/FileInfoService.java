package pl.hackyeah.hackyeah24_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.hackyeah.hackyeah24_backend.exceptions.rest.RestException;
import pl.hackyeah.hackyeah24_backend.exceptions.rest.RestNotFoundException;
import pl.hackyeah.hackyeah24_backend.models.FileInfo;
import pl.hackyeah.hackyeah24_backend.repositories.FileInfoRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileInfoService {

    private  FileInfoRepository fileInfoRepository;

    @Autowired
    public FileInfoService(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }
    @Value("${yeah.file-path}")
    private String FOLDER_PATH;

    public FileInfoService() {
    }


    public FileInfo findById(int id){
        return fileInfoRepository.findById(id).orElseThrow(()->new RestNotFoundException(id));
    }

    public List<FileInfo> upload(MultipartFile[] files) {
        List<FileInfo> filesInfos = new ArrayList<>();
        try{
            for(MultipartFile file: files){
                if(file.isEmpty()){
                    throw new RestException("Empty file");
                }
                String contentType = file.getContentType();
                String path = file.getOriginalFilename();
                System.out.println(path);
                file.transferTo(new File(FOLDER_PATH + path));
                long fileSizeBytes = file.getSize();

                double fileSizeKB = (double) fileSizeBytes / (1024);
                filesInfos.add(new FileInfo(FOLDER_PATH + path,contentType,fileSizeKB));
            }
        }catch (IOException e) {
            throw new RestException(e.getMessage());
        }
        List<FileInfo> newFilesInfos = fileInfoRepository.saveAll(filesInfos);
        return newFilesInfos;
    }
    public List<FileInfo> upload(List<File> files) {
        List<FileInfo> filesInfos = new ArrayList<>();
        try {
            for (File file : files) {
                if (!file.exists() || file.isDirectory()) {
                    throw new RestException("Wystąpił błąd");
                }

                String contentType = Files.probeContentType(file.toPath());
                String path = file.getName();
                long fileSizeBytes = file.length();
                double fileSizeKB = (double) fileSizeBytes / (1024);
                filesInfos.add(new FileInfo(path, contentType, fileSizeKB));
            }
        } catch (IOException e) {
            throw new RestException(e.getMessage());
        }

        List<FileInfo> newFilesInfos = fileInfoRepository.saveAll(filesInfos);
        return newFilesInfos;
    }

    public FileInfo upload(MultipartFile file) throws IOException {
        FileInfo fileInfo;
        try{
            if(file.isEmpty()){
                throw new RestException("Empty file :(");
            }
            String contentType = file.getContentType();
            String path = file.getOriginalFilename();
            file.transferTo(new File(FOLDER_PATH + path));
            long fileSizeBytes = file.getSize();

            double fileSizeKB = (double) fileSizeBytes / (1024);
            fileInfo = new FileInfo(FOLDER_PATH + path,contentType,fileSizeKB);
        }catch (IOException e) {
            throw new RestException(e.getMessage());
        }
        return fileInfoRepository.save(fileInfo);
    }
    public byte[] getFile(int id) throws IOException {
        FileInfo fileInfo = findById(id);
        return getFile(fileInfo.getPath());
    }
    public byte[] getFile(String path) throws IOException {
        FileInfo fileInfo = fileInfoRepository.findFirstByPath(path)
                .orElseThrow(()->new RestNotFoundException("Cannot find file by path: %s".formatted(path)));
        String fullPath = fileInfo.getPath();
        byte[] images = Files.readAllBytes(new File(fullPath).toPath());
        return images;
    }

    public byte[] getFile(String path, Double scale) throws IOException {
        byte[] originalImage =getFile(path);
        if(scale>0&&scale!=1.0){
            String originalFormat = getImageFormatFromPath(path);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(originalImage));
            BufferedImage resizedImage = resizeImage(bufferedImage, scale);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, originalFormat, baos);
            originalImage = baos.toByteArray();
        }
        return originalImage;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, double scale) {
        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        java.awt.Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }
    private String getImageFormatFromPath(String path) {
        int dotIndex = path.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < path.length() - 1) {
            return path.substring(dotIndex + 1).toLowerCase();
        }
        return "jpg";
    }

    public List<FileInfo> findAll() {
        return fileInfoRepository.findAll();
    }


}
