package pl.hackyeah.hackyeah24_backend.models;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.hackyeah.hackyeah24_backend.enums.OrganizationSize;
import pl.hackyeah.hackyeah24_backend.models.Sector;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("Company")
@SQLDelete(sql = "UPDATE organizations SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Company extends Organization {
    public Company() {
    }
    public String getDescription(){return description;};
    public void setDescription(String description){this.description=description;};

//    public boolean getIsOpenToWork() {
//        return isOpenToWork;
//    }
//
//    public void setOpenToWork(boolean openToWork) {
//        isOpenToWork = openToWork;
//    }

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

//    @Column(name="is_open_to_work", nullable = false)
//    protected boolean isOpenToWork = true;

    @Column(name="min_budget")
    protected Double minBudget;

    @Column(name="max_budget")
    protected Double maxBudget;

    @Column(name="description")
    protected String description;

    public Company(String email, String nip, String name, Sector sector, OrganizationSize size, String description,Long logoId, String websiteUrl, boolean verified, String password, boolean isOpenToWork, Double minBudget, Double maxBudget) {
        super(isOpenToWork,email, nip, name, sector, size, logoId, websiteUrl, verified, password);
       // this.isOpenToWork = isOpenToWork;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.description=description;
    }
}
