package com.JobsAppliedDms.JobsAppliedDms.entity;

/*
* Job Entity
* Represent a job stored in the DB
* */

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* Use Lombok annotations to speed up development */

@Entity
@Table(
        name = "jobs"
)
public class Job
{
    /* Job Attributes */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job title cannot be blank")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Job title must only contain letters")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Job description cannot be blank")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Job salary cannot be null")
    @DecimalMin(value = "1.00", message = "Job salary cannot be less than 1.00")
    @Column(name = "salary", nullable = false)
    private Double salary;

    @NotBlank(message = "Job type cannot be empty")
    @Pattern(regexp = "^(full-time|part-time|contract|freelance|temporary)$",
            message = "Job type must be of type: full-time, part-time, contract, freelance, or temporary")
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Automatically initialize the createdAt attribute on instantiation
    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    /* Job Relationships */
    @ManyToOne
    @NotNull(message = "Company id cannot be null")
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @NotNull(message = "Category id cannot be null")
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    public  Job() {}

    public Job(Long id, String title, String description, Double salary, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
