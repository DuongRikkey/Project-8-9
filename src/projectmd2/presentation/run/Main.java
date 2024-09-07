package projectmd2.presentation.run;

import projectmd2.business.design.IUser;
import projectmd2.business.entity.User;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.user.GuestView;

import java.util.Scanner;

public class Main {
    private static IUser userLits = new UserImpl();
    public static User userLogin = null;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(Colors.CYAN+"*************** SHOP ***************"+Colors.RESET);
            System.out.println("1. Visit Shop");
            System.out.println("2. Login");
            System.out.println("3. Register");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");
            System.out.println(Colors.CYAN+"************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    GuestView.showGuestView();
                    break;
                case 2:
                    userLits.login();
                    break;
                case 3:
                    userLits.register();
                    break;
                case 4:
                    userLits.forgotPassword();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
