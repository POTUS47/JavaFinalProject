package com.finalproject.DTO;

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
        private MultipartFile Pic;
        private String Description;

        public uploadDesPic(MultipartFile pic, String Description) {
            this.Pic = pic;
            this.Description = Description;
        }

        public String getDescription() {
            return Description;
        }

        public MultipartFile getPic() {
            return Pic;
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


}
