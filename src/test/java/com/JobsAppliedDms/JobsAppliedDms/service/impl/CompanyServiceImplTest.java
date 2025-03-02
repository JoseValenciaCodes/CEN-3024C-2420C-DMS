package com.JobsAppliedDms.JobsAppliedDms.service.impl;

import com.JobsAppliedDms.JobsAppliedDms.dto.CompanyDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.Company;
import com.JobsAppliedDms.JobsAppliedDms.exception.CompanyNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.CompanyPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest
{
    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private Company testCompany;
    private CompanyDto testCompanyDto;

    @BeforeEach
    void setUp() {
        testCompany = new Company();
        testCompany.setId(1L);
        testCompany.setName("TechCorp");
        testCompany.setDescription("A leading tech company.");
        testCompany.setLocation("San Francisco");
        testCompany.setHiringStatus(true);
        testCompany.setPriorityLevel(1);
        testCompany.setCreatedAt(LocalDateTime.now());

        testCompanyDto = new CompanyDto();
        testCompanyDto.setName("TechCorp");
        testCompanyDto.setDescription("A leading tech company.");
        testCompanyDto.setLocation("San Francisco");
        testCompanyDto.setHiringStatus(true);
        testCompanyDto.setPriorityLevel(1);
    }

    @Test
    void testGetAllCompanies() {
        when(companyRepository.findAll()).thenReturn(Arrays.asList(testCompany));

        List<CompanyPayload> result = companyService.getAllCompanies();

        assertEquals(1, result.size());
        assertEquals("TechCorp", result.get(0).getName());
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void testGetCompanyById_Found() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(testCompany));

        CompanyPayload result = companyService.getCompanyById(1L);

        assertNotNull(result);
        assertEquals("TechCorp", result.getName());
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCompanyById_NotFound() {
        when(companyRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFound.class, () -> companyService.getCompanyById(2L));

        verify(companyRepository, times(1)).findById(2L);
    }

    @Test
    void testAddCompany() {
        when(companyRepository.save(any(Company.class))).thenReturn(testCompany);

        CompanyPayload result = companyService.addCompany(testCompanyDto);

        assertNotNull(result);
        assertEquals("TechCorp", result.getName());
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void testUpdateCompanyById_Found() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(testCompany));
        when(companyRepository.save(any(Company.class))).thenReturn(testCompany);

        CompanyPayload result = companyService.updateCompanyById(1L, testCompanyDto);

        assertNotNull(result);
        assertEquals("TechCorp", result.getName());
        verify(companyRepository, times(1)).findById(1L);
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void testUpdateCompanyById_NotFound() {
        when(companyRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFound.class, () -> companyService.updateCompanyById(2L, testCompanyDto));

        verify(companyRepository, times(1)).findById(2L);
    }

    @Test
    void testDeleteCompanyById_Found() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(testCompany));
        doNothing().when(companyRepository).delete(testCompany);

        MessagePayload result = companyService.deleteCompanyById(1L);

        assertNotNull(result);
        assertEquals("Company was successfully deleted", result.getMessage());
        verify(companyRepository, times(1)).findById(1L);
        verify(companyRepository, times(1)).delete(testCompany);
    }

    @Test
    void testDeleteCompanyById_NotFound() {
        when(companyRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFound.class, () -> companyService.deleteCompanyById(2L));

        verify(companyRepository, times(1)).findById(2L);
    }
}
