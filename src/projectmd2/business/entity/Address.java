package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;
import projectmd2.business.untils.Validation.AddressValidate;
import projectmd2.presentation.run.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Address implements IOData<Address,String>, Serializable {
    private int addressId,userId;
    private String fullAddress,phone,receiveName;

    public Address() {
    }

    public Address(String fullAddress, int addressId, int userId, String phone, String receiveName) {
        this.fullAddress = fullAddress;
        this.addressId = addressId;
        this.userId = userId;
        this.phone = phone;
        this.receiveName = receiveName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {
        if(isAdd){
            List<Address> addressList = null;
            this.addressId = getNewId(addressList, ShopConstant.ADDRESS_PATH);
        }
        this.userId = Main.userLogin.getId();
        this.fullAddress = AddressValidate.inputFullAddress();
        this.phone = AddressValidate.inputPhone();
        this.receiveName = AddressValidate.inputReceiveName();
    }

    @Override
    public void displayData() {
        System.out.printf("%3s | %15s | %15s | %15s \n",this.addressId,this.fullAddress,this.phone,this.receiveName);
    }

    @Override
    public int getNewId(List<Address> list, String path) {
        list = IOFile.readFromFile(path);
        int idMax = 0;
        for(Address a : list) {
            if(a.getAddressId() > idMax) {
                idMax = a.getAddressId();
            }
        }
        return idMax + 1;
    }

    @Override
    public String toString() {
        return "Id: "+this.addressId + " | Full Adress: "+this.fullAddress;
    }
}
