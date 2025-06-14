package com.finalproject.service;


import com.finalproject.DTO.OrderDTOs.OrderRelatedDTO;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
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
}