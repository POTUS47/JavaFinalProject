package com.finalproject.service;


import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.exception.BusinessTagException;
import com.finalproject.model.Product;
import com.finalproject.model.StoreBusinessDirection;
import com.finalproject.model.SubCategory;
import com.finalproject.repository.ProductRepository;
import com.finalproject.repository.StoreBusinessDirectionRepository;
import com.finalproject.repository.SubCategoryRepository;
import com.finalproject.util.SnowflakeIdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final SnowflakeIdGenerator idGenerator;

    private ProductRepository productRepository;
    private StoreBusinessDirectionRepository storeBusinessDirectionRepository;
    private SubCategoryRepository subCategoryRepository;

    ProductService(){
        idGenerator = new SnowflakeIdGenerator();
    }

    public String addProduct( addProductDTO newProduct) {
        // 创建并保存商品实体
        Product product = createAndSaveProduct(newProduct);

        // 处理商品详情
        //saveProductDetails(product.getId(), newProduct.getPicDes());

        // 管理商家的运营方向
        manageStoreBusinessDirection(newProduct);

        return product.getProductId();
    }

    private Product createAndSaveProduct(addProductDTO newProduct) {
        // 生成唯一的 PRODUCT_ID
        String productId = "p" + idGenerator.nextId();

        // 创建商品实体并保存
        Product product = new Product(productId,newProduct.getProductName(),newProduct.getProductPrice(),newProduct.getQuantity(),
                newProduct.getTag(),newProduct.getDescription(),newProduct.getSubTag());

        productRepository.save(product);

        return product;
    }


//    private void saveProductDetails(String productId, List<ProductDetailDTO> details) {
//        if (details != null && !details.isEmpty()) {
//            details.forEach(detail -> {
//                ProductDetail pd = new ProductDetail(productId, detail);
//                productDetailRepository.save(pd);
//            });
//        }
//    }

    private void manageStoreBusinessDirection(addProductDTO newProduct)  {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(newProduct.getSubTag());
        if (subCategory.isEmpty()) {
            throw new BusinessTagException("SUBCATEGORY_NOT_FOUND", "子类别不存在");
        }

        // 检查该商家的TAG是否已经存在
        String businessTag = newProduct.getTag() + subCategory.get().getSubcategoryName();
        Optional<StoreBusinessDirection> existingTag = storeBusinessDirectionRepository.findByStoreIdAndBusinessTag(
                newProduct.getStoreId(), businessTag);

        existingTag.orElseGet(() -> {
            StoreBusinessDirection newStoreTag = new StoreBusinessDirection(
                    newProduct.getStoreId(), businessTag, 0);
            return storeBusinessDirectionRepository.save(newStoreTag);
        }).addLinkCount();

    }
}
