package com.JobsAppliedDms.JobsAppliedDms.service;

/* Category Service Interface
 * Define all ways to interact with the Category entity here
 *
 * */


import com.JobsAppliedDms.JobsAppliedDms.dto.CategoryDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.CategoryPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;

import java.util.List;

public interface CategoryService
{
    List<CategoryPayload> getAllCategories();
    List<CategoryPayload> addCategoriesInBulk(List<CategoryDto> categoryDtos);
    CategoryPayload getCategoryById(Long id);
    CategoryPayload addCategory(CategoryDto categoryDto);
    CategoryPayload updateCategoryById(Long id, CategoryDto categoryDto);
    MessagePayload deleteCategoryById(Long id);
}
