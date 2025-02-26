package com.example.chatgptbasedcookingingredients.cooking.services;

import com.example.chatgptbasedcookingingredients.chatgpt.ChatGPTRequest;
import com.example.chatgptbasedcookingingredients.chatgpt.ChatGPTResponse;
import com.example.chatgptbasedcookingingredients.cooking.records.CookingInstruction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class CookingService {

    private final RestClient restClient;

    public CookingService(@Value("${OPEN_AI_KEY}") String openaiApiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + openaiApiKey)
                .build();
    }

    public CookingInstruction generateCookingInstruction(@RequestBody String ingredients) {
        String prompt = "Generate a cooking instruction for a dish using ONLY the following ingredients: " +
                ingredients +
                ". Provide the response in JSON format with three fields: 'title' (dish name), 'description' (step-by-step instructions), and 'ingredients' (list of ingredients with quantities if applicable). Do not add ```json";

        ChatGPTResponse response = restClient.post()
                .body(new ChatGPTRequest(prompt))
                .retrieve()
                .body(ChatGPTResponse.class);

        // Parse the JSON string
        JSONObject jsonObject = new JSONObject(response.text());

        // Extract fields from JSON
        String title = jsonObject.getString("title");

        List<String> description = new ArrayList<>();
        JSONArray descriptionArray = jsonObject.getJSONArray("description");
        for (int i = 0; i < descriptionArray.length(); i++) {
            description.add(descriptionArray.getString(i));
        }

        List<String> ingredientList = new ArrayList<>();
        JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
        for (int i = 0; i < ingredientsArray.length(); i++) {
            ingredientList.add(ingredientsArray.getString(i));
        }

        return new CookingInstruction(title, description, ingredientList);
    }
}
