package projectmd2.presentation.admin.ordermenu;

import projectmd2.business.feature.orderimpl.OrderFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

public class OrderMenu {
    public static void showOrderMenu() {
        while (true){
            System.out.println(Colors.CYAN + "******************** ORDER MENU **********************" + Colors.RESET);
            System.out.println("1. Display All Order");
            System.out.println("2. Order Detail");
            System.out.println("3. Display Order By Status");
            System.out.println("4. Change Status Order By Id");
            System.out.println("5. Back to Main Menu");
            System.out.println(Colors.CYAN + "******************************************************" + Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    OrderFeature.showAllOrder(true);
                    break;
                case 2:
                    OrderFeature.detailOrder(true);
                    break;
                case 3:
                    OrderFeature.displayOrderByStatus(true);
                    break;
                case 4:
                    OrderFeature.changeStatus();
                    break;
                case 5:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
