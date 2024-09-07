package projectmd2.business.feature.wishlist;

import projectmd2.business.design.IWishlist;
import projectmd2.business.entity.WishList;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;

import java.util.List;

public class WishListImpl implements IWishlist {
    private static List<WishList> wishLists;

    public WishListImpl() {
        wishLists = IOFile.readFromFile(ShopConstant.WISHLIST_PATH);
    }

    @Override
    public List<WishList> findAll() {
        return wishLists;
    }

    @Override
    public WishList findById(Integer id) {
        return wishLists.stream().filter(w->w.getWishListId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(WishList wishList) {
        if(findById(wishList.getWishListId()) == null) {
            wishLists.add(wishList);
        }else{
            wishLists.set(wishLists.indexOf(findById(wishList.getWishListId())), wishList);
        }
        IOFile.writeToFile(ShopConstant.WISHLIST_PATH, wishLists);
    }

    @Override
    public void deleteById(Integer id) {
        wishLists.removeIf(w->w.getWishListId() == id);
        IOFile.writeToFile(ShopConstant.WISHLIST_PATH, wishLists);
    }
}
