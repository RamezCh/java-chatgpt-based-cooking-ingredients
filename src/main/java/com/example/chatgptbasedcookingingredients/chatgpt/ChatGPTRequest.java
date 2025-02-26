package com.example.chatgptbasedcookingingredients.chatgpt;

import java.util.Collections;
import java.util.List;

public record ChatGPTRequest(
        String model,
        List<ChatGPTRequestMessage> messages
) {
    public ChatGPTRequest(String message) {
        this("gpt-4o-mini", Collections.singletonList(new ChatGPTRequestMessage("user", message)));
    }
}