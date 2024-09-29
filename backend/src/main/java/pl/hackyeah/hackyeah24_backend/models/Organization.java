package pl.hackyeah.hackyeah24_backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.hackyeah.hackyeah24_backend.enums.OrganizationSize;
import pl.hackyeah.hackyeah24_backend.models.Sector;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("Organization")
@Table(name="organizations")
@SQLDelete(sql = "UPDATE organizations SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Organization extends BaseModel {

    @Column(name="email", nullable = false, unique = true)
    protected String email;

    @Column(name="is_open_to_work", nullable = false)
    protected boolean isOpenToWork = true;

    public boolean getIsOpenToWork() {
        return isOpenToWork;
    }

    public void setOpenToWork(boolean openToWork) {
        isOpenToWork = openToWork;
    }

    @Column(name="nip", nullable = false, unique = true)
    protected String nip;

    @Column(name="name", nullable = false)
    protected String name;

    @JoinColumn(name="sector", nullable = false)
    @ManyToOne
    @JsonIgnoreProperties("organizations")
    protected Sector sector;

    @Enumerated(EnumType.STRING)
    @Column(name="size")
    protected OrganizationSize size;

    @Column(name="logo_id")
    protected Long logoId;

    @Column(name="website_url")
    protected String websiteUrl;

    @Column(name="verified", nullable = false)
    protected boolean verified;

    @NotNull
    @JsonIgnore
    @Column(name="password")
    private String password;
    @ManyToMany
    @JoinTable(
            name = "organization_tags",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
    public Organization() {
    }


    public Organization(boolean isOpenToWork, String email, String nip, String name, Sector sector, OrganizationSize size, Long logoId, String websiteUrl, boolean verified, String password) {

        this.email = email;
        this.nip = nip;
        this.name = name;
        this.sector = sector;
        this.size = size;
        this.logoId = logoId;
        this.websiteUrl = websiteUrl;
        this.verified = verified;
        this.password = password;
        this.isOpenToWork=isOpenToWork;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isOpenToWork() {
        return isOpenToWork;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public OrganizationSize getSize() {
        return size;
    }

    public void setSize(OrganizationSize size) {
        this.size = size;
    }

    public Long getLogoId() {
        return logoId;
    }

    public void setLogoId(Long logoId) {
        this.logoId = logoId;
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
}