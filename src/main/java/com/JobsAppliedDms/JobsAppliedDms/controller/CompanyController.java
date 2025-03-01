package com.JobsAppliedDms.JobsAppliedDms.controller;

/* Company Controller
* Establish HTTP entry points related to the Company Entity
* */

import com.JobsAppliedDms.JobsAppliedDms.dto.CompanyDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.CompanyPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Expose the HTTP endpoints to interact with the Company Entity
* */

@RestController
@RequestMapping("/api/companies")
public class CompanyController
{
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    /* Make a GET request to get all companies */
    @GetMapping
    public ResponseEntity<List<CompanyPayload>> getALlCompanies()
    {
        List<CompanyPayload> companyPayloadList = companyService.getAllCompanies();
        return ResponseEntity.ok(companyPayloadList);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyPayload> getCompanyById(@PathVariable("id") Long id)
    {
        CompanyPayload companyPayload = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyPayload);
    }

    @PostMapping
    public ResponseEntity<CompanyPayload> addCompany(@Validated @RequestBody CompanyDto companyDto)
    {
        CompanyPayload companyPayload = companyService.addCompany(companyDto);
        return ResponseEntity.ok(companyPayload);
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyPayload> updateCompany(@PathVariable("id") Long id, @Validated @RequestBody CompanyDto companyDto)
    {
        CompanyPayload companyPayload = companyService.updateCompanyById(id, companyDto);
        return ResponseEntity.ok(companyPayload);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessagePayload> deleteCompany(@PathVariable("id") Long id)
    {
        MessagePayload messagePayload = companyService.deleteCompanyById(id);
        return ResponseEntity.ok(messagePayload);
    }

}
