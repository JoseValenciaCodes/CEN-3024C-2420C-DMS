package com.JobsAppliedDms.JobsAppliedDms.payload;

/* User Payload */
/* Payload to be used to return info about the user */


import java.time.LocalDateTime;

public class UserPayload
{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Boolean isEmployed;
    private LocalDateTime createdAt;

    public UserPayload() {}

    public UserPayload(Long id, String firstName, String lastName, String email, Integer age, Boolean isEmployed, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.isEmployed = isEmployed;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsEmployed() {
        return isEmployed;
    }

    public void setIsEmployed(Boolean employed) {
        isEmployed = employed;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
