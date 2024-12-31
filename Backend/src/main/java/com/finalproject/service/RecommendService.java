package com.finalproject.service;

import com.finalproject.util.FlaskApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendService {

    private final FlaskApiClient flaskApiClient;

    @Autowired
    public RecommendService(FlaskApiClient flaskApiClient) {
        this.flaskApiClient = flaskApiClient;
    }

    /**
     * 调用 Flask 接口生成商品特征向量
     */
    public boolean generateProductFeature(String productId, String description, String name, String imagePath) {
        return flaskApiClient.generateProductFeature(productId, description, name, imagePath);
    }

    /**
     * 根据图片查找相似商品
     */
    public List<Map<String, Object>> findSimilarProducts(String imagePath, int resultNum) {
        return flaskApiClient.findSimilarProducts(imagePath, resultNum);
    }

    /**
     * 更新用户特征
     */
    public void updateUserFeatures(String userId, String[] strings) {
        flaskApiClient.updateUserFeatures(userId, strings);
    }

    /**
     * 为用户推荐商品
     */
    public List<Map<String, Object>> recommendForUser(String userId) {
        return flaskApiClient.recommendForUser(userId);
    }
}
