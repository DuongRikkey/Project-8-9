package projectmd2.presentation.admin.categorymenu;

import projectmd2.business.feature.categoryimpl.CategoryFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class CategoryMenu {
    public static void showCategoryMenu(Scanner sc) {
        while (true) {
            System.out.println(Colors.CYAN+"*************** CATEGORY MENU **************"+Colors.RESET);
            System.out.println("1. Display All Category");
            System.out.println("2. Add Category");
            System.out.println("3. Edit Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Search Category By ID");
            System.out.println("6. Back");
            System.out.println(Colors.CYAN+"********************************************"+Colors.RESET);
            System.out.println(Colors.GREEN+"Enter Your Choice"+Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    CategoryFeature.displayAllCategory();
                    break;
                case 2:
                    CategoryFeature.addNewCategory(sc);
                    break;
                case 3:
                    CategoryFeature.editCategoryById(sc);
                    break;
                case 4:
                    CategoryFeature.deleteCategoryById(sc);
                    break;
                case 5:
                    CategoryFeature.searchCategoryById(sc);
                    break;
                case 6:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
