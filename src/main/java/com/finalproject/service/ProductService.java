package com.finalproject.service;


import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.model.Product;
import com.finalproject.repository.ProductRepository;
import com.finalproject.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final SnowflakeIdGenerator idGenerator;

    private ProductRepository productRepository;

    ProductService(){
        idGenerator = new SnowflakeIdGenerator();
    }

//    public String addProduct( addProductDTO newProduct) {
//        // 创建并保存商品实体
//        Product product = createAndSaveProduct(newProduct);
//
//        // 处理图片上传
//        saveImages(product.getId(), newProduct.getProductImages());
//
//        // 处理商品详情
//        saveProductDetails(product.getId(), newProduct.getPicDes());
//
//        // 管理商家的运营方向
//        manageStoreBusinessDirection(newProduct);
//
//        return product.getId();
//    }
//
//    private Product createAndSaveProduct(Product1DTO newProduct) {
//        // 生成唯一的 PRODUCT_ID
//        String productId = "p" + idGenerator.nextId();
//
//        // 创建商品实体并保存
//        Product product = new Product(productId, newProduct);
//        productRepository.save(product);
//
//        return product;
//    }
//
//    private void saveImages(String productId, List<ImageSource> images) {
//        if (images != null && !images.isEmpty()) {
//            images.forEach(image -> {
//                byte[] imageData = image.getImageData(); // 假设有一个方法来获取图像数据
//                ProductImage pi = new ProductImage(productId, imageData);
//                productImageRepository.save(pi);
//            });
//        }
//    }
//
//    private void saveProductDetails(String productId, List<ProductDetailDTO> details) {
//        if (details != null && !details.isEmpty()) {
//            details.forEach(detail -> {
//                ProductDetail pd = new ProductDetail(productId, detail);
//                productDetailRepository.save(pd);
//            });
//        }
//    }
//
//    private void manageStoreBusinessDirection(Product1DTO newProduct) throws CustomException {
//        Optional<SubCategory> subCategory = subCategoryRepository.findById(newProduct.getSubTag());
//        if (!subCategory.isPresent()) {
//            throw new CustomException("子分类不存在！", HttpStatus.NOT_FOUND);
//        }
//
//        // 检查该商家的TAG是否已经存在
//        String businessTag = newProduct.getTag() + subCategory.get().getSubcategoryName();
//        Optional<StoreBusinessDirection> existingTag = storeBusinessDirectionRepository.findByStoreIdAndBusinessTag(
//                newProduct.getStoreId(), businessTag);
//
//        existingTag.orElseGet(() -> {
//            StoreBusinessDirection newStoreTag = new StoreBusinessDirection(
//                    newProduct.getStoreId(), businessTag, 1);
//            return storeBusinessDirectionRepository.save(newStoreTag);
//        }).incrementLinkCount();
//    }
}
