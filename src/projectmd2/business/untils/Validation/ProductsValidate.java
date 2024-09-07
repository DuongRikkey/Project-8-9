package projectmd2.business.untils.Validation;

import projectmd2.business.design.ICategory;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Category;
import projectmd2.business.feature.categoryimpl.CategoryImpl;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;
import java.util.UUID;

public class ProductsValidate {
    private static IProducts products = new ProductsImpl();
    private static ICategory category = new CategoryImpl();

    // Can't be null and can't be duplicated
    public static String inputProductName(Scanner sc){
        while (true){
            System.out.println("Enter product name: ");
            String productName = InputMethods.getString();
            if(!products.existByUsername(products.findAll(), productName)){
                return productName;
            }else{
                System.err.println(ShopMessage.ERROR_PRODUCTNAME_EXIST);
            }
        }
    }

    // Created 1 UUID random
    public static String inputProductSku() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // Can't be null
    public static String inputProductDescriptions(Scanner sc) {
        System.out.println("Enter product description: ");
        return InputMethods.getString();
    }

    // Can't be null
    public static String inputProductImage(Scanner sc) {
        System.out.println("Enter product image: ");
        return InputMethods.getString();
    }

    //Can't be null and can't be less than 0
    public static int inputProductQuantity(Scanner sc) {
        while (true){
            System.out.println("Enter product quantity: ");
            int productQuantity = InputMethods.getInteger();
            if(productQuantity >=0){
                return productQuantity;
            }else {
                System.err.println(ShopMessage.ERROR_NUMBER_QUANTITY);
            }
        }
    }

    //Can't be null and can't be less than or equal to 0
    public static double inputUnitPrice(Scanner sc) {
        while (true){
            System.out.println("Enter product unit: ");
            double productUnitPrice = InputMethods.getDouble();
            if(productUnitPrice >0){
                return productUnitPrice;
            }else {
                System.err.println("UnitPrice cannot be less than 0");
            }
        }
    }

    //Can't be null and wrong id category
    public static Category inputProductCategory(Scanner sc) {
        System.out.println("*******List Category*******");
        category.findAll().forEach(System.out::println);
        while(true){
            System.out.println("Enter id category you want to add: ");
            int categoryId = InputMethods.getInteger();
            if(category.findById(categoryId) != null){
                return category.findById(categoryId);
            }
            else {
                System.err.println("Category does not exist, please try again");
            }
        }
    }
}
