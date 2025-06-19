    @Transactional
    public Result<Boolean>isProductBookmarked(String userId, String productId){
        if (userId == null || userId.trim().isEmpty() ||
                productId == null || productId.trim().isEmpty()) {
            return Result.error(500,"用户ID或商品ID不能为空");
        }
        boolean isBookmarked = bookmarkProductRepository.existsByBuyerIdAndProductId(userId, productId);
        return Result.success(isBookmarked);
    }