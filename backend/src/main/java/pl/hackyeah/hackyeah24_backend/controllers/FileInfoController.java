package pl.hackyeah.hackyeah24_backend.controllers;



import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.hackyeah.hackyeah24_backend.models.FileInfo;
import pl.hackyeah.hackyeah24_backend.responses.ResponseManager;
import pl.hackyeah.hackyeah24_backend.services.FileInfoService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
@Hidden
public class FileInfoController {

    private FileInfoService fileInfoService;
    @Autowired
    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<FileInfo>> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseManager.ok(fileInfoService.upload(files));
    }

    @PostMapping
    public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseManager.ok(fileInfoService.upload(file));
    }

    @GetMapping
    public ResponseEntity<byte[]> getImage(@RequestParam("id") int id,@RequestParam(value = "scale", required = false, defaultValue = "1.0") Double scale) throws IOException {
        byte[] image = fileInfoService.getFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @GetMapping("/getVideo")
    public ResponseEntity<byte[]> getVideo(@RequestParam("path") String path) throws IOException {
        byte[] video = fileInfoService.getFile(path);


        String contentType = "video/mp4";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(video);
    }



    @GetMapping("/all")
    public ResponseEntity<List<FileInfo>> findAll() {
        List<FileInfo> fileInfos = fileInfoService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);
    }
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("path") String path) throws IOException {
        byte[] image = fileInfoService.getFile(path);

        String contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                .body(image);
    }

    @GetMapping("/get")
    //@PreAuthorize("@permissionEvaluator.hasPermission(authentication, #path, 'write')")
    public ResponseEntity<?> getFile(@RequestParam("path") String path) throws IOException {
        byte[] fileData = fileInfoService.getFile(path);
        String fileName = new File(path).getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        MediaType mediaType = getMediaTypeForFileExtension(fileExtension);
        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(fileData);

    }

    private MediaType getMediaTypeForFileExtension(String fileExtension) {
        return switch (fileExtension.toLowerCase()) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "pdf" -> MediaType.APPLICATION_PDF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }

}
