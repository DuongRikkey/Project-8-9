package projectmd2.business.feature.userimpl.user;

import projectmd2.business.design.IUser;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.business.untils.Validation.UserValidate;
import projectmd2.presentation.run.Main;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class UserOfUserImpl {
    private static UserImpl userList = new UserImpl();
    private static final int FIFTY = 50000;
    private static final int ONEHUNDRED = 100000;
    private static final int TWOHUNDRED = 200000;
    private static final int FIVEHUNDRED = 500000;

    public static void displayUser() {
        System.out.println(Colors.CYAN + "*********** INFORMATION OF USER: " + Colors.RESET + Colors.RED + Main.userLogin.getUserName() + Colors.RESET + Colors.CYAN + " **********" + Colors.RESET);
        Main.userLogin.displayDataforUser();
    }

    public static void editUser() {
        Scanner sc = new Scanner(System.in);
        Main.userLogin.inputData(sc, false);
        userList.save(Main.userLogin);
        System.out.println("Edit successfully");
    }

    public static void changePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Colors.CYAN + "Enter old password" + Colors.RESET);
        String oldPassword = InputMethods.getString();
        if (oldPassword.equals(Main.userLogin.getPassword())) {
            System.out.println(Colors.CYAN+"Enter new password" + Colors.RESET);
            String newPassword = UserValidate.inputPassword(sc);
            Main.userLogin.setPassword(newPassword);
            userList.save(Main.userLogin);
            System.out.println(Colors.GREEN + "Password changed successfully" + Colors.RESET);
        } else {
            System.err.println("Old password does not match");
        }
    }

    public static void recharge() {
        System.out.println(Colors.CYAN + "**********DEPOSIT MONEY INTO ACCOUNT**********" + Colors.RESET);
        System.out.println("1. 50.000 VND");
        System.out.println("2. 100.000 VND");
        System.out.println("3. 200.000 VND");
        System.out.println("4. 500.000 VND");
        System.out.println("5. Back");
        System.out.println(Colors.CYAN + "**********************************************" + Colors.RESET);
        System.out.println("Enter your choice");
        byte choice = InputMethods.getByte();
        Locale localeVN = new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        switch (choice) {
            case 1:
                Main.userLogin.setWallet(Main.userLogin.getWallet() + rechargeMoney(FIFTY));
                userList.save(Main.userLogin);
                System.out.println(Colors.GREEN + "Wallet changed successfully" + Colors.RESET);
                System.out.println(Colors.GREEN + "Your Wallet: " + currencyFormatter.format(Main.userLogin.getWallet()) + Colors.RESET);
                break;
            case 2:
                Main.userLogin.setWallet(Main.userLogin.getWallet() + rechargeMoney(ONEHUNDRED));
                userList.save(Main.userLogin);
                System.out.println(Colors.GREEN + "Wallet changed successfully" + Colors.RESET);
                System.out.println(Colors.GREEN + "Your Wallet: " + currencyFormatter.format(Main.userLogin.getWallet()) + Colors.RESET);
                break;
            case 3:
                Main.userLogin.setWallet(Main.userLogin.getWallet() + rechargeMoney(TWOHUNDRED));
                userList.save(Main.userLogin);
                System.out.println(Colors.GREEN + "Wallet changed successfully" + Colors.RESET);
                System.out.println(Colors.GREEN + "Your Wallet: " + currencyFormatter.format(Main.userLogin.getWallet()) + Colors.RESET);
                break;
            case 4:
                Main.userLogin.setWallet(Main.userLogin.getWallet() + rechargeMoney(FIVEHUNDRED));
                userList.save(Main.userLogin);
                System.out.println(Colors.GREEN + "Wallet changed successfully" + Colors.RESET);
                System.out.println(Colors.GREEN + "Your Wallet: " + currencyFormatter.format(Main.userLogin.getWallet()) + Colors.RESET);
                break;
            case 5:
                return;
            default:
                System.err.println(ShopMessage.ERROR_ALERT);
        }
    }

    private static int rechargeMoney(int fifty) {
        System.out.println(Colors.GREEN + "Enter number you would like to recharge money" + Colors.RESET);
        int number = InputMethods.getInteger();
        return fifty * number;
    }
}
