package pl.hackyeah.hackyeah24_backend.requests;

public class ProjectRequest {
    public String name;
    public String short_about_description;
    public String long_about_description;
    public int image_id;
    public String how_description;
    public String needs_description;
    public int foundation_id;

    public String getName() {
        return name;
    }

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

    public void setImage_id(int image_id) {
        this.image_id = image_id;
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

    public int getFoundation_id() {
        return foundation_id;
    }

    public void setFoundation_id(int foundation_id) {
        this.foundation_id = foundation_id;
    }
}
