@Transactional
public Result<String> deleteOrderitem(String orderItemId) {
    // 检查orderItemId是否为空
    if (orderItemId == null || orderItemId.isEmpty()) {
        return Result.error(404, "orderItemId不能为空");
    }
    if (!orderItemId.matches("\\d{15}")) {
        return Result.error(404, "orderItemId格式错误");
    }
    Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
    if(orderItem.isPresent()) {
        orderItemRepository.delete(orderItem.get());
        return Result.success("订单项删除成功");
    } else {
        return Result.error(404, "不存在该订单项");
    }
}