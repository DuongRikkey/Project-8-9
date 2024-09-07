package projectmd2.business.feature.designImpl;

import projectmd2.business.design.ISecurityQuestion;
import projectmd2.business.entity.SecurityQuestion;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopConstant;

import java.awt.*;
import java.util.Scanner;

public class SecurityFeature {
    private static ISecurityQuestion securityQuestionList = new SecurityQuestionImpl();
    public static void addNewSecurityQuestion(Scanner sc){
        System.out.println("Enter the number of questions you want to add");
        int number = InputMethods.getInteger();
        for(int i = 0; i< number ;i++){
            SecurityQuestion newSecu = new SecurityQuestion();
            newSecu.inputData(sc,true);
            securityQuestionList.save(newSecu);
        }
        System.out.println("Add "+number+" security question success!");
        IOFile.writeToFile(ShopConstant.SECURITY_QUESTION_PATH,securityQuestionList.findAll());
    }

    public static void showAllSecurityQuestion(){
        if (securityQuestionList.findAll().isEmpty()){
            System.err.println("List security question is empty");
            return;
        }
        System.out.println("--------List Security Question--------");
        securityQuestionList.findAll().forEach(SecurityQuestion::displayData);
        System.out.println("---------------------------------------");
    }
    public static void editSecurityQuestionById(Scanner sc){
        System.out.println("Enter security question ID you want to edit");
        int secuId = InputMethods.getInteger();
        SecurityQuestion security = securityQuestionList.findById(secuId);
        if(security != null){
            System.out.println(Colors.YELLOW +"Old security information" + Colors.RESET);
            security.displayData();
            System.out.println(Colors.YELLOW +"New security Information" + Colors.RESET);
            security.inputData(sc,false);
            securityQuestionList.save(security);
            System.out.println(Colors.GREEN + "Edit security question with ID: "+secuId + " success!" + Colors.RESET);
        }else {
            System.err.println("Cant find security question with ID: "+secuId);
        }
    }

    public static void deleteSecurityQuestionById(Scanner sc) {
        System.out.println("Enter security question ID you want to edit");
        int secuId = InputMethods.getInteger();
        SecurityQuestion security = securityQuestionList.findById(secuId);
        if(security != null){
           securityQuestionList.deleteById(secuId);
            System.out.println(Colors.GREEN + "Delete security question with ID: "+secuId + " success!" + Colors.RESET);
        }else {
            System.err.println("Cant find security question with ID: "+secuId);
        }
    }
}
