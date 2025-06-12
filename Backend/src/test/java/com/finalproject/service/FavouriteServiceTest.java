package com.finalproject.service;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.ImageModels;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import com.finalproject.DTO.FavouriteDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.BookmarkStore;
import com.finalproject.model.Buyer;
import com.finalproject.model.Product;
import com.finalproject.model.Store;
import com.finalproject.repository.BookmarkProductRepository;
import com.finalproject.repository.BookmarkStoreRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

class FavouriteServiceTest {

    @Mock
    BookmarkStoreRepository mockStoreRepo;
    @Mock
    BookmarkProductRepository mockProductRepo;
    @Mock RestTemplate mockRest;

    FavouriteService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new FavouriteService(mockStoreRepo, mockProductRepo, mockRest);
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
            ResponseEntity<List<Product>> resp = new ResponseEntity<>(products, HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            assertEquals(1, result.size());
        }

        @Test
        @DisplayName("T02 - 商家ID合法但不存在于数据库")
        void testValidStoreIdButNotFound() {
            String storeId = "581960972537862";
            ResponseEntity<List<Product>> resp = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNull(result);
        }

        @Test
        @DisplayName("T03 - 商家ID合法但没有商品")
        void testValidStoreIdWithEmptyList() {
            String storeId = "581960972537862";
            ResponseEntity<List<Product>> resp = new ResponseEntity<>(List.of(), HttpStatus.OK);

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
        @DisplayName("T04 - storeId 为 null")
        void testStoreIdIsNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                service.getProductsByStoreId(null);
            });
        }

        @Test
        @DisplayName("T05 - storeId 为空字符串")
        void testStoreIdEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                service.getProductsByStoreId("");
            });
        }

        @Test
        @DisplayName("T06 - storeId 为最小值 0")
        void testStoreIdMinValue() {
            String storeId = "0";
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = new ResponseEntity<>(products, HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            assertEquals(1, result.size());
        }

        @Test
        @DisplayName("T07 - storeId 为略高于最小值 1")
        void testStoreIdSlightlyAboveMin() {
            String storeId = "1";
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = new ResponseEntity<>(products, HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            assertEquals(1, result.size());
        }

        @Test
        @DisplayName("T08 - storeId 超过最大长度 101 位")
        void testStoreIdSlightlyAboveMax() {
            String storeId = "1".repeat(101); // 101字符
            assertThrows(IllegalArgumentException.class, () -> {
                service.getProductsByStoreId(storeId);
            });
        }

        @Test
        @DisplayName("T09 - storeId 为最大值 100 位")
        void testStoreIdMaxLength() {
            String storeId = "9".repeat(100);
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = new ResponseEntity<>(products, HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            assertEquals(1, result.size());
        }

        @Test
        @DisplayName("T10 - storeId 为略低于最大值 99 位")
        void testStoreIdSlightlyBelowMax() {
            String storeId = "9".repeat(99);
            List<Product> products = generateProducts(1, storeId);
            ResponseEntity<List<Product>> resp = new ResponseEntity<>(products, HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/productController/products/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            List<Product> result = service.getProductsByStoreId(storeId);
            assertNotNull(result);
            assertEquals(1, result.size());
        }
    }

    @Nested
    @DisplayName("getStoreById(storeId)")
    class GetStoreById {
        private Store createMockStore(String storeId) {
            Store store = new Store();
            store.setStoreName("test");
            store.setAccountId(storeId);
            store.setEmail("test@test.com");
            store.setUserName("test");
            store.setPassword("test");
            return store;
        }

        @Test
        @DisplayName("T01 - 商家ID合法存在，返回Optional<Store>")
        void testValidStoreIdReturnsOptional() {
            String storeId = "581960972537861";
            Store store = createMockStore(storeId);
            ResponseEntity<Optional<Store>> resp = new ResponseEntity<>(Optional.of(store), HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent());
            assertEquals("SName", result.get().getStoreName());
        }

        @Test
        @DisplayName("T02 - 商家ID合法但数据库无记录，返回null")
        void testStoreNotFoundReturnsNull() {
            String storeId = "581960972537862";
            ResponseEntity<Optional<Store>> resp = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertNull(result);
        }

        @Test
        @DisplayName("T03 - storeId 为 null")
        void testStoreIdNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                service.getStoreById(null);
            });
        }

        @Test
        @DisplayName("T04 - storeId 为空字符串")
        void testStoreIdEmpty() {
            assertThrows(IllegalArgumentException.class, () -> {
                service.getStoreById("");
            });
        }

        @Test
        @DisplayName("T05 - storeId 为最小值 0")
        void testStoreIdMinValue() {
            String storeId = "0";
            Store store = createMockStore(storeId);
            ResponseEntity<Optional<Store>> resp = new ResponseEntity<>(Optional.of(store), HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent());
            assertEquals("MinStore", result.get().getStoreName());
        }

        @Test
        @DisplayName("T06 - storeId 为略高于最小值 1")
        void testStoreIdSlightlyAboveMin() {
            String storeId = "1";
            Store store = createMockStore(storeId);
            ResponseEntity<Optional<Store>> resp = new ResponseEntity<>(Optional.of(store), HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent());
            assertEquals("SlightMin", result.get().getStoreName());
        }

        @Test
        @DisplayName("T07 - storeId 超过最大长度 101 位")
        void testStoreIdSlightlyAboveMax() {
            String storeId = "1".repeat(101);
            assertThrows(IllegalArgumentException.class, () -> {
                service.getStoreById(storeId);
            });
        }

        @Test
        @DisplayName("T08 - storeId 为最大长度 100 位")
        void testStoreIdMaxLength() {
            String storeId = "9".repeat(100);
            Store store = createMockStore(storeId);
            ResponseEntity<Optional<Store>> resp = new ResponseEntity<>(Optional.of(store), HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent());
            assertEquals("MaxStore", result.get().getStoreName());
        }

        @Test
        @DisplayName("T09 - storeId 为略低于最大值 99 位")
        void testStoreIdSlightlyBelowMax() {
            String storeId = "9".repeat(99);
            Store store = createMockStore(storeId);
            ResponseEntity<Optional<Store>> resp = new ResponseEntity<>(Optional.of(store), HttpStatus.OK);

            when(mockRest.exchange(
                    eq("http://api/api/users/getStore/" + storeId),
                    eq(HttpMethod.GET),
                    isNull(),
                    any(ParameterizedTypeReference.class))
            ).thenReturn(resp);

            Optional<Store> result = service.getStoreById(storeId);
            assertTrue(result.isPresent());
            assertEquals("BelowMax", result.get().getStoreName());
        }
    }

    @Nested
    @DisplayName("GetFavouriteStores")
    class GetFavouriteStores {

        @Spy
        FavouriteService spyService;

        @BeforeEach
        void setup() {
            MockitoAnnotations.openMocks(this);
            spyService = Mockito.spy(new FavouriteService(mockStoreRepo, mockProductRepo, mockRest));
            ReflectionTestUtils.setField(spyService, "baseUrl", "http://api");
        }

        private Product createMockProduct(String storeId) {
            Product product = new Product();
            String productId = "123456";
            String productName = "Product";
            BigDecimal productPrice = BigDecimal.valueOf(10.0);
            int quantity = 100 ;
            String tag = "tag";
            String description = "Description of product";
            String storeTag = "storeTag";
            String subCategory = "subCategory";
            product.setStoreId(storeId);
            product.setProductId(productId);
            product.setDescription(description);
            product.setProductName(productName);
            product.setProductPrice(productPrice);
            product.setQuantity(quantity);
            product.setTag(tag);
            product.setSubCategory(subCategory);
            product.setStoreTag(storeTag);
            return product;
        }

        private Store createMockStore(String storeId) {
            Store store = new Store();
            store.setStoreName("test");
            store.setAccountId(storeId);
            store.setEmail("test@test.com");
            store.setUserName("test");
            store.setPassword("test");
            return store;
        }

        private BookmarkStore createBookmarkStore(String userId,String storeId) {
            BookmarkStore bookmarkStore = new BookmarkStore();
            bookmarkStore.setStoreAccountId(storeId);
            bookmarkStore.setBuyerAccountId(userId);
            return bookmarkStore;
        }

        @Test
        @DisplayName("T01 - 用户ID合法存在且有收藏商家")
        void testValidUserIdWithFavourites() {
            String userId = "581960972537860";
            String storeId= "181960972537860";
            // mock 1 个收藏记录
            BookmarkStore bookmark =createBookmarkStore(userId,storeId);
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(List.of(bookmark));
            // mock getStoreById
            Store store = createMockStore(storeId);
            doReturn(Optional.of(store)).when(spyService).getStoreById(storeId);
            // mock getProductsByStoreId
            Product product = createMockProduct(storeId);
            doReturn(List.of(product)).when(spyService).getProductsByStoreId(storeId);
            // mock getProductImagesById
            doReturn(List.of("http://image.jpg")).when(spyService).getProductImagesById(product.getProductId());
            // 调用实际方法
            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);
            // 断言结果
            assertEquals(200, result.getCode());
            assertNotNull(result.getData());
            assertEquals(1, result.getData().size());

            FavouriteStoresDTO dto = result.getData().get(0);
            assertEquals(storeId, dto.getStoreId());
            List<ProductDTO> products = dto.getProducts();
            assertEquals(1, products.size());
        }

        @Test
        @DisplayName("T02 - 用户ID合法存在但无收藏商家")
        void testValidUserIdWithoutFavourites() {
            String userId = "581960972537861";
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());

            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);

            assertEquals(404, result.getCode());
            assertEquals("没有收藏任何店铺", result.getMsg());
        }

        @Test
        @DisplayName("T03 - 用户ID合法但不存在于数据库")
        void testValidUserIdButNotInDb() {
            String userId = "581960972537862";
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(null);
            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);
            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T04 - 用户ID为 null（健壮边界值法）")
        void testUserIdIsNull() {
            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(null);
            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T05 - 用户ID为 \"\"（空字符串）")
        void testUserIdEmpty() {
            String userId = "";
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());
            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);
            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T06 - 用户ID为 0（最小值）")
        void testUserIdMinValue() {
            String userId = "0";
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());
            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);
            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T07 - 用户ID为 1（略高于最小值）")
        void testUserIdSlightlyAboveMin() {
            String userId = "1";
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());

            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);

            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T08 - 用户ID为 超长字符串（略高于最大值）")
        void testUserIdSlightlyAboveMax() {
            String userId = "1".repeat(101); // 假设最大长度为100
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());
            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);
            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T09 - 用户ID为最大长度字符串（100位）")
        void testUserIdMaxLength() {
            String userId = "9".repeat(100);
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());
            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);
            assertEquals(404, result.getCode());
            assertEquals("没有收藏任何店铺", result.getMsg());
        }

        @Test
        @DisplayName("T10 - 用户ID略低于最大长度（99位）")
        void testUserIdSlightlyBelowMax() {
            String userId = "9".repeat(99);
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(Collections.emptyList());

            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);

            assertEquals(404, result.getCode());
            assertEquals("没有收藏任何店铺", result.getMsg());
        }

        @Test
        @DisplayName("T11 - 用户ID合法，恰好一个收藏店铺")
        void testUserIdHasOneStore() {
            String userId = "581960972537863";
            String storeId = "store111";
            BookmarkStore bookmark = createBookmarkStore(userId, storeId);
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(List.of(bookmark));
            Store store = createMockStore(storeId);
            doReturn(Optional.of(store)).when(spyService).getStoreById(storeId);

            Product product = createMockProduct(storeId);
            doReturn(List.of(product)).when(spyService).getProductsByStoreId(storeId);
            doReturn(List.of("pic.jpg")).when(spyService).getProductImagesById(product.getProductId());

            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);

            assertEquals(200, result.getCode());
            assertNotNull(result.getData());
            assertEquals(1, result.getData().size());

            FavouriteStoresDTO dto = result.getData().get(0);
            assertEquals(storeId, dto.getStoreId());
            List<ProductDTO> products = dto.getProducts();
            assertEquals(1, products.size());
        }

        @Test
        @DisplayName("T12 - 用户ID合法，有两个收藏店铺")
        void testUserIdHasTwoStores() {
            String userId = "581960972537864";
            String storeId1 = "store1";
            String storeId2 = "store2";

            BookmarkStore b1 = createBookmarkStore(userId, storeId1);
            BookmarkStore b2 = createBookmarkStore(userId, storeId2);
            when(mockStoreRepo.findByBuyerId(userId)).thenReturn(List.of(b1, b2));

            Store s1 = createMockStore(storeId1);
            Store s2 = createMockStore(storeId2);
            doReturn(Optional.of(s1)).when(spyService).getStoreById(storeId1);
            doReturn(Optional.of(s2)).when(spyService).getStoreById(storeId2);

            Product prod = createMockProduct(storeId1);
            doReturn(List.of(prod)).when(spyService).getProductsByStoreId(anyString());
            doReturn(List.of("picx.jpg")).when(spyService).getProductImagesById(prod.getProductId());

            Result<List<FavouriteStoresDTO>> result = spyService.getFavouriteStores(userId);

            assertEquals(200, result.getCode());
            assertNotNull(result.getData());
            assertEquals(2, result.getData().size());
        }

    }

    @Nested
    @DisplayName("BookmarkProduct")
    class BookmarkProduct {

        @Test
        @DisplayName("T01 - 收藏成功")
        void testT01_bookmarkSuccess() {
            mockStoreExists("1529237152305151");
            when(mockStoreRepo.existsByBuyerIdAndStoreId("581960972537861", "1529237152305151")).thenReturn(false);
            Result<String> result = service.bookmarkStore("581960972537861", "1529237152305151");
            assertEquals(200, result.getCode());
            assertEquals("收藏成功", result.getMsg());
        }

        @Test
        @DisplayName("T02 - 取消收藏成功")
        void testT02_cancelBookmarkSuccess() {
            mockStoreExists("1529237152305152");
            when(mockStoreRepo.existsByBuyerIdAndStoreId("581960972537861", "1529237152305152")).thenReturn(true);
            Result<String> result = service.bookmarkStore("581960972537861", "1529237152305152");
            assertEquals(200, result.getCode());
            assertEquals("取消收藏成功", result.getMsg());
        }

        @Test
        @DisplayName("T03 - 用户和商家不存在")
        void testT03_bothNotExist() {
            mockStoreNotExists("1529237152305150");
            Result<String> result = service.bookmarkStore("581960972537860", "1529237152305150");
            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T04 - 用户不存在")
        void testT04_userNotExist() {
            mockStoreExists("1529237152305151");

            Result<String> result = service.bookmarkStore("581960972537860", "1529237152305151");

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T05 - 商家不存在")
        void testT05_storeNotExist() {
            mockStoreNotExists("1529237152305150");

            Result<String> result = service.bookmarkStore("581960972537861", "1529237152305150");

            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T06 - userId为null")
        void testT06_userIdNull() {
            Result<String> result = service.bookmarkStore(null, "1529237152305151");

            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T07 - userId为空字符串")
        void testT07_userIdEmpty() {
            Result<String> result = service.bookmarkStore("", "1529237152305151");

            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T08 - userId为0")
        void testT08_userIdZero() {
            mockStoreExists("1529237152305151");

            Result<String> result = service.bookmarkStore("0", "1529237152305151");

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T09 - userId为1")
        void testT09_userIdOne() {
            mockStoreExists("1529237152305151");

            Result<String> result = service.bookmarkStore("1", "1529237152305151");

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T10 - userId略低于最大值")
        void testT10_userIdNearMaxMinusOne() {
            String userId = "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998";
            mockStoreExists("1529237152305151");

            Result<String> result = service.bookmarkStore(userId, "1529237152305151");

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T11 - userId最大值")
        void testT11_userIdMax() {
            String userId = "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
            mockStoreExists("1529237152305151");

            Result<String> result = service.bookmarkStore(userId, "1529237152305151");

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T12 - userId略高于最大值")
        void testT12_userIdAboveMax() {
            String userId = "1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            Result<String> result = service.bookmarkStore(userId, "1529237152305151");

            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T13 - productId为null")
        void testT13_productIdNull() {
            Result<String> result = service.bookmarkStore("581960972537861", null);

            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T14 - productId为空字符串")
        void testT14_productIdEmpty() {
            Result<String> result = service.bookmarkStore("581960972537861", "");

            assertEquals(404, result.getCode());
        }

        @Test
        @DisplayName("T15 - productId为0")
        void testT15_productIdZero() {
            mockStoreExists("0");

            Result<String> result = service.bookmarkStore("581960972537861", "0");

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T16 - productId为1")
        void testT16_productIdOne() {
            mockStoreExists("1");

            Result<String> result = service.bookmarkStore("581960972537861", "1");

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T17 - productId略低于最大值")
        void testT17_productIdNearMaxMinusOne() {
            String storeId = "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998";
            mockStoreExists(storeId);

            Result<String> result = service.bookmarkStore("581960972537861", storeId);

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T18 - productId最大值")
        void testT18_productIdMax() {
            String storeId = "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
            mockStoreExists(storeId);

            Result<String> result = service.bookmarkStore("581960972537861", storeId);

            assertEquals(200, result.getCode());
        }

        @Test
        @DisplayName("T19 - productId略高于最大值")
        void testT19_productIdAboveMax() {
            String storeId = "1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            Result<String> result = service.bookmarkStore("581960972537861", storeId);

            assertEquals(404, result.getCode());
        }

        // 辅助方法
        void mockStoreExists(String storeId) {
            Store store = new Store();
            store.setAccountId(storeId);
            store.setStoreName("Test Store");
            store.setStoreScore(BigDecimal.valueOf(4.5));
            when(mockRest.getForObject("http://api/store/" + storeId, Store.class)).thenReturn(store);
        }

        void mockStoreNotExists(String storeId) {
            when(mockRest.getForObject("http://api/store/" + storeId, Store.class)).thenReturn(null);
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
        }

        @Test
        @DisplayName("T02 - 合法ID存在但未收藏")
        void testT02_notBookmarked() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "529237152305152"))
                    .thenReturn(false);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "529237152305152");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
        }

        @Test
        @DisplayName("T03 - 用户和商品不存在")
        void testT03_idsNotExist() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537860", "529237152305150"))
                    .thenReturn(false);

            Result<Boolean> result = service.isProductBookmarked("581960972537860", "529237152305150");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
        }

        @Test
        @DisplayName("T04 - userId不存在")
        void testT04_userNotExist() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537860", "529237152305151"))
                    .thenReturn(false);

            Result<Boolean> result = service.isProductBookmarked("581960972537860", "529237152305151");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
        }

        @Test
        @DisplayName("T05 - productId不存在")
        void testT05_productNotExist() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "529237152305150"))
                    .thenReturn(false);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "529237152305150");
            assertEquals(200, result.getCode());
            assertFalse(result.getData());
        }

        @Test
        @DisplayName("T06 - userId 为 null")
        void testT06_userIdNull() {
            assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked(null, "529237152305151"));
        }

        @Test
        @DisplayName("T07 - userId 为空字符串")
        void testT07_userIdEmpty() {
            assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked("", "529237152305151"));
        }

        @Test
        @DisplayName("T08 - userId 为最小值")
        void testT08_userIdMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("0", "529237152305151"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("0", "529237152305151");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
        }

        @Test
        @DisplayName("T09 - userId 略高于最小值")
        void testT09_userIdAboveMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("1", "529237152305151"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("1", "529237152305151");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
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
        }

        @Test
        @DisplayName("T12 - userId 略高于最大值")
        void testT12_userIdAboveMax() {
            assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked("1".repeat(101), "529237152305151"));
        }

        @Test
        @DisplayName("T13 - productId 为 null")
        void testT13_productIdNull() {
            assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked("581960972537861", null));
        }

        @Test
        @DisplayName("T14 - productId 为空字符串")
        void testT14_productIdEmpty() {
            assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked("581960972537861", ""));
        }

        @Test
        @DisplayName("T15 - productId 为最小值")
        void testT15_productIdMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "0"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "0");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
        }

        @Test
        @DisplayName("T16 - productId 略高于最小值")
        void testT16_productIdAboveMin() {
            when(mockProductRepo.existsByBuyerIdAndProductId("581960972537861", "1"))
                    .thenReturn(true);

            Result<Boolean> result = service.isProductBookmarked("581960972537861", "1");
            assertEquals(200, result.getCode());
            assertTrue(result.getData());
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
        }

        @Test
        @DisplayName("T19 - productId 略高于最大值")
        void testT19_productIdAboveMax() {
            assertThrows(IllegalArgumentException.class, () -> service.isProductBookmarked("581960972537861", "1".repeat(101)));
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
