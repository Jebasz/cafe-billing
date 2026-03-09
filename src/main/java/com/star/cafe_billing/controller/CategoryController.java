package com.star.cafe_billing.controller;

import com.star.cafe_billing.entity.Category;
import com.star.cafe_billing.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;


    @GetMapping
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();

    }

}