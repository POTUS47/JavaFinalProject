package com.finalproject.controller;


import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.exception.BusinessTagException;
import com.finalproject.model.Image;
import com.finalproject.service.ImageService;
import com.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productController")
public class ProductController {
    @Autowired
    private  ImageService imageService;
    @Autowired
    private ProductService productService;


    @PostMapping("/addNewProduct")
    public Result addNewProduct(@RequestBody addProductDTO newProduct,@RequestParam("ProductImages") List<MultipartFile> productImages) throws IOException {

        if (productImages == null||productImages.isEmpty()) {
            return new Result("400","上传的产品图片为空");
        }

        try {
            // 创建并保存商品实体
            String productId = productService.addProduct(newProduct);

            int num=imageService.saveImage(productImages,"商品");

            return Result.success("成功添加商品，商品Id:"+productId+"成功添加"+num+"张商品图片");
        } catch (BusinessTagException e) {
            return new Result(e.getErrorCode(),e.getErrorMessage());
        } catch (Exception e) {
            return new Result(e.getMessage(),e.getMessage());
        }
    }

//    @PostMapping("/upload")
//    public Result uploadImages(@RequestParam("files") List<MultipartFile> files) throws IOException {
//        if (files == null || files.isEmpty()) {
//            Result result=new Result("400","上传的产品图片为空");
//            return result;
//        }
//
//        // 过滤掉空文件并上传
//        List<Image> savedImages = files.stream()
//                .filter(file -> !file.isEmpty())
//                .map(file -> {
//                    try {
//                        // 假设所有的图片都属于 "测试" 类型
//                        return imageService.saveImage(file, "测试");
//                    } catch (IOException e) {
//                        throw new RuntimeException("Failed to save image", e);
//                    }
//                })
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(savedImages, HttpStatus.CREATED);
//    }

}