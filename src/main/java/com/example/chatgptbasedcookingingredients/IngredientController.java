package com.example.chatgptbasedcookingingredients;


import com.example.chatgptbasedcookingingredients.chatgpt.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final RestClient restClient;

    public IngredientController(@Value("${OPEN_AI_KEY}") String openaiApiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + openaiApiKey)
                .build();
    }

    @PostMapping
    public String categorizeIngredient(@RequestBody String ingredient) {
        ChatGPTResponse response = restClient.post()
                .body(new ChatGPTRequest("Is " + ingredient + " vegan, vegetarian or regular?"))
                .retrieve()
                .body(ChatGPTResponse.class);

        return response.text();
    }

}
