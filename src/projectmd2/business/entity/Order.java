package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;
import projectmd2.business.untils.Validation.OrderValidate;
import projectmd2.presentation.run.Main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Order implements IOData<Order,String>, Serializable {
    private static UserImpl userList = new UserImpl();
    private int orderId,userId;
    private double totalPrice;
    private String serialNumber,note,receiveName,receiveAddress,receivePhone;
    private Date createdAt,receiveAt;
    private List<Cart> carts;
    private Status status;
    private Payments payments;

    public Order() {
    }

    public Order(int orderId, int userId, double totalPrice, String serialNumber, String note, String receiveName, String receiveAddress, String receivePhone, Date createdAt, Date receiveAt, List<Cart> carts, Status status, Payments payments) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.serialNumber = serialNumber;
        this.note = note;
        this.receiveName = receiveName;
        this.receiveAddress = receiveAddress;
        this.receivePhone = receivePhone;
        this.createdAt = createdAt;
        this.receiveAt = receiveAt;
        this.carts = carts;
        this.status = status;
        this.payments = payments;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getReceiveAt() {
        return receiveAt;
    }

    public void setReceiveAt(Date receiveAt) {
        this.receiveAt = receiveAt;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {

    }
    public void inputDataOrder(Scanner sc, boolean isAdd,double totalPrice, Address address) {
        if(isAdd){
            List<Order> orders = null;
            this.orderId = getNewId(orders, ShopConstant.ORDER_PATH);
        }
        this.userId = Main.userLogin.getId();
        this.totalPrice = totalPrice;
        this.createdAt = new Date();
        this.note = OrderValidate.inputNote();
        this.serialNumber = OrderValidate.inputSerialNumber();
        this.receiveName = address.getReceiveName();
        this.receiveAddress = address.getFullAddress();
        this.receivePhone = address.getPhone();
        this.carts = OrderValidate.inputCart();
        this.receiveAt = OrderValidate.inputReceiveAt(this.createdAt);
        this.status = Status.WAITING;
    }

    @Override
    public void displayData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%3s | %15s | %15s | %15s | %7s | %10s | %10s | %10s \n",this.orderId,this.receiveAddress,this.receivePhone,this.totalPrice,this.payments,dateFormat.format(this.createdAt),dateFormat.format(this.receiveAt),this.status);
    }

    @Override
    public int getNewId(List<Order> list, String path) {
        list = IOFile.readFromFile(path);
        int idMax =0;
        for(Order order : list){
            if(order.getOrderId() > idMax){
                idMax = order.getOrderId();
            }
        }
        return idMax+1;
    }
    public void displayDetail(){
        System.out.println("Order ID: " + orderId);
        System.out.println("User Name: " + userList.findById(userId).getUserName());
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Serial Number: " + serialNumber);
        showProduct();
        System.out.printf("Receive Name: %s \n", receiveName);
        System.out.println("Receive Address: " + receiveAddress);
        System.out.println("Receive Phone: " + receivePhone);
        System.out.println("Payments: " +payments);
        System.out.println("Create Date: " + createdAt);
        System.out.println("Receive At: " + receiveAt);
        System.out.println("Status: " + status);
    }
    public void showProduct(){
        System.out.printf(Colors.GREEN+"%20s | %5s \n" +Colors.RESET,"Product Name","Quantity");
        for(Cart c: this.carts){
            System.out.printf("%20s | %5s \n",c.getProducts().getProductName(),c.getQuantity());
        }
    }
}
