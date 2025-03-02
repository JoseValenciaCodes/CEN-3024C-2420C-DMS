package com.JobsAppliedDms.JobsAppliedDms.payload;

/* Category Payload */
/* Payload to be used to return info about the category */


import java.time.LocalDateTime;

public class CategoryPayload
{
    private Long id;
    private String name;
    private String description;
    private Double avgSalary;
    private Integer demand;

    private LocalDateTime createdAt;

    public CategoryPayload() {}

    public CategoryPayload(Long id, String name, String description, Double avgSalary, Integer demand, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avgSalary = avgSalary;
        this.demand = demand;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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

    @Override
    public String toString() {
        return "CategoryPayload(" +
                "\nid=" + id +
                "\n, name='" + name + '\'' +
                "\n, description='" + description + '\'' +
                "\n, avgSalary=" + avgSalary +
                "\n, demand=" + demand +
                "\n, createdAt=" + createdAt +
                ')';
    }
}
