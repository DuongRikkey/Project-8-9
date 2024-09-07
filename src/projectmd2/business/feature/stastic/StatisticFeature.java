package projectmd2.business.feature.stastic;

import projectmd2.business.design.ICategory;
import projectmd2.business.design.IOrder;
import projectmd2.business.entity.Category;
import projectmd2.business.entity.Order;
import projectmd2.business.feature.categoryimpl.CategoryImpl;
import projectmd2.business.feature.orderimpl.OrderImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;

import java.util.Calendar;
import java.util.Comparator;

public class StatisticFeature {
    private static IOrder orderList = new OrderImpl();
    private static ICategory categoryList = new CategoryImpl();
    public static void showOrderOfMonth() {
        int month;
        while (true) {
            System.out.println("Enter Month");
            month = InputMethods.getInteger();
            if (month < 1 || month > 12) {
                System.out.println("Invalid Month");
            } else {
                break;
            }
        }
        System.out.println("Enter Year");
        int year = InputMethods.getInteger();
        int count = 0;
        int sum = 0;
        for (Order o : orderList.findAll()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(o.getCreatedAt());
            int orderMonth = cal.get(Calendar.MONTH) + 1;
            int orderYear = cal.get(Calendar.YEAR);
            if (orderMonth == month && orderYear == year) {
                count++;
                break;
            }
        }
        if (count == 0) {
            System.err.println("No Order Found");
        } else {
            count = 0;
            System.out.printf(Colors.GREEN + "%3s | %15s | %15s | %15s | %10s | %10s | %10s \n"
                    , "ID", "ADDRESS", "RECEIVE PHONE", "PRICE", "ORDER AT", "RECEIVE AT", "STATUS" + Colors.RESET);
            for (Order o : orderList.findAll()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(o.getCreatedAt());
                int orderMonth = cal.get(Calendar.MONTH) + 1;
                int orderYear = cal.get(Calendar.YEAR);
                if (orderMonth == month && orderYear == year) {
                    count++;
                    sum += (int) o.getTotalPrice();
                    o.displayData();
                }
            }
            System.out.println(Colors.GREEN + "Has " + count + " Orders in Month: " + month + " and Year: " + year + Colors.RESET);
            System.out.println(Colors.GREEN + "Total revenue: " + sum + Colors.RESET);
        }
    }

    public static void sortCategorisByNumberProducts() {
        if(categoryList.findAll().isEmpty()){
            System.err.println("Category is empty");
            return;
        }
        System.out.printf(Colors.GREEN+"%3s | %10s | %15s | %10s | %5s \n"+Colors.RESET,"ID","Name","Description","Status","Total Product");
        categoryList.findAll().stream().filter(Category::isStatus).sorted(Comparator.comparing(Category::totalPrduct).reversed()).forEach(Category::displayData);
    }
}
