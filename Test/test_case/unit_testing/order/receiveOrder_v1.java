    @Transactional
    public Result<String> receiveOrder(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            return Result.error(400, "订单不能为空");
        }

        Optional<Order> orderopt = orderRepository.findById(orderId);
        if (orderopt.isEmpty()) {
            return Result.error(404, "订单不存在");
        }


        Order order = orderopt.get();

        order.setOrderStatus(Order.OrderStatus.已完成);
        return Result.success(200,"确认收货成功");
    }
