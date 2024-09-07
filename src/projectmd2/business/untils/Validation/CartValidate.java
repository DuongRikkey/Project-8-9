package projectmd2.business.untils.Validation;

import projectmd2.business.design.ICart;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Products;
import projectmd2.business.feature.cartimpl.CartImpl;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.untils.InputMethods;

public class CartValidate {
    private static IProducts productsList = new ProductsImpl();

    public static Products inputProduct(int productID) {
        return productsList.findAll().stream().filter(p -> p.getProductId() == productID).findFirst().orElse(null);
    }

    public static int inputQuantity(Products products) {
        while (true) {
            System.out.println("Enter quantity");
            int quantity = InputMethods.getInteger();
            if (quantity > products.getStockQuantity()) {
                System.err.println("Quantity exceeds stock limit, Please try again!");
            }else{
                return quantity;
            }
        }
    }
}
