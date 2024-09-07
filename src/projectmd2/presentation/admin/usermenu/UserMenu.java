package projectmd2.presentation.admin.usermenu;

import projectmd2.business.feature.userimpl.UserFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class UserMenu {
    public static void showUserMenu(Scanner sc) {
        while (true) {
            System.out.println(Colors.CYAN+"*************** USER MENU **************"+Colors.RESET);
            System.out.println("1. Display All User");
            System.out.println("2. Edit User");
            System.out.println("3. Delete User");
            System.out.println("4. Search User By Name ");
            System.out.println("5. Display All Role");
            System.out.println("6. Exit");
            System.out.println(Colors.CYAN+"****************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    UserFeature.displayUser();
                    break;
                case 2:
                    UserFeature.editUser(sc);
                    break;
                case 3:
                    UserFeature.deleteUser(sc);
                    break;
                case 4:
                    UserFeature.findByName(sc);
                    break;
                case 5:
                    UserFeature.displayAllRole();
                    break;
                case 6:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
