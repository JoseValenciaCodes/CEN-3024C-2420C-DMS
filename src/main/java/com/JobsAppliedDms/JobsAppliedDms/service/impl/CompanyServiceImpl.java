package com.JobsAppliedDms.JobsAppliedDms.service.impl;

/* Company Service Implementation
 *
 * Implement all business logic required to interact with the Company entity
 * */

import com.JobsAppliedDms.JobsAppliedDms.dto.CompanyDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.Company;
import com.JobsAppliedDms.JobsAppliedDms.exception.CompanyNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.CompanyPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.CompanyRepository;
import com.JobsAppliedDms.JobsAppliedDms.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService
{
    // Attributes
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyPayload> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyPayload> companyPayloadList = new ArrayList<CompanyPayload>();

        for (Company company : companies)
        {
            companyPayloadList.add(new CompanyPayload(
                    company.getId(),
                    company.getName(),
                    company.getDescription(),
                    company.getLocation(),
                    company.getHiringStatus(),
                    company.getPriorityLevel(),
                    company.getCreatedAt()
            ));
        }

        return companyPayloadList;
    }

    @Override
    public CompanyPayload getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFound("The requested company could not be found")
        );

        return new CompanyPayload(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getLocation(),
                company.getHiringStatus(),
                company.getPriorityLevel(),
                company.getCreatedAt()
        );
    }

    @Override
    public CompanyPayload addCompany(CompanyDto companyDto)
    {
        Company company = new Company();

        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setLocation(companyDto.getLocation());
        company.setHiringStatus(companyDto.getHiringStatus());
        company.setPriorityLevel(companyDto.getPriorityLevel());

        Company newCompany = companyRepository.save(company);

        System.out.println("New Company Description " + newCompany.getDescription());

        return new CompanyPayload(
                newCompany.getId(),
                newCompany.getName(),
                newCompany.getDescription(),
                newCompany.getLocation(),
                newCompany.getHiringStatus(),
                newCompany.getPriorityLevel(),
                newCompany.getCreatedAt()
        );
    }

    @Override
    public CompanyPayload updateCompanyById(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFound("The requested company could not be found")
        );

        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setLocation(companyDto.getLocation());
        company.setHiringStatus(companyDto.getHiringStatus());
        company.setPriorityLevel(companyDto.getPriorityLevel());

        companyRepository.save(company);

        return new CompanyPayload(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getLocation(),
                company.getHiringStatus(),
                company.getPriorityLevel(),
                company.getCreatedAt()
        );

    }

    @Override
    public MessagePayload deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFound("The requested company could not be found")
        );

        companyRepository.delete(company);

        return new MessagePayload("Company was successfully deleted");
    }
}
