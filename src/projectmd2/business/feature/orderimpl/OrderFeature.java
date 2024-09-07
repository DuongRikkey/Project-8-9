package projectmd2.business.feature.orderimpl;

import projectmd2.business.design.IOrder;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.*;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.run.Main;

import java.util.List;

public class OrderFeature {
    private static IOrder orderList = new OrderImpl();
    private static IProducts productList = new ProductsImpl();
    private static UserImpl userList = new UserImpl();

    //Display All Order From UserLogin
    public static void showAllOrder(boolean isAdmin) {
        if (!isAdmin) {
            if (orderList.findAll().stream().noneMatch(o -> o.getUserId() == Main.userLogin.getId())) {
                System.err.println("Order list is empty");
                return;
            }

        } else {
            if (orderList.findAll().isEmpty()) {
                System.err.println("Order list is empty");
                return;
            }
        }
        System.out.println(Colors.CYAN + "*************** Order History ***************" + Colors.RESET);
        System.out.printf(Colors.GREEN + "%3s | %15s | %15s | %15s | %7s | %10s | %10s | %10s \n"
                , "ID", "ADDRESS", "RECEIVE PHONE", "PRICE", "PAYMENTS", "ORDER AT", "RECEIVE AT", "STATUS" + Colors.RESET);
        if (isAdmin) {
            orderList.findAll().forEach(Order::displayData);
        } else {
            orderList.findAll().stream().filter(o -> o.getUserId() == Main.userLogin.getId()).forEach(Order::displayData);
        }

    }

    //Display Order Detail By Order Id And UserLogin
    public static void detailOrder(boolean isAdmin) {
        if (isAdmin) {
            if (orderList.findAll().isEmpty()) {
                System.err.println("Order list is empty");
                return;
            }
        } else {
            if (orderList.findAll().stream().noneMatch(o -> o.getUserId() == Main.userLogin.getId())) {
                System.err.println("Order list is empty");
                return;
            }
        }
        System.out.println(Colors.CYAN + "Enter Order ID you want to show the details of the order" + Colors.RESET);
        int orderId = InputMethods.getInteger();
        if (isAdmin) {
            if (orderList.findById(orderId) != null) {
                orderList.findById(orderId).displayDetail();
            } else {
                System.err.println("Order not found");
            }
        } else {
            if (orderList.findById(orderId) != null && orderList.findById(orderId).getUserId() == Main.userLogin.getId()) {
                orderList.findById(orderId).displayDetail();
                if (orderList.findById(orderId).getStatus() == Status.WAITING) {
                    System.out.println("1. Cancel Order");
                    System.out.println("2. Back");
                    System.out.println(Colors.GREEN + "Enter your choice" + Colors.RESET);
                    int choice = InputMethods.getInteger();
                    switch (choice) {
                        case 1:
                            if(orderList.findById(orderId).getPayments() == Payments.ONLINE) {
                                orderList.findById(orderId).setStatus(Status.DENIED);
                                orderList.save(orderList.findById(orderId));
                                Main.userLogin.setWallet((int) (Main.userLogin.getWallet() + orderList.findById(orderId).getTotalPrice()));
                                userList.save(Main.userLogin);
                                System.out.println(Colors.GREEN+"Called Order with ID: " + orderId + " successfully"+Colors.RESET);
                            }else{
                                orderList.findById(orderId).setStatus(Status.DENIED);
                                orderList.save(orderList.findById(orderId));
                                System.out.println(Colors.GREEN+"Called Order with ID: " + orderId + " successfully"+Colors.RESET);
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.err.println(ShopMessage.ERROR_ALERT);
                    }
                }
            } else {
                System.err.println("Order not found");
            }
        }
    }

    //Display All Order By Status
    public static void displayOrderByStatus(boolean isAdmin) {
        if (!isAdmin) {
            if (orderList.findAll().stream().noneMatch(o -> o.getUserId() == Main.userLogin.getId())) {
                System.err.println("Order list is empty");
                return;
            }

        } else {
            if (orderList.findAll().isEmpty()) {
                System.err.println("Order list is empty");
                return;
            }
        }
        System.out.println(Colors.CYAN + "**********LIST STATUS ***********" + Colors.RESET);
        Status[] statuses = Status.values();
        int count = 1;
        for (Status status : statuses) {
            System.out.println(count + ". " + status);
            count++;
        }

        String status;
        Status inputStatus;
        while (true) {
            try {
                System.out.println(Colors.CYAN + "Enter status you want to show" + Colors.RESET);
                status = InputMethods.getString();
                inputStatus = Status.valueOf(status);
                break;
            } catch (Exception e) {
                System.err.println("Invalid Status, Please Try Again!");
            }
        }

        Status finalInputStatus = inputStatus;
        if (!isAdmin) {
            if (orderList.findAll().stream().noneMatch(o -> o.getUserId() == Main.userLogin.getId() && o.getStatus().equals(finalInputStatus))) {
                System.err.println("Order not found");
            } else {
                System.out.println(Colors.CYAN + "*************** Order By Status ***************" + Colors.RESET);
                System.out.printf(Colors.GREEN + "%3s | %15s | %15s | %15s | %7s | %10s | %10s | %10s \n"
                        , "ID", "ADDRESS", "RECEIVE PHONE", "PRICE", "PAYMENTS", "ORDER AT", "RECEIVE AT", "STATUS" + Colors.RESET);
                orderList.findAll().stream().filter(o -> o.getStatus().equals(finalInputStatus) && o.getUserId() == Main.userLogin.getId()).forEach(Order::displayData);
                System.out.println(Colors.GREEN + "Has found "
                        + orderList.findAll().stream().filter(o -> o.getStatus().equals(finalInputStatus) && o.getUserId() == Main.userLogin.getId()).count()
                        + " orders with status " + finalInputStatus + Colors.RESET);
            }
        } else {
            if (orderList.findAll().stream().noneMatch(o -> o.getStatus().equals(finalInputStatus))) {
                System.err.println("Order not found");
            } else {
                System.out.println(Colors.CYAN + "*************** Order By Status ***************" + Colors.RESET);
                System.out.printf(Colors.GREEN + "%3s | %15s | %15s | %15s | %7s | %10s | %10s | %10s \n"
                        , "ID", "ADDRESS", "RECEIVE PHONE", "PRICE", "PAYMENTS", "ORDER AT", "RECEIVE AT", "STATUS" + Colors.RESET);
                orderList.findAll().stream().filter(o -> o.getStatus().equals(finalInputStatus)).forEach(Order::displayData);
                System.out.println(Colors.GREEN + "Has found "
                        + orderList.findAll().stream().filter(o -> o.getStatus().equals(finalInputStatus)).count()
                        + " orders with status: " + finalInputStatus + Colors.RESET);
            }

        }
    }


    public static void changeStatus() {
        System.out.println("Enter order ID you want to change status");
        int orderId = InputMethods.getInteger();
        if (orderList.findById(orderId) != null) {
            System.out.println(Colors.CYAN + "**********LIST STATUS ***********" + Colors.RESET);
            Status[] statuses = Status.values();
            int count = 1;
            for (Status status : statuses) {
                System.out.println(count + ". " + status);
                count++;
            }
            Status inputStatus;
            while (true) {
                try {
                    System.out.println(Colors.CYAN + "Enter status you want to change " + Colors.RESET);
                    inputStatus = Status.valueOf(InputMethods.getString());
                    break;
                } catch (Exception e) {
                    System.err.println("Invalid Status, Please Try Again!");
                }
            }
            updateQuantityProduct(orderList.findById(orderId), inputStatus);
            orderList.findById(orderId).setStatus(inputStatus);
            orderList.save(orderList.findById(orderId));
            System.out.println(Colors.GREEN + "Order status changed to " + inputStatus + Colors.RESET);
        } else {
            System.err.println("Order not found");
        }
    }

    private static void updateQuantityProduct(Order oldStatus, Status newStatus) {
        if (oldStatus.getStatus() != Status.SUCCESS && newStatus == Status.SUCCESS) {
            List<Cart> cart = oldStatus.getCarts();
            for (Cart c : cart) {
                if (productList.findAll().stream().anyMatch(p -> p.getProductId() == c.getProducts().getProductId())) {
                    Products newProducts = productList.findById(c.getProducts().getProductId());
                    newProducts.setStockQuantity(newProducts.getStockQuantity() - c.getQuantity());
                    newProducts.setSelled(newProducts.getSelled() + c.getQuantity());
                    productList.save(newProducts);
                }
            }
        } else if (oldStatus.getStatus() == Status.SUCCESS && newStatus != Status.SUCCESS && oldStatus.getPayments() == Payments.ONLINE) {
            List<Cart> cart = oldStatus.getCarts();
            for (Cart c : cart) {
                if (productList.findAll().stream().anyMatch(p -> p.getProductId() == c.getProducts().getProductId())) {
                    Products newProducts = productList.findById(c.getProducts().getProductId());
                    newProducts.setStockQuantity(newProducts.getStockQuantity() + c.getQuantity());
                    newProducts.setSelled(newProducts.getSelled() - c.getQuantity());
                    productList.save(newProducts);
                    User user = userList.findById(oldStatus.getUserId());
                    user.setWallet((int) (user.getWallet() + oldStatus.getTotalPrice()));
                    userList.save(user);
                }
            }
        } else if (oldStatus.getStatus() == Status.SUCCESS && newStatus != Status.SUCCESS) {
            List<Cart> cart = oldStatus.getCarts();
            for (Cart c : cart) {
                if (productList.findAll().stream().anyMatch(p -> p.getProductId() == c.getProducts().getProductId())) {
                    Products newProducts = productList.findById(c.getProducts().getProductId());
                    newProducts.setStockQuantity(newProducts.getStockQuantity() + c.getQuantity());
                    newProducts.setSelled(newProducts.getSelled() - c.getQuantity());
                    productList.save(newProducts);
                }
            }
        }
    }
}
