package pl.hackyeah.hackyeah24_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tags")
public class Tag extends BaseModel {


    @ManyToMany(mappedBy = "tags")
    private Set<Organization> organizations = new HashSet<>();
    @NotNull
    private String name;

    public Tag() {
    }

    public Tag(String name,Set<Organization> organizations) {
        this.name = name;
        this.organizations = organizations;
    }

}
