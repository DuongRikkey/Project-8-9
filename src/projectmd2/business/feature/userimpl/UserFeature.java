package projectmd2.business.feature.userimpl;

import projectmd2.business.entity.RoleName;
import projectmd2.business.entity.User;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.admin.usermenu.UserMenuEditForAdmin;
import projectmd2.presentation.run.Main;

import java.util.Scanner;

public class UserFeature {
    private static UserImpl userList = new UserImpl();
    public static void displayUser() {
        System.out.printf(Colors.GREEN+"%3s | %20s | %20s | %5s | %10s | %10s | %10s | %5s | %5s \n"+Colors.RESET,
                "ID","User Name","Full Name","Role","Wallet","Created","Updated","Status","Deleted");
        userList.findAll().forEach(User::displayData);
    }

    public static void editUser(Scanner sc) {
        System.out.print(Colors.CYAN+"Enter ID user you want to edit"+Colors.RESET);
        int userId = InputMethods.getInteger();
        User user = userList.findById(userId);
        if(user!= null) {
            if(userList.findById(userId).getRoleName() == RoleName.ADMIN && Main.userLogin.getRoleName() != RoleName.ADMIN) {
                System.err.println("User is admin, cannot edit user");
                return;
            }else if (userList.findById(userId).getRoleName().equals(RoleName.MOD) && Main.userLogin.getRoleName() != RoleName.ADMIN && userId != Main.userLogin.getId()) {
                System.err.println("User is moderator, cannot edit user");
                return;
            }
            UserMenuEditForAdmin.showEditUserForUser(sc,user);
        }else{
            System.err.println("User not found");
        }
    }

    public static void deleteUser(Scanner sc) {
        System.out.println(Colors.CYAN+"Enter ID user you want to delete"+Colors.RESET);
        int userId = InputMethods.getInteger();
        if(userList.findById(userId) != null) {
            if(userList.findById(userId).getRoleName().equals(RoleName.ADMIN)) {
                System.err.println("User is admin, cannot delete user");
                return;
            } else if (userList.findById(userId).getRoleName().equals(RoleName.MOD) && Main.userLogin.getRoleName() != RoleName.ADMIN) {
                System.err.println("User is moderator, cannot delete user");
                return;
            }
            userList.findById(userId).setDeleted(true);
            userList.save(userList.findById(userId));
            System.out.println(Colors.GREEN+"Delete User Successfully "+Colors.RESET);
        }else{
            System.err.println("User not found");
        }
    }

    public static void findByName(Scanner sc) {
        System.out.println(Colors.CYAN+"Enter user name you want to find"+Colors.RESET);
        String userName = InputMethods.getString();
        User user = userList.findAll().stream().filter(u->u.getUserName().equals(userName)).findFirst().orElse(null);
        if(user != null) {
            System.out.printf(Colors.GREEN+"%3s | %20s | %20s | %5s | %10s | %10s | %10s | %5s | %5s \n"+Colors.RESET,
                    "ID", "User Name", "Full Name", "Role","Wallet", "Created", "Updated", "Status", "Deleted");
            user.displayData();
        }else{
            System.err.println("User not found");
        }
    }

    public static void displayAllRole() {
        RoleName[] roles = RoleName.values();
        int count = 1;
        System.out.println(Colors.CYAN+"***************LIST ROLES***************"+Colors.RESET);
        for(RoleName role : roles) {
            System.out.println(count +". " +role);
            count++;
        }
        System.out.println(Colors.GREEN+"Has " + (count-1) + " roles"+Colors.RESET);
    }
}
