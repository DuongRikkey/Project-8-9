package projectmd2.business.feature.userimpl;

import projectmd2.business.entity.RoleName;
import projectmd2.business.entity.User;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.Validation.UserValidate;

import java.util.Map;
import java.util.Scanner;

public class EditUserForAdminFeature {
    private static UserImpl userList = new UserImpl();

    public static void changeFullName(User user) {
        user.setFullName(UserValidate.inputFullName());
        userList.save(user);
    }

    public static void changePassword(Scanner sc, User user) {
        user.setPassword(UserValidate.inputPassword(sc));
        userList.save(user);
    }

    public static void changeEmail(Scanner sc, User user) {
        user.setEmail(UserValidate.inputEmail(sc));
        userList.save(user);
    }


    public static void changePhone(Scanner sc, User user) {
        user.setPhone(UserValidate.inputPhone(sc));
        userList.save(user);
    }

    public static void changeAddress(User user) {
        user.setAddress(UserValidate.inputAddress());
        userList.save(user);
    }

    public static void changeSecurityQuestion(Scanner sc, User user) {
        user.setSecurityQuestion((Map<String, String>) UserValidate.inputSecurityQuestion(sc));
        userList.save(user);
    }

    public static void changeRole(User user) {
        if (user.getRoleName() == RoleName.USER) {
            user.setRoleName(RoleName.MOD);
            userList.save(user);
        } else if (user.getRoleName() == RoleName.MOD) {
            user.setRoleName(RoleName.USER);
            userList.save(user);
        }
    }

    public static void changeStatus(User user) {
        user.setStatus(!user.isStatus());
        userList.save(user);
    }
}
