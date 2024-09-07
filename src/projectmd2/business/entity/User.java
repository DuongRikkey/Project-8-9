package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;
import projectmd2.business.untils.Validation.UserValidate;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class User implements Serializable, IOData<User, String> {
    private int id, wallet;
    private String userName, password, fullName, email, phone, address, avatar;
    private Map<String, String> securityQuestion;
    private RoleName roleName;
    private Date createdAt, updateAt;
    private boolean status, isDeleted;

    public User() {

    }

    public User(int id, int wallet, String userName, String password, String fullName, String email, String phone, String address, String avatar, Map<String, String> securityQuestion, RoleName roleName, Date createdAt, Date updateAt, boolean status, boolean isDeleted) {
        this.id = id;
        this.wallet = wallet;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.securityQuestion = securityQuestion;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, String> getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(Map<String, String> securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {
        if (isAdd) {
            List<User> users = null;
            this.id = getNewId(users, ShopConstant.USER_PATH);
            this.createdAt = new Date();
        }
        this.userName = UserValidate.inputUsername(sc);
        this.password = UserValidate.inputPassword(sc);
        this.fullName = UserValidate.inputFullName();
        this.email = UserValidate.inputEmail(sc);
        this.phone = UserValidate.inputPhone(sc);
        this.address = UserValidate.inputAddress();
        this.avatar = UserValidate.inputAvarta();
        this.securityQuestion = (Map<String, String>) UserValidate.inputSecurityQuestion(sc);
        this.wallet = 0;
        this.roleName = RoleName.USER;
        this.updateAt = new Date();
        this.status = true;
        this.isDeleted = false;
    }

    @Override
    public void displayData() {
        Locale localeVN = new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%3s | %20s | %20s | %5s | %10s | %10s | %10s | %5s | %5s \n",
                this.id, this.userName, this.fullName, this.roleName,currencyFormatter.format(this.wallet), dateFormat.format(this.createdAt), dateFormat.format(this.updateAt), this.status, this.isDeleted);
    }

    public void displayDataforUser() {
        Locale localeVN = new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%3s | %20s | %20s | %5s | %10s \n",
                this.id, this.userName, this.fullName, this.roleName,currencyFormatter.format(this.wallet));
    }

    @Override
    public int getNewId(List<User> list, String path) {
        list = IOFile.readFromFile(path);
        int idMax = 0;
        for (User user : list) {
            if (user.getId() > idMax) {
                idMax = user.getId();
            }
        }
        return idMax + 1;
    }
}

