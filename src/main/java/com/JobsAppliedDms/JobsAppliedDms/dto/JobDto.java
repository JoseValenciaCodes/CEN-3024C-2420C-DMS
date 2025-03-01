package com.JobsAppliedDms.JobsAppliedDms.dto;

/* Job Dto
* Data Transfer Object to be used to get data about jobs
* */

import jakarta.validation.constraints.*;

public class JobDto
{
    private Long id;

    @NotBlank(message = "Job title cannot be blank")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Job title must only contain letters")
    private String title;

    @NotBlank(message = "Job description cannot be blank")
    private String description;

    @NotNull(message = "Job salary cannot be null")
    @DecimalMin(value = "1.00", message = "Job salary cannot be less than 1.00")
    private Double salary;

    @NotBlank(message = "Job type cannot be empty")
    @Pattern(regexp = "^(full-time|part-time|contract|freelance|temporary)$",
            message = "Job type must be of type: full-time, part-time, contract, freelance, or temporary")
    private String type;

    @NotNull(message = "Company ID cannot be null")
    @Min(value = 1, message = "Company ID cannot be less than 1")
    private Long companyId;

    @NotNull(message = "Category ID cannot be null")
    @Min(value = 1, message = "Category ID cannot be less than 1")
    private Long categoryId;

    public JobDto()
    {

    }

    public JobDto(Long id, String title, String description, Double salary, String type, Long companyId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.type = type;
        this.companyId = companyId;
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
}
