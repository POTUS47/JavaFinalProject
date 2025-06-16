@Transactional
    public Result<String> deleteOrder(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            return Result.error(400, "订单id不能为空");
        }
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            return Result.error(404, "订单不存在");
        }

        // 确认订单存在后，再查找并删除 orderItem
        List<OrderItem> orderItemList = orderItemRepository.findByOrderId(orderId);
        orderItemRepository.deleteAll(orderItemList);

        orderRepository.delete(optionalOrder.get());

        return Result.success(200,"订单删除成功");
    }