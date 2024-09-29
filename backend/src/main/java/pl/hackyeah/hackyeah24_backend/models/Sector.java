package pl.hackyeah.hackyeah24_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="sectors")
public class Sector extends BaseModel{

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "sector")
    private List<Organization> organizations;

    public Sector() {
    }

    public Sector(String name, String description, List<Organization> organizations) {
        this.name = name;
        this.description = description;
        this.organizations=organizations;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
