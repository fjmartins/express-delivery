package dao;

import models.Address;
import services.IResult;

/**
 * Created by kleber on 31/05/16.
 */
public interface IAddressDao {
    void add(Address address, IResult<Address> result);
    void find(Address address, IResult<Address> result);
}
