package com.JobsAppliedDms.JobsAppliedDms.payload;

/* User Payload */
/* Payload to be used to return info about the user */


import java.time.LocalDateTime;

public class JobPayload
{
    private Long id;
    private String title;
    private String description;
    private Double salary;
    private String type;

    private Long companyId;

    private Long categoryId;
    private LocalDateTime createdAt;

    public JobPayload() {}

    public JobPayload(Long id, String title, String description, Double salary, String type, Long companyId, Long categoryId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.type = type;
        this.companyId = companyId;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
}
