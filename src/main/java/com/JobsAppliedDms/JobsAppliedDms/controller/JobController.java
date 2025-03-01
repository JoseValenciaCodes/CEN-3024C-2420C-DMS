package com.JobsAppliedDms.JobsAppliedDms.controller;

/* Job Controller
* Expose HTTP Endpoints to interact with the Job Entity*/

import com.JobsAppliedDms.JobsAppliedDms.dto.JobDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.JobPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController
{
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Get All Jobs of user
    @GetMapping
    public ResponseEntity<List<JobPayload>> getAllJobs()
    {
        List<JobPayload> jobPayloadList = jobService.getAllJobs();
        return ResponseEntity.ok(jobPayloadList);
    }

    // Get All Company Jobs of User
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobPayload>> getJobsOfCompany(@PathVariable("companyId") Long companyId)
    {
        List<JobPayload> companyJobsPayloadList = jobService.getJobsOfCompany(companyId);
        return ResponseEntity.ok(companyJobsPayloadList);
    }

    // Get All Category Jobs of User
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<JobPayload>> getJobsOfCategory(@PathVariable("categoryId") Long categoryId)
    {
        List<JobPayload> categoryJobsPayloadList = jobService.getJobsOfCategory(categoryId);
        return ResponseEntity.ok(categoryJobsPayloadList);
    }

    // Get A Job By Its ID
    @GetMapping("{id}")
    public ResponseEntity<JobPayload> getJobById(@PathVariable("id") Long id)
    {
        JobPayload jobPayload = jobService.getJobById(id);
        return ResponseEntity.ok(jobPayload);
    }

    // Add company
    @PostMapping
    public ResponseEntity<JobPayload> addJob(@Validated @RequestBody JobDto jobDto)
    {
        JobPayload jobPayload = jobService.addJob(jobDto);
        return ResponseEntity.ok(jobPayload);
    }

    // Update company by id
    @PutMapping("{id}")
    public ResponseEntity<JobPayload> updateJob(@PathVariable("id") Long id, @Validated @RequestBody JobDto jobDto)
    {
        JobPayload jobPayload = jobService.updateJob(id, jobDto);
        return ResponseEntity.ok(jobPayload);
    }

    // Delete Company By Id
    @DeleteMapping("{id}")
    public ResponseEntity<MessagePayload> deleteJob(@PathVariable("id") Long id)
    {
        MessagePayload messagePayload = jobService.deleteJob(id);
        return ResponseEntity.ok(messagePayload);
    }

}
