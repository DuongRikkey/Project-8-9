package projectmd2.presentation.user.addressforuser;

import projectmd2.business.entity.Address;
import projectmd2.business.feature.addressimpl.AddressFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class AddressForUserMenu {
    public static void addressForUserMenu() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(Colors.CYAN+"******************** ADDRESS MENU **********************"+Colors.RESET);
            System.out.println("1. List Address");
            System.out.println("2. Add Address");
            System.out.println("3. Edit Address");
            System.out.println("4. Delete Address By Id");
            System.out.println("5. Search Address By Id");
            System.out.println("6. Back");
            System.out.println(Colors.CYAN+"********************************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
            byte choiceAddress = InputMethods.getByte();
            switch (choiceAddress) {
                case 1:
                    AddressFeature.displayAllAddress();
                    break;
                case 2:
                    AddressFeature.addNewAddress(sc);
                    break;
                case 3:
                    AddressFeature.editAddressById(sc);
                    break;
                case 4:
                    AddressFeature.deleteAddressById(sc);
                    break;
                case 5:
                    AddressFeature.searchAddressById(sc);
                    break;
                case 6:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
