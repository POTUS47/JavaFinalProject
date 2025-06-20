@Transactional
public Result<List<FavouriteStoresDTO>>getFavouriteStores(String userId){

    // 查询该用户收藏的所有店铺
    List<BookmarkStore> bookmarkedStores = bookmarkStoreRepository.findByBuyerId(userId);
    if (bookmarkedStores.isEmpty()) {
        return Result.error(404, "没有收藏任何店铺");
    }
    // 构造返回的DTO列表
    List<FavouriteStoresDTO> favouriteStores = new ArrayList<>();
    // 遍历用户“收藏店铺”的model
    for (BookmarkStore bookmarkStore : bookmarkedStores) {
        // 初始化DTO
        FavouriteStoresDTO favouriteStoreDTO = new FavouriteStoresDTO();
        favouriteStoreDTO.setBuyerId(bookmarkStore.getBuyerAccountId());
        favouriteStoreDTO.setStoreId(bookmarkStore.getStoreAccountId());

        // 获取店铺信息
        Store store = getStoreById(bookmarkStore.getStoreAccountId()).get();
        favouriteStoreDTO.setStoreName(store.getStoreName());
        favouriteStoreDTO.setStoreScore(store.getStoreScore());

        // 获取该店铺下的商品信息
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> products = getProductsByStoreId(store.getAccountId());

        if (products != null) {
            for (Product product : products) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductId(product.getProductId());
                productDTO.setProductName(product.getProductName());
                productDTO.setProductPrice(product.getProductPrice());

                // 获取商品的图片信息
                List<String> productImageUrls = getProductImagesById(product.getProductId());
                if (!productImageUrls.isEmpty()) {
                    productDTO.setProductPic(productImageUrls.getFirst());
                }
                productDTOList.add(productDTO);
            }
        }
        // 设置该店铺下的所有商品信息
        favouriteStoreDTO.setProducts(productDTOList);
        favouriteStores.add(favouriteStoreDTO);
    }

    return Result.success(favouriteStores);
}