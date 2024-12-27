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
import java.time.LocalDate;
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
            order.setOrderStatus(Order.OrderStatus.处理中);
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

    // 修改收货人信息
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

    // 删除订单
    @Transactional
    public Result<String> deleteOrder(String orderId) {
        System.out.println("------------");
        System.out.println(orderId);
        List<OrderItem> orderItemList = orderItemRepository.findByOrderId(orderId);
        orderItemRepository.deleteAll(orderItemList);
        Order order = orderRepository.findById(orderId).get();
        orderRepository.delete(order);
        return Result.success("订单删除成功");
    }

    // 根据订单实体获取单个订单信息
    private OrderCenterDTO convertToOrderCenterDTO(Order order) {
        // 获取店铺信息
        Optional<Store> storeOpt = storeRepository.findById(order.getStoreId());
        Store store = storeOpt.orElseThrow(() -> new RuntimeException("店铺信息未找到"));

        // 获取买家信息
        Optional<Buyer> buyerOpt = buyerRepository.findById(order.getBuyerId());
        Buyer buyer = buyerOpt.orElseThrow(() -> new RuntimeException("买家信息未找到"));

        // 构建OrderCenterDTO
        OrderCenterDTO orderCenterDTO = new OrderCenterDTO();
        orderCenterDTO.setOrderId(order.getOrderId());
        orderCenterDTO.setUsername(buyer.getUserName());
        orderCenterDTO.setStoreName(store.getStoreName());
        orderCenterDTO.setAddress(order.getShippingAddress());
        orderCenterDTO.setCreateTime(order.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
        orderCenterDTO.setPaid(order.getPaymentStatus() != Order.PaymentStatus.等待支付);
        orderCenterDTO.setTotalPay(order.getTotalPrice());
        orderCenterDTO.setOrderStatus(order.getOrderStatus().toString());

        // 获取该订单的所有订单项信息
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getOrderId());
        for (OrderItem orderItem : orderItems) {
            Product product = productRepository.findById(orderItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("商品信息未找到"));
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setProductId(product.getProductId());
            orderItemDTO.setProductName(product.getProductName());
            orderItemDTO.setProductPrice(product.getProductPrice());

            // 获取商品图片信息
            List<ProductImage> productImages = productImageRepository.findByProductId(product.getProductId());
            if (!productImages.isEmpty()) {
                orderItemDTO.setProductImage("http://47.97.59.189:8080/images/" + productImages.get(0).getImageId());
            }

            orderItemDTOList.add(orderItemDTO);
        }

        // 设置订单项到DTO
        orderCenterDTO.setOrderItems(orderItemDTOList);
        return orderCenterDTO;
    }

    // 通用获取订单信息接口
    @Transactional
    public Result<List<OrderCenterDTO>> getAllOrders(List<Order> orders) {
        List<OrderCenterDTO> orderInfos = new ArrayList<>();
        for (Order order : orders) {
            OrderCenterDTO orderCenterDTO = convertToOrderCenterDTO(order);
            orderInfos.add(orderCenterDTO);
        }
        return Result.success(orderInfos);
    }

    // 获取单个订单信息
    @Transactional
    public Result<OrderCenterDTO> getOneOrder(String orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return Result.error(404, "订单不存在");
        }
        Order order = orderOpt.get();
        OrderCenterDTO orderCenterDTO = convertToOrderCenterDTO(order);
        return Result.success(orderCenterDTO);
    }

    // 买家获取订单信息
    @Transactional
    public Result<List<OrderCenterDTO>> getBuyersAllOrders(String userId){
        // 查找该买家的所有订单信息
        List<Order> orders = orderRepository.findByBuyerId(userId);
        if (orders == null || orders.isEmpty()) {
            return Result.error(404,"无相关用户订单");
        }
        Result<List<OrderCenterDTO>> response=getAllOrders(orders);
        return Result.success(response.getData());
    }

    // 商家获取订单信息
    @Transactional
    public Result<List<OrderCenterDTO>> getStoreAllOrders(String userId){
        // 查找该买家的所有订单信息
        List<Order> orders = orderRepository.findByStoreId(userId);
        if (orders == null || orders.isEmpty()) {
            return Result.error(404,"无相关商家订单");
        }
        Result<List<OrderCenterDTO>> response=getAllOrders(orders);
        return Result.success(response.getData());
    }

    // 买家确认收货
    @Transactional
    public Result<String> receiveOrder(String orderId) {
        System.out.println("------------");
        System.out.println(orderId);

        Optional<Order> orderopt =orderRepository.findById(orderId);
        if (orderopt.isEmpty()) {
            return Result.error(404,"订单不存在");
        }
        Order order = orderopt.get();
        order.setOrderStatus(Order.OrderStatus.已完成);
        return Result.success("确认收货成功");
    }

    // 获取商家某天的交易量和交易额
    @Transactional
    public Result<OrderStatisticsDTO> getOrderNumberByDate(String storeId,String date) {
        // 将日期字符串转换为 LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        // 获取当天的开始时间和结束时间
        LocalDateTime startDate = localDate.atStartOfDay();  // 00:00:00
        LocalDateTime endDate = localDate.plusDays(1).atStartOfDay().minusNanos(1);  // 23:59:59.999999999

        // 查询该商家在该日期范围内的所有订单
        List<Order> orders = orderRepository.findByStoreIdAndOrderTimeBetween(storeId, startDate, endDate);

        // 统计订单数量和总金额
        int orderCount = orders.size();
        BigDecimal totalAmount = orders.stream()
                .map(Order::getTotalPrice)  // 获取每个订单的总金额
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // 汇总所有订单的总金额

        // 构建并返回统计结果
        OrderStatisticsDTO statisticsDTO = new OrderStatisticsDTO();
        statisticsDTO.setOrderCount(orderCount);
        statisticsDTO.setTotalAmount(totalAmount);

        return Result.success(statisticsDTO);
    }

    // 商家发货
    @Transactional
    public Result<String> updateDeliveryNumber(String deliveryNumber,String orderId) {
        Optional<Order> orderOpt =orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return Result.error(404,"订单不存在");
        }
        Order order = orderOpt.get();
        order.setOrderStatus(Order.OrderStatus.运输中);
        order.setShippingNumber(deliveryNumber);
        orderRepository.save(order);
        return Result.success("成功更新快递单号");
    }

}
