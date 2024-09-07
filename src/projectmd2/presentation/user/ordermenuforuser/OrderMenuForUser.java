package projectmd2.presentation.user.ordermenuforuser;

import projectmd2.business.feature.orderimpl.OrderFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

public class OrderMenuForUser {
    public static void showOrderMenuForUser() {
        while (true){
            System.out.println(Colors.CYAN + "*********************** ORDER MENU ***********************" + Colors.RESET);
            System.out.println("1. Order history");
            System.out.println("2. Order detail");
            System.out.println("3. Display Order By Status");
            System.out.println("4. Exit");
            System.out.println(Colors.CYAN + "**********************************************************" + Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    OrderFeature.showAllOrder(false);
                    break;
                case 2:
                    OrderFeature.detailOrder(false);
                    break;
                case 3:
                    OrderFeature.displayOrderByStatus(false);
                    break;
                case 4:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
