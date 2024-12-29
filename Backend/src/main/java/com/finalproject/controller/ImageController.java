package com.finalproject.controller;

import com.finalproject.DTO.Result;
import com.finalproject.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * 返回指定ID的图片资源
     *
     * @param imageId 图片的唯一标识符
     * @return 包含图片数据的HTTP响应
     */
    @GetMapping("/images/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageId) {
        return imageService.loadImageAsResource(imageId);
    }

    @PostMapping("/upload_image/{type}")
    public ResponseEntity<Result<String>> getImage(@RequestParam("file") MultipartFile file, @PathVariable String type) throws IOException {
       Result<String> result=imageService.saveImage(file, type);
        return ResponseEntity.status(result.getCode()).body(result);
    }




}