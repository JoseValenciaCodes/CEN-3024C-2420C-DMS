package com.JobsAppliedDms.JobsAppliedDms.entity;

/*
* Company Entity
* Define a company of the application
* */


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* Use Lombok Annotations to speed up development */

@Entity
@Table(name = "companies")
public class Company
{
    /* Define Company Attributes */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Company description cannot be empty")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message = "Company location cannot be empty")
    @Column(name = "location", nullable = false)
    private String location;

    @NotNull(message = "Company Hiring Status cannot be null")
    @Column(name = "hiring_status", nullable = false)
    private Boolean hiringStatus;

    @NotNull(message = "Company priority level cannot be null")
    @Min(value = 1, message = "Company priority level cannot be less than 1")
    @Max(value = 5, message = "Company priority level cannot be more than 5")
    @Column(name = "priority_level", nullable = false)
    private Integer priorityLevel;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Automatically initialize the createdAt attribute on instantiation
    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    /* Company Relationships */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

    public Company() {}

    public Company(Long id, String name, String description, String location, Boolean hiringStatus, Integer priorityLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.hiringStatus = hiringStatus;
        this.priorityLevel = priorityLevel;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getHiringStatus() {
        return hiringStatus;
    }

    public void setHiringStatus(Boolean hiringStatus) {
        this.hiringStatus = hiringStatus;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
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
