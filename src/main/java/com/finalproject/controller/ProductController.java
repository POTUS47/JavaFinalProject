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
    private ProductService productService;

    //添加新商品但不传图片
    @PostMapping("/addNewProduct")
    public ResponseEntity<Result<String>> addNewProduct(@RequestBody addProductDTO newProduct, Authentication authentication){
        String userId = (String) authentication.getPrincipal();
        Result<String> response = productService.addProduct(userId,newProduct);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //添加商品图片
    @PostMapping("/addProductImage")
    public ResponseEntity<Result<String>> addProductImage(@RequestParam("ProductImages") List<MultipartFile> productImages,
                                                          @RequestParam("productId") String productId){
        Result<String> response = null;
        try {
            response = productService.addPhoto(productImages,productId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(response.getCode()).body(response);

    }

    //删除一个商品的图片 todo test
    //
    @DeleteMapping("/deleteProductImage/{productId}/{imageId}")
    public ResponseEntity<Result<String>> deleteProImage(@PathVariable("productId") String productId,
                                                         @PathVariable("imageId") String imageId){
        Result<String> response = productService.deleteProImage(productId,imageId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //添加商品图文详情
    //返回productId
    @PostMapping("/addProductDesPic")
    public ResponseEntity<Result<String>> addDesPic(@RequestBody List<uploadDesPic> despic,
                                                          @RequestParam("productId") String productId){
        Result<String> response = null;
        try {
            response = productService.addDesPic(despic,productId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(response.getCode()).body(response);

    }

    //获取商品的图文描述 todo test
    @GetMapping("/getProductDetails/{productId}")
    public ResponseEntity<Result<List<DesPic>>> getDesPic(@PathVariable("productId") String productId){
        Result<List<DesPic>> response = productService.getDesPic(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //用户在商家中根据关键词搜索商品todo test
    @GetMapping("/search")
    public ResponseEntity<Result<List<ShowProductDTO>>> search(@RequestParam String storeId, @RequestParam String keyword) {
        Result<List<ShowProductDTO>> response=productService.searchInStore(storeId, keyword);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //修改某个图文详情中的文字描述 todo test
    @PostMapping("/updateProductDescription")
    public ResponseEntity<Result<String>> updateProductDescription(@RequestBody UpdateDescriptionRequest request){
        Result<String> response = productService.updateDescription(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //为某商品删除一个图文详情 todo test
    //返回了对应商品的Id
    @PostMapping("/deleteProductDetail/{imageId}")
    public ResponseEntity<Result<String>> deleteProductDetail(@PathVariable("imageId") String imageId){
        Result<String> response = productService.deleteDetail(imageId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //获得某商品的所有图片
    //没有图片会返回默认图片的id todo test
    @GetMapping("/getProductImages/{productId}")
    public ResponseEntity<Result<List<String>>> getProductImages(@PathVariable("productId") String productId) {
        Result<List<String>> response=productService.getProductImages(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //修改商品信息 todo test
    @PutMapping("/editProduct")
    public ResponseEntity<Result<String>> editProduct(@RequestBody ShowProductDTO dto,Authentication auth){
        String userId = (String) auth.getPrincipal();
        Result<String> response = productService.editPro(dto,userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //获得商品的详细信息
    @PostMapping("/GetProductInfo")
    public ResponseEntity<Result<productDetailDTO>> getProductInfo(@RequestParam String productId,Authentication auth){
        String userId = (String) auth.getPrincipal();
        Result<productDetailDTO> response=productService.getProductDetail(productId,userId);
        return ResponseEntity.status(response.getCode()).body(response);}

    // 根据 product_id 获取 Product 信息
    @GetMapping("/product/{productId}")
    public Optional<Product> getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    // 根据 product_id 获取 Product 信息
    @GetMapping("/products/{storeId}")
    public ResponseEntity<List<Product>> getProductsByStoreId(@PathVariable String storeId) {
        return ResponseEntity.ok(productService.getProductsByStoreId(storeId));
    }

    // 根据商品ID获取商品的所有图片
    @GetMapping("/productImages/{productId}")
    public List<ProductImage> getProductImagesByProductId(@PathVariable String productId) {
        return productService.getProductImagesByProductId(productId);
    }

}