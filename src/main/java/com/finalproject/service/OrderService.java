package com.finalproject.service;
import com.finalproject.DTO.OrderDTOs.*;
import com.finalproject.DTO.OrderItemDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.finalproject.repository.*;
import com.finalproject.util.SnowflakeIdGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;


@Service
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final BuyerRepository buyerRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    // 构造函数注入
    public OrderService(OrderItemRepository orderItemRepository,
                        OrderRepository orderRepository,
                        StoreRepository storeRepository,
                        ProductRepository productRepository,
                        ProductImageRepository productImageRepository,
                        BuyerRepository buyerRepository){
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.buyerRepository = buyerRepository;
        this.snowflakeIdGenerator = new SnowflakeIdGenerator();
    }


    // 当前是一种商品只有一个，所以暂时没有处理数量问题
    @Transactional
    public Result<List<OrderRelatedDTO>> addOrders(String userId, List<String> productIds) {

        Boolean isNull=false;
        // 查找买家信息,转为接口获取
        Optional<Buyer> buyer = buyerRepository.findById(userId);
        if (buyer.isEmpty()) {
            return Result.error(404, "买家不存在");
        }
        String buyerAddress = buyer.get().getAddress();
        String username = buyer.get().getUserName();

        // 按照店铺分组商品
        Map<String, List<String>> storeProductMap = new HashMap<>();

        // 查找商品信息并根据店铺ID分组,需要转为接口
        List<Product> products = productRepository.findAllById(productIds);
        for (Product product : products) {
            // 有库存才能买啊
            if (product.getQuantity() > 0) {
                String storeId = product.getStoreId();
                storeProductMap.computeIfAbsent(storeId, k -> new ArrayList<>()).add(product.getProductId());
            }else{
                isNull=true;
            }
        }

        List<OrderRelatedDTO> orderRelatedDTOList = new ArrayList<>();

        // 对每个店铺生成一个订单
        for (Map.Entry<String, List<String>> entry : storeProductMap.entrySet()) {
            String storeId = entry.getKey();
            List<String> productIdList = entry.getValue();

            // 获取店铺信息
            Optional<Store> storeOpt = storeRepository.findById(storeId);
            if (storeOpt.isEmpty()) {
                return Result.error(404, "店铺不存在");
            }
            Store store = storeOpt.get();

            // 生成订单ID
            String orderId = snowflakeIdGenerator.nextId();

            // 计算订单总价格
            BigDecimal totalPrice = BigDecimal.ZERO;//初始化为0
            List<OrderItem> orderItems = new ArrayList<>();

            // 创建订单
            Order order = new Order();
            order.setOrderId(orderId);
            order.setBuyerId(userId);
            order.setStoreId(storeId);
            order.setShippingAddress(buyerAddress);
            order.setBillingAddress(store.getAddress());
            order.setOrderTime(LocalDateTime.now());
            order.setOrderStatus(Order.OrderStatus.等待确认);
            order.setPaymentStatus(Order.PaymentStatus.等待支付);
            order.setUsername(username);

            List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

            // 查找对应店铺的每个商品并添加到订单项
            for (String productId : productIdList) {

                Optional<Product> productOpt = productRepository.findById(productId);
                Product product = productOpt.get();

                // 创建订单项
                OrderItem orderItem = new OrderItem();
                String itemId = snowflakeIdGenerator.nextId();
                orderItem.setItemId(itemId);
                orderItem.setProductId(productId);
                orderItem.setQuantity(1);  // 假设每个商品购买数量为1,需要修改
                orderItem.setUnitPrice(product.getProductPrice());
                orderItem.setOrderId(order.getOrderId());

                // 累加总价格
                totalPrice = totalPrice.add(product.getProductPrice());

                // 减少商品库存
                product.setQuantity(product.getQuantity() - 1);
                productRepository.save(product);

                // 添加订单项
                orderItems.add(orderItem);

                // 创建DTO
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setProductId(productId);
                orderItemDTO.setProductName(product.getProductName());
                List<ProductImage> productImages = productImageRepository.findByProductId(product.getProductId());
                if (!productImages.isEmpty()) {
                    orderItemDTO.setProductImage("http://47.97.59.189:8080/images/" + productImages.getFirst().getImageId());
                }
                orderItemDTO.setProductPrice(product.getProductPrice());
                orderItemDTOList.add(orderItemDTO);

            }
            // 保存订单和订单项（注意顺序，因为有外码约束）
            order.setTotalPrice(totalPrice);
            orderRepository.save(order);
            orderItemRepository.saveAll(orderItems);

            // 构建返回的DTO
            OrderRelatedDTO orderRelatedDTO = new OrderRelatedDTO();
            orderRelatedDTO.setOrderId(orderId);
            orderRelatedDTO.setUsername(order.getUsername());
            orderRelatedDTO.setAddress(order.getShippingAddress());
            orderRelatedDTO.setCreateTime(order.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
            orderRelatedDTO.setPaid(order.getPaymentStatus() != Order.PaymentStatus.等待支付);
            orderRelatedDTO.setOrderItems(orderItemDTOList);

            orderRelatedDTOList.add(orderRelatedDTO);
        }

        return Result.success(200, isNull ?"存在商品缺货，请注意查收":"订单生成成功",orderRelatedDTOList);
    }

    @Transactional
    public Result<String> changeNameAndAddress(ChangeNameAndAddressDTO changeNameAndAddressDTO) {
        String orderId=changeNameAndAddressDTO.getOrderId();
        String name=changeNameAndAddressDTO.getName();
        String address=changeNameAndAddressDTO.getAddress();

        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return Result.error(404,"订单不存在");
        }

        Order order = orderOpt.get();
        order.setShippingAddress(address);
        order.setUsername(name);
        orderRepository.save(order);
        return  Result.success(200,"修改成功");
    }

    // 查询订单的状态
    @Transactional
    public Result<Map<String,String>> getOrderStatus(String orderId){
        Optional<Order> order =orderRepository.findById(orderId);
        if(order.isEmpty()) {
            return Result.error(404,"希望查询状态的订单不存在！");
        }
        String status =order.get().getOrderStatus().toString();
        Map<String,String> map = new HashMap<>();
        map.put("status",status);
        return Result.success(map);
    }

}
