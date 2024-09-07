package projectmd2.business.feature.productsimpl.Admin;

import projectmd2.business.design.ICategory;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Category;
import projectmd2.business.entity.Products;
import projectmd2.business.feature.categoryimpl.CategoryImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.admin.productsmenu.EditProductMenu;

import java.util.Comparator;
import java.util.Scanner;

public class ProductsFeature {
    private static IProducts productsList = new ProductsImpl();
    private static ICategory categoryList = new CategoryImpl();

    public static void displayAllProducts() {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println(Colors.CYAN+"-----------------------------------------------------------------------------------------------------------------"+Colors.RESET);
        System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s | %5s | %15s | %15s \n"+Colors.RESET
                , "ID", "Product Name", "Description", "Unit Price", "Category","Stock","Sold", "Created At", "Updated At");
        productsList.findAll().forEach(Products::displayData);
        System.out.println(Colors.CYAN+"-----------------------------------------------------------------------------------------------------------------"+Colors.RESET);
        System.out.println(Colors.CYAN+"Has " + productsList.findAll().size() + " products"+Colors.RESET);
    }

    public static void addNewProducts(Scanner sc) {
        if(categoryList.findAll().isEmpty()){
            System.err.println("Category list is empty,Please add category first");
        }
        System.out.println("Enter the number of the products you want to create: ");
        int productsNumber = InputMethods.getInteger();
        for (int i = 0; i < productsNumber; i++) {
            System.out.println(Colors.GREEN + "Add product " + (i + 1) + Colors.RESET);
            Products newProduct = new Products();
            newProduct.inputData(sc, true);
            productsList.save(newProduct);
        }
        System.out.println(Colors.GREEN+"Add " + productsNumber + " products successfully"+Colors.RESET);

    }
    public static void editProductsById(Scanner sc) {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println("Enter product ID you want to edit: ");
        int productID = InputMethods.getInteger();
        Products newProduct = productsList.findById(productID);
        if (newProduct != null) {
            System.out.println(Colors.CYAN+"Old prodduct with ID " + productID + " information"+Colors.RESET);
            System.out.println(Colors.CYAN+"-----------------------------------------------------------------------------------------------------------------"+Colors.RESET);
            System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s | %5s | %15s | %15s \n"+Colors.RESET
                    , "ID", "Product Name", "Description", "Unit Price", "Category","Stock","Sold", "Created At", "Updated At");
            newProduct.displayData();
            System.out.println(Colors.CYAN+"-----------------------------------------------------------------------------------------------------------------"+Colors.RESET);
            EditProductMenu.showEditProductMenu(newProduct);
        } else {
            System.err.println("Cant find product with ID: " + productID);
        }
    }

    public static void deleteProductsById(Scanner sc) {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println("Enter product ID you want to delete: ");
        int productID = InputMethods.getInteger();
        Products newProduct = productsList.findById(productID);
        if (newProduct != null) {
            productsList.deleteById(productID);
            System.out.println(Colors.GREEN+"Deleted product with ID: " + productID + " successfully"+Colors.RESET);
        }else{
            System.err.println("Cant find product with ID: " + productID);
        }
    }

    public static void searchProductsById(Scanner sc) {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println("Enter product ID you want to search: ");
        int productID = InputMethods.getInteger();
        if (productsList.findById(productID) != null) {
            System.out.printf(Colors.GREEN+"%3s | %20s | %15s | %10s | %15s | %5s | %5s | %15s | %15s \n"+Colors.RESET
                    , "ID", "Product Name", "Description", "Unit Price", "Category","Stock","Sold", "Created At", "Updated At");
            productsList.findById(productID).displayData();
        }else{
            System.err.println("Cant find product with ID: " + productID);
        }
    }
}
