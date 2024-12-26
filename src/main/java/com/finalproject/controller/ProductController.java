package com.finalproject.controller;


import com.finalproject.model.Image;
import com.finalproject.service.ImageService;
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

    private final ImageService imageService;

    @Autowired
    public ProductController(ImageService imageService) {
        this.imageService = imageService;
    }

//    @PostMapping("/addNewProduct")
//    public ResponseEntity<String> addNewProduct(@RequestBody addProductDTO newProduct) {
//
//    }

    @PostMapping("/upload")
    public ResponseEntity<List<Image>> uploadImages(@RequestParam("files") List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 过滤掉空文件并上传
        List<Image> savedImages = files.stream()
                .filter(file -> !file.isEmpty())
                .map(file -> {
                    try {
                        // 假设所有的图片都属于 "测试" 类型
                        return imageService.saveImage(file, "测试");
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to save image", e);
                    }
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(savedImages, HttpStatus.CREATED);
    }

}