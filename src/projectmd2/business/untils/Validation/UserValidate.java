package projectmd2.business.untils.Validation;

import projectmd2.business.design.ISecurityQuestion;
import projectmd2.business.design.IUser;
import projectmd2.business.entity.SecurityQuestion;
import projectmd2.business.feature.designImpl.SecurityQuestionImpl;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserValidate {
    private static IUser userList = new UserImpl();
    private static ISecurityQuestion securityQuestionList = new SecurityQuestionImpl();
    public static String inputUsername(Scanner sc){
        String userNameRegex = "^[a-zA-Z0-9]{6,100}$";
        while(true){
            System.out.println("Enter username : ");
            String userName = InputMethods.getString();
            if(Pattern.matches(userNameRegex,userName)){
                if(!userList.existByUsername(userName)){
                    return userName;
                }else {
                    System.out.println(ShopMessage.ERROR_USERNAME_EXIST);
                }
            }else{
                System.err.println(ShopMessage.ERROR_USERNAME_INVALID);
            }
        }
    }

    //Has at least 1 digit and 1 uppercase character
    public static String inputPassword(Scanner sc) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z]).+$";
        while(true){
            System.out.println("Enter password : ");
            String password = InputMethods.getString();
            if(Pattern.matches(passwordRegex,password)){
                return password;
            }else {
                System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }

    public static String inputFullName() {
        System.out.println("Enter full name : ");
        return InputMethods.getString();
    }

    public static String inputEmail(Scanner sc) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        while(true){
            System.out.println("Enter email : ");
            String email = InputMethods.getString();
            if(Pattern.matches(emailRegex,email)){
                return email;
            }else {
                System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }

    // Region Viet Nam
    public static String inputPhone(Scanner sc) {
        String regionVn = "+84";
        String phoneRegex = "";
        while(true){
            System.out.println("Enter phone number : ");
            long phone = InputMethods.getLong();
             phoneRegex = String.valueOf(phone);
            if(phoneRegex.length() == 9){
                break;
            }else {
                System.err.println("Phone must have 9 digits");
            }
        }
        return regionVn+ phoneRegex;
    }

    public static String inputAddress() {
        System.out.println("Enter address : ");
        return InputMethods.getString();
    }

    public static String inputAvarta() {
        System.out.println("Enter avatar : ");
        return InputMethods.getString();
    }


    public static Object inputSecurityQuestion(Scanner sc) {
        System.out.println("********List Security Question*********");
        securityQuestionList.findAll().forEach(SecurityQuestion::displayData);
        while (true){
            System.out.println("Enter the Id question you want");
            int choice = InputMethods.getInteger();
            if(securityQuestionList.findById(choice) != null){
                System.out.println("Enter your answer");
                String answer = InputMethods.getString();
                Map<String,String> secu = new HashMap<>();
                secu.put(securityQuestionList.findById(choice).getQuestion(),answer);
                return secu;
            }else {
                System.err.println("No question found, Please try again!");
            }
        }
    }
}
