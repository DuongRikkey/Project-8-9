package projectmd2.presentation.user;

import projectmd2.business.feature.productsimpl.User.ProductFeatureForUser;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

public class GuestView {
    public static void showGuestView() {
        while (true){
            System.out.println(Colors.CYAN + "********************** GUEST VIEW **********************" + Colors.RESET);
            System.out.println("1. Products");
            System.out.println("2. Back to Main Menu");
            System.out.println(Colors.CYAN + "********************************************************" + Colors.RESET);
            System.out.println(Colors.GREEN + "Enter your choice" + Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    ProductFeatureForUser.displayAllProductsUser();
                    break;
                case 2:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
