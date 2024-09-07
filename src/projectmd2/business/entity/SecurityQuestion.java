package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopConstant;
import projectmd2.business.untils.Validation.SecurityQuestionValidate;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class SecurityQuestion implements IOData<SecurityQuestion,String>, Serializable {
    private int id;
    private String question;

    public SecurityQuestion() {
    }

    public SecurityQuestion(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {

        this.question = SecurityQuestionValidate.securityQuestionValidate(sc);
        List<SecurityQuestion> sqlist = null;
        if(isAdd){
            this.id = getNewId(sqlist, ShopConstant.SECURITY_QUESTION_PATH);
        }
    }

    @Override
    public void displayData() {
        System.out.println(this.id+". " +this.getQuestion());
    }

    @Override
    public int getNewId(List<SecurityQuestion> list, String path) {
        list = IOFile.readFromFile(path);
        int idMax = 0;
        for(SecurityQuestion sq : list){
            if(sq.getId() > idMax){
                idMax = sq.getId();
            }
        }
        return idMax +1;
    }
}
