package com.JobsAppliedDms.JobsAppliedDms.service.impl;

/* Category Service Implementation
 *
 * Implement all business logic required to interact with the Category entity
 * */

import com.JobsAppliedDms.JobsAppliedDms.dto.CategoryDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.Category;
import com.JobsAppliedDms.JobsAppliedDms.exception.CategoryNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.CategoryPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.CategoryRepository;
import com.JobsAppliedDms.JobsAppliedDms.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryPayload> getAllCategories() {

        // Get All Categories
        List<Category> categories = categoryRepository.findAll();
        List<CategoryPayload> categoryPayloadList = new ArrayList<CategoryPayload>();

        for (Category category : categories)
        {
            categoryPayloadList.add(
                    new CategoryPayload(
                            category.getId(),
                            category.getName(),
                            category.getDescription(),
                            category.getAvgSalary(),
                            category.getDemand(),
                            category.getCreatedAt()
                    )
            );
        }

        return categoryPayloadList;
    }

    @Override
    public List<CategoryPayload> addCategoriesInBulk(List<CategoryDto> categoryDtos)
    {
        List<Category> categories = new ArrayList<Category>();
        List<CategoryPayload> categoryPayloadList = new ArrayList<CategoryPayload>();

        for (CategoryDto categoryDto : categoryDtos)
        {
            categories.add(new Category(
                    categoryDto.getId(),
                    categoryDto.getName(),
                    categoryDto.getDescription(),
                    categoryDto.getAvgSalary(),
                    categoryDto.getDemand()
            ));
        }

        categoryRepository.saveAll(categories);

        for (Category category : categories)
        {
            categoryPayloadList.add(new CategoryPayload(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getAvgSalary(),
                    category.getDemand(),
                    category.getCreatedAt()
            ));
        }

        return categoryPayloadList;
    }

    @Override
    public CategoryPayload getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFound("The category of id " + id + " could not be found")
        );

        return new CategoryPayload(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getAvgSalary(),
                category.getDemand(),
                category.getCreatedAt()
        );
    }

    @Override
    public CategoryPayload addCategory(CategoryDto categoryDto) {
        Category category = new Category(
                categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getDescription(),
                categoryDto.getAvgSalary(),
                categoryDto.getDemand()
        );

        Category newCategory = categoryRepository.save(category);

        return new CategoryPayload(
                newCategory.getId(),
                newCategory.getName(),
                newCategory.getDescription(),
                newCategory.getAvgSalary(),
                newCategory.getDemand(),
                newCategory.getCreatedAt()
        );
    }

    @Override
    public CategoryPayload updateCategoryById(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFound("The category of id " + id + " could not be found")
        );

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setAvgSalary(categoryDto.getAvgSalary());
        category.setDemand(categoryDto.getDemand());

        Category updatedCategory = categoryRepository.save(category);

        return new CategoryPayload(
                updatedCategory.getId(),
                updatedCategory.getName(),
                updatedCategory.getDescription(),
                updatedCategory.getAvgSalary(),
                updatedCategory.getDemand(),
                updatedCategory.getCreatedAt()
        );
    }

    @Override
    public MessagePayload deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFound("The category of id " + id + " could not be found")
        );

        categoryRepository.delete(category);

        return new MessagePayload("Category was successfully deleted");
    }
}
