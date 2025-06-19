    @Transactional
    public Result<Boolean>isProductBookmarked(String userId, String productId){
        boolean isBookmarked = bookmarkProductRepository.existsByBuyerIdAndProductId(userId, productId);
        return Result.success(isBookmarked);
    }