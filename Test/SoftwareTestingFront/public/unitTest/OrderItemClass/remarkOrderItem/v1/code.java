public Result<String> remarkOrderItem(UpdateOrderItemRemarkDTO orderItemRemarkDto) {
    Optional<OrderItem> orderItemOpt = orderItemRepository.findById(orderItemRemarkDto.getOrderItemId());
    if (orderItemOpt.isEmpty()) {
        return Result.error(404, "未找到订单");
    }

    OrderItem orderitem = orderItemOpt.get();
    orderitem.setScore(orderItemRemarkDto.getScore());
    orderitem.setRemark(orderItemRemarkDto.getRemark());
    orderItemRepository.save(orderitem);

    return Result.success(200, "订单评价已提交");
}