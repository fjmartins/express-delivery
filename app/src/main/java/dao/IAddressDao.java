package dao;


import models.Address;
import services.IResult;

/**
 * Created by kleber on 31/05/16.
 */
public interface IAddressDao {

    void find(IResult<Address> result);

    void register(Address address, IResult<Address> result);
}
