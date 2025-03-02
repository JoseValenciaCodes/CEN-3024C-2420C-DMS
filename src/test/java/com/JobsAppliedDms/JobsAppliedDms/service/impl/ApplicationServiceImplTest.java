package com.JobsAppliedDms.JobsAppliedDms.service.impl;

import com.JobsAppliedDms.JobsAppliedDms.dto.ApplicationDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.Application;
import com.JobsAppliedDms.JobsAppliedDms.entity.Job;
import com.JobsAppliedDms.JobsAppliedDms.entity.User;
import com.JobsAppliedDms.JobsAppliedDms.exception.ApplicationNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.ApplicationPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.*;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceImplTest
{
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private HttpSession session;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    private User user;
    private Job job;
    private Application application;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        job = new Job();
        job.setId(10L);

        application = new Application();
        application.setId(100L);
        application.setUser(user);
        application.setJob(job);
        application.setAppliedAt(LocalDateTime.now());
        application.setStatus("Pending");
        application.setShortlisted(false);
        application.setResumeLink("resume.pdf");

        when(session.getAttribute("userId")).thenReturn(1L);
    }

    @Test
    void getAllApplications_ShouldReturnApplications() {
        when(applicationRepository.findAll()).thenReturn(List.of(application));

        List<ApplicationPayload> applications = applicationService.getAllApplications(session);

        assertEquals(1, applications.size());
        assertEquals(100L, applications.get(0).getId());
    }

    @Test
    void getApplicationById_ValidId_ShouldReturnApplication() {
        when(applicationRepository.findAll()).thenReturn(List.of(application));

        ApplicationPayload result = applicationService.getApplicationById(session, 100L);

        assertNotNull(result);
        assertEquals(100L, result.getId());
    }

    @Test
    void getApplicationById_InvalidId_ShouldThrowException() {
        when(applicationRepository.findAll()).thenReturn(List.of());

        assertThrows(ApplicationNotFound.class, () -> applicationService.getApplicationById(session, 200L));
    }

    @Test
    void addApplication_ShouldSaveAndReturnApplication() {
        ApplicationDto dto = new ApplicationDto(100L, LocalDateTime.now(), "Pending", false, "https://resumes.com/resume.pdf", 1L, 10L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        ApplicationPayload result = applicationService.addApplication(session, dto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
    }

    @Test
    void deleteApplication_ValidId_ShouldDeleteApplication() {
        when(applicationRepository.findAll()).thenReturn(List.of(application));

        MessagePayload message = applicationService.deleteApplication(session, 100L);

        assertEquals("The application was successfully deleted from the system", message.getMessage());
        verify(applicationRepository, times(1)).delete(application);
    }
}
