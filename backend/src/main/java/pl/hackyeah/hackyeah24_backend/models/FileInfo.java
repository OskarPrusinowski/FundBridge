package pl.hackyeah.hackyeah24_backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "files_infos")
public class FileInfo extends BaseModel {

    @Column(name = "path")
    @NotBlank
    private String path;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private double size;

    public FileInfo() {}
    public FileInfo(int id) {
        this.id =id;
    }

    public FileInfo(String path, String contentType, double size) {
        this.path = path;
        this.contentType = contentType;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

