package projectmd2.business.untils.Validation;

import projectmd2.business.untils.InputMethods;

public class AddressValidate {
    public static String inputFullAddress(){
        System.out.println("Enter Full Address");
        return InputMethods.getString();
    }

    public static String inputPhone() {
        String regionVn = "+84";
        String phoneRegex = "";
        while(true){
            System.out.println("Enter phone number : ");
            long phone = InputMethods.getLong();
            phoneRegex = String.valueOf(phone);
            if(phoneRegex.length() == 9){
                break;
            }else {
                System.err.println("Phone must have 9 digits");
            }
        }
        return regionVn+ phoneRegex;
    }

    public static String inputReceiveName() {
        System.out.println("Enter receive name");
        return InputMethods.getString();
    }
}
