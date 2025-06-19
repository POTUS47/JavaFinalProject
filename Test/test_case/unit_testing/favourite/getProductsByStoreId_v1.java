    @Transactional
    public List<Product> getProductsByStoreId(String storeId) {
        String url = baseUrl + "/api/productController/products/" + storeId;
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        return response.getBody();
    }