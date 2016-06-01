package dao;

import java.util.List;

import models.Address;
import services.IResult;

/**
 * Created by kleber on 31/05/16.
 */
public interface IAddressDao {

    List<Address> find(IResult<Address> result);

    void register(Address address, IResult<Address> result);
}
