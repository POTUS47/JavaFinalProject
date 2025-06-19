    @Transactional
    public Optional<Store> getStoreById(String storeId) {
        if (storeId == null || storeId.trim().isEmpty()) {
            return Optional.empty();
        }
        String url = baseUrl + "/api/users/getStore/" + storeId;
        ResponseEntity<Optional<Store>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return Optional.ofNullable(response.getBody()).orElse(Optional.empty());
    }