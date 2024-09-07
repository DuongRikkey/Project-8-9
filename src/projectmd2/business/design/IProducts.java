package projectmd2.business.design;

import projectmd2.business.entity.Products;

import java.util.List;

public interface IProducts extends IGenericDesign<Products,Integer> {
    boolean existByUsername(List<Products> productList, String path);
}
