package projectmd2.presentation.admin.securityquestion;

import projectmd2.business.feature.designImpl.SecurityFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class SecurityQuestionMenu {
    public static void showSecurityQuesttionMenu(Scanner sc) {
        while (true) {
            System.out.println(Colors.CYAN+"************* SECURITY QUESTION MANAGEMENT *************"+Colors.RESET);
            System.out.println("1. Displays a list of security questions");
            System.out.println("2. Add security questions");
            System.out.println("3. Edit security questions");
            System.out.println("4. Delete security questions");
            System.out.println("5. Back");
            System.out.println(Colors.CYAN+"********************************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    SecurityFeature.showAllSecurityQuestion();
                    break;
                case 2:
                    SecurityFeature.addNewSecurityQuestion(sc);
                    break;
                case 3:
                    SecurityFeature.editSecurityQuestionById(sc);
                    break;
                case 4:
                    SecurityFeature.deleteSecurityQuestionById(sc);
                    break;
                case 5:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
