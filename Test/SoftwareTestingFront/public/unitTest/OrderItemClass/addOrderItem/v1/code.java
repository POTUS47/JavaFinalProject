public Result<String> addOrderItem(AddOrderItemDTO orderItemDTO) {
    // 检查订单是否存在
    Optional<Order> orderOpt = orderRepository.findById(orderItemDTO.getOrderId());
    if (orderOpt.isEmpty()) {
        return Result.error(404, "订单不存在");
    }
    // 检查商品是否存在
    Optional<Product> productOpt = orderService.getProductById(orderItemDTO.getProductId());
    if (productOpt.isEmpty()) {
        return Result.error(404, "商品不存在");
    }
    // 检查订单项是否已存在
    Optional<OrderItem> existingOrderItem = orderItemRepository.findById(orderItemDTO.getItemId());
    if (existingOrderItem.isPresent()) {
        return Result.error(409, "订单项已存在");
    }

    // 创建新的订单项
    OrderItem orderItem = new OrderItem();
    orderItem.setItemId(orderItemDTO.getItemId());
    orderItem.setProductId(orderItemDTO.getProductId());
    orderItem.setQuantity(1);  // 假设每个商品购买数量为1,需要修改
    orderItem.setUnitPrice(orderItemDTO.getUnitPrice());
    orderItem.setOrderId(orderItemDTO.getOrderId());
    orderItem.setTotalPay(orderItemDTO.getTotalPay());
    orderItem.setActualPay(orderItemDTO.getActualPay());
    orderItem.setItemStatus(OrderItem.ItemStatus.无售后);
    orderItem.setScore(BigDecimal.ZERO);


    // 保存订单项
    orderItemRepository.save(orderItem);

    return Result.success("订单项添加成功", orderItem.getItemId());
}