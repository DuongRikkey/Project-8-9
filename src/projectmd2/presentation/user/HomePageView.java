package projectmd2.presentation.user;

import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.run.Main;
import projectmd2.presentation.user.cartforuser.CartMenuForUser;
import projectmd2.presentation.user.ordermenuforuser.OrderMenuForUser;
import projectmd2.presentation.user.productforuser.ProductMenuForUser;
import projectmd2.presentation.user.userinformation.UserInformationMenu;
import projectmd2.presentation.user.wishlistforuser.WishListMenu;

import java.util.Scanner;

public class HomePageView {
    public static void showHomePageViewMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("**************HOME PAGE MENU**************");
            System.out.println("1. Shop");
            System.out.println("2. User Information");
            System.out.println("3. Cart");
            System.out.println("4. Order");
            System.out.println("5. Wishlist");
            System.out.println("6. Log out");
            System.out.println("*******************************************");
            System.out.println("Enter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    ProductMenuForUser.showProductMenuForUser(sc);
                    break;
                case 2:
                    UserInformationMenu.showUserInformation();
                    break;
                case 3:
                    CartMenuForUser.showCartMenuForUser();
                    break;
                case 4:
                    OrderMenuForUser.showOrderMenuForUser();
                    break;
                case 5:
                    WishListMenu.showWishListMenu();
                    break;
                case 6:
                    Main.userLogin = null;
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
