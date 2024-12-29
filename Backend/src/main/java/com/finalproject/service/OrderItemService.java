package com.finalproject.service;
import com.finalproject.DTO.OrderItemDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.finalproject.repository.*;
import com.finalproject.service.OrderService;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    // 构造函数注入
    public OrderItemService(OrderItemRepository orderItemRepository,
                            OrderRepository orderRepository,
                            StoreRepository storeRepository, ProductRepository productRepository,
                            OrderService orderService){
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.orderService = orderService;
    }

    // 更新订单项评分评价
    @Transactional
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

    // 检查订单项是否被评价
    @Transactional
    public Result<Boolean> isOrderItemRemarked(String orderItemId) {

        Optional<OrderItem> orderItemOpt = orderItemRepository.findById(orderItemId);
        if (orderItemOpt.isEmpty()) {
            return Result.error(404, "未找到订单项");
        }

        OrderItem orderitem = orderItemOpt.get();
        Boolean isRemarked= orderitem.getRemark() != null;

        return Result.success(isRemarked);
    }


    /*
    * Optional<Order>  只能包含 0 或 1 个元素
    * List<Order>  可以包含多个元素，返回的是一个列表（即 0 或多个）
    * */
    // 获取店铺所有订单评论
    @Transactional
    public Result<List<GetStoreRemarkDTO>> getStoreRemarks(String storeId) {
        // 确认店铺存在
        Optional<Store> storeOpt = storeRepository.findById(storeId);
        if (storeOpt.isEmpty()) {
            return Result.error(404,"店铺不存在");
        }

        List<Order> orders = orderRepository.findByStoreId(storeId);
        if (orders.isEmpty()) {
            return Result.error(404,"店铺评价不存在");
        }

        List<GetStoreRemarkDTO> getStoreRemarkDTOs = new ArrayList<>();
        for (Order order : orders) {
            String orderId = order.getOrderId();
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

            for (OrderItem orderItem : orderItems) {
                if(orderItem.getScore()!=null) {
                    GetStoreRemarkDTO getStoreRemarkDTO = new GetStoreRemarkDTO();
                    getStoreRemarkDTO.setOrderId(orderId);
                    getStoreRemarkDTO.setOrderScore(orderItem.getScore());
                    getStoreRemarkDTO.setOrderRemark(orderItem.getRemark());
                    getStoreRemarkDTO.setBuyerName(order.getUsername());
                    // order关联buyer,可调用父类getPhotoId()获取头像id
                    getStoreRemarkDTO.setBuyerAvatar("http://47.97.59.189:8080/images/"+order.getBuyer().getPhotoId());
                    getStoreRemarkDTOs.add(getStoreRemarkDTO);
                }
            }
        }
        return Result.success(getStoreRemarkDTOs);
    }

    // 判断是否存在当前商品的订单项
    @Transactional
    public Result<Boolean> isExistProductOrder(String productId){
        Optional<Product>product=orderService.getProductById(productId);
        if (product.isEmpty()) {
            return Result.error(404,"商品不存在");
        }
        List<OrderItem> orderItems=orderItemRepository.findByProductId(product.get().getProductId());
        // 订单项不空
        return Result.success(!orderItems.isEmpty());
    }

    // 判断是否存在某订单
    @Transactional
    public Result<Boolean> isExistOrder(String orderId){
        Optional<Order>order=orderRepository.findById(orderId);
        if (order.isEmpty()) {
            return Result.error(404,"订单不存在");
        }
        return Result.success(true);
    }

    @Transactional
    // 退货子系统接口使用的辅助函数，管理订单项申请退货逻辑
    public Result<Map<String,String>> returnOrderItem(String user_id,String item_id){
        Result<Map<String,String>> response;
        response=this.isUserExistOrderItem(user_id,item_id);
        if(response.getCode()!=200){
            return response;
        }
        String orderId=response.getData().get("orderId");
        response=orderService.getOrderStatus(orderId);
        if(response.getCode()!=200){
            return response;
        }
        String orderStatus=response.getData().get("status");

        if(!orderStatus.equals("已完成")){
            return Result.error(400,"请先确认收货！");
        }
        response=startAfterSell(item_id);
        return response;
    }

    @Transactional
    // 退货子系统接口使用的辅助函数，管理订单项审批退货逻辑
    public Result<Map<String,String>> approveReturnOrderItem(String user_id,String item_id,
    Boolean isApprove){
        Result<Map<String,String>> response;
        response=this.isStoreExistOrderItem(user_id,item_id);
        if(isApprove||response.getCode()!=200){
            return response;
        }
        response=endAfterSell(item_id);
        return response;
    }


    // 搜索某用户是否存在一个ID为XXX的订单项
    @Transactional
    public Result<Map<String,String>> isUserExistOrderItem(String userId, String orderItemId){
        Optional<OrderItem> item =orderItemRepository.findOrderItemByUserIdAndItemId(userId,orderItemId);
        if(item.isEmpty()) {
            return Result.error(404,"订单项不存在或无权限访问");
        }
        Map<String,String> data = new HashMap<>();
        String orderId=item.get().getOrderId();
        data.put("orderId",orderId);
        return Result.success(data);
    }

    // 搜索某商家是否存在一个ID为XXX的订单项
    @Transactional
    public Result<Map<String,String>> isStoreExistOrderItem(String userId, String orderItemId){
        Optional<OrderItem> item =orderItemRepository.findOrderItemByStoreIdAndItemId(userId,orderItemId);
        if(item.isEmpty()) {
            return Result.error(404,"订单项不存在或无权限访问");
        }
        Map<String,String> data = new HashMap<>();
        String orderId=item.get().getOrderId();
        data.put("orderId",orderId);
        return Result.success(data);
    }

    // 返回订单项的状态
    @Transactional
    public Result<String> getItemStatus(String orderItemId){
        Optional<OrderItem> item =orderItemRepository.findById(orderItemId);
        if(item.isEmpty()) {
            return Result.error(404,"希望查询状态的订单项不存在！");
        }
        String status =item.get().getItemStatus().toString();
        return Result.success(status);
    }

    // 将订单项的状态设置为"售后中"
    @Transactional
    public Result<Map<String,String>> startAfterSell(String orderItemId){
        Optional<OrderItem> item =orderItemRepository.findById(orderItemId);
        if(item.isEmpty()) {
            return Result.error(404,"希望修改状态的订单项不存在！");
        }
        if(!item.get().getItemStatus().equals(OrderItem.ItemStatus.无售后)){
            return Result.error(400,"当前状态无法开启售后！");
        }
        item.get().setItemStatus(OrderItem.ItemStatus.售后中);
        orderItemRepository.save(item.get());
        Map<String,String> data = new HashMap<>();
        data.put("orderId",item.get().getOrderId());
        data.put("message","成功进入售后状态！");
        return Result.success(data);
    }

    // 将订单项的状态设置为"售后结束"
    @Transactional
    public Result<Map<String,String>> endAfterSell(String orderItemId){
        Optional<OrderItem> item =orderItemRepository.findById(orderItemId);
        if(item.isEmpty()) {
            return Result.error(404,"希望修改状态的订单项不存在！");
        }
        if(!item.get().getItemStatus().equals(OrderItem.ItemStatus.售后中)){
            return Result.error(400,"当前状态无法结束售后！");
        }
        item.get().setItemStatus(OrderItem.ItemStatus.售后结束);
        orderItemRepository.save(item.get());
        Map<String,String> data = new HashMap<>();
        data.put("orderId",item.get().getOrderId());
        data.put("message","售后结束成功！");
        return Result.success(data);
    }

    // 返回商家待处理的售后订单项
    @Transactional
    public Result<List<String>> getStoreCurrentAfterSellItem(String userId){
        Optional<List<OrderItem>> items =orderItemRepository.findAfterSalesOrderItemsByStoreId(userId);
        if(items.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        // 提取每个 OrderItem 的 id
        List<String> itemIds = items.get().stream()
                .map(OrderItem::getItemId)
                .collect(Collectors.toList());
        return Result.success(itemIds);
    }

    // 返回商家历史售后订单项
    @Transactional
    public Result<List<String>> getStoreHistoryAfterSellItem(String userId){
        Optional<List<OrderItem>> items =orderItemRepository.findHistoryAfterSalesOrderItemsByStoreId(userId);
        if(items.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        // 提取每个 OrderItem 的 id
        List<String> itemIds = items.get().stream()
                .map(OrderItem::getItemId)
                .collect(Collectors.toList());
        return Result.success(itemIds);
    }
    // 返回买家售后中订单项
    @Transactional
    public Result<List<String>> getBuyerCurrentAfterSellItem(String userId){
        Optional<List<OrderItem>> items =orderItemRepository.findAfterSalesOrderItemsByBuyerId(userId);
        if(items.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        // 提取每个 OrderItem 的 id
        List<String> itemIds = items.get().stream()
                .map(OrderItem::getItemId)
                .collect(Collectors.toList());
        return Result.success(itemIds);
    }
    // 返回买家历史售后订单项
    @Transactional
    public Result<List<String>> getBuyerHistoryAfterSellItem(String userId){
        Optional<List<OrderItem>> items =orderItemRepository.findHistoryAfterSalesOrderItemsByBuyerId(userId);
        if(items.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        // 提取每个 OrderItem 的 id
        List<String> itemIds = items.get().stream()
                .map(OrderItem::getItemId)
                .collect(Collectors.toList());
        return Result.success(itemIds);
    }

    public Result<PaymentAndIdDTO> getPaymentAndId(String returnId) {
        Optional<OrderItem> res=orderItemRepository.findByItemId(returnId);
        if(res.isEmpty()) {
            return Result.error(404,"不存在该订单项");
        }
        PaymentAndIdDTO paymentAndIdDTO = new PaymentAndIdDTO();
        paymentAndIdDTO.setOrderId(res.get().getOrderId());
        paymentAndIdDTO.setActualPay(res.get().getActualPay());
        return Result.success(paymentAndIdDTO);
    }

    public Result<BuyerShopperIdDTO> getStoreBuyerId(String orderId) {
        Optional<Order> res=orderRepository.findByOrderId(orderId);
        if(res.isEmpty()) {
            return Result.error(404,"不存在该订单");
        }
        BuyerShopperIdDTO Ids = new BuyerShopperIdDTO();
        Ids.setBuyerId(res.get().getBuyerId());
        Ids.setStoreId(res.get().getStoreId());
        return Result.success(Ids);
    }
}
