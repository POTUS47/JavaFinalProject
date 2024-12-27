package com.finalproject.controller;


import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.exception.BusinessTagException;
import com.finalproject.model.Buyer;
import com.finalproject.model.Image;
import com.finalproject.model.Product;
import com.finalproject.model.ProductImage;
import com.finalproject.service.ImageService;
import com.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productController")
public class ProductController {
    @Autowired
    private  ImageService imageService;

    @Autowired
    private ProductService productService;


    //添加新商品但不传图片
    @PostMapping("/addNewProduct")
    public ResponseEntity<Result<String>> addNewProduct(@RequestBody addProductDTO newProduct, Authentication authentication){
        //商家Id
        String userId = (String) authentication.getPrincipal();
        Result<String> response = productService.addProduct(userId,newProduct);
        return ResponseEntity.status(response.getCode()).body(response);

//        if (productImages == null || productImages.isEmpty()) {
//            Result result = Result.error(400, "上传的图片为空");
//            return ResponseEntity.status();
//        }
//
//        // 创建并保存商品实体
//        String productId = productService.addProduct(newProduct);
    }

    //添加商品图片
//    @PostMapping("/addProductImage")
//    public ResponseEntity<Result<String>> addProductImage(@RequestParam("ProductImages") List<MultipartFile> productImages){
//
//    }

    //
    @PostMapping("/GetProductDetails")
    public ResponseEntity<Result<productDetailDTO>> getProductDetails(@RequestParam String productId,Authentication auth) {
        String userId = (String) auth.getPrincipal();
        Result<productDetailDTO> response = productService.getProductDetail(productId, userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    // 根据 product_id 获取 Product 信息
    @GetMapping("/product/{productId}")
    public Optional<Product> getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    // 根据 product_id 获取 Product 信息
    @GetMapping("/products/{storeId}")
    public List<Product> getProductsByStoreId(@PathVariable String storeId) {
        return productService.getProductsByStoreId(storeId);
    }

    // 根据商品ID获取商品的所有图片
    @GetMapping("/productImages/{productId}")
    public List<ProductImage> getProductImagesByProductId(@PathVariable String productId) {
        return productService.getProductImagesByProductId(productId);
    }

}