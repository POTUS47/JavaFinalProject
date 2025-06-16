@Transactional
public Result<String> remarkOrderItem(UpdateOrderItemRemarkDTO orderItemRemarkDto) {
    // 1. 检查orderItemId是否为空
    if (orderItemRemarkDto.getOrderItemId() == null || orderItemRemarkDto.getOrderItemI().isEmpty()) {
        return Result.error(404, "orderItemId不能为空");
    }
    // 2. 检查orderItemId格式是否为15位纯数字
    if (!orderItemRemarkDto.getOrderItemId().matches("\\d{15}")) {
        return Result.error(404, "orderItemId格式错误");
    }
    // 3. 检查评分范围 (0-5)
    if (orderItemRemarkDto.getScore() == null
            || orderItemRemarkDto.getScore().compareTo(BigDecimal.ZERO) < 0
            || orderItemRemarkDto.getScore().compareTo(new BigDecimal("5")) > 0) {
        return Result.error(404, "评分只能在0-5之间");
    }
    // 4. 检查remark长度 (不超过200字符)
    if (orderItemRemarkDto.getRemark() != null && orderItemRemarkDto.getRemark().length()> 200) {
        return Result.error(500, "remark超出200的长度限制");
    }
    // 5. 检查订单项是否存在
    Optional<OrderItem> orderItemOpt = orderItemRepository.findById(orderItemRemarkDtogetOrderItemId());
    if (orderItemOpt.isEmpty()) {
        return Result.error(404, "未找到订单");
    }
    // 6. 更新订单项评价信息
    OrderItem orderitem = orderItemOpt.get();
    orderitem.setScore(orderItemRemarkDto.getScore());
    orderitem.setRemark(orderItemRemarkDto.getRemark());
    orderItemRepository.save(orderitem);
    return Result.success(200, "订单评价已提交");
}