package com.JobsAppliedDms.JobsAppliedDms.service.impl;

import com.JobsAppliedDms.JobsAppliedDms.dto.ApplicationDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.*;
import com.JobsAppliedDms.JobsAppliedDms.exception.ApplicationNotFound;
import com.JobsAppliedDms.JobsAppliedDms.exception.CategoryNotFound;
import com.JobsAppliedDms.JobsAppliedDms.exception.CompanyNotFound;
import com.JobsAppliedDms.JobsAppliedDms.exception.JobNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.ApplicationPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.*;
import com.JobsAppliedDms.JobsAppliedDms.service.ApplicationService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* Application Service Implementation
* Implement Business Logic for Application Crud Operations
* */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService
{
    private ApplicationRepository applicationRepository;
    private CompanyRepository companyRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private JobRepository jobRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserRepository userRepository, CompanyRepository companyRepository, CategoryRepository categoryRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.categoryRepository = categoryRepository;
    }

    // Return user ID from httpSession
    private Long getUserIdFromSession(HttpSession session)
    {
        return (Long) session.getAttribute("userId");
    }

    // Get all Applications of the current user
    @Override
    public List<ApplicationPayload> getAllApplications(HttpSession httpSession) {

        // Get user Id
        Long userId = getUserIdFromSession(httpSession);

        // Grab Applications
        List<Application> userApplications = applicationRepository.findAll().stream().filter(application -> Objects.equals(application.getUser().getId(), userId)).toList();
        List<ApplicationPayload> applicationPayloadList = new ArrayList<>();

        for (Application application : userApplications)
        {
            applicationPayloadList.add(new ApplicationPayload(
                    application.getId(),
                    application.getAppliedAt(),
                    application.getStatus(),
                    application.getShortlisted(),
                    application.getResumeLink(),
                    application.getUser().getId(),
                    application.getJob().getId(),
                    application.getCreatedAt()
            ));
        }

        return applicationPayloadList;
    }

    @Override
    public List<ApplicationPayload> getApplicationsOfACompany(HttpSession httpSession, Long companyId) {
        // Get user Id
        Long userId = getUserIdFromSession(httpSession);

        // Get the company
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CompanyNotFound("The requested company could not be found")
        );

        // Get Jobs Of Company
        List<Job> companyJobs = company.getJobs();

        // Get applications of company jobs
        List<Application> companyApplications = new ArrayList<>();
        List<ApplicationPayload> applicationPayloadList = new ArrayList<>();

        for (Job companyJob : companyJobs)
        {
            // Get the applications of each job of this user
            List<Application> jobApplications = companyJob.getApplications().stream().filter(application -> Objects.equals(application.getUser().getId(), userId)).toList();

            // Extends the company Applications List
            companyApplications.addAll(jobApplications);
        }

        for (Application companyApplication : companyApplications)
        {
            applicationPayloadList.add(
                    new ApplicationPayload(
                            companyApplication.getId(),
                            companyApplication.getAppliedAt(),
                            companyApplication.getStatus(),
                            companyApplication.getShortlisted(),
                            companyApplication.getResumeLink(),
                            companyApplication.getUser().getId(),
                            companyApplication.getJob().getId(),
                            companyApplication.getCreatedAt()
                    )
            );
        }

        return applicationPayloadList;
    }

    @Override
    public List<ApplicationPayload> getApplicationsOfACategory(HttpSession httpSession, Long categoryId) {
        // Get user Id
        Long userId = getUserIdFromSession(httpSession);

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFound("Category of id " + categoryId + " could not be found")
        );

        // Grab all jobs of this category
        List<Job> categoryJobs = category.getJobs();

        // Get applications of these jobs
        List<Application> categoryApplications = new ArrayList<>();
        List<ApplicationPayload> applicationPayloadList = new ArrayList<>();

        for (Job categoryJob : categoryJobs)
        {
            // Get applications of each category Job
            List<Application> categoryJobApplications = categoryJob.getApplications().stream().filter(application -> Objects.equals(application.getUser().getId(), userId)).toList();

            // Extend the application payload list
            categoryApplications.addAll(categoryJobApplications);
        }

        for (Application application : categoryApplications)
        {
            applicationPayloadList.add(
                    new ApplicationPayload(
                            application.getId(),
                            application.getAppliedAt(),
                            application.getStatus(),
                            application.getShortlisted(),
                            application.getResumeLink(),
                            application.getUser().getId(),
                            application.getJob().getId(),
                            application.getCreatedAt()
                    )
            );
        }

        return applicationPayloadList;
    }

    @Override
    public ApplicationPayload getApplicationById(HttpSession httpSession, Long id) {
        Long userId = getUserIdFromSession(httpSession);

        // Get Applications of this user
        List<Application> userApplications = applicationRepository.findAll().stream().filter(application -> Objects.equals(application.getUser().getId(), userId)).toList();

        // Get the application of id
        List<Application> filteredApplications = userApplications.stream().filter(application1 -> application1.getId() == id).toList();

        if (filteredApplications.isEmpty())
        {
            throw new ApplicationNotFound("Application of id " + id + " could not be found");
        }

        Application application = filteredApplications.get(0);

        return new ApplicationPayload(
                application.getId(),
                application.getAppliedAt(),
                application.getStatus(),
                application.getShortlisted(),
                application.getResumeLink(),
                application.getUser().getId(),
                application.getJob().getId(),
                application.getCreatedAt()
        );
    }

    @Override
    public ApplicationPayload addApplication(HttpSession httpSession, ApplicationDto applicationDto) {

        Long userId = getUserIdFromSession(httpSession);

        User user = userRepository.findById(userId).orElse(null);
        Job job = jobRepository.findById(applicationDto.getJobId()).orElseThrow(
                () -> new JobNotFound("Job of id " + applicationDto.getJobId() + " could not be found")
        );

        Application application = new Application();

        application.setAppliedAt(applicationDto.getAppliedAt());
        application.setStatus(applicationDto.getStatus());
        application.setShortlisted(applicationDto.getShortlisted());
        application.setResumeLink(applicationDto.getResumeLink());
        application.setUser(user);
        application.setJob(job);

        Application newApplication = applicationRepository.save(application);

        return new ApplicationPayload(
                newApplication.getId(),
                newApplication.getAppliedAt(),
                newApplication.getStatus(),
                newApplication.getShortlisted(),
                newApplication.getResumeLink(),
                newApplication.getUser().getId(),
                newApplication.getJob().getId(),
                newApplication.getCreatedAt()
        );
    }

    @Override
    public ApplicationPayload updateApplication(HttpSession httpSession, Long id, ApplicationDto applicationDto) {
        Long userId = getUserIdFromSession(httpSession);

        // Get Applications of this user
        List<Application> userApplications = applicationRepository.findAll().stream().filter(application -> Objects.equals(application.getUser().getId(), userId)).toList();

        // Get the application of id
        Application application = userApplications.stream().filter(application1 -> application1.getId() == id).toList().get(0);

        if (application == null)
        {
            throw new ApplicationNotFound("Application of id " + applicationDto.getId() + " could not be found");
        }

        Job job = jobRepository.findById(applicationDto.getJobId()).orElseThrow(
                () -> new JobNotFound("Job of id " + applicationDto.getJobId() + " could not be found")
        );

        application.setAppliedAt(applicationDto.getAppliedAt());
        application.setStatus(applicationDto.getStatus());
        application.setShortlisted(applicationDto.getShortlisted());
        application.setResumeLink(applicationDto.getResumeLink());
        application.setJob(job);

        Application updatedApplication = applicationRepository.save(application);

        return new ApplicationPayload(
                updatedApplication.getId(),
                updatedApplication.getAppliedAt(),
                updatedApplication.getStatus(),
                updatedApplication.getShortlisted(),
                updatedApplication.getResumeLink(),
                updatedApplication.getUser().getId(),
                updatedApplication.getJob().getId(),
                updatedApplication.getCreatedAt()
        );
    }

    @Override
    public MessagePayload deleteApplication(HttpSession httpSession, Long id) {
        Long userId = getUserIdFromSession(httpSession);

        // Get Applications of this user
        List<Application> userApplications = applicationRepository.findAll().stream().filter(application -> Objects.equals(application.getUser().getId(), userId)).toList();

        // Get the application of id
        Application application = userApplications.stream().filter(application1 -> application1.getId() == application1.getId()).toList().get(0);

        if (application == null)
        {
            throw new ApplicationNotFound("Application of id " + id + " could not be found");
        }

        applicationRepository.delete(application);

        return new MessagePayload("The application was successfully deleted from the system");
    }
}
