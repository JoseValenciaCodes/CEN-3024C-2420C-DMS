package com.JobsAppliedDms.JobsAppliedDms.repository;

import com.JobsAppliedDms.JobsAppliedDms.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Category Repository
 * Handle DB Interactions related to the Category Entity
 * */

public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
