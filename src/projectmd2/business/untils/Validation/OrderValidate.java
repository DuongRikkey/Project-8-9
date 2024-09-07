package projectmd2.business.untils.Validation;

import projectmd2.business.design.ICart;
import projectmd2.business.entity.Cart;
import projectmd2.business.entity.Payments;
import projectmd2.business.feature.cartimpl.CartImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.run.Main;

import java.awt.*;
import java.util.*;
import java.util.List;

public class OrderValidate {
    private static ICart cartList = new CartImpl();

    // Note ca'nt null
    public static String inputNote() {
        System.out.println(Colors.CYAN+"Enter note"+Colors.RESET);
        return InputMethods.getString();
    }

    // Random uuid
    public static String inputSerialNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    public static List<Cart> inputCart() {
        List<Cart> cart = new ArrayList<>();
        for(Cart c: cartList.findAll()){
            if(c.getUserId() == Main.userLogin.getId()){
                cart.add(c);
            }
        }
        return cart;
    }

    public static Date inputReceiveAt(Date createdAt) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 4);
        Date deliveryDate = calendar.getTime();
        return deliveryDate;
    }

//    public static Payments inputPayment() {
//
//    }
}
