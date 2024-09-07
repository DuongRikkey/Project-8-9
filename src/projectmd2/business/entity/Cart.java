package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;
import projectmd2.business.untils.Validation.CartValidate;
import projectmd2.presentation.run.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart implements Serializable, IOData<Cart,String> {
    private int cartId,userId,quantity;
    private Products products;
    private double totalPrice;

    public Cart() {
    }

    public Cart(int cartId, int userId, int quantity, Products products, double totalPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.quantity = quantity;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {

    }
    public void inputDataCart(Scanner sc, boolean isAdd, int productId) {
        if(isAdd){
            List<Cart> carts = null;
            this.cartId = getNewId(carts, ShopConstant.CART_PATH);
        }
        this.userId = Main.userLogin.getId();
        this.products = CartValidate.inputProduct(productId);
        this.quantity = CartValidate.inputQuantity(this.products);
        this.totalPrice = this.quantity * this.products.getUnitPrice();
    }

    @Override
    public void displayData() {
        System.out.printf("%3s | %7s | %15s | %7s | %10s \n"
                ,this.cartId,this.userId,this.products.getProductName(),this.quantity,this.totalPrice);
    }

    @Override
    public int getNewId(List<Cart> list, String path) {
        list = IOFile.readFromFile(path);
        int idMax = 0;
        for (Cart cart : list) {
            if (cart.getCartId() > idMax) {
                idMax = cart.getCartId();
            }
        }
        return idMax+1;
    }
}
