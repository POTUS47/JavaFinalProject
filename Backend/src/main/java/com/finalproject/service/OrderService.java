package com.finalproject.service;
import com.finalproject.DTO.OrderDTOs.*;
import com.finalproject.DTO.OrderItemDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.finalproject.repository.*;
import com.finalproject.util.SnowflakeIdGenerator;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;
    private final RestTemplate restTemplate;

    @Autowired
    private ReturnRepository returnRepository;

    // 构造函数注入
    public OrderService(OrderItemRepository orderItemRepository,
                        OrderRepository orderRepository,
                        RestTemplate restTemplate){
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.snowflakeIdGenerator = new SnowflakeIdGenerator();
        this.restTemplate=restTemplate;
    }

    @Value("${api.base-url}")
    private String baseUrl;

    /*
    把所有检查用户存不存在的接口都删掉了
     */

    /////////////////////////////////////////////////////////////////////////以下是面向内部接口
    @Transactional
    public Optional<Product> getProductById(String productId) {
        String url = baseUrl + "/api/productController/product/" + productId;
        ResponseEntity<Optional<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    @Transactional
    public Integer reduceProductById(String productId) {
        String url = baseUrl + "/api/productController/product/quantity/" + productId;
        ResponseEntity<Integer> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    @Transactional
    public List<Product> getProductsByStoreId(String storeId) {
        String url = baseUrl + "/api/productController/products/" + storeId;
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        return response.getBody();
    }

    @Transactional
    public Optional<Store> getStoreById(String storeId) {
        String url = baseUrl + "/api/users/getStore/" + storeId;
        ResponseEntity<Optional<Store>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    @Transactional
    public Optional<Buyer> getBuyerById(String buyerId) {
        String url = baseUrl + "/api/users/buyer/" + buyerId;
        ResponseEntity<Optional<Buyer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    // 可以根据productId获取商品的所有图片的url!
    @Transactional
    public List<String> getProductImagesById(String productId) {
        // 获取商品的图片信息
        String url = baseUrl + "/api/productController/productImages/" + productId;
        ResponseEntity<List<ProductImage>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductImage>>() {
                });

        List<ProductImage>productImages = response.getBody();
        List<String>imageUrls=new ArrayList<>();
        if (productImages != null) {
            for (ProductImage productImage : productImages){
                String imageUrl=baseUrl+"/images/"+productImage.getImageId();
                imageUrls.add(imageUrl);
            }
        }
        if(imageUrls.isEmpty()){
            imageUrls.add(baseUrl+"/images/1");
        }
        return imageUrls;
    }

    // 买家支付订单
    @Transactional
    public Result<Map<String, String>> transferMoney(String userId, String storeId, BigDecimal amount) {
        // 获取商品的图片信息
        String url = baseUrl + "/api/users/" + userId+"/pay/"+storeId+"/"+amount;
        ResponseEntity<Result<Map<String, String>>> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<Result<Map<String, String>>>() {
                });
        return response.getBody();
    }

    // 增加积分
    @Transactional
    public Integer addCredit(String userId, Integer amount) {
        // 获取商品的图片信息
        String url = baseUrl + "/api/users/credit/add/" + userId+"/"+amount;
        ResponseEntity<Integer> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<Integer>() {
                });
        return response.getBody();
    }

    // 减少积分
    @Transactional
    public Integer reduceCredit(String userId, Integer amount) {
        // 获取商品的图片信息
        String url = baseUrl + "/api/users/credit/reduce/" + userId+"/"+amount;
        ResponseEntity<Integer> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<Integer>() {
                });
        return response.getBody();
    }


    /////////////////////////////////////////////////////////////////////////以下是面向外部接口
    @Transactional
    public Result<List<OrderRelatedDTO>> addOrders(String userId, List<String> productIds) {

        if (userId == null || userId.trim().isEmpty()) {
            return Result.error(400, "买家不能为空");
        }
        if (productIds == null || productIds.isEmpty()) {
            return Result.error(400, "商品列表不能为空");
        }
        Boolean isNull=false;
        // 查找买家信息,转为接口获取
        Optional<Buyer> buyer = getBuyerById(userId);
        if (buyer.isEmpty()) {
            return Result.error(404, "买家不存在");
        }
        String buyerAddress = buyer.get().getAddress();
        String username = buyer.get().getUserName();

        // 按照店铺分组商品
        Map<String, List<String>> storeProductMap = new HashMap<>();

        // 查找商品信息并根据店铺ID分组
        for(String productId : productIds) {
            Optional<Product> productOpt = getProductById(productId);
            if (productOpt.isEmpty()) {
                continue;
            }
            Product product=productOpt.get();
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
            Optional<Store> storeOpt = getStoreById(storeId);
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

                Optional<Product> productOpt = getProductById(productId);
                Product product = productOpt.get();

                // 创建订单项
                OrderItem orderItem = new OrderItem();
                String itemId = snowflakeIdGenerator.nextId();
                orderItem.setItemId(itemId);
                orderItem.setProductId(productId);
                orderItem.setQuantity(1);  // 假设每个商品购买数量为1,需要修改
                orderItem.setUnitPrice(product.getProductPrice());
                orderItem.setOrderId(order.getOrderId());
                orderItem.setTotalPay(product.getProductPrice());
                orderItem.setActualPay(product.getProductPrice());
                orderItem.setItemStatus(OrderItem.ItemStatus.无售后);
                orderItem.setScore(BigDecimal.ZERO);

                // 累加总价格
                totalPrice = totalPrice.add(product.getProductPrice());

                // 减少商品库存
                reduceProductById(productId);

                // 添加订单项
                orderItems.add(orderItem);

                // 创建DTO
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setProductId(productId);
                orderItemDTO.setProductName(product.getProductName());
                List<String> productImageUrls = getProductImagesById(productId);
                if (!productImageUrls.isEmpty()) {
                    orderItemDTO.setProductImage(productImageUrls.getFirst());
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

    // 支付接口，包含买家支付，商家得利，修改订单状态，买家获得积分
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

        if (usedCredits > 0) {
            BigDecimal maxCredits = totalPay.multiply(BigDecimal.valueOf(100)); // 价格对应的最大积分
            if (BigDecimal.valueOf(usedCredits).compareTo(maxCredits) > 0) {
                usedCredits = maxCredits.intValue();  // 积分超过则调整为最大积分
            }

        } else {
            // 积分为0，视同不使用积分，直接用0即可（其实这里不改也没影响，因为已经是0）
            usedCredits = 0;
        }

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

    // 根据订单实体获取单个订单信息
    @Transactional
    public OrderCenterDTO convertToOrderCenterDTO(Order order) {
        // 获取店铺信息
        Optional<Store>storeOpt=getStoreById(order.getStoreId());
        Store store = storeOpt.orElseThrow(() -> new RuntimeException("店铺信息未找到"));

        // 获取买家信息
        Optional<Buyer> buyerOpt = getBuyerById(order.getBuyerId());
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
            Optional<Product>productOpt=getProductById(orderItem.getProductId());
            if (productOpt.isEmpty()) {
                continue;
            }
            Product product = productOpt.get();
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setItemId(orderItem.getItemId());
            orderItemDTO.setProductId(product.getProductId());
            orderItemDTO.setProductName(product.getProductName());
            orderItemDTO.setProductPrice(product.getProductPrice());
            orderItemDTO.setItemStatus(orderItem.getItemStatus().toString());

            // 获取商品图片信息
            List<String> productImageUrls = getProductImagesById(product.getProductId());
            if (!productImageUrls.isEmpty()) {
                orderItemDTO.setProductImage(productImageUrls.getFirst());
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

    // 根据一组订单号获取相关订单信息
    @Transactional
    public Result<List<OrderCenterDTO>> getOrders(List<String> orderIds){
        List<OrderCenterDTO> orderInfos = new ArrayList<>();
        for (String orderId : orderIds) {
            OrderCenterDTO orderCenterDTO = getOneOrder(orderId).getData();
            orderInfos.add(orderCenterDTO);
        }
        return Result.success(orderInfos);
    }
    // 买家获取订单信息
    @Transactional
    public Result<List<OrderCenterDTO>> getBuyersAllOrders(String userId){
        // 查找该买家的所有订单信息
        List<Order> orders = orderRepository.findByBuyerId(userId);
        if (orders == null || orders.isEmpty()) {
            return Result.success("无相关用户订单", Collections.emptyList());
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
        if (orderId == null || orderId.trim().isEmpty()) {
            return Result.error(400, "订单不能为空");
        }

        Optional<Order> orderopt = orderRepository.findById(orderId);
        if (orderopt.isEmpty()) {
            return Result.error(404, "订单不存在");
        }


        Order order = orderopt.get();
        if(order.getOrderStatus()==Order.OrderStatus.已完成){
            return Result.success(400, "订单已完成，不能重复确认收货");}
        else{
        order.setOrderStatus(Order.OrderStatus.已完成);
        return Result.success(200,"确认收货成功");}
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

    private int getPendingReturnsCount(String storeId){
        List<String> orderIds = orderRepository.findOrderIdsByStoreId(storeId);

        List<String> itemIds = orderItemRepository.findItemIdsByOrderIds(orderIds);

        return returnRepository.countPendingReturnsByItemIds(itemIds);
    }


    public Result<StateDTO> getState(String userId) {
        int shipment=orderRepository.getWaitingShip(userId);
        int waitReturn =getPendingReturnsCount(userId);
        int todayOrder =orderRepository.countOrdersByToday();
        BigDecimal revenue = orderRepository.getTodayRevenue();
        if(revenue==null){
            revenue=BigDecimal.ZERO;
        }

        StateDTO stateDTO = new StateDTO();
        stateDTO.setWaitingForShipment(shipment);
        stateDTO.setWaitingForReturn(waitReturn);
        stateDTO.setOrderCount(todayOrder);
        stateDTO.setTotalAmount(revenue);
        return Result.success(stateDTO);

    }

    public Result<List<Integer>> getOrdersPerDayInLastSevenDays() {
        // 获取今天的日期
        LocalDate today = LocalDate.now();

        // 计算七天前的日期
        LocalDate sevenDaysAgo = today.minusDays(6);

        // 存储结果的 List
        List<Integer> orderCounts = new ArrayList<>();

        // 循环查询每一天的订单数量
        for (int i = 0; i < 7; i++) {
            LocalDate date = sevenDaysAgo.plusDays(i);
            int count = orderRepository.countOrdersByDate(date);
            orderCounts.add(count);
        }

        return Result.success(orderCounts);
    }

    public Result<NameAndScore> updateStoreRating(String storeId) {
        List<String> orderIds = orderRepository.findOrderIdsByStoreId(storeId);

        if (orderIds.isEmpty()) {
            return Result.error(404,"店铺暂无评分");
        }

        List<BigDecimal> scores = orderItemRepository.findScoresByOrderIds(orderIds);

        if (scores.isEmpty()) {
            return Result.error(404,"店铺暂无评分");
        }

        BigDecimal totalScore = BigDecimal.ZERO;
        for (BigDecimal score : scores) {
            totalScore = totalScore.add(score);
        }

        BigDecimal averageScore = totalScore.divide(new BigDecimal(scores.size()), 2, RoundingMode.HALF_UP);
        BigDecimal formattedScore = averageScore.setScale(1, RoundingMode.HALF_UP);
        String result = formattedScore.toString();
        String storeName=updateScoreSubSys(storeId,formattedScore);
        NameAndScore res=new NameAndScore();
        res.setStoreName(storeName);
        res.setStoreScore(result);
        return Result.success(res);

    }

    public String updateScoreSubSys(String storeId,BigDecimal score){
        String url=baseUrl+"/api/users/UpdateStoreScore/"+ storeId +"/"+ score;
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<String>() {
                });
        return response.getBody();
    }

    @Transactional
    public Result<String> getOrderIdByItemId(String itemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findByItemId(itemId);
        if (orderItem.isEmpty()) {
            return Result.error(404, "订单项不存在");
        }
        return Result.success(orderItem.get().getOrderId());
    }

}
