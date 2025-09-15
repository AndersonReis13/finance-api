package com.andersonreis13.financialmanegment.mapper.category;

import com.andersonreis13.financialmanegment.dtos.category.CategoryCreateRequest;
import com.andersonreis13.financialmanegment.entities.Category;
import com.andersonreis13.financialmanegment.entities.User;

import java.time.LocalDateTime;

public class CategoryMapper {

    public static Category dtoToEntity(CategoryCreateRequest request, User user){
        return new Category(
                request.id(),
                user,
                request.name(),
                LocalDateTime.now()
        );
    }
}
