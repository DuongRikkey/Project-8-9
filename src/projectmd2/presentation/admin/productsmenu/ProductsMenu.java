package projectmd2.presentation.admin.productsmenu;

import projectmd2.business.feature.productsimpl.Admin.ProductsFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class ProductsMenu {
    public static void showProductsMenu(Scanner sc) {
        while (true) {
            System.out.println(Colors.CYAN+"*************** PRODUCTS MENU **************"+Colors.RESET);
            System.out.println("1. Display All Products");
            System.out.println("2. Add New Product");
            System.out.println("3. Edit Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product By ID");
            System.out.println("6. Exit");
            System.out.println(Colors.CYAN+"********************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    ProductsFeature.displayAllProducts();
                    break;
                case 2:
                    ProductsFeature.addNewProducts(sc);
                    break;
                case 3:
                    ProductsFeature.editProductsById(sc);
                    break;
                case 4:
                    ProductsFeature.deleteProductsById(sc);
                    break;
                case 5:
                    ProductsFeature.searchProductsById(sc);
                    break;
                case 6:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
