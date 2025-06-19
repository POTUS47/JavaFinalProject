@Transactional
public Optional<Store> getStoreById(String storeId) {
    String url = baseUrl + "/api/users/getStore/" + storeId;
    ResponseEntity<Optional<Store>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {
            }
    );
    return response.getBody();
}