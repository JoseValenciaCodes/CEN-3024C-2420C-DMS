package com.JobsAppliedDms.JobsAppliedDms.service;

/* Application Service Interface
* Define all ways to interact with the Application Entity here
* */

import com.JobsAppliedDms.JobsAppliedDms.dto.ApplicationDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.ApplicationPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ApplicationService
{
    List<ApplicationPayload> getAllApplications(HttpSession httpSession);
    List<ApplicationPayload> getApplicationsOfACompany(HttpSession httpSession, Long companyId);
    List<ApplicationPayload> getApplicationsOfACategory(HttpSession httpSession, Long categoryId);
    ApplicationPayload getApplicationById(HttpSession httpSession, Long id);
    ApplicationPayload addApplication(HttpSession httpSession, ApplicationDto applicationDto);
    ApplicationPayload updateApplication(HttpSession httpSession, Long id, ApplicationDto applicationDto);
    MessagePayload deleteApplication(HttpSession session, Long id);

}
