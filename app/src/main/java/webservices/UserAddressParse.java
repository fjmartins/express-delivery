package webservices;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import dao.IAddressDao;
import models.Address;
import services.IResult;

/**
 * Created by morte on 01/06/2016.
 */
public class UserAddressParse implements IAddressDao {

    @Override
    public void add(final Address address, final IResult<Address> result) {
        ParseUser userParse = ParseUser.getCurrentUser();
        ParseObject addressParse = new ParseObject("Address");
        addressParse.put("zipCode", address.getZipCode());
        addressParse.put("district", address.getDistrict());
        addressParse.put("city", address.getCity());
        addressParse.put("state", address.getState());
        addressParse.put("number", address.getNumber());
        addressParse.put("Street", address.getStreet());
        addressParse.put("complement", address.getComplement());
        userParse.put("Address", addressParse);
        userParse.saveInBackground(new SaveCallback() {
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
    public Address find(IResult<Address> result) {
        ParseUser userParse = ParseUser.getCurrentUser();
        ParseObject parseObject = (ParseObject) userParse.get("Address");
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Address");
        try {
            parseObject = query.get(parseObject.getObjectId());
        } catch (ParseException e) {
            result.onError(e.getMessage());
        }

        Address addressObject = new Address();
        addressObject.setCity(parseObject.get("city").toString());
        addressObject.setZipCode(parseObject.get("zipCode").toString());
        addressObject.setNumber(parseObject.get("number").toString());
        addressObject.setComplement(parseObject.get("complement").toString());
        addressObject.setDistrict(parseObject.get("district").toString());
        addressObject.setStreet(parseObject.get("city").toString());
        addressObject.setState(parseObject.get("state").toString());

        result.onSuccess(addressObject);
        return addressObject;
    }
}