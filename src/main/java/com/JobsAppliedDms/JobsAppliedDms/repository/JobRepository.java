package com.JobsAppliedDms.JobsAppliedDms.repository;

import com.JobsAppliedDms.JobsAppliedDms.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Job Repository
 * Handle DB Interactions related to the Job Entity
 * */
public interface JobRepository extends JpaRepository<Job, Long>
{
}
