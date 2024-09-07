package projectmd2.business.feature.cartimpl;

import projectmd2.business.design.IAddress;
import projectmd2.business.design.ICart;
import projectmd2.business.design.IOrder;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Cart;
import projectmd2.business.entity.Order;
import projectmd2.business.entity.Payments;
import projectmd2.business.feature.addressimpl.AddressImpl;
import projectmd2.business.feature.orderimpl.OrderImpl;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.run.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartFeature {
    private static ICart cartList = new CartImpl();
    private static IProducts productsList = new ProductsImpl();
    private static IAddress addressList = new AddressImpl();
    private static IOrder orderList = new OrderImpl();
    private static UserImpl userList = new UserImpl();
    public static void displayCart() {
        if(cartList.findAll().stream().noneMatch(c->c.getUserId() == Main.userLogin.getId())){
            System.err.println("Cart is empty");
            return;
        }
        System.out.println(Colors.CYAN + "*************All Product In Cart **************" + Colors.RESET);
        System.out.printf(Colors.GREEN + "%3s | %7s | %15s | %7s | %10s \n" + Colors.RESET
                , "ID", "User Id", "Product Name", "Quantity", "Total Price");
        cartList.findAll().stream().filter(c -> c.getUserId() == Main.userLogin.getId()).forEach(Cart::displayData);
    }

    public static void addToCart(Scanner sc) {
        System.out.println(Colors.CYAN + "Enter Product ID you want to add to cart" + Colors.RESET);
        int productId = InputMethods.getInteger();
        if (productsList.findById(productId) != null && productsList.findById(productId).getCategory().isStatus()) {
            if (cartList.findAll().stream().anyMatch(c -> c.getProducts().getProductId() == productId && c.getUserId() == Main.userLogin.getId())) {
                Cart cartDetail = cartList.findAll().stream().
                        filter(c -> c.getProducts().getProductId() == productId && c.getUserId() == Main.userLogin.getId())
                        .findFirst().orElse(null);
                System.out.println(Colors.CYAN + "That product is already in the cart,enter quantity you want to add" + Colors.RESET);
                while (true) {
                    int quantity = InputMethods.getInteger();
                    if (cartDetail != null && cartDetail.getQuantity() + quantity <= productsList.findById(productId).getStockQuantity()) {
                        cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
                        cartDetail.setTotalPrice(cartDetail.getQuantity() * cartDetail.getProducts().getUnitPrice());
                        cartList.save(cartDetail);
                        System.out.println(Colors.GREEN + "Added product to the cart" + Colors.RESET);
                        break;
                    } else {
                        System.err.println("Out of stock");
                    }
                }
            } else {
                Cart cart = new Cart();
                cart.inputDataCart(sc, true, productId);
                cartList.save(cart);
                System.out.println(Colors.GREEN + "Add to cart successfully" + Colors.RESET);
            }
        } else {
            System.err.println("Product ID does not exist");
        }
    }

    public static void removeProductFromCartById() {
        if(cartList.findAll().isEmpty()){
            System.err.println("Cart is empty");
            return;
        }
        System.out.println(Colors.CYAN + "Enter Cart ID you want to remove from Cart" + Colors.RESET);
        int cartId = InputMethods.getInteger();
        if (cartList.findById(cartId) != null && cartList.findById(cartId).getUserId() == Main.userLogin.getId()) {
            cartList.deleteById(cartId);
            System.out.println(Colors.GREEN + "Remove from cart successfully" + Colors.RESET);
        } else {
            System.err.println("Cart ID does not exist");
        }
    }


    public static void removeAllCart() {
        if(cartList.findAll().isEmpty()){
            System.err.println("Cart is empty");
            return;
        }
        List<Cart> carts = new ArrayList<>();
        for (Cart cart : cartList.findAll()) {
            if (cart.getUserId() == Main.userLogin.getId()) {
                carts.add(cart);
            }
        }
        for (Cart cart : carts) {
            cartList.deleteById(cart.getCartId());
        }
        System.out.println(Colors.GREEN + "Remove all cart successfully" + Colors.RESET);
    }

    public static void cartDetail() {
        if(cartList.findAll().isEmpty()){
            System.err.println("Cart is empty");
            return;
        }
        System.out.println(Colors.CYAN + "Enter Cart ID you want to change quantity" + Colors.RESET);
        int cartId = InputMethods.getInteger();
        if (cartList.findById(cartId) != null && cartList.findById(cartId).getUserId() == Main.userLogin.getId()) {
            System.out.println("Enter quantity you want to change");
            int quantity = InputMethods.getInteger();
            if (quantity > cartList.findById(cartId).getProducts().getStockQuantity()) {
                System.err.println("Out of stock");
            } else {
                cartList.findById(cartId).setQuantity(quantity);
                cartList.findById(cartId).setTotalPrice(cartList.findById(cartId).getQuantity() * cartList.findById(cartId).getProducts().getUnitPrice());
                cartList.save(cartList.findById(cartId));
                System.out.println(Colors.GREEN + "Change quantity successfully" + Colors.RESET);
            }
        }
    }

    public static void checkOut(Scanner sc) {
        if (cartList.findAll().stream().noneMatch(c -> c.getUserId() == Main.userLogin.getId())) {
            System.err.println("There are no products in the cart");
            return;
        }
        while (true) {
            System.out.println("Choose payment method");
            Payments[] pay = Payments.values();
            for (int i = 0; i < pay.length; i++) {
                System.out.println((i+1) + ". " + pay[i].name());
            }
//            System.out.println("1. COD ( Cash On Delivery )");
//            System.out.println("2. Pay by e-wallet");
//            System.out.println("Back");
            Payments inputPayments;
            while (true){
                try{  System.out.println(Colors.GREEN+"Enter your choice"+Colors.RESET);
                    inputPayments = Payments.valueOf(InputMethods.getString());
                    break;
                }catch (Exception e){
                    System.err.println("Invalid choice,Please try again");
                }
            }

            int sum = 0;
            for(Cart cart : cartList.findAll()) {
                if(cart.getUserId() == Main.userLogin.getId()) {
                    sum += (int) cart.getTotalPrice();
                }
            }
            if(inputPayments == Payments.ONLINE){
                if(Main.userLogin.getWallet() < sum ){
                    System.err.println("You dont have enough money");
                }else{
                    payment(sc,sum,inputPayments);
                    Main.userLogin.setWallet(Main.userLogin.getWallet() - sum);
                    userList.save(Main.userLogin);
                    break;
                }
            }else{
                payment(sc,sum,inputPayments);
                break;
            }
//            if (choice == 1) {
//                payment(sc,sum);
//                break;
//
//            }else if (choice == 2) {
//                if(Main.userLogin.getWallet() < sum){
//                    System.err.println("You dont have enough money");
//                }else{
//                    payment(sc,sum);
//                    Main.userLogin.setWallet(Main.userLogin.getWallet() - sum);
//                    userList.save(Main.userLogin);
//                    break;
//                }
//            }else if (choice == 3) {
//                break;
//            }else{
//                System.err.println(ShopMessage.ERROR_ALERT);
//            }
        }
    }

    public static void payment(Scanner sc,int sum, Payments inputPayments){
        System.out.println(Colors.CYAN+"********** LIST ADDRESS *********"+Colors.RESET);
        addressList.findAll().stream().filter(a -> a.getUserId() == Main.userLogin.getId()).forEach(System.out::println);
        while (true) {
            System.out.println(Colors.CYAN+"Enter the address id you want to check out"+Colors.RESET);
            int idAdress = InputMethods.getInteger();
            if (addressList.findById(idAdress) != null) {
                Order newOrder = new Order();
                newOrder.inputDataOrder(sc,true,sum,addressList.findById(idAdress));
                newOrder.setPayments(inputPayments);
                orderList.save(newOrder);
                System.out.println(Colors.GREEN+"Order added successfully" + Colors.RESET);
                List<Cart> cartsDelete = new ArrayList<>();
                for (Cart cart : cartList.findAll()) {
                    if(cart.getUserId() == Main.userLogin.getId()) {
                        cartsDelete.add(cart);
                    }
                }
                for (Cart cart : cartsDelete) {
                    cartList.deleteById(cart.getCartId());
                }
                break;
            }else{
                System.err.println("Address not found");
            }
        }
    }
}
