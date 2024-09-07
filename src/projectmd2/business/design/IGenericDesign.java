package projectmd2.business.design;

import java.util.List;

public interface IGenericDesign <T,E>{
    List<T> findAll();
    T findById(E id);
    void save(T t);
    void deleteById(E id);
}
