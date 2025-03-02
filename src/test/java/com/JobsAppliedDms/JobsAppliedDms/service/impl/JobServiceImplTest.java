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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobServiceImplTest
{
    @Mock
    private JobRepository jobRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    private Job job;
    private Company company;
    private Category category;
    private JobDto jobDto;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setId(1L);
        company.setName("TechCorp");

        category = new Category();
        category.setId(1L);
        category.setName("Software");

        job = new Job();
        job.setId(1L);
        job.setTitle("Software Engineer");
        job.setDescription("Develop software");
        job.setSalary(70000.0);
        job.setType("Full-Time");
        job.setCompany(company);
        job.setCategory(category);

        jobDto = new JobDto();
        jobDto.setTitle("Software Engineer");
        jobDto.setDescription("Develop software");
        jobDto.setSalary(70000.0);
        jobDto.setType("Full-Time");
        jobDto.setCompanyId(company.getId());
        jobDto.setCategoryId(category.getId());
    }

    @Test
    void getAllJobs_ReturnsListOfJobPayloads() {
        when(jobRepository.findAll()).thenReturn(Arrays.asList(job));

        List<JobPayload> result = jobService.getAllJobs();

        assertEquals(1, result.size());
        assertEquals(job.getTitle(), result.get(0).getTitle());
        verify(jobRepository, times(1)).findAll();
    }

    @Test
    void getJobsOfCompany_ValidCompanyId_ReturnsJobs() {
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        company.setJobs(List.of(job)); // Company already has jobs set

        List<JobPayload> result = jobService.getJobsOfCompany(company.getId());

        assertEquals(1, result.size());
        assertEquals(job.getTitle(), result.get(0).getTitle());
        verify(companyRepository, times(1)).findById(company.getId());
    }

    @Test
    void getJobsOfCompany_InvalidCompanyId_ThrowsException() {
        when(companyRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFound.class, () -> jobService.getJobsOfCompany(999L));
    }

    @Test
    void getJobById_ValidId_ReturnsJobPayload() {
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));

        JobPayload result = jobService.getJobById(job.getId());

        assertEquals(job.getTitle(), result.getTitle());
        verify(jobRepository, times(1)).findById(job.getId());
    }

    @Test
    void getJobById_InvalidId_ThrowsException() {
        when(jobRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(JobNotFound.class, () -> jobService.getJobById(999L));
    }

    @Test
    void addJob_ValidData_ReturnsJobPayload() {
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        JobPayload result = jobService.addJob(jobDto);

        assertEquals(job.getTitle(), result.getTitle());
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    void addJob_InvalidCompany_ThrowsException() {
        when(companyRepository.findById(999L)).thenReturn(Optional.empty());

        jobDto.setCompanyId(999L);
        assertThrows(CompanyNotFound.class, () -> jobService.addJob(jobDto));
    }

    @Test
    void addJob_InvalidCategory_ThrowsException() {
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        jobDto.setCategoryId(999L);
        assertThrows(CategoryNotFound.class, () -> jobService.addJob(jobDto));
    }

    @Test
    void updateJob_ValidId_ReturnsUpdatedJobPayload() {
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        JobPayload result = jobService.updateJob(job.getId(), jobDto);

        assertEquals(job.getTitle(), result.getTitle());
        verify(jobRepository, times(1)).save(job);
    }

    @Test
    void updateJob_InvalidId_ThrowsException() {
        when(jobRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(JobNotFound.class, () -> jobService.updateJob(999L, jobDto));
    }

    @Test
    void updateJob_ChangeCompanyAndCategory_Success() {
        Company newCompany = new Company();
        newCompany.setId(2L);
        Category newCategory = new Category();
        newCategory.setId(2L);

        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
        when(companyRepository.findById(newCompany.getId())).thenReturn(Optional.of(newCompany));
        when(categoryRepository.findById(newCategory.getId())).thenReturn(Optional.of(newCategory));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        jobDto.setCompanyId(newCompany.getId());
        jobDto.setCategoryId(newCategory.getId());

        JobPayload result = jobService.updateJob(job.getId(), jobDto);

        assertEquals(job.getTitle(), result.getTitle());
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    void deleteJob_ValidId_Success() {
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));

        MessagePayload result = jobService.deleteJob(job.getId());

        assertEquals("Job was successfully deleted", result.getMessage());
        verify(jobRepository, times(1)).delete(job);
    }

    @Test
    void deleteJob_InvalidId_ThrowsException() {
        when(jobRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(JobNotFound.class, () -> jobService.deleteJob(999L));
    }
}
