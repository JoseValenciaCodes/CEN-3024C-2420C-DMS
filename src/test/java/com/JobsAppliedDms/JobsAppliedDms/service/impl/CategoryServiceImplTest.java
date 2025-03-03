package com.JobsAppliedDms.JobsAppliedDms.service.impl;

/* CategoryServiceImplTest
* Testing out the functionality of the Category Service
* */

import com.JobsAppliedDms.JobsAppliedDms.dto.CategoryDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.Category;
import com.JobsAppliedDms.JobsAppliedDms.exception.CategoryNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.CategoryPayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest
{
    // Import mock dependencies
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    private Category category;
    private CategoryDto categoryDto;

    // Set up mock data before unit tests
    @BeforeEach
    void setUp() {
        category = new Category(1L, "IT", "Technology Jobs", 80000.0, 5);
        categoryDto = new CategoryDto(1L, "IT", "Technology Jobs", 80000.0, 5);
    }

    // Test successfully adding a category
    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));

        List<CategoryPayload> result = categoryServiceImpl.getAllCategories();

        assertEquals(1, result.size());
        assertEquals("IT", result.get(0).getName());
        verify(categoryRepository, times(1)).findAll();
    }

    // Test getting a found category by ID
    @Test
    void testGetCategoryById_Found() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryPayload result = categoryServiceImpl.getCategoryById(1L);

        assertNotNull(result);
        assertEquals("IT", result.getName());
        verify(categoryRepository, times(1)).findById(1L);
    }

    // Test not getting a category by ID due to CategoryNotFound Error
    @Test
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFound.class, () -> categoryServiceImpl.getCategoryById(1L));
        verify(categoryRepository, times(1)).findById(1L);
    }

    // Test successfully adding a category
    @Test
    void testAddCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryPayload result = categoryServiceImpl.addCategory(categoryDto);

        assertNotNull(result);
        assertEquals("IT", result.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    // Test successfully updating a category by ID
    @Test
    void testUpdateCategoryById_Found() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryPayload result = categoryServiceImpl.updateCategoryById(1L, categoryDto);

        assertNotNull(result);
        assertEquals("IT", result.getName());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    // Test Being Unable to update a category because it was not found
    @Test
    void testUpdateCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFound.class, () -> categoryServiceImpl.updateCategoryById(1L, categoryDto));
        verify(categoryRepository, times(1)).findById(1L);
    }

    // Test successfully deleting a category
    @Test
    void testDeleteCategoryById_Found() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).delete(category);

        MessagePayload result = categoryServiceImpl.deleteCategoryById(1L);

        assertNotNull(result);
        assertEquals("Category was successfully deleted", result.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).delete(category);
    }

    // Test failing to delete a category because it was not found
    @Test
    void testDeleteCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFound.class, () -> categoryServiceImpl.deleteCategoryById(1L));
        verify(categoryRepository, times(1)).findById(1L);
    }
}
