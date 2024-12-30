package com.finalproject.controller;
import com.finalproject.DTO.FavouriteDTOs;
import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.exception.BusinessTagException;
import com.finalproject.model.Buyer;
import com.finalproject.model.Image;
import com.finalproject.model.Product;
import com.finalproject.model.ProductImage;
import com.finalproject.repository.CategoryRepository;
import com.finalproject.service.ImageService;
import com.finalproject.service.ProductService;
import com.finalproject.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productController")
public class ProductController {

    private ProductService productService;
    private RecommendService recommendService;

    @Autowired
    ProductController(ProductService productService, RecommendService recommendService) {
        this.productService = productService;
        this.recommendService = recommendService;
    }

    //通过id获取本店所有商品
    @GetMapping("/GetProductsByStoreIdAndViewType")
    public ResponseEntity<List<ProductDTO>> getProductsByStoreIdAndViewType(@RequestParam("storeId") String storeId,
                                                                            @RequestParam("isBuyer") Boolean isBuyer,
                                                                            Authentication auth) {
        String userId = (String) auth.getPrincipal();
        Result<List<ProductDTO>> response= productService.getAllProduct(userId,storeId,isBuyer);
        return ResponseEntity.status(response.getCode()).body(response.getData());
    }

    // 根据 productId 获取 Product 信息
    @GetMapping("/product/{productId}")
    public Optional<Product> getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    // 根据 productId 修改 product 库存
    @PutMapping("/product/{productId}")
    public Integer reduceProductById(@PathVariable String productId) {
        System.out.println("Received PATCH request for productId: " + productId);
        return productService.reduceProductById(productId);
    }

    // 查找某店铺的所有商品
    @GetMapping("/products/{storeId}")
    public List<Product> getProductsByStoreId(@PathVariable String storeId) {
        return productService.getProductsByStoreId(storeId);
    }

    // 根据商品ID获取商品的所有图片
    @GetMapping("/productImages/{productId}")
    public List<ProductImage> getProductImagesByProductId(@PathVariable String productId) {
        return productService.getProductImagesByProductId(productId);
    }

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

    //获取商品的图文描述
    @GetMapping("/getProductDetails/{productId}")
    public ResponseEntity<Result<List<DesPic>>> getDesPic(@PathVariable("productId") String productId){
        Result<List<DesPic>> response = productService.getDesPic(productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //用户在商家中根据关键词搜索商品
    @GetMapping("/search")
    public ResponseEntity<Result<List<ShowProductDTO>>> search(@RequestParam String storeId, @RequestParam String keyword) {
        Result<List<ShowProductDTO>> response=productService.searchInStore(storeId, keyword);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //用户根据关键词搜索全部商品
    @GetMapping("/searchAll")
    public ResponseEntity<Result<List<ShowProductDTO>>> search( @RequestParam String keyword) {
        Result<List<ShowProductDTO>> response=productService.searchAll(keyword);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //修改某个图文详情中的文字描述
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
    //没有图片会返回默认图片的url
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

    @GetMapping("/GetAllCategories")
    public ResponseEntity<Result<List<CatSubDTO>>> getAllCategoriesWithSubcategories() {
        Result<List<CatSubDTO>> response = productService.getAllCategoriesWithSubcategories();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //用小分类id获取商品
    @GetMapping("/Classification/getProductsBySubTagId")
    public ResponseEntity<Result<List<GCDDTO>>> getProductsBySubTagId(@RequestParam("subTagId") String subTagId) {
        Result<List<GCDDTO>> response=productService.getProductsBySubTagId(subTagId);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    // 为用户推荐商品
    @GetMapping("/recommend/user")
    public ResponseEntity<List<Map<String, Object>>> recommendForUser(Authentication auth) {
        String userId = (String) auth.getPrincipal();
        try {
            List<Map<String, Object>> recommendations = recommendService.recommendForUser(userId);
            return ResponseEntity.ok(recommendations);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // 查找相似商品
    @PostMapping("/recommend/similar-products")
    public ResponseEntity<List<Map<String, Object>>> findSimilarProducts(
            @RequestBody Map<String, Object> request) {
        try {
            String imagePath = (String) request.get("imagePath");
            int resultNum = (int) request.getOrDefault("resultNum", 10);
            if (imagePath == null || imagePath.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            List<Map<String, Object>> similarProducts = recommendService.findSimilarProducts(imagePath, resultNum);
            return ResponseEntity.ok(similarProducts);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}