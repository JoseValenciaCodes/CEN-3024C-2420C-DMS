package com.JobsAppliedDms.JobsAppliedDms.repository;

import com.JobsAppliedDms.JobsAppliedDms.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Company Repository
 * Handle DB Interactions related to the Company Entity
 * */

public interface CompanyRepository extends JpaRepository<Company, Long>
{
}
