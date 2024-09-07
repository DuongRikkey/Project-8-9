package projectmd2.business.feature.addressimpl;

import projectmd2.business.design.IAddress;
import projectmd2.business.entity.Address;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;

import java.util.ArrayList;
import java.util.List;

public class AddressImpl implements IAddress {
    private static List<Address> addressList;
    public AddressImpl() {
        addressList = IOFile.readFromFile(ShopConstant.ADDRESS_PATH);
    }
    @Override
    public List<Address> findAll() {
        return addressList;
    }

    @Override
    public Address findById(Integer id) {
        return addressList.stream().filter(a->a.getAddressId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Address address) {
        if(findById(address.getAddressId()) == null) {
            addressList.add(address);
        }else{
            addressList.set(addressList.indexOf(findById(address.getAddressId())), address);
        }
        IOFile.writeToFile(ShopConstant.ADDRESS_PATH, addressList);
    }

    @Override
    public void deleteById(Integer id) {
            addressList.removeIf(p->p.getAddressId() == id);
            IOFile.writeToFile(ShopConstant.ADDRESS_PATH, addressList);
    }
}
