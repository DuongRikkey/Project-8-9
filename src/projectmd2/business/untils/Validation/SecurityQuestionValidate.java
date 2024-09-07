package projectmd2.business.untils.Validation;

import projectmd2.business.design.ISecurityQuestion;
import projectmd2.business.entity.SecurityQuestion;
import projectmd2.business.feature.designImpl.SecurityQuestionImpl;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class SecurityQuestionValidate {
    private static ISecurityQuestion iSecurityQuestion = new SecurityQuestionImpl();
    public static String securityQuestionValidate(Scanner sc){
        while (true){
            System.out.println("Input security question");
            String secuQuestion = InputMethods.getString();
            if(iSecurityQuestion.findAll().stream().noneMatch(e->e.getQuestion().equals(secuQuestion))){
                return secuQuestion;
            }else {
                System.err.println("Security Question already exists, Please try again!");
            }
        }

    }
}
