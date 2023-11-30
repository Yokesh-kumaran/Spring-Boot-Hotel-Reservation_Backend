package com.restapi.dto;

import com.restapi.model.Amenity;
import com.restapi.model.Category;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDto {
    public CategoryResponse mapToCategoryResponse(List<Category> categories) {
        CategoryResponse categoryResponse = new CategoryResponse();

        ArrayList<CategoryRequest> categoryRequests = new ArrayList<>();
        for (Category category : categories) {
            Long amenityId = category.getAmenity() != null ? category.getAmenity().getId() : null;
            categoryRequests.add(new CategoryRequest(category.getId(), category.getName(), amenityId));
        }

        categoryResponse.setCategories(categoryRequests);
        return categoryResponse;
    }

    public Category mapToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        if (categoryRequest.getId() != null) {
            category.setId(categoryRequest.getId());
        }
        category.setName(categoryRequest.getName());
//        Amenity amenity = new Amenity();
//        amenity.setId(categoryRequest.getAmenityId());
//        category.setAmenity(amenity);
        return category;
    }
}
