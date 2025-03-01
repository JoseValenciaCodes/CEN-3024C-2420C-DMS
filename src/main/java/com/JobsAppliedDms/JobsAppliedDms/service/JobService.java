package com.JobsAppliedDms.JobsAppliedDms.service;

/* Job Service Interface
 * Define all ways to interact with the Job entity here
 *
 * */

import com.JobsAppliedDms.JobsAppliedDms.dto.JobDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.JobPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;

import java.util.List;

public interface JobService
{
    List<JobPayload> getAllJobs();
    List<JobPayload> getJobsOfCompany(Long companyId);
    List<JobPayload> getJobsOfCategory(Long categoryId);
    JobPayload getJobById(Long jobId);
    JobPayload addJob(JobDto jobDto);
    JobPayload updateJob(Long id, JobDto jobDto);
    MessagePayload deleteJob(Long id);
}
