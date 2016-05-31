package webservices;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import dao.IAddressDao;
import models.Address;
import services.IResult;

/**
 * Created by kleber on 31/05/16.
 */
public class AddressParse implements IAddressDao {
    @Override
    public void add(final Address address, final IResult<Address> result) {
        final ParseObject addressParse = new ParseObject("Address");

        addressParse.put("Zipcode", address.getZipCode());
        addressParse.put("Street", address.getStreet());
        addressParse.put("District", address.getDistrict());
        addressParse.put("State", address.getState());
        addressParse.put("City", address.getCity());
        addressParse.put("Number", address.getNumber());
        addressParse.put("Complement", address.getComplement());
        addressParse.put("User", address.getUser());

        addressParse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    result.onSuccess(address);
                } else {
                    result.onError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void find(Address address, IResult<Address> result) {
        List<Address> addresses = new ArrayList<Address>();
        List<ParseObject> listParse = new ArrayList<ParseObject>();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Address");
        query.whereEqualTo("User", address.getUser());

        try {
            listParse = query.find();
        } catch(Exception e) {
            result.onError(e.getMessage());
        }

        for (ParseObject obj : listParse) {
            Address addr = new Address();

            addr.setZipCode(obj.getString("Zipcode"));
            addr.setStreet(obj.getString("Street"));
            addr.setDistrict(obj.getString("District"));
            addr.setState(obj.getString("State"));
            addr.setCity(obj.getString("City"));
            addr.setNumber(obj.getString("Number"));
            addr.setComplement(obj.getString("Complement"));
            addr.setUser(obj.getString("User"));

            addresses.add(addr);
        }

        result.onSuccess(addresses);
    }
}
