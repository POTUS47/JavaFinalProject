package com.finalproject.DTO;

import com.finalproject.model.SubCategory;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTOs {

    public static class addProductDTO{
        private String ProductName;
        private BigDecimal ProductPrice ;
        private String Tag ;
        private String SubTag ;
        private String Description;
        private String StoreTag ;
        private Integer Quantity ;
//        private List<IFormFile>? ProductImages ;
//        private List<PicDes>? PicDes ;


        public String getProductName() {
            return ProductName;
        }
        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }
        public BigDecimal getProductPrice() {
            return ProductPrice;
        }
        public void setProductPrice(BigDecimal ProductPrice) {
            this.ProductPrice = ProductPrice;
        }
        public String getTag() {
            return Tag;
        }
        public void setTag(String Tag) {
            this.Tag = Tag;
        }
        public String getSubTag() {
            return SubTag;
        }
        public void setSubTag(String SubTag) {
            this.SubTag = SubTag;
        }
        public String getDescription() {
            return Description;
        }
        public void setDescription(String Description) {
            this.Description = Description;
        }
        public String getStoreTag() {
            return StoreTag;
        }
        public void setStoreTag(String StoreTag) {
            this.StoreTag = StoreTag;
        }
        public Integer getQuantity() {
            return Quantity;
        }
        public void setQuantity(Integer Quantity) {
            this.Quantity = Quantity;
        }


    }

    public static class ProductDTO{
        private String productId;
        private String productName;
        private BigDecimal productPrice ;
        private String tag ;
        private String subTag ;
        private String description;
        private String storeTag ;
        private Integer quantity ;
        private Boolean isOnSale;
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Boolean getOnSale() {
            return isOnSale;
        }

        public void setOnSale(Boolean onSale) {
            isOnSale = onSale;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getStoreTag() {
            return storeTag;
        }

        public void setStoreTag(String storeTag) {
            this.storeTag = storeTag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSubTag() {
            return subTag;
        }

        public void setSubTag(String subTag) {
            this.subTag = subTag;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public BigDecimal getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }
    public static class DesPic{
        private String Url;
        private String Description;

        public DesPic(String Url, String Description) {
            this.Url = Url;
            this.Description = Description;
        }

        public String getUrl() {
            return Url;
        }

        public String getDescription() {
            return Description;
        }
    }

    public static class uploadDesPic{
        private MultipartFile file;
        private String Description;
        private String imageId;



        public String getDescription() {
            return Description;
        }

        public MultipartFile getPic() {
            return file;
        }

        public String getImageId() {
            return imageId;
        }
        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public void setFile(MultipartFile file) {
            this.file = file;
        }

        public void setDescription(String description) {
            Description = description;
        }
    }

    public static class productDetailDTO{
        private String ProductName;
        private BigDecimal ProductPrice ;
        private String Tag ;
        private String SubTag ;
        private String Description;
        private String StoreTag ;
        private Integer Quantity ;
        private String StoreName ;
        private String StoreId;
        private String FromWhere;
        private BigDecimal StoreScore;
        private Boolean IsProductStared;
        private Boolean IsStoreStared;
        private List<String> Pictures;
        private List<DesPic> ImageAndText;

        public productDetailDTO(String ProductName,
                                BigDecimal ProductPrice,
                                String Tag,
                                String SubTag,
                                String Description,
                                String StoreTag,
                                Integer Quantity,
                                String StoreName,
                                String StoreId,
                                String FromWhere,
                                BigDecimal StoreScore,
                                Boolean IsProductStared,
                                Boolean IsStoreStared,
                                List<String> Pictures,
                                List<DesPic> ImageAndText) {
            this.ProductName = ProductName;
            this.ProductPrice = ProductPrice;
            this.Tag = Tag;
            this.SubTag = SubTag;
            this.Description = Description;
            this.StoreTag = StoreTag;
            this.Quantity = Quantity;
            this.StoreName = StoreName;
            this.StoreId = StoreId;
            this.FromWhere = FromWhere;
            this.StoreScore = StoreScore;
            this.IsProductStared = IsProductStared;
            this.IsStoreStared = IsStoreStared;
            this.Pictures = Pictures;
            this.ImageAndText = ImageAndText;
        }

        public String getProductName() {
            return ProductName;
        }

        public BigDecimal getProductPrice() {
            return ProductPrice;
        }

        public String getTag() {
            return Tag;
        }

        public String getSubTag() {
            return SubTag;
        }

        public String getDescription() {
            return Description;
        }

        public String getStoreTag() {
            return StoreTag;
        }

        public Integer getQuantity() {
            return Quantity;
        }

        public String getStoreName() {
            return StoreName;
        }

        public String getStoreId() {
            return StoreId;
        }

        public String getFromWhere() {
            return FromWhere;
        }

        public BigDecimal getStoreScore() {
            return StoreScore;
        }

        public Boolean getProductStared() {
            return IsProductStared;
        }

        public Boolean getStoreStared() {
            return IsStoreStared;
        }

        public List<String> getPictures() {
            return Pictures;
        }

        public List<DesPic> getImageAndText() {
            return ImageAndText;
        }
    }

    public static class ProductIdDTO{
        private String productId;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }

    public static class ShowProductDTO {
        private String productId;
        private String productName;
        private BigDecimal productPrice;
        private int quantity;
        private String tag;
        private String subTag;
        private String description;
        private String productPic;
        private String storeTag;
        private Boolean isOnSale;
        ShowProductDTO(){

        }
        public ShowProductDTO(String productId, String productName,
                              BigDecimal productPrice, int quantity,
                              String tag, String subTag,
                              String description,String productPic,
                              String storeTag) {
            this.productId = productId;
            this.productName = productName;
            this.productPrice = productPrice;
            this.quantity = quantity;
            this.tag = tag;
            this.subTag = subTag;
            this.description = description;
            this.productPic = productPic;
            this.storeTag = storeTag;

        }

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public BigDecimal getProductPrice() {
            return productPrice;
        }

        public String getTag() {
            return tag;
        }

        public String getSubTag() {
            return subTag;
        }

        public String getDescription() {
            return description;
        }

        public String getProductPic() {
            return productPic;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getStoreTag() {
            return storeTag;
        }

        public Boolean getOnSale() {
            return isOnSale;
        }

        public void setOnSale(Boolean onSale) {
            isOnSale = onSale;
        }
    }

    public static class UpdateDescriptionRequest{
        private String ImageId;
        private String Description;

        public UpdateDescriptionRequest(String ImageId, String Description) {
            this.ImageId = ImageId;
            this.Description = Description;
        }

        public void setImageId(String ImageId) {
            this.ImageId = ImageId;
        }
        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getImageId() {
            return ImageId;
        }

        public String getDescription() {
            return Description;
        }
    }

    public static class CatSubDTO{
        private String LargeCategoryName;
        private List<CategoryDTO> SubCategories;

        public CatSubDTO(String LargeCategoryName, List<CategoryDTO> SubCategories) {
            this.LargeCategoryName = LargeCategoryName;
            this.SubCategories = SubCategories;
        }
        public String getLargeCategoryName() {
            return LargeCategoryName;
        }
        public List<CategoryDTO> getSubCategories() {
            return SubCategories;
        }
    }

    public static class CategoryDTO{
        private String SubCategoryName;
        private String SubCategoryId;

        public CategoryDTO(String SubCategoryName, String SubCategoryId) {
            this.SubCategoryName = SubCategoryName;
            this.SubCategoryId = SubCategoryId;
        }
        public String getSubCategoryName() {
            return SubCategoryName;
        }
        public String getSubCategoryId() {
            return SubCategoryId;
        }
    }

    public static class GCDDTO{
        private String productId;
        private String productName;
        private String imageUrl;
        private BigDecimal productPrice;


        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getProductName() {
            return productName;
        }

        public BigDecimal getProductPrice() {
            return productPrice;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
        public void setProductPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductId() {
            return productId;
        }
        public void setProductId(String productId) {
            this.productId = productId;
        }
    }


}
