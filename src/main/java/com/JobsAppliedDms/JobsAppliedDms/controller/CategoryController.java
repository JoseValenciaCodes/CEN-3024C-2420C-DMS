package com.JobsAppliedDms.JobsAppliedDms.controller;

import com.JobsAppliedDms.JobsAppliedDms.dto.CategoryDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.CategoryPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Expose the HTTP endpoints to interact with the Category Entity
 * */

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    /* Make a GET Request for all categories */
    @GetMapping
    public ResponseEntity<List<CategoryPayload>> getAllCategories()
    {
        List<CategoryPayload> categoryPayloadList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryPayloadList);
    }

    /* Get a specific category */
    @GetMapping("{id}")
    public ResponseEntity<CategoryPayload> getCategoryById(@PathVariable("id") Long id)
    {
        CategoryPayload categoryPayload = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryPayload);
    }

    /* Add a new category */
    @PostMapping
    public ResponseEntity<CategoryPayload> addCategory(@Validated @RequestBody CategoryDto categoryDto)
    {
        CategoryPayload categoryPayload = categoryService.addCategory(categoryDto);
        return ResponseEntity.ok(categoryPayload);
    }

    /* Update category */
    @PutMapping("{id}")
    public ResponseEntity<CategoryPayload> updateCategory(@PathVariable("id") Long id, @Validated @RequestBody CategoryDto categoryDto)
    {
        CategoryPayload categoryPayload = categoryService.updateCategoryById(id, categoryDto);
        return ResponseEntity.ok(categoryPayload);
    }

    /* Delete category */
    @DeleteMapping("{id}")
    public ResponseEntity<MessagePayload> deleteCategory(@PathVariable("id") Long id)
    {
        MessagePayload messagePayload = categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(messagePayload);
    }
}
