package com.finalproject.controller;

import com.finalproject.model.Account;
import com.finalproject.model.BookmarkProduct;
import com.finalproject.model.Product;
import com.finalproject.repository.BookmarkProductRepository;
import com.finalproject.repository.BuyerRepository;
import com.finalproject.repository.ProductRepository;
import com.finalproject.service.AccountService;
import com.finalproject.service.FavouriteService;
import com.finalproject.service.ProductService;
import com.finalproject.service.RecommendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private final AccountService userService;
    private final ProductService productService;
    private final RecommendService recommendService;
    private final FavouriteService favouriteService;

    private final BookmarkProductRepository bookmarkProductRepository;
    private final ProductRepository productRepository;




    public TestController(AccountService userService, ProductService productService,
                          RecommendService recommendService, FavouriteService favouriteService,
                          BookmarkProductRepository bookmarkProductRepository, ProductRepository productRepository
                          ) {
        this.userService = userService;
        this.productService = productService;
        this.recommendService = recommendService;
        this.favouriteService = favouriteService;
        this.bookmarkProductRepository = bookmarkProductRepository;
        this.productRepository = productRepository;
    }
    @PostMapping("/generate-all-users-feature")
    public ResponseEntity<Map<String,String> > generateUsersFeature(){
        Map<String,String> map = new HashMap<>();
        List<BookmarkProduct> bookmarks = bookmarkProductRepository.findAll();
        if (bookmarks.isEmpty()) {
            map.put("message","No bookmarks found");
            return ResponseEntity.status(404).body(map);
        }
        int index=0;
        for (BookmarkProduct bookmark : bookmarks) {
            String userId = bookmark.getBuyerId();  // 获取用户ID
            String productId = bookmark.getProductId();  // 获取商品ID
            String indexString = String.valueOf(++index);
            String output = userId + "收藏了" + productId;
            //System.out.println("第"+ ++index +"条数据：" +userId+"收藏了"+productId);
            map.put(indexString,output);

            // 使用商品ID在ProductRepository中查找商品
            Optional<Product> optionalProduct = productRepository.findById(productId);

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                // 获取商品的名称和描述
                String productName = product.getProductName();
                String productDescription = product.getDescription();
                // 将商品名称和描述放入String[]中
                String[] productDetails = {productName, productDescription};

                // 调用函数A，将userId和productDetails传递给它
                recommendService.updateUserFeatures(userId, productDetails);
            } else {
                // 如果找不到商品，可能需要记录一个警告或错误
                System.err.println("Product not found for productId: " + productId);
            }
        }
        // 返回成功响应
        return ResponseEntity.ok(map);
    }

    @PostMapping("/generate-all-products-feature")
    public ResponseEntity<Map<String,String> > generateProductsFeature(){
        Map<String,String> map = new HashMap<>();
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            map.put("message","No products found");
            return ResponseEntity.status(404).body(map);
        }
        int index=0;
        for (Product bookmark : products) {
            String name = bookmark.getProductName();  // 获取用户ID
            String productId = bookmark.getProductId();  // 获取商品ID
            String description = bookmark.getDescription();
            String indexString = String.valueOf(++index);
            String output = "商品"+ productId+ "有名称" + name;
            //System.out.println("第"+ ++index +"条数据：" +userId+"收藏了"+productId);
            map.put(indexString,output);
            recommendService.generateProductFeature(productId, description,name);
        }
        // 返回成功响应
        return ResponseEntity.ok(map);
    }



}
