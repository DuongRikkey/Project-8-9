package projectmd2.presentation.admin.statisticsmenu;

import projectmd2.business.feature.stastic.StatisticFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.awt.*;

public class StatisticsMenu {
    public static void showStatisticsMenu() {
        while (true) {
            System.out.println(Colors.CYAN + "***************** STATISTICS MENU ******************" + Colors.RESET);
            System.out.println("1. Order statistics for the month");
            System.out.println("2. Sort categories by number of products");
            System.out.println("3. Back");
            System.out.println(Colors.CYAN + "****************************************************" + Colors.RESET);
            System.out.println(Colors.GREEN + "Enter your choice" + Colors.RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    StatisticFeature.showOrderOfMonth();
                    break;
                case 2:
                    StatisticFeature.sortCategorisByNumberProducts();
                    break;
                case 3:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
