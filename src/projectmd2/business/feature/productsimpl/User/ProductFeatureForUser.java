package projectmd2.business.feature.productsimpl.User;

import projectmd2.business.design.ICategory;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Category;
import projectmd2.business.entity.Products;
import projectmd2.business.feature.categoryimpl.CategoryImpl;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductFeatureForUser {
    private static IProducts productsList = new ProductsImpl();
    private static ICategory categoryList = new CategoryImpl();
    public static void displayAllProductsUser(){
        System.out.println(Colors.CYAN+"****************LIST PRODUCTS *****************"+Colors.RESET);
        System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s \n"+Colors.RESET
                ,"ID","Product Name","Description","Price", "Category","Stock");
        productsList.findAll().stream().filter(p->p.getCategory().isStatus()).forEach(Products::displayDataForUser);
        System.out.println(Colors.GREEN+"Has" + productsList.findAll().stream().filter(p->p.getCategory().isStatus()).count() +" products"+Colors.RESET);
    }

    public static void showNewProductMenuForUser() {
        System.out.println(Colors.CYAN+"**************** PRODUCTS NEWEST ****************"+Colors.RESET);
        System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s \n"+Colors.RESET
                ,"ID","Product Name","Description","Price", "Category","Stock");
        productsList.findAll().stream().filter(p->p.getCategory().isStatus())
                .sorted(Comparator.comparing(Products::getCreatedAt).reversed())
                .forEach(Products::displayDataForUser);
        System.out.println(Colors.CYAN+"*************************************************"+Colors.RESET);
    }

    public static void showProductsByCategories(Scanner sc) {
        System.out.println(Colors.CYAN+"**********LIST CATEGORIES**********"+Colors.RESET);
        System.out.printf(Colors.GREEN+"%3s | %15s  \n"+Colors.RESET,"ID","Name");
        categoryList.findAll().stream().filter(Category::isStatus).forEach(Category::displayDataforUser);
        System.out.println(Colors.CYAN+"***********************************"+Colors.RESET);
        System.out.println(Colors.CYAN+"Enter category ID you want to show products"+Colors.RESET);
        int choice = InputMethods.getInteger();
        Category category = categoryList.findById(choice);
        if(category != null && category.isStatus()){
            System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s \n"+Colors.RESET
                    ,"ID","Product Name","Description","Price", "Category","Stock");
            productsList.findAll().stream().filter(p->p.getCategory().isStatus()
                            && p.getCategory().getCategoryId() == choice)
                    .forEach(Products::displayDataForUser);
        }else{
            System.err.println("Category not found");
        }
    }

    public static void showProductDetailById() {
        System.out.println(Colors.CYAN+"Enter product ID you want to show"+Colors.RESET);
        int productId = InputMethods.getInteger();
        if(productsList.findById(productId) != null && productsList.findById(productId).getCategory().isStatus()){
            System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s \n"+Colors.RESET
                    ,"ID","Product Name","Description","Price", "Category","Stock");
            productsList.findById(productId).displayDataForUser();
        }else {
            System.err.println("Product not found");
        }
    }

    public static void searchProductByNameOrDescriptions() {
        System.out.println(Colors.CYAN+"Enter product name or description you want to search"+Colors.RESET);
        String productName = InputMethods.getString();
        System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s \n"+Colors.RESET
                ,"ID","Product Name","Description","Price", "Category","Stock");
        productsList.findAll().stream().filter(p->p.getCategory().isStatus()
                && (p.getProductName().contains(productName) || p.getDescription().contains(productName)))
                .forEach(Products::displayDataForUser);
    }

    public static void showSellingProducts() {
        if(productsList.findAll().isEmpty()){
            System.err.println("No products found");
            return;
        }
        System.out.println(Colors.CYAN+"****************** TOP 3 SELLING PRODUCTS ******************"+Colors.RESET);
        System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s   \n"+Colors.RESET
                ,"ID","Product Name","Descriptions","Price","Category","Stock");
        productsList.findAll().stream().filter(p->p.getCategory().isStatus()).sorted(Comparator.comparing(Products::getSelled).reversed()).limit(3).forEach(Products::displayDataForUser);
    }
}
