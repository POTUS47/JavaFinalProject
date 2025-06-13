public Result<String> deleteOrderitem(String orderItemId) {
    Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
    if(orderItem.isPresent()) {
        orderItemRepository.delete(orderItem.get());
        return Result.success("订单项删除成功");
    } else {
        return Result.error(404,"不存在该订单项");
    }
}