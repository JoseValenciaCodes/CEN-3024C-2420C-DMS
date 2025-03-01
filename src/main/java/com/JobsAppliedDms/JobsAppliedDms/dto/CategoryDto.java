package com.JobsAppliedDms.JobsAppliedDms.dto;

/* Category DTO
* Data Transfer Object used to get data about a category from the user
* */


import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class CategoryDto
{
    private Long id;
    @NotBlank(message = "Category name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Category name must only contain letters")
    private String name;

    @NotBlank(message = "Category description cannot be blank")
    private String description;

    @NotNull(message = "Category average salary cannot be null")
    @DecimalMin(value = "1.00", message = "Category average salary cannot be less than 1.00")
    private Double avgSalary;

    @NotNull(message = "Category demand cannot be null")
    @Min(value = 1, message = "Category demand cannot be less than 1")
    @Max(value = 5, message = "Category demand cannot be more than 5")
    private Integer demand;
    public CategoryDto() {}

    public CategoryDto(Long id, String name, String description, Double avgSalary, Integer demand) {
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

}
