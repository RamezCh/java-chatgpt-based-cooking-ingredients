package com.example.chatgptbasedcookingingredients.ingredient.controllers;

import com.example.chatgptbasedcookingingredients.ingredient.records.IngredientResponse;
import com.example.chatgptbasedcookingingredients.ingredient.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService service;

    @PostMapping
    public IngredientResponse categorizeIngredient(@RequestBody String ingredient) {

        return service.categorizeIngredient(ingredient);
    }

}
