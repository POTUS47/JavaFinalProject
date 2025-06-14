package com.finalproject.service;


import com.finalproject.DTO.OrderDTOs.OrderRelatedDTO;
import com.finalproject.DTO.OrderDTOs.CreditsDTO;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import org.mockito.ArgumentMatchers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.finalproject.repository.OrderItemRepository;
import com.finalproject.repository.OrderRepository;
import com.finalproject.util.SnowflakeIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 宽泛地 Mock restTemplate，避免空指针，按 userId 返回 Buyer 或空
        when(restTemplate.getForEntity(anyString(), eq(Buyer.class)))
                .thenAnswer(invocation -> {
                    String url = invocation.getArgument(0);
                    if (url.contains("user123")) {
                        return ResponseEntity.of(Optional.of(createMockBuyer("user123")));
                    } else {
                        return ResponseEntity.of(Optional.empty());
                    }
                });

    }

    private Buyer createMockBuyer(String userId) {
        Buyer buyer = new Buyer();
        buyer.setAccountId(userId);
        buyer.setUserName("buyerName");
        buyer.setAddress("buyer address");
        return buyer;
    }

    private Product createMockProduct(String productId, String storeId, int quantity, BigDecimal price, String name) {
        Product product = new Product();
        product.setProductId(productId);
        product.setStoreId(storeId);
        product.setQuantity(quantity);
        product.setProductPrice(price);
        product.setProductName(name);
        return product;
    }

    private Store createMockStore(String storeId) {
        Store store = new Store();
        store.setAccountId(storeId);
        store.setAddress("store address");
        return store;
    }


    @Nested
    @DisplayName("addOrders 方法测试")
    class AddOrdersTests {
        @Test
        @DisplayName("TC_ADDORDER01: 单个商品下单成功")
        void addOrders_SingleProduct_Success() {
            String userId = "user123";
            List<String> productIds = List.of("prod001");

            Buyer buyer = createMockBuyer(userId);
            Product product = createMockProduct("prod001", "store001", 10, new BigDecimal("100"), "Product 1");
            Store store = createMockStore("store001");

            OrderService spyService = spy(orderService);

            doReturn(Optional.of(buyer)).when(spyService).getBuyerById(userId);
            doReturn(Optional.of(product)).when(spyService).getProductById("prod001");
            doReturn(Optional.of(store)).when(spyService).getStoreById("store001");

            // 这里模拟雪花ID生成的是数字字符串
            when(snowflakeIdGenerator.nextId())
                    .thenReturn("123456789012345678") // 订单ID
                    .thenReturn("987654321098765432"); // 订单项ID

            doReturn(null).when(spyService).reduceProductById("prod001");
            doReturn(List.of("img1")).when(spyService).getProductImagesById("prod001");

            Result<List<OrderRelatedDTO>> result = spyService.addOrders(userId, productIds);

            assertTrue(result.isSuccess());
            assertEquals(200, result.getCode());
            assertEquals("订单生成成功", result.getMessage());
            assertEquals(1, result.getData().size());

            String orderId = result.getData().get(0).getOrderId();
            assertNotNull(orderId);
            assertTrue(orderId.matches("\\d+"), "订单ID应该是纯数字字符串");

            verify(orderRepository, times(1)).save(any(Order.class));
            verify(orderItemRepository, times(1)).saveAll(anyList());
        }


        @Test
        @DisplayName("TC_ADDORDER02: 多个商品下单成功")
        void addOrders_MultipleProducts_Success() {
            String userId = "user123";
            List<String> productIds = List.of("prod001", "prod002");

            Buyer buyer = createMockBuyer(userId);
            Product product1 = createMockProduct("prod001", "store001", 10, new BigDecimal("100"), "Product 1");
            Product product2 = createMockProduct("prod002", "store001", 5, new BigDecimal("200"), "Product 2");
            Store store = createMockStore("store001");

            OrderService spyService = spy(orderService);

            doReturn(Optional.of(buyer)).when(spyService).getBuyerById(userId);
            doReturn(Optional.of(product1)).when(spyService).getProductById("prod001");
            doReturn(Optional.of(product2)).when(spyService).getProductById("prod002");
            doReturn(Optional.of(store)).when(spyService).getStoreById("store001");

            when(snowflakeIdGenerator.nextId())
                    .thenReturn("123456789012345679") // 订单ID
                    .thenReturn("987654321098765433") // 第一个订单项ID
                    .thenReturn("987654321098765434"); // 第二个订单项ID

            doReturn(null).when(spyService).reduceProductById(anyString());
            doReturn(List.of("img1")).when(spyService).getProductImagesById("prod001");
            doReturn(List.of("img2")).when(spyService).getProductImagesById("prod002");

            Result<List<OrderRelatedDTO>> result = spyService.addOrders(userId, productIds);

            assertTrue(result.isSuccess());
            assertEquals(200, result.getCode());
            assertEquals(1, result.getData().size());
            assertEquals(2, result.getData().get(0).getOrderItems().size());

            String orderId = result.getData().get(0).getOrderId();
            assertNotNull(orderId);
            assertTrue(orderId.matches("\\d+"), "订单ID应该是纯数字字符串");

            verify(orderRepository, times(1)).save(any(Order.class));
            verify(orderItemRepository, times(1)).saveAll(anyList());
        }

        @Test
        @DisplayName("TC_ADDORDER03: 商品列表为空")
        void addOrders_EmptyProductList_Fail() {
            String userId = "user123";
            List<String> productIds = List.of();

            OrderService spyService = spy(orderService);

            // 对于这个用例，getBuyerById 可返回正常buyer，重点是商品列表为空
            doReturn(Optional.of(createMockBuyer(userId))).when(spyService).getBuyerById(userId);

            Result<List<OrderRelatedDTO>> result = spyService.addOrders(userId, productIds);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("商品列表不能为空", result.getMessage());
            verify(orderRepository, never()).save(any());
            verify(orderItemRepository, never()).saveAll(any());
        }

        @Test
        @DisplayName("TC_ADDORDER04: 用户不存在")
        void addOrders_UserNotFound_Fail() {
            String userId = "unknown_user";
            List<String> productIds = List.of("prod001");

            OrderService spyService = spy(orderService);

            // 模拟找不到用户
            doReturn(Optional.empty()).when(spyService).getBuyerById(userId);

            Result<List<OrderRelatedDTO>> result = spyService.addOrders(userId, productIds);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("买家不存在", result.getMessage());
            verify(orderRepository, never()).save(any());
            verify(orderItemRepository, never()).saveAll(any());
        }

        @Test
        @DisplayName("TC_ADDORDER05: 用户 ID 为空")
        void addOrders_UserIdEmpty_Fail() {
            String userId = "";
            List<String> productIds = List.of("prod001");

            OrderService spyService = spy(orderService);

            // 用户ID为空，直接返回失败，不查 buyer，模拟 getBuyerById 返回空或不调用都可以
            doReturn(Optional.empty()).when(spyService).getBuyerById(userId);

            Result<List<OrderRelatedDTO>> result = spyService.addOrders(userId, productIds);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("买家不能为空", result.getMessage());
            verify(orderRepository, never()).save(any());
            verify(orderItemRepository, never()).saveAll(any());
        }
    }

    @Nested
    @DisplayName("deleteOrder 方法测试")
    class DeleteOrderTests {

        @Test
        @DisplayName("TC_DELETEORDER01: 删除未支付订单")
        void deleteOrder_UnpaidOrder_Success() {
            String orderId = "unpaid_order123";

            Order unpaidOrder = new Order();
            unpaidOrder.setOrderId(orderId);
            unpaidOrder.setOrderStatus(Order.OrderStatus.处理中);
            unpaidOrder.setPaymentStatus(Order.PaymentStatus.等待支付);

            OrderService spyService = spy(orderService);

            doReturn(Optional.of(unpaidOrder)).when(orderRepository).findById(orderId);
            when(orderItemRepository.findByOrderId(orderId)).thenReturn(List.of());

            Result<String> result = spyService.deleteOrder(orderId);

            assertTrue(result.isSuccess());
            assertEquals(200, result.getCode());
            assertEquals("订单删除成功", result.getMessage());

            verify(orderItemRepository).deleteAll(anyList());
            verify(orderRepository).delete(unpaidOrder);
        }

        @Test
        @DisplayName("TC_DELETEORDER02: 删除已支付订单")
        void deleteOrder_PaidOrder_Success() {
            String orderId = "paid_order123";

            Order paidOrder = new Order();
            paidOrder.setOrderId(orderId);
            paidOrder.setOrderStatus(Order.OrderStatus.处理中); // 也可能是处理中，但支付状态不同
            paidOrder.setPaymentStatus(Order.PaymentStatus.已付款);

            OrderService spyService = spy(orderService);

            doReturn(Optional.of(paidOrder)).when(orderRepository).findById(orderId);
            when(orderItemRepository.findByOrderId(orderId)).thenReturn(List.of());

            Result<String> result = spyService.deleteOrder(orderId);

            assertTrue(result.isSuccess());
            assertEquals(200, result.getCode());
            assertEquals("订单删除成功", result.getMessage());

            verify(orderItemRepository).deleteAll(anyList());
            verify(orderRepository).delete(paidOrder);
        }

        @Test
        @DisplayName("TC_DELETEORDER03: 删除不存在的订单")
        void deleteOrder_OrderNotFound_Fail() {
            String orderId = "nonexistent_order";

            OrderService spyService = spy(orderService);
            doReturn(Optional.empty()).when(orderRepository).findById(orderId);

            Result<String> result = spyService.deleteOrder(orderId);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("订单不存在", result.getMessage());

            verify(orderRepository, never()).delete(any());
            verify(orderItemRepository, never()).deleteAll(anyList());
        }

        @Test
        @DisplayName("TC_DELETEORDER04: 订单id为空")
        void deleteOrder_EmptyOrderId_Fail() {
            String orderId = "";

            OrderService spyService = spy(orderService);

            Result<String> result = spyService.deleteOrder(orderId);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("订单id不能为空", result.getMessage());

            verify(orderRepository, never()).findById(anyString());
            verify(orderRepository, never()).delete(any());
            verify(orderItemRepository, never()).deleteAll(anyList());
        }
    }

    @Nested
    @DisplayName("payOrder 方法测试")
    class PayOrderTests {

        private OrderService spyOrderService;

        private Order createMockOrder(String orderId, String storeId, BigDecimal totalPrice) {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setStoreId(storeId);
            order.setTotalPrice(totalPrice);
            return order;
        }

        private Result<Map<String, String>> createSuccessTransferResult() {
            return Result.success(Map.of("status", "success"));
        }

        @BeforeEach
        void setup() {
            // 先初始化 spyOrderService
            spyOrderService = spy(orderService);

            // mock 内部调用
            doReturn(createSuccessTransferResult()).when(spyOrderService)
                    .transferMoney(anyString(), anyString(), any(BigDecimal.class));
            doReturn(1).when(spyOrderService).reduceCredit(anyString(), anyInt());
            doReturn(1).when(spyOrderService).addCredit(anyString(), anyInt());

            // 单独 mock restTemplate.exchange，避免空指针异常
            when(restTemplate.exchange(
                    contains("/api/users/buyer/"),
                    eq(HttpMethod.GET),
                    ArgumentMatchers.<HttpEntity<?>>isNull(),
                    ArgumentMatchers.<ParameterizedTypeReference<Optional<Buyer>>>any()
            )).thenAnswer(invocation -> {
                String url = invocation.getArgument(0, String.class);
                if (url.contains("user123")) {
                    Optional<Buyer> buyer = Optional.of(createMockBuyer("user123"));
                    return ResponseEntity.ok(buyer);
                } else {
                    return ResponseEntity.ok(Optional.empty());
                }
            });
        }

        @Test
        @DisplayName("TC_PAYORDER01: 用户支付订单时不使用积分")
        void payOrder_NoCredits_Success() {
            String userId = "user123";
            String orderId = "order123";

            doReturn(Optional.of(createMockBuyer(userId)))
                    .when(spyOrderService).getBuyerById(userId);

            Order order = createMockOrder(orderId, "store001", new BigDecimal("100"));
            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

            Result<CreditsDTO> result = spyOrderService.payOrder(userId, orderId, 0);

            assertTrue(result.isSuccess());
            assertEquals(100, result.getData().getBonus());
        }


        @Test
        @DisplayName("TC_PAYORDER02: 使用小于订单金额的积分")
        void payOrder_WithPartialCredits_Success() {
            String userId = "user123";
            String orderId = "order123";

            Order order = createMockOrder(orderId, "store001", new BigDecimal("100"));
            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

            Result<CreditsDTO> result = spyOrderService.payOrder(userId, orderId, 50);

            assertTrue(result.isSuccess());
            assertEquals(99, result.getData().getBonus()); // 100 - 0.5 -> 99.5 -> floor 99
        }

        @Test
        @DisplayName("TC_PAYORDER03: 使用超过订单金额的积分")
        void payOrder_ExcessiveCredits_Success() {
            String userId = "user123";
            String orderId = "order123";

            Order order = createMockOrder(orderId, "store001", new BigDecimal("10"));
            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

            Result<CreditsDTO> result = spyOrderService.payOrder(userId, orderId, 1500); // 15元 > 10元

            assertTrue(result.isSuccess());
            assertEquals(0, result.getData().getBonus());
        }

        @Test
        @DisplayName("TC_PAYORDER04: 用户id不存在")
        void payOrder_NonexistentUser_Fail() {
            // 这里也要用 spyOrderService stub transferMoney 返回错误
            doReturn(Result.error(404, "买家不存在")).when(spyOrderService).transferMoney(any(), any(), any());

            Order order = createMockOrder("order123", "store001", new BigDecimal("100"));
            when(orderRepository.findById("order123")).thenReturn(Optional.of(order));

            Result<CreditsDTO> result = spyOrderService.payOrder("nonexistent_user", "order123", 0);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("买家不存在", result.getMsg());
        }

        @Test
        @DisplayName("TC_PAYORDER05: 订单id不存在")
        void payOrder_OrderNotFound_Fail() {
            when(orderRepository.findById("nonexistent_order")).thenReturn(Optional.empty());

            Result<CreditsDTO> result = spyOrderService.payOrder("user123", "nonexistent_order", 0);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("订单不存在", result.getMsg());
        }

        @Test
        @DisplayName("TC_PAYORDER06: 积分为负数")
        void payOrder_NegativeCredits_Fail() {
            Result<CreditsDTO> result = spyOrderService.payOrder("user123", "order123", -50);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("积分不能为负数", result.getMsg());
        }

        @Test
        @DisplayName("TC_PAYORDER07: 用户id为空")
        void payOrder_EmptyUserId_Fail() {
            Result<CreditsDTO> result = spyOrderService.payOrder("", "order123", 0);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("用户不能为空", result.getMsg());
        }

        @Test
        @DisplayName("TC_PAYORDER08: 订单id为空")
        void payOrder_EmptyOrderId_Fail() {
            Result<CreditsDTO> result = spyOrderService.payOrder("user123", "", 0);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("订单不能为空", result.getMsg());
        }

        @Test
        @DisplayName("TC_PAYORDER09: 积分为空")
        void payOrder_NullCredits_Success() {
            String userId = "user123";
            String orderId = "order123";

            Order order = createMockOrder(orderId, "store001", new BigDecimal("100"));
            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

            Result<CreditsDTO> result = spyOrderService.payOrder(userId, orderId, null);

            assertTrue(result.isSuccess());
            assertEquals(100, result.getData().getBonus());
        }

        @Test
        @DisplayName("TC_PAYORDER10: 使用全部订单金额的积分")
        void payOrder_UseAllCredits_Success() {
            String userId = "user123";
            String orderId = "order123";

            Order order = createMockOrder(orderId, "store001", new BigDecimal("10"));
            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

            Result<CreditsDTO> result = spyOrderService.payOrder(userId, orderId, 1000);

            assertTrue(result.isSuccess());
            assertEquals(0, result.getData().getBonus());
        }
    }

    @Nested
    @DisplayName("receiveOrder 方法测试")
    class ReceiveOrderTests {

        @Test
        @DisplayName("TC_RECIEVEORDER01: 确认收货已发货的订单")
        void receiveOrder_ShippedOrder_Success() {
            String orderId = "shipped_order123";
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderStatus(Order.OrderStatus.运输中);

            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

            Result<String> result = orderService.receiveOrder(orderId);

            assertTrue(result.isSuccess());
            assertEquals(200, result.getCode());
            assertEquals("确认收货成功", result.getMessage());
            assertEquals(Order.OrderStatus.已完成, order.getOrderStatus());
        }

        @Test
        @DisplayName("TC_RECIEVEORDER02: 确认收货不存在的订单")
        void receiveOrder_OrderNotFound_Fail() {
            String orderId = "nonexistent_order";

            when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

            Result<String> result = orderService.receiveOrder(orderId);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("订单不存在", result.getMessage());
        }

        @Test
        @DisplayName("TC_RECIEVEORDER03: 订单 ID 为空")
        void receiveOrder_OrderIdEmpty_Fail() {
            String orderId = "";

            Result<String> result = orderService.receiveOrder(orderId);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("订单不能为空", result.getMessage());
        }

        @Test
        @DisplayName("TC_RECIEVEORDER04: 收货一个已完成的订单")
        void receiveOrder_AlreadyCompletedOrder_Fail() {
            String orderId = "completed_order123";
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderStatus(Order.OrderStatus.已完成);

            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

            Result<String> result = orderService.receiveOrder(orderId);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("订单已完成，不能重复确认收货", result.getMessage());
        }
    }




}