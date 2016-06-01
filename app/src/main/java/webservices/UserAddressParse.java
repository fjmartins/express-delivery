package webservices;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import dao.IAddressDao;
import models.Address;
import services.IResult;

/**
 * Created by morte on 01/06/2016.
 */
public class UserAddressParse implements IAddressDao {

    @Override
    public void find(final IResult<Address> result) {
        ParseUser userParse = ParseUser.getCurrentUser();
        ParseRelation<ParseObject> relation = userParse.getRelation("Address");
        ParseQuery<ParseObject> query = relation.getQuery();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    List<Address> addressList = new ArrayList<>();
                    for (ParseObject parseObject : objects) {
                        Address addressObject = new Address();
                        addressObject.setCity(parseObject.get("city").toString());
                        addressObject.setZipCode(parseObject.get("zipCode").toString());
                        addressObject.setNumber(parseObject.get("number").toString());
                        addressObject.setComplement(parseObject.get("complement").toString());
                        addressObject.setDistrict(parseObject.get("district").toString());
                        addressObject.setStreet(parseObject.get("city").toString());
                        addressObject.setState(parseObject.get("state").toString());
                        addressList.add(addressObject);
                    }
                    result.onSuccess(addressList);
                } else {
                    result.onError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void register(final Address address, final IResult<Address> result) {
        ParseUser userParse = ParseUser.getCurrentUser();
        ParseRelation<ParseObject> relation = userParse.getRelation("Address");

        ParseObject addressParse = new ParseObject("Address");
        addressParse.put("zipCode", address.getZipCode());
        addressParse.put("district", address.getDistrict());
        addressParse.put("city", address.getCity());
        addressParse.put("state", address.getState());
        addressParse.put("number", address.getNumber());
        addressParse.put("Street", address.getStreet());
        addressParse.put("complement", address.getComplement());
        try {
            addressParse.save();
        } catch (ParseException e) {
            result.onError(e.getMessage());
        }
        relation.add(addressParse);

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
}