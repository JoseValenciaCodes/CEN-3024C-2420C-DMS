package com.JobsAppliedDms.JobsAppliedDms.service.impl;

import com.JobsAppliedDms.JobsAppliedDms.dto.JobDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.Category;
import com.JobsAppliedDms.JobsAppliedDms.entity.Company;
import com.JobsAppliedDms.JobsAppliedDms.entity.Job;
import com.JobsAppliedDms.JobsAppliedDms.exception.CategoryNotFound;
import com.JobsAppliedDms.JobsAppliedDms.exception.CompanyNotFound;
import com.JobsAppliedDms.JobsAppliedDms.exception.JobNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.JobPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.CategoryRepository;
import com.JobsAppliedDms.JobsAppliedDms.repository.CompanyRepository;
import com.JobsAppliedDms.JobsAppliedDms.repository.JobRepository;
import com.JobsAppliedDms.JobsAppliedDms.service.JobService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* Job Service Implementation
* Implement Business Logic for JOB Crud Operations
* */

@Service
@Transactional
public class JobServiceImpl implements JobService
{
    private CompanyRepository companyRepository;
    private CategoryRepository categoryRepository;
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository, CategoryRepository categoryRepository)
    {
        this.jobRepository = jobRepository;
        this.categoryRepository = categoryRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<JobPayload> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobPayload> jobPayloadList = new ArrayList<JobPayload>();

        for (Job job : jobs)
        {
            jobPayloadList.add(new JobPayload(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getSalary(),
                    job.getType(),
                    job.getCompany().getId(),
                    job.getCategory().getId(),
                    job.getCreatedAt()
            ));
        }

        return jobPayloadList;
    }

    @Override
    public List<JobPayload> getJobsOfCompany(Long companyId)
    {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CompanyNotFound("The requested company could not be found")
        );

        List<Job> companyJobs = company.getJobs();

        List<JobPayload> companyJobPayloadList = new ArrayList<JobPayload>();

        for (Job job : companyJobs)
        {
            companyJobPayloadList.add(new JobPayload(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getSalary(),
                    job.getType(),
                    job.getCompany().getId(),
                    job.getCategory().getId(),
                    job.getCreatedAt()
            ));
        }

        return companyJobPayloadList;
    }

    @Override
    public List<JobPayload> getJobsOfCategory(Long categoryId)
    {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFound("The requested category could not be found")
        );

        List<Job> categoryJobs = category.getJobs();

        List<JobPayload> categoryJobsPayload = new ArrayList<JobPayload>();

        for (Job job : categoryJobs)
        {
            categoryJobsPayload.add(new JobPayload(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getSalary(),
                    job.getType(),
                    job.getCompany().getId(),
                    job.getCategory().getId(),
                    job.getCreatedAt()
            ));
        }

        return categoryJobsPayload;
    }

    @Override
    public JobPayload getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new JobNotFound("Job of id " + jobId + " could not be found")
        );

        return new JobPayload(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSalary(),
                job.getType(),
                job.getCompany().getId(),
                job.getCategory().getId(),
                job.getCreatedAt()
        );
    }

    @Override
    public JobPayload addJob(JobDto jobDto) {
        Job job = new Job();

        // Look for associated company
        Company jobCompany = companyRepository.findById(jobDto.getCompanyId()).orElseThrow(
                () -> new CompanyNotFound("The company of id " + jobDto.getCompanyId() + " could not be found")
        );

        // Look for associated category
        Category jobCategory = categoryRepository.findById(jobDto.getCategoryId()).orElseThrow(
                () -> new CategoryNotFound("The category of id " + jobDto.getCategoryId() + " could not be found")
        );

        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setSalary(jobDto.getSalary());
        job.setType(jobDto.getType());
        job.setCompany(jobCompany);
        job.setCategory(jobCategory);

        Job newJob = jobRepository.save(job);

        return new JobPayload(
                newJob.getId(),
                newJob.getTitle(),
                newJob.getDescription(),
                newJob.getSalary(),
                newJob.getType(),
                job.getCompany().getId(),
                job.getCategory().getId(),
                newJob.getCreatedAt()
        );
    }

    @Override
    public JobPayload updateJob(Long id, JobDto jobDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new JobNotFound("Job of id " + id + " could not be found")
        );

        // Only change job and category if they are different
        if (!Objects.equals(jobDto.getCompanyId(), job.getCompany().getId()))
        {
            // Look for associated company
            Company jobCompany = companyRepository.findById(jobDto.getCompanyId()).orElseThrow(
                    () -> new CompanyNotFound("The company of id " + jobDto.getCompanyId() + " could not be found")
            );

            job.setCompany(jobCompany);
        }

        if (!Objects.equals(jobDto.getCategoryId(), job.getCategory().getId()))
        {
            // Look for associated category
            Category jobCategory = categoryRepository.findById(jobDto.getCategoryId()).orElseThrow(
                    () -> new CategoryNotFound("The category of id " + jobDto.getCategoryId() + " could not be found")
            );

            job.setCategory(jobCategory);
        }


        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setSalary(jobDto.getSalary());
        job.setType(jobDto.getType());

        Job updatedJob = jobRepository.save(job);

        return new JobPayload(
                updatedJob.getId(),
                updatedJob.getTitle(),
                updatedJob.getDescription(),
                updatedJob.getSalary(),
                updatedJob.getType(),
                job.getCompany().getId(),
                job.getCategory().getId(),
                updatedJob.getCreatedAt()
        );


    }

    @Override
    public MessagePayload deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new JobNotFound("Job of id " + id + " could not be found")
        );

        jobRepository.delete(job);

        return new MessagePayload("Job was successfully deleted");
    }
}
