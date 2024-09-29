package pl.hackyeah.hackyeah24_backend.requests;

import jakarta.persistence.Column;
import pl.hackyeah.hackyeah24_backend.enums.OrganizationSize;

import java.util.List;

public class FoundationRequest {
    String name;
    String email;
    int sector_id;
    String websiteUrl;
    boolean verified;
    String nip;
    Long logoId;
    String password;
    public String experience;
    public String strengths;
    public String history;
    public String additionalInfo;
    public String goals;

    public List<Integer> getTagsIds() {
        return tagsIds;
    }

    public void setTagsIds(List<Integer> tagsIds) {
        this.tagsIds = tagsIds;
    }

    public List<Integer> tagsIds;

    public OrganizationSize getSize() {
        return size;
    }

    public void setSize(OrganizationSize size) {
        this.size = size;
    }

    public OrganizationSize size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}
