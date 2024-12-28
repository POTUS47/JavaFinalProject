package com.finalproject.service;
import com.finalproject.DTO.OrderItemDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.finalproject.repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    // 构造函数注入
    public OrderItemService(OrderItemRepository orderItemRepository,
                            OrderRepository orderRepository,
                            StoreRepository storeRepository, ProductRepository productRepository){
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
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
        Optional<Product>product=productRepository.findById(productId);
        if (product.isEmpty()) {
            return Result.error(404,"商品不存在");
        }
        List<OrderItem> orderItems=orderItemRepository.findByProductId(product.get().getProductId());
        // 订单项不空
        return Result.success(!orderItems.isEmpty());
    }

}
