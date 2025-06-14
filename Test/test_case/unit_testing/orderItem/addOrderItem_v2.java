public Result<String> addOrderItem(AddOrderItemDTO orderItemDTO) {
    // 检查参数是否为空
    if (orderItemDTO.getOrderId() == null || orderItemDTO.getOrderId().isEmpty()) {
        return Result.error(404, "orderId不能为空");
    }
    if (orderItemDTO.getProductId() == null || orderItemDTO.getProductId().isEmpty()) {
        return Result.error(404, "productId不能为空");
    }
    if (orderItemDTO.getItemId() == null || orderItemDTO.getItemId().isEmpty()) {
        return Result.error(404, "itemId不能为空");
    }
    // 检查数值范围
    if (orderItemDTO.getUnitPrice() == null || orderItemDTO.getUnitPrice().compareT(BigDecimal.ZERO) < 0
            || orderItemDTO.getUnitPrice().compareTo(new BigDecimal("99999999")) > 0) {
        return Result.error(400, "unitPrice只能在0-99999999之间");
    }
    if (orderItemDTO.getQuantity() == null || orderItemDTO.getQuantity() < 1 ||orderItemDTO.getQuantity() > 9999) {
        return Result.error(400, "quantity只能在1-9999之间");
    }
    if (orderItemDTO.getActualPay() == null || orderItemDTO.getActualPay().compareT(BigDecimal.ZERO) < 0
            || orderItemDTO.getActualPay().compareTo(new BigDecimal("99999999")) > 0) {
        return Result.error(400, "actualPay只能在0-99999999之间");
    }
    if (orderItemDTO.getScore() == null || orderItemDTO.getScore().compareTo(BigDecimalZERO) < 0
            || orderItemDTO.getScore().compareTo(new BigDecimal("5")) > 0) {
        return Result.error(400, "评分只能在0-5之间");
    }
    // 检查totalPay计算是否正确 (unitPrice * quantity)
    BigDecimal expectedTotalPay = orderItemDTO.getUnitPrice().multiply(new BigDecima(orderItemDTO.getQuantity()));
    if (orderItemDTO.getTotalPay() == null || orderItemDTO.getTotalPay().compareT(expectedTotalPay) != 0) {
        return Result.error(400, "totalPay与实际计算不符");
    }
    // 检查订单是否存在
    Optional<Order> orderOpt = orderRepository.findById(orderItemDTO.getOrderId());
    if (orderOpt.isEmpty()) {
        return Result.error(404, "订单不存在");
    }
    // 检查商品是否存在
    Optional<Product> productOpt = orderService.getProductById(orderItemDTO.getProductI());
    if (productOpt.isEmpty()) {
        return Result.error(404, "商品不存在");
    }
    // 检查订单项是否已存在
    Optional<OrderItem> existingOrderItem = orderItemRepository.findById(orderItemDTOgetItemId());
    if (existingOrderItem.isPresent()) {
        return Result.error(409, "订单项已存在");
    }
    // 创建新的订单项
    OrderItem orderItem = new OrderItem();
    orderItem.setItemId(orderItemDTO.getItemId());
    orderItem.setProductId(orderItemDTO.getProductId());
    orderItem.setQuantity(1);
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