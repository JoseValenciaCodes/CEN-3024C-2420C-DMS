package com.JobsAppliedDms.JobsAppliedDms.repository;

import com.JobsAppliedDms.JobsAppliedDms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
* User Repository
* Handle DB Interactions related to the User Entity
* */
public interface UserRepository extends JpaRepository<User, Long>
{
    boolean existsByEmail(String email); // Check if a user exists by email
    Optional<User> findByEmail(String email); // Find a user by the email
}
