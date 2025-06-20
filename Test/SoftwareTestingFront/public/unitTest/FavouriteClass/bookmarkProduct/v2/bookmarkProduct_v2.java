@Transactional
public Result<String> bookmarkProduct(String userId, String productId) {
    if (productId == null || productId.trim().isEmpty()) {
        return Result.error(400, "商品ID不能为空");
    }
    if (userId == null || userId.trim().isEmpty()) {
        return Result.error(400, "用户ID不能为空");
    }

    Optional<Product> productOpt = getProductById(productId);
    if (productOpt.isEmpty()) {
        return Result.error(404, "未找到商品信息");
    }

    // 查询买家信息
    Optional<Buyer> buyerOpt = getBuyerById(userId);
    if (buyerOpt.isEmpty()) {
        return Result.error(404, "未找到买家信息");
    }

    // 收藏则取消收藏
    boolean isBookmarked = bookmarkProductRepository.existsByBuyerIdAndProductId(userId, productId);
    if (isBookmarked) {
        try {
            System.out.println("保存");
            bookmarkProductRepository.deleteBookmarkProduct(userId, productId);
            return Result.success(200,"取消收藏成功");
        } catch (Exception e) {
            return Result.error(500, "数据库操作异常");
        }
    }

    try {
        System.out.println("保存");
        bookmarkProductRepository.insertBookmarkProduct(userId, productId);
        // bookmarkProductRecord(userId, productId);
        return Result.success(200,"收藏成功");
    } catch (Exception e) {
        return Result.error(500, "数据库操作异常");
    }

}