package com.finalproject.service;

import com.finalproject.DTO.OrderItemDTOs;
import com.finalproject.model.Order;
import com.finalproject.model.Product;
import com.finalproject.repository.OrderItemRepository;
import com.finalproject.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Nested;
import com.finalproject.util.TestResultExporter;


class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderService orderService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==============================================
    // 嵌套类：测试addOrderItem方法
    // ==============================================
    @Nested
    class AddOrderItemTests {

        // 创建有效的DTO基础对象
        private OrderItemDTOs.AddOrderItemDTO createBaseValidDTO() {
            OrderItemDTOs.AddOrderItemDTO dto = new OrderItemDTOs.AddOrderItemDTO();
            dto.setOrderId("529238668013568");
            dto.setProductId("p529235889053696");
            dto.setItemId("529238668238848");
            dto.setUnitPrice(new BigDecimal("9.99"));
            dto.setTotalPay(new BigDecimal("9.99"));
            dto.setActualPay(new BigDecimal("9.99"));
            dto.setQuantity(1);
            return dto;
        }

        @Test
        @DisplayName("AOI-016 添加订单项成功")
        void whenValidInput_thenSuccess() {
            // 准备测试数据
            OrderItemDTOs.AddOrderItemDTO dto = new OrderItemDTOs.AddOrderItemDTO();
            dto.setOrderId("529238668013568");
            dto.setProductId("p529235889053696");
            dto.setItemId("529238668238859");
            dto.setUnitPrice(new BigDecimal("9.99"));
            dto.setTotalPay(new BigDecimal("9.99"));
            dto.setActualPay(new BigDecimal("9.99"));
            dto.setQuantity(1);
            Order mockOrder = new Order();
            Product mockProduct = new Product();

            // 模拟依赖行为
            when(orderRepository.findById("529238668013568")).thenReturn(Optional.of(mockOrder));
            when(orderService.getProductById("p529235889053696")).thenReturn(Optional.of(mockProduct));
            when(orderItemRepository.findById("529238668238859")).thenReturn(Optional.empty());
            when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> {
                return invocation.<OrderItem>getArgument(0);
            });

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);
            // 记录测试结果到CSV
            TestResultExporter.exportResult(
                    "AOI-016",
                    result.isSuccess(),
                    result.getMessage() + " | 返回数据: " + result.getData()
            );

            // 验证结果
            assertTrue(result.isSuccess());
            assertEquals("订单项添加成功", result.getMessage());
            assertEquals("529238668238859", result.getData());

            // 验证交互
            verify(orderRepository).findById("529238668013568");
            verify(orderService).getProductById("p529235889053696");
            verify(orderItemRepository).findById("529238668238859");
            verify(orderItemRepository).save(any(OrderItem.class));
        }


        @Test
        @DisplayName("AOI-001: 添加订单项时orderId为空")
        void addOrderItem_WhenOrderIdIsEmpty_ShouldReturnError() {
            // 准备测试数据
            OrderItemDTOs.AddOrderItemDTO dto = createBaseValidDTO();
            dto.setOrderId(""); // 设置orderId为空

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("orderId不能为空", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }

        // ==============================================
        // 在这里添加更多addOrderItem的测试用例
        // 命名模式：when[条件]_then[预期结果]
        // 例如：
        // @Test
        // void whenOrderNotExists_thenReturnError() {...}
        //
        // @Test
        // void whenProductNotExists_thenReturnError() {...}
        //
        // @Test
        // void whenItemAlreadyExists_thenReturnError() {...}
        // ==============================================
    }

    // ==============================================
    // 在这里添加其他功能的嵌套测试类
    // 命名模式：[MethodName]Tests
    // 例如：
    // @Nested
    // class UpdateOrderItemTests {...}
    //
    // @Nested
    // class DeleteOrderItemTests {...}
    //
    // @Nested
    // class GetOrderItemTests {...}
    // ==============================================
}