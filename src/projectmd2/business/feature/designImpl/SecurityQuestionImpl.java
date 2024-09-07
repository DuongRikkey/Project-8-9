package projectmd2.business.feature.designImpl;

import projectmd2.business.design.ISecurityQuestion;
import projectmd2.business.entity.SecurityQuestion;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;

import java.util.List;

public class SecurityQuestionImpl implements ISecurityQuestion {
    private static List<SecurityQuestion> securityQuestions;

    public SecurityQuestionImpl() {
        securityQuestions = IOFile.readFromFile(ShopConstant.SECURITY_QUESTION_PATH);
    }

    @Override
    public List<SecurityQuestion> findAll() {
        return securityQuestions;
    }

    @Override
    public SecurityQuestion findById(Integer id) {
        return securityQuestions.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(SecurityQuestion securityQuestion) {
        if (findById(securityQuestion.getId()) != null) {
            securityQuestions.set(securityQuestions.indexOf(findById(securityQuestion.getId())), securityQuestion);
        } else {
            securityQuestions.add(securityQuestion);
        }
        IOFile.writeToFile(ShopConstant.SECURITY_QUESTION_PATH, securityQuestions);
    }

    @Override
    public void deleteById(Integer id) {
        securityQuestions.remove(findById(id));
        IOFile.writeToFile(ShopConstant.SECURITY_QUESTION_PATH, securityQuestions);
    }
}
