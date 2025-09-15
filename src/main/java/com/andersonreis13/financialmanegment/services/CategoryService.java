package com.andersonreis13.financialmanegment.services;

import com.andersonreis13.financialmanegment.dtos.category.*;
import com.andersonreis13.financialmanegment.dtos.user.UserDTO;
import com.andersonreis13.financialmanegment.entities.Category;
import com.andersonreis13.financialmanegment.entities.User;
import com.andersonreis13.financialmanegment.exceptions.BadRequestException;
import com.andersonreis13.financialmanegment.exceptions.NotFoundException;
import com.andersonreis13.financialmanegment.mapper.category.CategoryMapper;
import com.andersonreis13.financialmanegment.repositories.CategoryRepository;
import com.andersonreis13.financialmanegment.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


    public CategoryDTO findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Categoria não encontrada"));

        return new CategoryDTO(category.getId(), category.getUserId().getId(),
                category.getName());
    }

    public List<CategoryDTO> findAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()-> new BadRequestException("Usuario não encontrado"));

        List<Category> categories = categoryRepository.findByUserId(user.getId());

        return categories.stream()
                .map(category -> new CategoryDTO(category.getId(),
                        category.getUserId().getId(), category.getName()))
                .collect(Collectors.toList());
    }

    public CategoryCreateResponse createCategory(CategoryCreateRequest request){
        User user = userRepository.findById(request.userId())
                .orElseThrow(()-> new NotFoundException("Usuario não encontrado"));

       Category category =  categoryRepository.save(CategoryMapper.dtoToEntity(request, user));

        return new CategoryCreateResponse("Criado com sucesso", category.getId(), new UserDTO(user.getId(),
                user.getName(), user.getEmail()), request.name());
    }

    public CategoryUpdateResponse updateCategory(CategoryUpdateRequest request){
        Category category = categoryRepository.findById(request.id())
                .orElseThrow(()-> new NotFoundException("Categoria não encontrada"));

        categoryRepository.save(category);

        return new CategoryUpdateResponse("Categoria atualizada", request.id(), request.name());
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Categoria não encontrado"));

        categoryRepository.delete(category);
    }
}
