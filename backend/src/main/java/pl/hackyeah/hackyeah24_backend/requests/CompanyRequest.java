package pl.hackyeah.hackyeah24_backend.requests;

import lombok.Data;
import pl.hackyeah.hackyeah24_backend.enums.OrganizationSize;

import java.util.List;


public class CompanyRequest {
    String name;
    String description;
    String email;
    int sector_id;
    OrganizationSize size;
    String websiteUrl;
    boolean isOpenToWork;
    Double minBudget;
    Double maxBudget;
    boolean verified;
    String nip;
    Long logoId;
    String password;
    List<Integer> tagsIds;

    public List<Integer> getTagsIds() {
        return tagsIds;
    }

    public void setTagsIds(List<Integer> tagsIds) {
        this.tagsIds = tagsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSector_id() {
        return sector_id;
    }

    public void setSector_id(int sector_id) {
        this.sector_id = sector_id;
    }

    public OrganizationSize getSize() {
        return size;
    }

    public void setSize(OrganizationSize size) {
        this.size = size;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public boolean isOpenToWork() {
        return isOpenToWork;
    }

    public void setOpenToWork(boolean openToWork) {
        isOpenToWork = openToWork;
    }

    public Double getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(Double minBudget) {
        this.minBudget = minBudget;
    }

    public Double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(Double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Long getLogoId() {
        return logoId;
    }

    public void setLogoId(Long logoId) {
        this.logoId = logoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
