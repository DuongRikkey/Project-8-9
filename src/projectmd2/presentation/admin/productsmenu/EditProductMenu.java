package projectmd2.presentation.admin.productsmenu;

import projectmd2.business.design.ICategory;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Products;
import projectmd2.business.feature.categoryimpl.CategoryImpl;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.untils.*;

import java.awt.*;

public class EditProductMenu {
    private static IProducts productsList = new ProductsImpl();
    private static ICategory categoryList = new CategoryImpl();
    public static void showEditProductMenu(Products product) {
        while (true) {
            System.out.println(Color.CYAN+"**************** EDIT PRODUCT MENU ****************"+ Colors.RESET);
            System.out.println("1. Edit Product Name");
            System.out.println("2. Edit Product Description");
            System.out.println("3. Edit Product Price");
            System.out.println("4. Edit Product Quantity");
            System.out.println("5. Edit Product Category");
            System.out.println("6. Exit");
            System.out.println(Color.CYAN+"***************************************************"+ Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    System.out.println("Enter new product name");
                    String name = InputMethods.getString();
                    product.setProductName(name);
                    break;
                case 2:
                    System.out.println("Enter new product description");
                    String description = InputMethods.getString();
                    product.setDescription(description);
                    break;
                case 3:
                    System.out.println("Enter new product price");
                    double price = InputMethods.getDouble();
                    product.setUnitPrice(price);
                    break;
                case 4:
                    System.out.println("Enter new product quantity");
                    int productQuantity = InputMethods.getInteger();
                    product.setStockQuantity(productQuantity);
                    break;
                case 5:
                    System.out.println("**********List Category***********");
                    categoryList.findAll().forEach(System.out::println);
                    while (true){
                        System.out.println("Enter new category ID: ");
                        int categoryID = InputMethods.getInteger();
                        if (categoryList.findById(categoryID) != null) {
                            product.setCategory(categoryList.findById(categoryID));
                            break;
                        }else{
                            System.out.println("Category not found");
                        }
                    }
                    break;
                case 6:
                    productsList.save(product);
                    IOFile.writeToFile(ShopConstant.PRODUCTS_PATH,productsList.findAll());
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
