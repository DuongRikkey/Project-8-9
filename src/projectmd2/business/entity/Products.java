package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;
import projectmd2.business.untils.Validation.ProductsValidate;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Products implements IOData<Products, String>, Serializable {
    private int productId,stockQuantity,selled;
    private String productName,sku,image,description;
    private double unitPrice;
    private Category category;
    private Date createdAt,updatedAt;

    public Products() {
    }

    public Products(int productId, int stockQuantity, int selled, String productName, String sku, String image, String description, double unitPrice, Category category, Date createdAt, Date updatedAt) {
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.selled = selled;
        this.productName = productName;
        this.sku = sku;
        this.image = image;
        this.description = description;
        this.unitPrice = unitPrice;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getSelled() {
        return selled;
    }

    public void setSelled(int selled) {
        this.selled = selled;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {
        List<Products> productsList = null;
        if(isAdd){
            this.productId = getNewId(productsList, ShopConstant.PRODUCTS_PATH);
            this.createdAt = new Date();
        }
        this.productName = ProductsValidate.inputProductName(sc);
        this.sku = ProductsValidate.inputProductSku();
        this.description = ProductsValidate.inputProductDescriptions(sc);
        this.image = ProductsValidate.inputProductImage(sc);
        this.stockQuantity = ProductsValidate.inputProductQuantity(sc);
        this.unitPrice = ProductsValidate.inputUnitPrice(sc);
        this.category = ProductsValidate.inputProductCategory(sc);
        this.updatedAt = new Date();

    }

    @Override
    public void displayData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Locale localeVN = new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        System.out.printf("%3s | %20s | %15s | %10s | %15s | %5s | %5s | %15s | %15s \n"
                ,this.productId,this.productName,this.description,currencyFormatter.format(this.unitPrice),
                this.category.getCategoryName(),this.getStockQuantity(),this.selled,dateFormat.format(this.createdAt),dateFormat.format(this.updatedAt));
    }
    public void displayDataForUser() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Locale localeVN = new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        System.out.printf("%3s | %20s | %15s | %10s | %15s | %5s   \n"
                ,this.productId,this.productName,this.description,currencyFormatter.format(this.unitPrice),
                this.category.getCategoryName(),this.stockQuantity);
    }
    @Override
    public int getNewId(List<Products> list, String path) {
        list = IOFile.readFromFile(path);
        int idMax = 0;
        for(Products p : list){
            if(p.getProductId() >idMax){
                idMax = p.getProductId();
            }
        }
        return idMax+1;
    }
}
