package pl.hackyeah.hackyeah24_backend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("Foundation")
@SQLDelete(sql = "UPDATE organizations SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Foundation extends Organization {

    @Column(name="experience", length = 65535)
    public String experience;

    @Column(name="strengths", length = 65535)
    public String strengths;

    @Column(name="history", length = 65535)
    public String history;

    @Column(name="additional_info", length = 65535)
    public String additionalInfo;

    @Column(name="goals", length = 65535)
    public String goals;

    @OneToMany(mappedBy = "foundation")
    @JsonIgnoreProperties("foundation")
    public List<Project> projects;


    public Foundation(String experience, String history, String strengths, String additionalInfo, String goals) {
        this.experience = experience;
        this.history = history;
        this.strengths = strengths;
        this.additionalInfo = additionalInfo;
        this.goals = goals;
    }

    public Foundation() {

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
