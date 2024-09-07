package projectmd2.business.feature.productsimpl.Admin;

import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Products;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;

import java.util.List;

public class ProductsImpl implements IProducts {
    private static List<Products> productsList;

    public ProductsImpl() {
        productsList = IOFile.readFromFile(ShopConstant.PRODUCTS_PATH);
    }

    @Override
    public List<Products> findAll() {
        return productsList;
    }

    @Override
    public Products findById(Integer id) {
        return productsList.stream().filter(e->e.getProductId()==id).findFirst().orElse(null);
    }

    @Override
    public void save(Products products) {
        if (findById(products.getProductId()) == null) {
            productsList.add(products);
        }else {
            productsList.set(productsList.indexOf(findById(products.getProductId())),products);
        }
        IOFile.writeToFile(ShopConstant.PRODUCTS_PATH, productsList);
    }

    @Override
    public void deleteById(Integer id) {
        productsList.removeIf(p->p.getProductId()==id);
        IOFile.writeToFile(ShopConstant.PRODUCTS_PATH, productsList);
    }

    @Override
    public boolean existByUsername(List<Products> productList, String path) {
        return productList.stream().anyMatch(e->e.getProductName().equals(path));
    }
}
