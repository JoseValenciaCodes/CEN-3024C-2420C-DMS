package com.JobsAppliedDms.JobsAppliedDms.entity;

/* Application Entity
*
* Represent an application stored in the DB */

/* Use Lombok annotations to speed up development */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "applications"
)
public class Application
{
    /* Application Attributes */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Application time cannot be null")
    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;

    @NotBlank(message = "Application status cannot be empty")
    @Pattern(regexp = "^(accepted|rejected|pending)$",
            message = "Application status must be pending, accepted, or rejected")
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull(message = "Application shortlisted status cannot be null")
    @Column(name = "shortlisted", nullable = false)
    private Boolean shortlisted;

    @NotBlank(message = "Resume link cannot be empty")
    @URL(message = "Resume Link must be a valid URL Link")
    @Column(name = "resume_link", nullable = false)
    private String resumeLink;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Automatically initialize the createdAt attribute on instantiation
    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    /* Application Relationships */
    @ManyToOne
    @NotNull(message = "User id cannot be null")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @NotNull(message = "Job id cannot be null")
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public Application() {}

    public Application(Long id, LocalDateTime appliedAt, String status, Boolean shortlisted, String resumeLink) {
        this.id = id;
        this.appliedAt = appliedAt;
        this.status = status;
        this.shortlisted = shortlisted;
        this.resumeLink = resumeLink;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
