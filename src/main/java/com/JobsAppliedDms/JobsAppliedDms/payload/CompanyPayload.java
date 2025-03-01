package com.JobsAppliedDms.JobsAppliedDms.payload;

/* Company Payload */
/* Payload to be used to return info about the company */


import java.time.LocalDateTime;

public class CompanyPayload
{
    private Long id;
    private String name;
    private String description;
    private String location;
    private Boolean hiringStatus;
    private Integer priorityLevel;
    private LocalDateTime createdAt;

    public CompanyPayload() {}

    public CompanyPayload(Long id, String name, String description, String location, Boolean hiringStatus, Integer priorityLevel, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.hiringStatus = hiringStatus;
        this.priorityLevel = priorityLevel;
        this.createdAt = createdAt;
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

    public String getDescription() {return description; }

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
}
