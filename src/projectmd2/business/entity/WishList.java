package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;
import projectmd2.presentation.run.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WishList implements IOData<WishList, String>, Serializable {
    private int wishListId;
    private User userWishList;
    private Products productWishList;

    public WishList() {
    }

    public WishList(int wishListId, User userWishList, Products productWishList) {
        this.wishListId = wishListId;
        this.userWishList = userWishList;
        this.productWishList = productWishList;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public User getUserWishList() {
        return userWishList;
    }

    public void setUserWishList(User userWishList) {
        this.userWishList = userWishList;
    }

    public Products getProductWishList() {
        return productWishList;
    }

    public void setProductWishList(Products productWishList) {
        this.productWishList = productWishList;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {

    }
    public void inputData(Products products) {
        List<WishList> wishLists = new ArrayList<>();
        this.wishListId = getNewId(wishLists, ShopConstant.WISHLIST_PATH);
        this.userWishList = Main.userLogin;
        this.productWishList = products;
    }
    @Override
    public void displayData() {
        System.out.printf("%3s | %20s | %20s \n", this.wishListId, this.userWishList.getUserName(), this.productWishList.getProductName());
    }

    @Override
    public int getNewId(List<WishList> list, String path) {
        list = IOFile.readFromFile(path);
        int idMax = 0;
        for (WishList wishList : list) {
            if (wishList.getWishListId() > idMax) {
                idMax = wishList.getWishListId();
            }
        }
        return idMax + 1;
    }
}
