package com.JobsAppliedDms.JobsAppliedDms.controller;

import com.JobsAppliedDms.JobsAppliedDms.dto.ApplicationDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.ApplicationPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.service.ApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController
{
    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationPayload>> getAllApplications(HttpSession httpSession)
    {
        List<ApplicationPayload> applicationPayloadList = applicationService.getAllApplications(httpSession);
        return ResponseEntity.ok(applicationPayloadList);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ApplicationPayload>> getApplicationsOfACompany(HttpSession httpSession, @PathVariable("companyId") Long companyId)
    {
        List<ApplicationPayload> applicationPayloadList = applicationService.getApplicationsOfACompany(httpSession, companyId);
        return ResponseEntity.ok(applicationPayloadList);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ApplicationPayload>> getApplicationsOfACategory(HttpSession httpSession, @PathVariable("categoryId") Long categoryId)
    {
        List<ApplicationPayload> applicationPayloadList = applicationService.getApplicationsOfACategory(httpSession, categoryId);
        return ResponseEntity.ok(applicationPayloadList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationPayload> getApplicationById(HttpSession httpSession, @PathVariable("id") Long id)
    {
        ApplicationPayload applicationPayload = applicationService.getApplicationById(httpSession, id);
        return ResponseEntity.ok(applicationPayload);
    }

    @PostMapping
    public ResponseEntity<ApplicationPayload> addApplication(HttpSession session, @Validated @RequestBody ApplicationDto applicationDto)
    {
        ApplicationPayload applicationPayload = applicationService.addApplication(session, applicationDto);
        return ResponseEntity.ok(applicationPayload);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApplicationPayload> updateApplication(HttpSession session, @PathVariable("id") Long id, @Validated @RequestBody ApplicationDto applicationDto)
    {
        ApplicationPayload applicationPayload = applicationService.updateApplication(session, id, applicationDto);
        return ResponseEntity.ok(applicationPayload);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessagePayload> deleteApplication(HttpSession httpSession, @PathVariable("id") Long id)
    {
        MessagePayload messagePayload = applicationService.deleteApplication(httpSession, id);
        return ResponseEntity.ok(messagePayload);
    }

}
