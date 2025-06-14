public Result<String> getItemStatus(String orderItemId){
    Optional<OrderItem> item =orderItemRepository.findById(orderItemId);
    if(item.isEmpty()) {
        return Result.error(404,"希望查询状态的订单项不存在！");
    }
    String status =item.get().getItemStatus().toString();
    return Result.success(status);
}