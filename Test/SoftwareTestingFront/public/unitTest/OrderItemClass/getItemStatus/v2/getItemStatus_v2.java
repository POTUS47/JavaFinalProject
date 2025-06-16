@Transactional
    public Result<String> getItemStatus(String orderItemId) {
        // 1. 检查orderItemId是否为空
        if (orderItemId == null || orderItemId.isEmpty()) {
            return Result.error(404, "orderItemId不能为空");
        }

        // 2. 检查orderItemId格式是否为15位纯数字
        if (!orderItemId.matches("\\d{15}")) {
            return Result.error(404, "orderItemId格式错误");
        }

        // 3. 检查订单项是否存在
        Optional<OrderItem> item = orderItemRepository.findById(orderItemId);
        if (item.isEmpty()) {
            return Result.error(404, "希望查询状态的订单项不存在！");
        }

        // 4. 返回订单项状态
        String status = item.get().getItemStatus().toString();
        return Result.success(status);
    }