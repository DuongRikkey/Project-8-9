package projectmd2.business.feature.orderimpl;

import projectmd2.business.design.IOrder;
import projectmd2.business.entity.Order;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;

import java.util.List;
import java.util.Scanner;

public class OrderImpl implements IOrder {
    private static List<Order> orders;

    public OrderImpl() {
        orders = IOFile.readFromFile(ShopConstant.ORDER_PATH);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        return orders.stream().filter(o->o.getOrderId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Order order) {
        if(findById(order.getOrderId()) == null) {
            orders.add(order);
        }else{
            orders.set(orders.indexOf(findById(order.getOrderId())), order);
        }
        IOFile.writeToFile(ShopConstant.ORDER_PATH, orders);
    }

    @Override
    public void deleteById(Integer id) {
        orders.removeIf(o->o.getOrderId() == id);
        IOFile.writeToFile(ShopConstant.ORDER_PATH, orders);
    }
}
