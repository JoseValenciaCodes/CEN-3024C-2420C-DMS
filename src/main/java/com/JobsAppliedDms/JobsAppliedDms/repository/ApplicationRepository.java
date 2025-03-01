package com.JobsAppliedDms.JobsAppliedDms.repository;

import com.JobsAppliedDms.JobsAppliedDms.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Application Repository
 * Handle DB Interactions related to the Application Entity
 * */

public interface ApplicationRepository extends JpaRepository<Application, Long>
{
}
