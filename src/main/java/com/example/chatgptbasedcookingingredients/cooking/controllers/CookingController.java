package com.example.chatgptbasedcookingingredients.cooking.controllers;


import com.example.chatgptbasedcookingingredients.cooking.records.CookingInstruction;
import com.example.chatgptbasedcookingingredients.cooking.services.CookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cook")
@RequiredArgsConstructor
public class CookingController {

    private final CookingService service;

    @PostMapping
    public CookingInstruction generateCookingInstruction(@RequestBody String ingredients) {
        return service.generateCookingInstruction(ingredients);
    }

}
