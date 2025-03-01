package com.JobsAppliedDms.JobsAppliedDms.payload;

/* Application Payload */
/* Payload to be used to return info about the application */

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class ApplicationPayload
{
    private Long id;
    private LocalDateTime appliedAt;
    private String status;
    private Boolean shortlisted;
    private String resumeLink;

    private Long userId;

    private Long jobId;
    private LocalDateTime createdAt;

    public ApplicationPayload() {}

    public ApplicationPayload(Long id, LocalDateTime appliedAt, String status, Boolean shortlisted, String resumeLink, Long userId, Long jobId, LocalDateTime createdAt) {
        this.id = id;
        this.appliedAt = appliedAt;
        this.status = status;
        this.shortlisted = shortlisted;
        this.resumeLink = resumeLink;
        this.userId = userId;
        this.jobId = jobId;
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
}
