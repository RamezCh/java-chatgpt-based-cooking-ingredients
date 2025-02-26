package com.example.chatgptbasedcookingingredients.ingredient.services;

import com.example.chatgptbasedcookingingredients.chatgpt.ChatGPTRequest;
import com.example.chatgptbasedcookingingredients.chatgpt.ChatGPTResponse;
import com.example.chatgptbasedcookingingredients.ingredient.records.IngredientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

@Service
public class IngredientService {

        private final RestClient restClient;

        public IngredientService(@Value("${OPEN_AI_KEY}") String openaiApiKey) {
            this.restClient = RestClient.builder()
                    .baseUrl("https://api.openai.com/v1/chat/completions")
                    .defaultHeader("Authorization", "Bearer " + openaiApiKey)
                    .build();
        }

        public IngredientResponse categorizeIngredient(@RequestBody String ingredient) {
            ChatGPTResponse response = restClient.post()
                    .body(new ChatGPTRequest("Is " + ingredient + " vegan, vegetarian or regular?"))
                    .retrieve()
                    .body(ChatGPTResponse.class);

            return new IngredientResponse(response.text());
        }

}
