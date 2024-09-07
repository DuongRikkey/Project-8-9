package projectmd2.business.feature.wishlist;

import projectmd2.business.design.IProducts;
import projectmd2.business.design.IWishlist;
import projectmd2.business.entity.WishList;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.run.Main;

public class WishListFeature {
    private static IWishlist wishlist = new WishListImpl();
    private static IProducts products = new ProductsImpl();

    //Display All Wishlist by User Login
    public static void displayAllWishlist() {
        if(wishlist.findAll().stream().noneMatch(w->w.getUserWishList().getId() == Main.userLogin.getId())){
            System.err.println("Wishlist is empty");
            return;
        }
        System.out.println(Colors.CYAN+"***********LIST WISHLIST**************"+Colors.RESET);
        System.out.printf(Colors.GREEN+"%3s | %20s | %20s \n" +Colors.RESET, "ID", "User Name", "Product");
        wishlist.findAll().stream().filter(w->w.getUserWishList().getId() == Main.userLogin.getId()).forEach(WishList::displayData);
    }

    public static void addWishlist() {
        System.out.println(Colors.CYAN+"Enter Product Id you want to add to Wishlist"+Colors.RESET);
        int productId = InputMethods.getInteger();
        if(products.findById(productId) != null ) {
            if(wishlist.findAll().stream().noneMatch(w->w.getProductWishList().getProductId() == productId)){
                WishList newWishlist = new WishList();
                newWishlist.inputData(products.findById(productId));
                wishlist.save(newWishlist);
                System.out.println(Colors.GREEN+"Wishlist added successfully"+Colors.RESET);
            }else{
                System.err.println("Product is already exists in wishlist");
            }

        }else{
            System.err.println("Wishlist not found");
        }
    }

    public static void removeProductFromWishlist() {
        if(wishlist.findAll().stream().noneMatch(w->w.getUserWishList().getId() == Main.userLogin.getId())){
            System.err.println("Wishlist is empty");
            return;
        }
        System.out.println(Colors.CYAN+"Enter wishlist id you want to remove from your Wishlist"+Colors.RESET);
        int productId = InputMethods.getInteger();
        if(products.findById(productId) != null) {
            wishlist.deleteById(productId);
            System.out.println(Colors.GREEN+"Wishlist removed successfully"+Colors.RESET);
        }
    }
}
