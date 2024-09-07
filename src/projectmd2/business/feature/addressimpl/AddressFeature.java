package projectmd2.business.feature.addressimpl;

import projectmd2.business.design.IAddress;
import projectmd2.business.entity.Address;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.run.Main;

import java.util.Scanner;

public class AddressFeature {
    private static IAddress addressList = new AddressImpl();
    public static void displayAllAddress() {
        if(addressList.findAll().stream().noneMatch(a -> a.getUserId() == Main.userLogin.getId())){
            System.err.println("List address is empty");
            return;
        }
        System.out.println("*********LIST ADDRESS OF "+ Main.userLogin.getUserName() +"*********");
        System.out.printf("%3s | %15s | %15s | %15s \n","ID","Address","Phone","Receive Name");
        addressList.findAll().stream().filter(a->a.getUserId() == Main.userLogin.getId()).forEach(Address::displayData);
    }

    public static void addNewAddress(Scanner sc) {
        System.out.println("**********ADD NEW ADDRESS*********");
        Address newAddress = new Address();
        newAddress.inputData(sc,true);
        addressList.save(newAddress);
        System.out.println("Add address successful");
    }

    public static void editAddressById(Scanner sc) {
        System.out.println("Enter Address ID you want to edit");
        int addressId = InputMethods.getInteger();
        Address newAddress = addressList.findById(addressId);
        if(newAddress != null && newAddress.getUserId() == Main.userLogin.getId()){
            newAddress.inputData(sc,false);
            addressList.save(newAddress);
        }else{
            System.err.println("Address not found");
        }
    }


    public static void deleteAddressById(Scanner sc) {
        System.out.println("Enter Address ID you want to delete");
        int addressId = InputMethods.getInteger();
        Address deleteAddress = addressList.findById(addressId);
        if(deleteAddress != null && deleteAddress.getUserId() == Main.userLogin.getId()){
            addressList.deleteById(addressId);
            System.out.println("Delete address successful");
        }else{
            System.err.println("Address not found");
        }
    }

    public static void searchAddressById(Scanner sc) {
        System.out.println("Enter Address ID you want to search");
        int addressId = InputMethods.getInteger();
        Address searchAddress = addressList.findAll().stream().filter(a->a.getAddressId() == addressId
                && a.getUserId() == Main.userLogin.getId()).findFirst().orElse(null);
        if(searchAddress != null){
            System.out.printf("%3s | %15s | %15s | %15s \n","ID","Address","Phone","Receive Name");
            searchAddress.displayData();
        }else{
            System.err.println("Address not found");
        }
    }
}
