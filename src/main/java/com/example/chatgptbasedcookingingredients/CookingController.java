package com.example.chatgptbasedcookingingredients;

import com.example.chatgptbasedcookingingredients.chatgpt.ChatGPTRequest;
import com.example.chatgptbasedcookingingredients.chatgpt.ChatGPTResponse;
import com.example.chatgptbasedcookingingredients.cooking.CookingInstruction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/cook")
public class CookingController {

    private final RestClient restClient;

    public CookingController(@Value("${OPEN_AI_KEY}") String openaiApiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + openaiApiKey)
                .build();
    }

    @PostMapping
    public String generateCookingInstruction(@RequestBody String ingredients) {
        String prompt = "Generate a cooking instruction for a dish using ONLY the following ingredients: " +
                ingredients +
                ". Provide the response in JSON format with three fields: 'title' (dish name), 'description' (step-by-step instructions), and 'ingredients' (list of ingredients with quantities if applicable). Do not add ```json";

        ChatGPTResponse response = restClient.post()
                .body(new ChatGPTRequest(prompt))
                .retrieve()
                .body(ChatGPTResponse.class);

        return response.text();
    }

}
