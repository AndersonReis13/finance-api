package com.andersonreis13.financialmanegment.controllers;

import com.andersonreis13.financialmanegment.dtos.category.*;
import com.andersonreis13.financialmanegment.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryCreateResponse> createCategory(@RequestBody CategoryCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request));
    }

    @PutMapping
    public ResponseEntity<CategoryUpdateResponse> updateCategory(@RequestBody CategoryUpdateRequest request){
        return ResponseEntity.ok().body(categoryService.updateCategory(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
