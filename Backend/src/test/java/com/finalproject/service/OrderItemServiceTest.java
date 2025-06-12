package com.finalproject.service;

import com.finalproject.DTO.OrderItemDTOs;
import com.finalproject.DTO.OrderItemDTOs.AddOrderItemDTO;
import com.finalproject.DTO.OrderItemDTOs.UpdateOrderItemRemarkDTO;
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
            dto.setScore(new BigDecimal("4"));
            return dto;
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


        @Test
        @DisplayName("AOI-002: 添加订单项时productId为空")
        void addOrderItem_WhenProductIdIsEmpty_ShouldReturnError() {
            // 准备测试数据
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setProductId(""); // 设置productId为空

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("productId不能为空", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }

        // AOI-003: itemId为空
        @Test
        @DisplayName("AOI-003: 添加订单项时itemId为空")
        void addOrderItem_WhenItemIdIsEmpty_ShouldReturnError() {
            // 准备测试数据
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setItemId(""); // 设置itemId为空

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("itemId不能为空", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }

        @Test
        @DisplayName("AOI-004: 添加订单项时，unitPrice小于范围")
        void addOrderItem_WhenUnitPriceIsLackOfRange_ShouldReturnError() {
            // 准备测试数据
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setUnitPrice(new BigDecimal(-1));

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("unitPrice只能在0-99999999之间", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }

        @Test
        @DisplayName("AOI-005: 添加订单项时，unitPrice小于范围")
        void addOrderItem_WhenUnitPriceIsOutOfRange_ShouldReturnError() {
            // 准备测试数据
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setUnitPrice(new BigDecimal(100000000));

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("unitPrice只能在0-99999999之间", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }

        @Test
        @DisplayName("AOI-006: 添加订单项时，totalPay与实际计算不符")
        void addOrderItem_WhenTotalPayMismatch_ShouldReturnError() {
            // 准备测试数据
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setUnitPrice(new BigDecimal("9.99"));
            dto.setQuantity(1);
            dto.setTotalPay(new BigDecimal("12")); // 故意设置错误的总价(9.99*1=9.99≠12)

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("totalPay与实际计算不符", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }

        @Test
        @DisplayName("AOI-007: 添加订单项时，quantity小于范围")
        void addOrderItem_WhenQuantityLackOfRange_ShouldReturnError() {
            // 准备测试数据
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setQuantity(0); // 设置超出范围的quantity值(0不在1-9999之间)

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("quantity只能在1-9999之间", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }

        @Test
        @DisplayName("AOI-008: 添加订单项时，quantity小于范围")
        void addOrderItem_WhenQuantityOutOfRange_ShouldReturnError() {
            // 准备测试数据
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setQuantity(10000); // 设置超出范围的quantity值(0不在1-9999之间)

            // 执行测试
            Result<String> result = orderItemService.addOrderItem(dto);

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("quantity只能在1-9999之间", result.getMessage());

            // 验证没有调用任何repository方法
            verifyNoInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository);
            verifyNoInteractions(orderService);
        }


        @Test
        @DisplayName("AOI-009: 添加订单项时，actualPay为负数")
        void addOrderItem_WhenActualPayIsNegative_ShouldReturnError() {
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setActualPay(new BigDecimal("-1")); // 设置负数的actualPay

            Result<String> result = orderItemService.addOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("actualPay只能在0-99999999之间", result.getMessage());
            verifyNoInteractions(orderRepository, orderItemRepository, orderService);
        }


        @Test
        @DisplayName("AOI-010: 添加订单项时，actualPay超过最大值")
        void addOrderItem_WhenActualPayExceedsMax_ShouldReturnError() {
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setActualPay(new BigDecimal("100000000")); // 超过最大值

            Result<String> result = orderItemService.addOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("actualPay只能在0-99999999之间", result.getMessage());
            verifyNoInteractions(orderRepository, orderItemRepository, orderService);
        }


        @Test
        @DisplayName("AOI-011: 添加订单项时，评分为负数")
        void addOrderItem_WhenScoreIsNegative_ShouldReturnError() {
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setScore(new BigDecimal("-0.1")); // 设置负分

            Result<String> result = orderItemService.addOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("评分只能在0-5之间", result.getMessage());
            verifyNoInteractions(orderRepository, orderItemRepository, orderService);
        }

        @Test
        @DisplayName("AOI-012: 添加订单项时，评分超过5分")
        void addOrderItem_WhenScoreExceedsMax_ShouldReturnError() {
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setScore(new BigDecimal("5.1")); // 超过5分

            Result<String> result = orderItemService.addOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(400, result.getCode());
            assertEquals("评分只能在0-5之间", result.getMessage());
            verifyNoInteractions(orderRepository, orderItemRepository, orderService);
        }

        @Test
        @DisplayName("AOI-013: 添加订单项时，订单不存在")
        void addOrderItem_WhenOrderNotExists_ShouldReturnError() {
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setOrderId("12345678901234"); // 不存在的订单ID

            // 模拟订单不存在
            when(orderRepository.findById("12345678901234")).thenReturn(Optional.empty());

            Result<String> result = orderItemService.addOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("订单不存在", result.getMessage());
            verify(orderRepository).findById("12345678901234");
            verifyNoMoreInteractions(orderRepository);
            verifyNoInteractions(orderItemRepository, orderService);
        }

        @Test
        @DisplayName("AOI-014: 添加订单项时，商品不存在")
        void addOrderItem_WhenProductNotExists_ShouldReturnError() {
            AddOrderItemDTO dto = createBaseValidDTO();
            dto.setProductId("p12345678901234"); // 不存在的商品ID

            // 模拟订单存在
            when(orderRepository.findById(dto.getOrderId())).thenReturn(Optional.of(new Order()));
            // 模拟商品不存在
            when(orderService.getProductById("p12345678901234")).thenReturn(Optional.empty());

            Result<String> result = orderItemService.addOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("商品不存在", result.getMessage());
            verify(orderRepository).findById(dto.getOrderId());
            verify(orderService).getProductById("p12345678901234");
            verifyNoInteractions(orderItemRepository);
        }


        @Test
        @DisplayName("AOI-015: 添加订单项时，订单项已经存在")
        void addOrderItem_WhenItemAlreadyExists_ShouldReturnError() {
            AddOrderItemDTO dto = createBaseValidDTO();

            // 模拟订单存在
            when(orderRepository.findById(dto.getOrderId())).thenReturn(Optional.of(new Order()));
            // 模拟商品存在
            when(orderService.getProductById(dto.getProductId())).thenReturn(Optional.of(new Product()));
            // 模拟订单项已存在
            when(orderItemRepository.findById(dto.getItemId())).thenReturn(Optional.of(new OrderItem()));

            Result<String> result = orderItemService.addOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(409, result.getCode());
            assertEquals("订单项已存在", result.getMessage());
            verify(orderRepository).findById(dto.getOrderId());
            verify(orderService).getProductById(dto.getProductId());
            verify(orderItemRepository).findById(dto.getItemId());
            verifyNoMoreInteractions(orderItemRepository);
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
    }

    @Nested
    class DeleteOrderItemTests {

        @Test
        @DisplayName("DOI-001: 删除订单项时，orderItemId为空")
        void deleteOrderItem_WhenOrderItemIdIsEmpty_ShouldReturnError() {
            // 执行测试
            Result<String> result = orderItemService.deleteOrderitem("");

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("orderItemId不能为空", result.getMessage());

            // 验证没有调用repository方法
            verifyNoInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("DOI-002: 删除订单项时，不存在该orderItemId")
        void deleteOrderItem_WhenOrderItemNotExists_ShouldReturnError() {
            // 模拟订单项不存在
            when(orderItemRepository.findById("123456789999876")).thenReturn(Optional.empty());

            // 执行测试
            Result<String> result = orderItemService.deleteOrderitem("123456789999876");

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("不存在该订单项", result.getMessage());

            // 验证交互
            verify(orderItemRepository).findById("123456789999876");
            verifyNoMoreInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("DOI-003: 删除订单项时，存在该orderItemId")
        void deleteOrderItem_WhenOrderItemExists_ShouldSuccess() {
            // 模拟订单项存在
            OrderItem mockItem = new OrderItem();
            when(orderItemRepository.findById("529238668238848")).thenReturn(Optional.of(mockItem));
            doNothing().when(orderItemRepository).delete(mockItem);

            // 执行测试
            Result<String> result = orderItemService.deleteOrderitem("529238668238848");

            // 验证结果
            assertTrue(result.isSuccess());
            assertEquals("订单项删除成功", result.getData());

            // 验证交互
            verify(orderItemRepository).findById("529238668238848");
            verify(orderItemRepository).delete(mockItem);
            verifyNoMoreInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("DOI-004: orderItemId格式错误")
        void deleteOrderItem_WhenOrderItemIdFormatInvalid_ShouldReturnError() {
            // 执行测试
            Result<String> result = orderItemService.deleteOrderitem("o123");

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("orderItemId格式错误", result.getMessage());

            // 验证没有调用repository方法
            verifyNoInteractions(orderItemRepository);
        }
    }


    @Nested
    class GetItemStatusTests {

        @Test
        @DisplayName("GIS-001: 获取订单项状态时，orderItemId为空")
        void getItemStatus_WhenOrderItemIdIsEmpty_ShouldReturnError() {
            // 执行测试
            Result<String> result = orderItemService.getItemStatus("");

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("orderItemId不能为空", result.getMessage());

            // 验证没有调用repository方法
            verifyNoInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("GIS-002: 获取订单项状态时，不存在该orderItemId")
        void getItemStatus_WhenOrderItemNotExists_ShouldReturnError() {
            // 模拟订单项不存在
            when(orderItemRepository.findById("123456789999876")).thenReturn(Optional.empty());

            // 执行测试
            Result<String> result = orderItemService.getItemStatus("123456789999876");

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("希望查询状态的订单项不存在！", result.getMessage());

            // 验证交互
            verify(orderItemRepository).findById("123456789999876");
            verifyNoMoreInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("GIS-003: 获取订单项状态时，存在该orderItemId")
        void getItemStatus_WhenOrderItemExists_ShouldReturnStatus() {
            // 创建模拟订单项并设置状态
            OrderItem mockItem = new OrderItem();
            mockItem.setItemStatus(OrderItem.ItemStatus.已发货); // 假设ItemStatus是枚举

            // 模拟订单项存在
            when(orderItemRepository.findById("529238668238848")).thenReturn(Optional.of(mockItem));

            // 执行测试
            Result<String> result = orderItemService.getItemStatus("529238668238848");

            // 验证结果
            assertTrue(result.isSuccess());
            assertEquals("已发货", result.getData());

            // 验证交互
            verify(orderItemRepository).findById("529238668238848");
            verifyNoMoreInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("GIS-004: 获取订单项状态时，orderItemId格式错误")
        void getItemStatus_WhenOrderItemIdFormatInvalid_ShouldReturnError() {
            // 执行测试
            Result<String> result = orderItemService.getItemStatus("o123");

            // 验证结果
            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("orderItemId格式错误", result.getMessage());

            // 验证没有调用repository方法
            verifyNoInteractions(orderItemRepository);
        }
    }

    @Nested
    class RemarkOrderItemTests {

        private UpdateOrderItemRemarkDTO createBaseValidDTO() {
            UpdateOrderItemRemarkDTO dto = new UpdateOrderItemRemarkDTO();
            dto.setOrderItemId("529238668238848");
            dto.setScore(new BigDecimal("4"));
            dto.setRemark("很不错");
            return dto;
        }

        @Test
        @DisplayName("ROI-001: 修改订单项时，orderItemId格式错误")
        void remarkOrderItem_WhenOrderItemIdFormatInvalid_ShouldReturnError() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();
            dto.setOrderItemId("o123"); // 设置格式错误的ID

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("orderItemId格式错误", result.getMessage());
            verifyNoInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("ROI-002: 修改订单项时，不存在该orderItemId")
        void remarkOrderItem_WhenOrderItemNotExists_ShouldReturnError() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();
            dto.setOrderItemId("123456789999876"); // 不存在的ID

            when(orderItemRepository.findById("123456789999876")).thenReturn(Optional.empty());

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("未找到订单", result.getMessage());
            verify(orderItemRepository).findById("123456789999876");
            verifyNoMoreInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("ROI-003: 修改订单项时，orderItemId为空")
        void remarkOrderItem_WhenOrderItemIdIsEmpty_ShouldReturnError() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();
            dto.setOrderItemId(""); // 空ID

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("orderItemId不能为空", result.getMessage());
            verifyNoInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("ROI-004: 修改订单项时，remark超出长度限制")
        void remarkOrderItem_WhenRemarkTooLong_ShouldReturnError() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();
            // 创建超过200字符的评价
            dto.setRemark("这是一段超出200个字符的评价".repeat(10));

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(500, result.getCode());
            assertEquals("remark超出200的长度限制", result.getMessage());
            verifyNoInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("ROI-005: 修改订单项时，remark为空且评分成功")
        void remarkOrderItem_WhenRemarkEmptyButScoreValid_ShouldSuccess() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();
            dto.setRemark(""); // 空评价

            OrderItem mockItem = new OrderItem();
            when(orderItemRepository.findById("529238668238848")).thenReturn(Optional.of(mockItem));
            when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(inv -> inv.getArgument(0));

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertTrue(result.isSuccess());
            assertEquals(200, result.getCode());
            assertEquals("订单评价已提交", result.getMessage());
            verify(orderItemRepository).findById("529238668238848");
            verify(orderItemRepository).save(mockItem);
        }

        @Test
        @DisplayName("ROI-006: 修改订单项时，评分低于0")
        void remarkOrderItem_WhenScoreBelowZero_ShouldReturnError() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();
            dto.setScore(new BigDecimal("-0.1")); // 负分

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("评分只能在0-5之间", result.getMessage());
            verifyNoInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("ROI-007: 修改订单项时，评分高于5")
        void remarkOrderItem_WhenScoreAboveFive_ShouldReturnError() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();
            dto.setScore(new BigDecimal("5.1")); // 超过5分

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertFalse(result.isSuccess());
            assertEquals(404, result.getCode());
            assertEquals("评分只能在0-5之间", result.getMessage());
            verifyNoInteractions(orderItemRepository);
        }

        @Test
        @DisplayName("ROI-008: 修改订单项时，存在该orderItemId并且评分成功")
        void remarkOrderItem_WhenValidInput_ShouldSuccess() {
            UpdateOrderItemRemarkDTO dto = createBaseValidDTO();

            OrderItem mockItem = new OrderItem();
            when(orderItemRepository.findById("529238668238848")).thenReturn(Optional.of(mockItem));
            when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(inv -> inv.getArgument(0));

            Result<String> result = orderItemService.remarkOrderItem(dto);

            assertTrue(result.isSuccess());
            assertEquals(200, result.getCode());
            assertEquals("订单评价已提交", result.getMessage());
            verify(orderItemRepository).findById("529238668238848");
            verify(orderItemRepository).save(mockItem);
            assertEquals(new BigDecimal("4"), mockItem.getScore());
            assertEquals("很不错", mockItem.getRemark());
        }
    }


}