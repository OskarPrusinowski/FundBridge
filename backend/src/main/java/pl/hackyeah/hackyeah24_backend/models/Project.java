package pl.hackyeah.hackyeah24_backend.models;

import jakarta.persistence.*;

import java.util.List;

@Table(name="projects")
@Entity
public class Project extends BaseModel {
    @Column(name="name", nullable = false)
    public String name;

    @Column(name="short_about_description", length = 65535)
    public String short_about_description;

    @Column(name="long_about_description", length = 65535)
    public String long_about_description;

    @Column(name="image_id")
    public int image_id;

    @Column(name="how_description", length = 65535)
    public String how_description;

    @Column(name="needs_description", length = 65535)
    public String needs_description;

    @ManyToOne
    @JoinColumn(name="foundation_id")
    public Foundation foundation;


    public Project(Foundation foundation,String short_about_description, String long_about_description, int image_id, String how_description, String needs_description, String name) {
        this.short_about_description = short_about_description;
        this.long_about_description = long_about_description;
        this.image_id = image_id;
        this.how_description = how_description;
        this.needs_description = needs_description;
        this.name = name;
        this.foundation=foundation;
    }

    public Project() {

    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getShort_about_description() {
        return short_about_description;
    }

    public void setShort_about_description(String short_about_description) {
        this.short_about_description = short_about_description;
    }

    public String getLong_about_description() {
        return long_about_description;
    }

    public void setLong_about_description(String long_about_description) {
        this.long_about_description = long_about_description;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image) {
        this.image_id = image;
    }

    public String getHow_description() {
        return how_description;
    }

    public void setHow_description(String how_description) {
        this.how_description = how_description;
    }

    public String getNeeds_description() {
        return needs_description;
    }

    public void setNeeds_description(String needs_description) {
        this.needs_description = needs_description;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }
}
