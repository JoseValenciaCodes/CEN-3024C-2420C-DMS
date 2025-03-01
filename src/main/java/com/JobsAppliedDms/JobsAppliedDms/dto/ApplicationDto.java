package com.JobsAppliedDms.JobsAppliedDms.dto;

/* Application DTO
* Data Transfer Object to be used to get data about applications
* */

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public class ApplicationDto
{
    private Long id;
    @NotNull(message = "Application time cannot be null")
    private LocalDateTime appliedAt;

    @NotBlank(message = "Application status cannot be empty")
    @Pattern(regexp = "^(accepted|rejected|pending)$",
            message = "Application status must be pending, accepted, or rejected")
    private String status;
    @NotNull(message = "Application shortlisted status cannot be null")
    private Boolean shortlisted;

    @NotBlank(message = "Resume link cannot be empty")
    @URL(message = "Resume Link must be a valid URL Link")
    private String resumeLink;

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID cannot be less than 1")
    private Long userId;

    @NotNull(message = "Job ID cannot be null")
    @Min(value = 1, message = "Job ID cannot be less than 1")
    private Long jobId;

    public ApplicationDto() {}

    public ApplicationDto(Long id, LocalDateTime appliedAt, String status, Boolean shortlisted, String resumeLink, Long userId, Long jobId) {
        this.id = id;
        this.appliedAt = appliedAt;
        this.status = status;
        this.shortlisted = shortlisted;
        this.resumeLink = resumeLink;
        this.userId = userId;
        this.jobId = jobId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getShortlisted() {
        return shortlisted;
    }

    public void setShortlisted(Boolean shortlisted) {
        this.shortlisted = shortlisted;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
