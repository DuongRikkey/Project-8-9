package projectmd2.presentation.admin.usermenu;

import projectmd2.business.entity.User;
import projectmd2.business.feature.userimpl.EditUserForAdminFeature;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.*;

import java.util.Scanner;

public class UserMenuEditForAdmin {
    private static UserImpl userList = new UserImpl();

    public static void showEditUserForUser(Scanner sc, User user) {
        while (true){
            System.out.println(Colors.CYAN+"************EDIT USER "+Colors.RESET + Colors.RED+ user.getUserName().toUpperCase()+Colors.RESET + Colors.CYAN+ "**************"+Colors.RESET);
            System.out.println("1.Edit Full Name");
            System.out.println("2.Edit Password");
            System.out.println("3.Edit Email");
            System.out.println("4.Edit Phone");
            System.out.println("5.Edit Address");
            System.out.println("6.Edit Security Question");
            System.out.println("7.Edit Role");
            System.out.println("8.Edit Status");
            System.out.println("9.Back");
            System.out.println(Colors.CYAN+"*********************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    EditUserForAdminFeature.changeFullName(user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 2:
                    EditUserForAdminFeature.changePassword(sc, user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 3:
                    EditUserForAdminFeature.changeEmail(sc, user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 4:
                    EditUserForAdminFeature.changePhone(sc, user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 5:
                    EditUserForAdminFeature.changeAddress(user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 6:
                    EditUserForAdminFeature.changeSecurityQuestion(sc, user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 7:
                    EditUserForAdminFeature.changeRole(user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 8:
                    EditUserForAdminFeature.changeStatus(user);
                    System.out.println(Colors.GREEN + " Edit successfully" + Colors.RESET);
                    break;
                case 9:
                    userList.save(user);
                    IOFile.writeToFile(ShopConstant.USER_PATH, userList.findAll());
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }

        }


    }
}
