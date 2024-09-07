package projectmd2.presentation.admin;

import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.admin.categorymenu.CategoryMenu;
import projectmd2.presentation.admin.ordermenu.OrderMenu;
import projectmd2.presentation.admin.productsmenu.ProductsMenu;
import projectmd2.presentation.admin.securityquestion.SecurityQuestionMenu;
import projectmd2.presentation.admin.statisticsmenu.StatisticsMenu;
import projectmd2.presentation.admin.usermenu.UserMenu;

import java.util.Scanner;

public class DashBoardView {
    public static void showDashBoardView(Scanner sc) {
        while (true) {
            System.out.println(Colors.CYAN + "******************** DASHBOARD **********************" + Colors.RESET);
            System.out.println("1. Category Management System");
            System.out.println("2. Products Management System");
            System.out.println("3. User Management System Administrator");
            System.out.println("4. Security Question Management System");
            System.out.println("5. Order Management System");
            System.out.println("6. Statistics Management System");
            System.out.println("7. Log out");
            System.out.println(Colors.CYAN + "*****************************************************" + Colors.RESET);
            System.out.println(Colors.GREEN + "Enter your choice" + Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    CategoryMenu.showCategoryMenu(sc);
                    break;
                case 2:
                    ProductsMenu.showProductsMenu(sc);
                    break;
                case 3:
                    UserMenu.showUserMenu(sc);
                    break;
                case 4:
                    SecurityQuestionMenu.showSecurityQuesttionMenu(sc);
                    break;
                case 5:
                    OrderMenu.showOrderMenu();
                    break;
                case 6:
                    StatisticsMenu.showStatisticsMenu();
                    break;
                case 7:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }

    }
}
