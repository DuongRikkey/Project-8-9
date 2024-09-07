package projectmd2.presentation.user.productforuser;

import projectmd2.business.feature.productsimpl.Admin.ProductsFeature;
import projectmd2.business.feature.productsimpl.User.ProductFeatureForUser;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class ProductMenuForUser {
    public static void showProductMenuForUser(Scanner sc) {
        while (true){
            System.out.println(Colors.CYAN+"**************** PRODUCTS MENU ***************"+Colors.RESET);
            System.out.println("1. Display All Products");
            System.out.println("2. Products New");
            System.out.println("3. Selling Prodcuts");
            System.out.println("4. Products By Categories");
            System.out.println("5. Show Product Detail By Id");
            System.out.println("6. Search Product By Name Or Description");
            System.out.println("7. Back");
            System.out.println(Colors.CYAN+"***********************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choiceProducts = InputMethods.getByte();
            switch (choiceProducts) {
                case 1:
                    ProductFeatureForUser.displayAllProductsUser();
                    break;
                case 2:
                    ProductFeatureForUser.showNewProductMenuForUser();
                    break;
                case 3:
                    ProductFeatureForUser.showSellingProducts();
                    break;
                case 4:
                    ProductFeatureForUser.showProductsByCategories(sc);
                    break;
                case 5:
                    ProductFeatureForUser.showProductDetailById();
                    break;
                case 6:
                    ProductFeatureForUser.searchProductByNameOrDescriptions();
                    break;
                case 7:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
