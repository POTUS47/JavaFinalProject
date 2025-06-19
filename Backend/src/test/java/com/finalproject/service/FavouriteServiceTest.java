package com.finalproject.service;
import com.finalproject.model.*;
import com.finalproject.repository.BookmarkProductRepository;
import com.finalproject.repository.BookmarkStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.Result;
import org.mockito.Mockito;
import com.finalproject.model.Product;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavouriteServiceTest {

    @Mock
    BookmarkStoreRepository mockStoreRepo;
    @Mock
    BookmarkProductRepository mockProductRepo;
    @Mock
    RestTemplate mockRest;
    @InjectMocks
    FavouriteService service;
    @BeforeEach
    void setup() {
        // 手动设置 baseUrl（模拟 @Value 注入）
        ReflectionTestUtils.setField(service, "baseUrl", "http://api");
    }
    @Nested
    @DisplayName("getProductsByStoreId(storeId)")
    class GetProductsByStoreId {
        public static List<Product> generateProducts(int count, String storeId) {
            List<Product> products = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                String productId = ""+i;
                String productName = "Product " + i;
                BigDecimal productPrice = BigDecimal.valueOf(i * 10.0);
                int quantity = 100 + i;
                String tag = "tag" + i;
                String description = "Description of product " + i;
                String storeTag = "storeTag" + i;
                String subCategory = "subCategory" + i;

                products.add(new Product(
                        productId,
                        productName,
                        productPrice,
                        quantity,
                        tag,
                        description,
                        subCategory,
                        storeId,
                        storeTag
                ));
            }
            return products;
        }
        @Test
        @DisplayName("T01 - 商家ID合法存在且有商品")
        void testValidStoreIdWithProducts() {
            String storeId = "581960972537861";
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = ResponseEntity.ok(products);
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    ArgumentMatchers.<ParameterizedTypeReference<List<Product>>>any()
            )).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            Product expected = products.get(0);
            Product actual = result.get(0);
            assertEquals(expected.getProductId(), actual.getProductId());
            assertEquals(expected.getProductName(), actual.getProductName());
            assertEquals(expected.getStoreId(), actual.getStoreId());
        }

        @Test
        @DisplayName("T02 - 商家ID合法但不存在于数据库")
        void testValidStoreIdButNotFound() {
            String storeId = "581960972537862";
            // 404响应返回null body
            ResponseEntity<List<Product>> resp = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNull(result);
        }

        @Test
        @DisplayName("T03 - 商家ID合法但没有商品")
        void testValidStoreIdWithEmptyList() {
            String storeId = "581960972537862";
            ResponseEntity<List<Product>> resp = ResponseEntity.ok(List.of());

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("T04 - storeId 为 null")
        void testStoreIdIsNull_ReturnsEmptyList() {
            List<Product> result = service.getProductsByStoreId(null);
            assertNotNull(result);      // 确保不为 null
            assertTrue(result.isEmpty()); // 确保为空列表
        }

        @Test
        @DisplayName("T05 - storeId 为空字符串")
        void testStoreIdEmpty() {
            String storeId="";
            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);      // 确保不为 null
            assertTrue(result.isEmpty()); // 确保为空列表
        }

        @Test
        @DisplayName("T06 - storeId 为最小值 0")
        void testStoreIdMinValue() {
            String storeId = "0";
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = ResponseEntity.ok(products);
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);
            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            Product expected = products.get(0);
            Product actual = result.get(0);
            assertEquals(expected.getProductId(), actual.getProductId());
            assertEquals(expected.getProductName(), actual.getProductName());
            assertEquals(expected.getStoreId(), actual.getStoreId());
        }

        @Test
        @DisplayName("T07 - storeId 为略高于最小值 1")
        void testStoreIdSlightlyAboveMin() {
            String storeId = "1";
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = ResponseEntity.ok(products);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            Product expected = products.get(0);
            Product actual = result.get(0);
            assertEquals(expected.getProductId(), actual.getProductId());
            assertEquals(expected.getProductName(), actual.getProductName());
            assertEquals(expected.getStoreId(), actual.getStoreId());
        }

        @Test
        @DisplayName("T08 - storeId 超过最大长度 101 位")
        void testStoreIdSlightlyAboveMax() {
            String storeId = "1".repeat(101); // 101字符
            // 服务方法没有长度校验，所以会正常请求
            ResponseEntity<List<Product>> resp = ResponseEntity.ok(List.of());
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);
            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("T09 - storeId 为最大值 100 位")
        void testStoreIdMaxLength() {
            String storeId = "9".repeat(100);
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = ResponseEntity.ok(products);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            Product expected = products.get(0);
            Product actual = result.get(0);
            assertEquals(expected.getProductId(), actual.getProductId());
            assertEquals(expected.getProductName(), actual.getProductName());
            assertEquals(expected.getStoreId(), actual.getStoreId());
        }

        @Test
        @DisplayName("T10 - storeId 为略低于最大值 99 位")
        void testStoreIdSlightlyBelowMax() {
            String storeId = "9".repeat(99);
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = ResponseEntity.ok(products);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            Product expected = products.get(0);
            Product actual = result.get(0);
            assertEquals(expected.getProductId(), actual.getProductId());
            assertEquals(expected.getProductName(), actual.getProductName());
            assertEquals(expected.getStoreId(), actual.getStoreId());
        }
    }

    @Nested
    @DisplayName("getStoreById(storeId)")
    class GetStoreById {
        private Store generateStore(String storeId) {
            Store store = new Store();
            store.setAccountId(storeId);
            store.setStoreName("Store " + storeId);
            store.setStoreScore(BigDecimal.valueOf(4.5));
            store.setAddress("同济大学");
            return store;
        }

        @Test
        @DisplayName("T01 - 商家ID合法存在")
        void testValidStoreIdExists() {
            String storeId = "581960972537861";
            Store store = generateStore(storeId);
            ResponseEntity<Optional<Store>> resp = ResponseEntity.ok(Optional.of(store));

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class) // 使用更宽松的匹配器
            )).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent(), "结果应包含商家信息");
            Store actual = result.get();
            assertEquals(storeId, actual.getAccountId(), "商家ID不匹配");
            assertEquals("Store " + storeId, actual.getStoreName(), "商家名称不匹配");
            assertEquals(BigDecimal.valueOf(4.5), actual.getStoreScore(), "评分不匹配");
            assertEquals("同济大学", actual.getAddress(), "地址不匹配");
        }

        @Test
        @DisplayName("T02 - 商家ID合法但不存在")
        void testValidStoreIdNotFound() {
            String storeId = "581960972537862";
            ResponseEntity<Optional<Store>> response = ResponseEntity.ok(Optional.empty());
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(response);
            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("T03 - storeId 为 null")
        void testStoreIdIsNull() {
            Optional<Store> result = service.getStoreById(null);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("T04 - storeId 为空字符串")
        void testStoreIdEmpty() {
            String storeId = "";
            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("T05 - storeId 为最小值 0")
        void testStoreIdMinValue() {
            String storeId = "0";
            Store store = generateStore(storeId);
            ResponseEntity<Optional<Store>> resp = ResponseEntity.ok(Optional.of(store));

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class) // 使用更宽松的匹配器
            )).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent(), "结果应包含商家信息");
            Store actual = result.get();
            assertEquals(storeId, actual.getAccountId(), "商家ID不匹配");
            assertEquals("Store " + storeId, actual.getStoreName(), "商家名称不匹配");
            assertEquals(BigDecimal.valueOf(4.5), actual.getStoreScore(), "评分不匹配");
            assertEquals("同济大学", actual.getAddress(), "地址不匹配");
        }

        @Test
        @DisplayName("T06 - storeId 为略高于最小值 1")
        void testStoreIdSlightlyAboveMin() {
            String storeId = "1";
            Store store = generateStore(storeId);
            ResponseEntity<Optional<Store>> resp = ResponseEntity.ok(Optional.of(store));

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent(), "结果应包含商家信息");
            Store actual = result.get();
            assertEquals(storeId, actual.getAccountId(), "商家ID不匹配");
            assertEquals("Store " + storeId, actual.getStoreName(), "商家名称不匹配");
            assertEquals(BigDecimal.valueOf(4.5), actual.getStoreScore(), "评分不匹配");
            assertEquals("同济大学", actual.getAddress(), "地址不匹配");
        }

        @Test
        @DisplayName("T07 - storeId 超过最大长度 101 位")
        void testStoreIdSlightlyAboveMax() {
            String storeId = "1".repeat(101);
            ResponseEntity<Optional<Store>> response = ResponseEntity.ok(Optional.empty());

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(response);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("T08 - storeId 为最大值 100 位")
        void testStoreIdMaxLength() {
            String storeId = "9".repeat(100);
            Store store = generateStore(storeId);
            ResponseEntity<Optional<Store>> resp = ResponseEntity.ok(Optional.of(store));

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent(), "结果应包含商家信息");
            Store actual = result.get();
            assertEquals(storeId, actual.getAccountId(), "商家ID不匹配");
            assertEquals("Store " + storeId, actual.getStoreName(), "商家名称不匹配");
            assertEquals(BigDecimal.valueOf(4.5), actual.getStoreScore(), "评分不匹配");
            assertEquals("同济大学", actual.getAddress(), "地址不匹配");
        }

        @Test
        @DisplayName("T09 - storeId 为略低于最大值 99 位")
        void testStoreIdSlightlyBelowMax() {
            String storeId = "9".repeat(99);
            Store store = generateStore(storeId);
            ResponseEntity<Optional<Store>> resp = ResponseEntity.ok(Optional.of(store));

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent(), "结果应包含商家信息");
            Store actual = result.get();
            assertEquals(storeId, actual.getAccountId(), "商家ID不匹配");
            assertEquals("Store " + storeId, actual.getStoreName(), "商家名称不匹配");
            assertEquals(BigDecimal.valueOf(4.5), actual.getStoreScore(), "评分不匹配");
            assertEquals("同济大学", actual.getAddress(), "地址不匹配");
        }
    }


    @Nested
    @DisplayName("BookmarkProduct")
    class BookmarkProductTest {

        @Test
        @DisplayName("T01 - 合法ID存在且未收藏")
        void bookmarkProduct_whenValidIdsAndNotBookmarked_shouldBookmarkSuccess() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "152305151";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T02 - 合法ID存在且已收藏")
        void bookmarkProduct_whenValidIdsAndBookmarked_shouldUnbookmarkSuccess() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "152305152";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(true);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("取消收藏成功", result.getMsg());
            verify(mockProductRepo).deleteBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T03 - 用户不存在")
        void bookmarkProduct_whenUserNotExist_shouldReturn404() {
            // 准备测试数据
            String userId = "581960972537860529237";
            String productId = "152305150";

            // 模拟依赖行为
            // 首先模拟商品存在
            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            // 然后模拟用户不存在
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.empty()));

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("未找到买家信息", result.getMsg());
            verifyNoInteractions(mockProductRepo);
        }

        @Test
        @DisplayName("T04 - 商品不存在")
        void bookmarkProduct_whenProductNotExist_shouldReturn404() {
            // 准备测试数据
            String userId = "581960972537860";
            String productId = "529237152305151";

            // 只模拟商品不存在（不需要模拟用户检查）
            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.empty()));

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("未找到商品信息", result.getMsg());
            verifyNoInteractions(mockProductRepo);
            // 验证没有调用用户检查
            verify(mockRest, never()).exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    any(HttpMethod.class),
                    isNull(),
                    any(ParameterizedTypeReference.class));
        }

        @Test
        @DisplayName("T05 - 商品不存在（用户存在）")
        void bookmarkProduct_whenUserExistButProductNot_shouldReturn404() {
            // 准备测试数据
            String userId = "581960972537861";
            String productId = "529237152305150";

            // 只模拟商品不存在（不需要模拟用户检查）
            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.empty()));

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("未找到商品信息", result.getMsg());
            verifyNoInteractions(mockProductRepo);
            // 验证没有调用用户检查
            verify(mockRest, never()).exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    any(HttpMethod.class),
                    isNull(),
                    any(ParameterizedTypeReference.class));
        }

        @Test
        @DisplayName("T06 - userId为null")
        void bookmarkProduct_whenUserIdNull_shouldReturn404() {
            // 准备测试数据
            String userId = null;
            String productId = "152305151";
            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);
            // 验证结果
            assertEquals(400, result.getCode());
            assertEquals("用户ID不能为空", result.getMsg());
            verifyNoInteractions(mockProductRepo);
        }

        @Test
        @DisplayName("T07 - userId为空字符串")
        void bookmarkProduct_whenUserIdEmpty_shouldReturn404() {
            // 准备测试数据
            String userId = "";
            String productId = "152305151";

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);
            // 验证结果
            assertEquals(400, result.getCode());
            assertEquals("用户ID不能为空", result.getMsg());
            verifyNoInteractions(mockProductRepo);
        }

        @Test
        @DisplayName("T08 - userId最小值")
        void bookmarkProduct_whenUserIdMinValue_shouldSucceed() {
            // 准备测试数据
            String userId = "0";
            String productId = "152305151";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T09 - userId略高于最小值")
        void bookmarkProduct_whenUserIdSlightlyAboveMin_shouldSucceed() {
            // 准备测试数据
            String userId = "1";
            String productId = "152305151";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T10 - userId略低于最大值")
        void bookmarkProduct_whenUserIdSlightlyBelowMax_shouldSucceed() {
            // 准备测试数据
            String userId = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998";
            String productId = "152305151";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T11 - userId最大值")
        void bookmarkProduct_whenUserIdMaxValue_shouldSucceed() {
            // 准备测试数据
            String userId = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
            String productId = "152305151";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T12 - userId略高于最大值")
        void bookmarkProduct_whenUserIdExceedMax_shouldReturn404() {
            // 准备测试数据
            String userId = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            String productId = "152305151";

            // 模拟商品存在
            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            // 模拟用户不存在
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.empty()));

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("未找到买家信息", result.getMsg());
            verifyNoInteractions(mockProductRepo);
        }

        @Test
        @DisplayName("T13 - productId为null")
        void bookmarkProduct_whenProductIdNull_shouldReturn400() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = null;

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(400, result.getCode());
            assertEquals("商品ID不能为空", result.getMsg());
            verifyNoInteractions(mockProductRepo);
        }

        @Test
        @DisplayName("T14 - productId为空字符串")
        void bookmarkProduct_whenProductIdEmpty_shouldReturn400() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "";
            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);
            // 验证结果
            assertEquals(400, result.getCode());
            assertEquals("商品ID不能为空", result.getMsg());
            verifyNoInteractions(mockProductRepo);
        }

        // T15 - productId最小值（收藏成功）
        @Test
        @DisplayName("T15 - productId最小值")
        void bookmarkProduct_whenProductIdMinValue_shouldSucceed() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "0";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T16 - productId略高于最小值")
        void bookmarkProduct_whenProductIdSlightlyAboveMin_shouldSucceed() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "1";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T17 - productId略低于最大值")
        void bookmarkProduct_whenProductIdSlightlyBelowMax_shouldSucceed() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T18 - productId最大值")
        void bookmarkProduct_whenProductIdMaxValue_shouldSucceed() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";

            // 模拟依赖行为
            when(mockRest.exchange(
                    eq("http://api/api/users/buyer/" + userId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Buyer())));

            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.of(new Product())));

            when(mockProductRepo.existsByBuyerIdAndProductId(userId, productId))
                    .thenReturn(false);

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
            verify(mockProductRepo).insertBookmarkProduct(userId, productId);
        }

        @Test
        @DisplayName("T19 - productId略高于最大值")
        void bookmarkProduct_whenProductIdExceedMax_shouldReturn404() {
            // 准备测试数据
            String userId = "581960972537861529237";
            String productId = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            // 模拟商品不存在
            when(mockRest.exchange(
                    eq("http://api/api/productController/product/" + productId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(ResponseEntity.ok(Optional.empty()));

            // 执行测试
            Result<String> result = service.bookmarkProduct(userId, productId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("未找到商品信息", result.getMsg());
            verifyNoInteractions(mockProductRepo);
        }
    }

    @Nested
    @DisplayName("IsProductBookmarked")
    class IsProductBookmarked {

        @Test
        @DisplayName("T01 - 合法ID存在且已收藏")
        void testT01_bookmarkedExists() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "529237152305151"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "529237152305151");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T02 - 合法ID存在但未收藏")
        void testT02_notBookmarked() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "529237152305152"))
                    .thenReturn(false);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "529237152305152");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T03 - 用户和商品不存在")
        void testT03_idsNotExist() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537860", "529237152305150"))
                    .thenReturn(false);
            Result<Boolean> result = service.isProductBookmarked("581960972537860", "529237152305150");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T04 - userId不存在")
        void testT04_userNotExist() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537860", "529237152305151"))
                    .thenReturn(false);
            Result<Boolean> result = service.isProductBookmarked("581960972537860", "529237152305151");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T05 - productId不存在")
        void testT05_productNotExist() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "529237152305150"))
                    .thenReturn(false);
            Result<Boolean> result = service.isProductBookmarked("581960972537861", "529237152305150");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T06 - userId 为 null")
        void testT06_userIdNull() {
//            assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked(null, "529237152305151"));
            Result<Boolean> result=service.isProductBookmarked(null, "529237152305151");
            assertEquals(500, result.getCode());
            assertNull(result.getData());
            assertEquals("用户ID或商品ID不能为空",result.getMessage());
        }

        @Test
        @DisplayName("T07 - userId 为空字符串")
        void testT07_userIdEmpty() {
            //assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked("", "529237152305151"));
            Result<Boolean> result=service.isProductBookmarked("", "529237152305151");
            assertEquals(500, result.getCode());
            assertNull(result.getData());
            assertEquals("用户ID或商品ID不能为空",result.getMessage());
        }

        @Test
        @DisplayName("T08 - userId 为最小值")
        void testT08_userIdMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("0", "529237152305151"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("0", "529237152305151");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T09 - userId 略高于最小值")
        void testT09_userIdAboveMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("1", "529237152305151"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("1", "529237152305151");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T10 - userId 略低于最大值")
        void testT10_userIdBelowMax() {
            String userId = "9".repeat(99);
            when(mockProductRepo.existsByBuyerIdAndProductId(userId, "529237152305151"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked(userId, "529237152305151");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T11 - userId 为最大值")
        void testT11_userIdMax() {
            String userId = "9".repeat(100);
            when(mockProductRepo.existsByBuyerIdAndProductId(userId, "529237152305151"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked(userId, "529237152305151");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T12 - userId 略高于最大值")
        void testT12_userIdAboveMax() {
            when(mockProductRepo.existsByBuyerIdAndProductId( "1".repeat(101),"529237152305151"))
                    .thenThrow(new DataAccessException("Database error") {});
            assertThrows(DataAccessException.class, () -> service.isProductBookmarked("1".repeat(101), "529237152305151"));
        }

        @Test
        @DisplayName("T13 - productId 为 null")
        void testT13_productIdNull() {
            Result<Boolean> result=service.isProductBookmarked("581960972537861", null);
            assertEquals(500, result.getCode());
            assertNull(result.getData());
            assertEquals("用户ID或商品ID不能为空",result.getMessage());
        }

        @Test
        @DisplayName("T14 - productId 为空字符串")
        void testT14_productIdEmpty() {
            Result<Boolean> result=service.isProductBookmarked("581960972537861", "");
            assertEquals(500, result.getCode());
            assertNull(result.getData());
            assertEquals("用户ID或商品ID不能为空",result.getMessage());
        }

        @Test
        @DisplayName("T15 - productId 为最小值")
        void testT15_productIdMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "0"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "0");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T16 - productId 略高于最小值")
        void testT16_productIdAboveMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "1"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "1");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T17 - productId 略低于最大值")
        void testT17_productIdBelowMax() {
            String productId = "9".repeat(99);
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", productId))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", productId);
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T18 - productId 为最大值")
        void testT18_productIdMax() {
            String productId = "9".repeat(100);
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", productId))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", productId);
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
            assertEquals("ok", result.getMsg());
        }

        @Test
        @DisplayName("T19 - productId 略高于最大值")
        void testT19_productIdAboveMax() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "1".repeat(101)))
                    .thenThrow(new DataAccessException("Database error") {});
            assertThrows(DataAccessException.class, () -> service.isProductBookmarked("581960972537861", "1".repeat(101)));
        }
    }


    @Nested
    @DisplayName("GetFavouriteStores")
    class GetFavouriteStoresTest {

        // T01: 用户ID合法存在且有收藏商家
        @Test
        @DisplayName("T01 - 商家ID合法存在且有商品")
        void getFavouriteStores_whenValidUserWithBookmarks_shouldReturnDTOList() {
            // 准备测试数据
            String userId = "581960972537860";
            BookmarkStore bookmark = new BookmarkStore();
            bookmark.setStoreAccountId("store1");
            bookmark.setBuyerAccountId(userId);

            Store store = new Store();
            store.setAccountId("store1");
            store.setStoreName("Test Store");
            store.setStoreScore(BigDecimal.valueOf(4.5));

            Product product = new Product();
            product.setProductId("prod1");
            product.setProductName("Product 1");
            product.setProductPrice(BigDecimal.valueOf(100.0));
            product.setStoreId("store1");

            ProductImage image = new ProductImage();
            image.setImageId("img1");

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Arrays.asList(bookmark));

            // 模拟REST调用 - 修正了URL路径
            // 1. 模拟获取店铺信息
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/store1"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store)));

            // 2. 模拟获取商品列表
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/store1"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Arrays.asList(product)));

            // 3. 模拟获取商品图片
            when(mockRest.exchange(
                    eq("http://api/api/productController/productImages/prod1"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Arrays.asList(image)));

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals(1, result.getData().size());

            FavouriteStoresDTO dto = result.getData().get(0);
            assertEquals("store1", dto.getStoreId());
            assertEquals("Test Store", dto.getStoreName());
            assertEquals(1, dto.getProducts().size());
            assertEquals("http://api/images/img1", dto.getProducts().get(0).getProductPic());
        }

        // T02: 用户ID合法存在且无收藏商家
        @Test
        @DisplayName("T02 - 用户ID合法存在且无收藏商家")
        void getFavouriteStores_whenValidUserNoBookmarks_shouldReturn404() {
            // 准备测试数据
            String userId = "581960972537861";

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("没有收藏任何店铺", result.getMsg());
        }

        // T03: 用户ID合法但不存在于数据库
        @Test
        @DisplayName("T03 - 用户ID合法但不存在于数据库")
        void getFavouriteStores_whenValidUserNotExist_shouldReturn404() {
            // 准备测试数据
            String userId = "581960972537862";

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("没有收藏任何店铺", result.getMsg());
        }

        // T04: 用户ID为null
        @Test
        @DisplayName("T04 - 用户ID为null")
        void getFavouriteStores_whenUserIdIsNull_shouldReturn400() {
            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(null);

            // 验证结果
            assertEquals(400, result.getCode());
            assertTrue(result.getMsg().contains("用户ID不能为空"));
        }

        // T05: 用户ID为空字符串
        @Test
        @DisplayName("T05 - 用户ID为空字符串")
        void getFavouriteStores_whenUserIdIsEmpty_shouldReturn400() {
            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores("");

            // 验证结果
            assertEquals(400, result.getCode());
            assertTrue(result.getMsg().contains("用户ID不能为空"));
        }

        // T06: 用户ID为"0"
        @Test
        @DisplayName("T06 - 用户ID为0")
        void getFavouriteStores_whenUserIdIsZero_shouldReturnSuccess() {
            // 准备测试数据
            String userId = "0";
            BookmarkStore bookmark = new BookmarkStore();
            bookmark.setStoreAccountId("store0");
            bookmark.setBuyerAccountId(userId);

            Store store = new Store();
            store.setAccountId("store0");
            store.setStoreName("Store 0");
            store.setStoreScore(BigDecimal.valueOf(4.0));

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Arrays.asList(bookmark));

            // 添加店铺信息模拟
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/store0"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store)));

            // 添加商品列表模拟（空列表）
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/store0"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals(1, result.getData().size());
        }

        // T07: 用户ID为"1"
        @Test
        @DisplayName("T07 - 用户ID为1")
        void getFavouriteStores_whenUserIdIsOne_shouldReturnSuccess() {
            // 准备测试数据
            String userId = "1";
            BookmarkStore bookmark = new BookmarkStore();
            bookmark.setStoreAccountId("store1");
            bookmark.setBuyerAccountId(userId);

            Store store = new Store();
            store.setAccountId("store1");
            store.setStoreName("Store 1");
            store.setStoreScore(BigDecimal.valueOf(4.0));

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Arrays.asList(bookmark));

            // 添加店铺信息模拟
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/store1"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store)));

            // 添加商品列表模拟（空列表）
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/store1"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals(1, result.getData().size());
        }

        // T08: 用户ID超长
        @Test
        @DisplayName("T08 - 用户ID超长")
        void getFavouriteStores_whenUserIdTooLong_shouldReturn404() {
            // 准备测试数据
            String userId = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(404, result.getCode());
            assertEquals("没有收藏任何店铺", result.getMsg());
        }

        // T09: 用户ID为最大长度
        @Test
        @DisplayName("T09 - 用户ID为最大长度")
        void getFavouriteStores_whenUserIdMaxLength_shouldReturnSuccess() {
            // 准备测试数据
            String userId = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
            BookmarkStore bookmark = new BookmarkStore();
            bookmark.setStoreAccountId("storeMax");
            bookmark.setBuyerAccountId(userId);

            Store store = new Store();
            store.setAccountId("storeMax");
            store.setStoreName("Max Store");
            store.setStoreScore(BigDecimal.valueOf(4.0));

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Arrays.asList(bookmark));

            // 添加店铺信息模拟
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/storeMax"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store)));

            // 添加商品列表模拟（空列表）
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/storeMax"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals(1, result.getData().size());
        }

        // T10: 用户ID略低于最大长度
        @Test
        @DisplayName("T10 - 用户ID略低于最大长度")
        void getFavouriteStores_whenUserIdBelowMaxLength_shouldReturnSuccess() {
            // 准备测试数据
            String userId = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998";
            BookmarkStore bookmark = new BookmarkStore();
            bookmark.setStoreAccountId("storeBelowMax");
            bookmark.setBuyerAccountId(userId);

            Store store = new Store();
            store.setAccountId("storeBelowMax");
            store.setStoreName("Below Max Store");
            store.setStoreScore(BigDecimal.valueOf(4.0));

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Arrays.asList(bookmark));

            // 添加店铺信息模拟
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/storeBelowMax"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store)));

            // 添加商品列表模拟（空列表）
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/storeBelowMax"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals(1, result.getData().size());
        }

        // T11: 用户恰有一个收藏商家
        @Test
        @DisplayName("T11 - 用户恰有一个收藏商家")
        void getFavouriteStores_whenSingleBookmark_shouldReturnOneDTO() {
            // 准备测试数据
            String userId = "581960972537863";
            BookmarkStore bookmark = new BookmarkStore();
            bookmark.setStoreAccountId("storeSingle");
            bookmark.setBuyerAccountId(userId);

            Store store = new Store();
            store.setAccountId("storeSingle");
            store.setStoreName("Single Store");
            store.setStoreScore(BigDecimal.valueOf(4.0));

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Arrays.asList(bookmark));

            // 添加店铺信息模拟
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/storeSingle"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store)));

            // 添加商品列表模拟（空列表）
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/storeSingle"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals(1, result.getData().size());
        }

        // T12: 用户恰有两个收藏商家
        @Test
        @DisplayName("T12 - 用户恰有两个收藏商家")
        void getFavouriteStores_whenTwoBookmarks_shouldReturnTwoDTOs() {
            // 准备测试数据
            String userId = "581960972537864";

            BookmarkStore bookmark1 = new BookmarkStore();
            bookmark1.setStoreAccountId("store1");
            bookmark1.setBuyerAccountId(userId);

            BookmarkStore bookmark2 = new BookmarkStore();
            bookmark2.setStoreAccountId("store2");
            bookmark2.setBuyerAccountId(userId);

            Store store1 = new Store();
            store1.setAccountId("store1");
            store1.setStoreName("Store One");
            store1.setStoreScore(BigDecimal.valueOf(4.0));

            Store store2 = new Store();
            store2.setAccountId("store2");
            store2.setStoreName("Store Two");
            store2.setStoreScore(BigDecimal.valueOf(4.5));

            // 模拟repository行为
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Arrays.asList(bookmark1, bookmark2));

            // 添加店铺信息模拟 - store1
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/store1"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store1)));

            // 添加店铺信息模拟 - store2
            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/store2"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Optional.of(store2)));

            // 添加商品列表模拟（空列表） - store1
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/store1"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

            // 添加商品列表模拟（空列表） - store2
            when(mockRest.exchange(
                    eq("http://api/api/productController/products/store2"),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class)
            )).thenReturn(ResponseEntity.ok(Collections.emptyList()));

            // 执行测试
            Result<List<FavouriteStoresDTO>> result = service.getFavouriteStores(userId);

            // 验证结果
            assertEquals(200, result.getCode());
            assertEquals(2, result.getData().size());
        }
    }


    @Nested
    @DisplayName("BookmarkProductRecord")
    class BookmarkProductRecord {

        private Product createMockProduct(String productId) {
            Product product = new Product();
            product.setProductId(productId);
            product.setProductName("ProductName");
            product.setDescription("ProductDesc");
            product.setStoreId("store123");
            product.setProductPrice(new BigDecimal("10.0"));
            product.setQuantity(100);
            product.setTag("tag");
            product.setStoreTag("storeTag");
            product.setSubCategory("subCat");
            return product;
        }

        @Test
        @DisplayName("T01 - 正常更新成功")
        void testBookmarkProductRecord_T01() {
            String userId = "581960972537861";
            String productId = "1529237152305151";
            Product mockProduct = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(mockProduct)).when(spyService).getProductById(productId);

            when(mockRest.exchange(
                    anyString(), eq(HttpMethod.POST), isNull(),
                    any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("更新成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("更新成功", result.getData());
        }

        @Test
        @DisplayName("T02 - ID合法但商品不存在，返回404")
        void testBookmarkProductRecord_T02() {
            String userId = "581960972537861";
            String productId = "1529237152305152";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T03 - ID合法但商品不存在，返回404")
        void testBookmarkProductRecord_T03() {
            String userId = "581960972537860";
            String productId = "152305150";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T04 - userId不存在数据库中")
        void testBookmarkProductRecord_T04() {
            String userId = "581960972537860";
            String productId = "152305151";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T05 - productId不存在数据库中")
        void testBookmarkProductRecord_T05() {
            String userId = "581960972537861";
            String productId = "152305150";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T06 - userId 为 null")
        void testBookmarkProductRecord_T06() {
            String userId = null;
            String productId = "152305151";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T07 - userId 为空字符串")
        void testBookmarkProductRecord_T07() {
            String userId = "";
            String productId = "152305151";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T08 - userId 最小值")
        void testBookmarkProductRecord_T08() {
            String userId = "0";
            String productId = "152305151";
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T09 - userId 略高于最小值")
        void testBookmarkProductRecord_T09() {
            String userId = "1";
            String productId = "152305151";
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T10 - userId 略低于最大值")
        void testBookmarkProductRecord_T10() {
            String userId = "9".repeat(99) + "8";
            String productId = "152305151";
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T11 - userId 最大值")
        void testBookmarkProductRecord_T11() {
            String userId = "9".repeat(100);
            String productId = "152305151";
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T12 - userId 超过最大值")
        void testBookmarkProductRecord_T12() {
            String userId = "1" + "0".repeat(100);
            String productId = "152305151";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T13 - productId 为 null")
        void testBookmarkProductRecord_T13() {
            String userId = "581960972537861";
            String productId = null;

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T14 - productId 为空字符串")
        void testBookmarkProductRecord_T14() {
            String userId = "581960972537861";
            String productId = "";

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }

        @Test
        @DisplayName("T15 - productId 最小值")
        void testBookmarkProductRecord_T15() {
            String userId = "581960972537861";
            String productId = "0";
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T16 - productId 略高于最小值")
        void testBookmarkProductRecord_T16() {
            String userId = "581960972537861";
            String productId = "1";
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T17 - productId 略低于最大值")
        void testBookmarkProductRecord_T17() {
            String userId = "581960972537861";
            String productId = "9".repeat(99) + "8";
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T18 - productId 最大值")
        void testBookmarkProductRecord_T18() {
            String userId = "581960972537861";
            String productId = "9".repeat(100);
            Product product = createMockProduct(productId);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.of(product)).when(spyService).getProductById(productId);

            when(mockRest.exchange(anyString(), eq(HttpMethod.POST), isNull(), any(ParameterizedTypeReference.class)))
                    .thenReturn(new ResponseEntity<>("操作成功", HttpStatus.OK));

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(200, result.getCode());
            assertEquals("操作成功", result.getData());
        }

        @Test
        @DisplayName("T19 - productId 超过最大值")
        void testBookmarkProductRecord_T19() {
            String userId = "581960972537861";
            String productId = "1" + "0".repeat(100);

            FavouriteService spyService = Mockito.spy(service);
            doReturn(Optional.empty()).when(spyService).getProductById(productId);

            Result<String> result = spyService.bookmarkProductRecord(userId, productId);

            assertEquals(404, result.getCode());
            assertEquals("收藏的商品不存在", result.getMsg());
        }
    }




}