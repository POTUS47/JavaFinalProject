package com.finalproject.service;

import com.finalproject.DTO.Result;
import com.finalproject.model.Image;
import com.finalproject.repository.ImageRepository;
import com.finalproject.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    private final ImageRepository imageRepository;
    private final ResourceLoader resourceLoader;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    public ImageService(ImageRepository imageRepository, ResourceLoader resourceLoader) {
        this.imageRepository = imageRepository;
        this.resourceLoader = resourceLoader;
        this.snowflakeIdGenerator = new SnowflakeIdGenerator();
    }

    /**
     * 上传图片并保存到文件系统中
     */
    public Result<String> saveImage(MultipartFile file, String type) throws IOException {
        if (file.isEmpty()) {
            return Result.error(404,"上传的文件为空");
        }
        // 生成唯一的文件名以避免覆盖同名文件
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
        String imageId = snowflakeIdGenerator.nextId();
        String uniqueFileName = imageId + fileExtension;

        // 构建完整的上传路径（包括特定的文件夹）
        Path specificUploadDir = Paths.get(uploadPath, type);
        if (!Files.exists(specificUploadDir)) {
            Files.createDirectories(specificUploadDir);
        }

        // 将文件保存到指定路径
        Path targetLocation = specificUploadDir.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), targetLocation);

        // 存储图片信息到数据库
        Image image = new Image();
        image.setImageId(imageId);
        image.setImageType(type);
        image.setFileName(uniqueFileName);

        // 使用绝对路径存储
        String absoluteFilePath = targetLocation.toAbsolutePath().toString();

        image.setFilePath(absoluteFilePath);
        imageRepository.save(image);
        return Result.success(imageId);
    }

    /**
     * 根据图片ID加载图片资源并返回HTTP响应
     */
    public ResponseEntity<Resource> loadImageAsResource(String imageId) {
        // 查找对应的图片记录
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("找不到对应的图片"));

        // 直接使用数据库中存储的绝对路径来加载图片资源
        String absoluteFilePath = image.getFilePath();

        // 打印或日志记录绝对路径用于调试
        System.out.println("Loading image from: " + absoluteFilePath);

        // 加载图片资源
        Resource resource = resourceLoader.getResource("file:" + absoluteFilePath);
        if (resource.exists() && resource.isReadable()) {
            // 设置适当的Content-Type头信息
            String contentType = getContentType(resource.getFilename());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String getContentType(String fileName) {
        // 根据文件名后缀确定Content-Type
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            String extension = fileName.substring(dotIndex + 1).toLowerCase();
            return switch (extension) {
                case "jpg", "jpeg" -> MediaType.IMAGE_JPEG_VALUE;
                case "png" -> MediaType.IMAGE_PNG_VALUE;
                case "gif" -> MediaType.IMAGE_GIF_VALUE;
                default -> MediaType.APPLICATION_OCTET_STREAM_VALUE;
            };
        } else {
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}