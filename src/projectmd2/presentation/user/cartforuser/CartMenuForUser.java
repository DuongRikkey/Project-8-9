package projectmd2.presentation.user.cartforuser;

import projectmd2.business.feature.cartimpl.CartFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class CartMenuForUser {
    public static void showCartMenuForUser() {
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println(Colors.CYAN + "**************CART MANAGEMENT FOR USER**************" + Colors.RESET);
            System.out.println("1. Display cart");
            System.out.println("2. Add to cart");
            System.out.println("3. Remove product from cart by ID");
            System.out.println("4. Remove all cart products");
            System.out.println("5. Cart Detail");
            System.out.println("6. Check out");
            System.out.println("7. Back");
            System.out.println(Colors.CYAN + "****************************************************" + Colors.RESET);
            System.out.println(Colors.CYAN + "Enter your choice:" + Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    CartFeature.displayCart();
                    break;
                case 2:
                    CartFeature.addToCart(sc);
                    break;
                case 3:
                    CartFeature.removeProductFromCartById();
                    break;
                case 4:
                    CartFeature.removeAllCart();
                    break;
                case 5:
                    CartFeature.cartDetail();
                    break;
                case 6:
                    CartFeature.checkOut(sc);
                    break;
                case 7:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
