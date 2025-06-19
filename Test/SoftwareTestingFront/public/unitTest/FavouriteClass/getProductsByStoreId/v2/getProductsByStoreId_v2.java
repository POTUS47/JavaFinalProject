@Transactional
public List<Product> getProductsByStoreId(String storeId) {
    if (storeId == null || storeId.trim().isEmpty()) {
        return Collections.emptyList();
    }
    String url = baseUrl + "/api/productController/products/" + storeId;
    ResponseEntity<List<Product>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Product>>() {}
    );
    return response.getBody();
}