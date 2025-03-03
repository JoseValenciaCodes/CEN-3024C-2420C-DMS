package com.JobsAppliedDms.JobsAppliedDms.entity;

/* Category Entity
* Represents a Job category in the DB
* */

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category
{
    /* Define Category Attributes */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Category name must only contain letters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Category description cannot be blank")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Category average salary cannot be null")
    @DecimalMin(value = "1.00", message = "Category average salary cannot be less than 1.00")
    @Column(name = "avg_salary", nullable = false)
    private Double avgSalary;

    @NotNull(message = "Category demand cannot be null")
    @Min(value = 1, message = "Category demand cannot be less than 1")
    @Max(value = 5, message = "Category demand cannot be more than 5")
    @Column(name = "demand", nullable = false)
    private Integer demand;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Automatically initialize the createdAt attribute on instantiation
    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    /* Category Relationships */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

    public Category() {}
    public Category(Long id, String name, String description, Double avgSalary, Integer demand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avgSalary = avgSalary;
        this.demand = demand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public Integer getDemand() {
        return demand;
    }

    public void setDemand(Integer demand) {
        this.demand = demand;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
