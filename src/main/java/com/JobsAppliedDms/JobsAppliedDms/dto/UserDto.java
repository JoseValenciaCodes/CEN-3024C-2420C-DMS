package com.JobsAppliedDms.JobsAppliedDms.dto;

import jakarta.validation.constraints.*;
/*
* User DTO
* Data Transfer object to be used to get user data from the user
* */
public class UserDto {


    private Long id;

    @NotBlank(message = "First name cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
    private String firstName;


    @NotBlank(message = "Last name cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
    private String lastName;


    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;


    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain one uppercase letter, one lowercase letter, and one digit"
    )
    @NotBlank(message = "Password is required")
    private String password;


    @NotNull(message = "Age cannot be null")
    @Min(value = 16, message = "Age must be at least 16")
    private Integer age;

    @NotNull(message = "The is employed attribute cannot be null")
    private Boolean isEmployed;

    public UserDto() {

    }

    public UserDto(Long id, String firstName, String lastName, String email, String password, Integer age, Boolean isEmployed) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.isEmployed = isEmployed;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
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
}

