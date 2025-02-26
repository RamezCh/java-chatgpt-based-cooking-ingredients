package com.example.chatgptbasedcookingingredients.cooking;

import java.util.List;

public record CookingInstruction(String title, String description, List<String> ingredients) {
}
