package com.example.chatgptbasedcookingingredients.cooking.records;

import java.util.List;

public record CookingInstruction(String title, List<String> description, List<String> ingredients) {
}
