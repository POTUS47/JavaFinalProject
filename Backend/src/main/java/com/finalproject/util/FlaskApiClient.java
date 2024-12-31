package com.finalproject.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;


import java.util.*;

@Component
public class FlaskApiClient {

    private static final String BASE_URL = "http://127.0.0.1:5000";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public FlaskApiClient() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 调用 Flask 的 /generate_product_feature 接口
     */
    public boolean generateProductFeature(String productId, String description,
                                          String name,String imagePath) {
        String url = BASE_URL + "/generate_product_feature";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", productId);
        requestBody.put("name", name);
        requestBody.put("description", description);
        requestBody.put("imagePath", imagePath);

        try {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, getHeaders());
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException e) {
            System.err.println("Error calling generateProductFeature: " + e.getResponseBodyAsString());
            return false;
        }
    }

    /**
     * 调用 Flask 的 /find_similar_products 接口
     */
    public List<Map<String, Object>> findSimilarProducts(String imagePath, int resultNum) {
        String url = BASE_URL + "/find_similar_products";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("imagePath", imagePath);
        requestBody.put("resultNum", resultNum);

        try {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, getHeaders());
            ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error calling findSimilarProducts: " + e.getResponseBodyAsString());
            return Collections.emptyList();
        }
    }

    /**
     * 调用 Flask 的 /update_user_features 接口
     */
    public void updateUserFeatures(String userId, String[] strings) {
        String url = BASE_URL + "/update_user_features";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_id", userId);
        requestBody.put("strings", strings);

        try {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, getHeaders());
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("User features updated successfully.");
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error calling updateUserFeatures: " + e.getResponseBodyAsString());
        }
    }

    /**
     * 调用 Flask 的 /recommend_for_user 接口
     */
    public List<Map<String, Object>> recommendForUser(String userId) {
        String url = BASE_URL + "/recommend_for_user";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_id", userId);

        try {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, getHeaders());
            ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error calling recommendForUser: " + e.getResponseBodyAsString());
            return Collections.emptyList();
        }
    }

    /**
     * 获取默认的 HTTP Headers
     */
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
