package projectmd2.presentation.user.wishlistforuser;

import projectmd2.business.feature.wishlist.WishListFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.awt.*;

public class WishListMenu {
    public static void showWishListMenu() {
        while (true) {
            System.out.println(Colors.CYAN + "*****************WISH LIST MENU******************" + Colors.RESET);
            System.out.println("1. Display Wish List");
            System.out.println("2. Add Product to Wish List");
            System.out.println("3. Remove Product from Wish List");
            System.out.println("4. Back to Main Menu");
            System.out.println(Colors.CYAN + "*************************************************" + Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    WishListFeature.displayAllWishlist();
                    break;
                case 2:
                    WishListFeature.addWishlist();
                    break;
                case 3:
                    WishListFeature.removeProductFromWishlist();
                    break;
                case 4:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }

    }
}
