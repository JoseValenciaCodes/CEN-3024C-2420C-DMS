package com.JobsAppliedDms.JobsAppliedDms.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
* Company DTO
* Data-Transfer Object to be used to get data about a company
* */
public class CompanyDto
{
    private Long id;
    @NotBlank(message = "Company name cannot be empty")
    private String name;

    @NotBlank(message = "Company description cannot be empty")
    private String description;

    @NotBlank(message = "Company location cannot be empty")
    private String location;

    @NotNull(message = "Company Hiring Status cannot be null")
    private Boolean hiringStatus;

    @NotNull(message = "Company priority level cannot be null")
    @Min(value = 1, message = "Company priority level cannot be less than 1")
    @Max(value = 5, message = "Company priority level cannot be more than 5")
    private Integer priorityLevel;

    public CompanyDto() {}

    public CompanyDto(Long id, String name, String description, String location, Boolean hiringStatus, Integer priorityLevel) {
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
}
