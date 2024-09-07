package projectmd2.business.feature.cartimpl;

import projectmd2.business.design.ICart;
import projectmd2.business.entity.Cart;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;

import java.util.ArrayList;
import java.util.List;

public class CartImpl implements ICart {
    private static List<Cart> carts;

    public CartImpl() {
        carts = IOFile.readFromFile(ShopConstant.CART_PATH);
    }

    @Override
    public List<Cart> findAll() {
        return carts;
    }

    @Override
    public Cart findById(Integer id) {
        return carts.stream().filter(c->c.getCartId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Cart cart) {
        if(findById(cart.getCartId()) == null) {
            carts.add(cart);
            System.out.println("Add");
        }else{
            carts.set(carts.indexOf(findById(cart.getCartId())),cart);
            System.out.println("Update");
        }
        IOFile.writeToFile(ShopConstant.CART_PATH, carts);
    }

    @Override
    public void deleteById(Integer id) {
        carts.removeIf(c->c.getCartId() == id);
        IOFile.writeToFile(ShopConstant.CART_PATH, carts);
    }
}
