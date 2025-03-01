package com.JobsAppliedDms.JobsAppliedDms.service;

/* Company Service Interface
 * Define all ways to interact with the User entity here
 *
 * */

import com.JobsAppliedDms.JobsAppliedDms.dto.CompanyDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.CompanyPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;

import java.util.List;

/* Company Service Interface
 * Define all ways to interact with the Company entity here
 *
 * */


public interface CompanyService
{
    List<CompanyPayload> getAllCompanies();
    CompanyPayload getCompanyById(Long id);
    CompanyPayload addCompany(CompanyDto companyDto);
    CompanyPayload updateCompanyById(Long id, CompanyDto companyDto);
    MessagePayload deleteCompanyById(Long id);
}
