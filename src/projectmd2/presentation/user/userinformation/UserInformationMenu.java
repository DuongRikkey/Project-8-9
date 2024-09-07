package projectmd2.presentation.user.userinformation;

import projectmd2.business.feature.userimpl.user.UserOfUserImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.user.addressforuser.AddressForUserMenu;

public class UserInformationMenu {
    public static void showUserInformation() {
        while (true){
            System.out.println(Colors.CYAN+"**************** USER INFORMATION ****************"+Colors.RESET);
            System.out.println("1. View User Information");
            System.out.println("2. Edit User Information");
            System.out.println("3. Address User Information");
            System.out.println("4. Change Password");
            System.out.println("5. Recharge");
            System.out.println("6. Back");
            System.out.println(Colors.CYAN+"**************************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    UserOfUserImpl.displayUser();
                    break;
                case 2:
                    UserOfUserImpl.editUser();
                    break;
                case 3:
                    AddressForUserMenu.addressForUserMenu();
                    break;
                case 4:
                    UserOfUserImpl.changePassword();
                    break;
                case 5:
                    UserOfUserImpl.recharge();
                    break;
                case 6:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
