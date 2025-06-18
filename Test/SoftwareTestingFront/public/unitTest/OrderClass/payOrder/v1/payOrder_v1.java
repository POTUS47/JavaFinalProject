 @Transactional
    public Result<CreditsDTO> payOrder (String userId, String orderId, Integer usedCredits){

        if (userId == null || userId.isBlank()) {
            return Result.error(400, "用户不能为空");
        }
        if (orderId == null || orderId.isBlank()) {
            return Result.error(400, "订单不能为空");
        }
        if (usedCredits != null && usedCredits < 0) {
            return Result.error(400, "积分不能为负数");
        }
        if (usedCredits == null) {
            usedCredits = 0; // 如果传null，默认不使用积分
        }

        Optional<Buyer> buyer = getBuyerById(userId);
        if (buyer.isEmpty()) {
            return Result.error(404, "买家不存在");
        }

        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return Result.error(404,"订单不存在");
        }
        Order order = orderOpt.get();

        // 计算实际价格
        BigDecimal totalPay = order.getTotalPrice();  // 获取总价格


        BigDecimal usedCreditsAmount = BigDecimal.valueOf(usedCredits).divide(BigDecimal.valueOf(100));  // 将积分转为金额
        BigDecimal actualPay = totalPay.subtract(usedCreditsAmount);  // 总价格减去积分金额

        // 支付订单
        Result<Map<String, String>> result=transferMoney(userId, order.getStoreId(), actualPay);
        if (result.getCode()!=200){
            return Result.error(result.getCode(),result.getMsg());
        }

        // 积分变化
        reduceCredit(userId,usedCredits);
        // 获取积分
        Integer addAmount=actualPay.divide(BigDecimal.valueOf(1), RoundingMode.FLOOR).intValue();  // 除以100并取整
        addCredit(userId,addAmount);

        CreditsDTO creditsDTO=new CreditsDTO();
        creditsDTO.setBonus(addAmount);

        // 修改订单状态
        order.setPaymentStatus(Order.PaymentStatus.已付款);
        order.setPaymentMethod(Order.PaymentMethod.钱包);
        order.setBonusCredits(addAmount);
        order.setPayTime(LocalDateTime.now());
        orderRepository.save(order);

        return Result.success(creditsDTO);
    }