package controllers;

import java.util.List;

import dao.IAddressDao;
import models.Address;
import services.IResult;
import dao.IUserDao;
import models.User;
import services.IResultUser;
import webservices.UserAddressParse;
import webservices.UserParse;

/**
 * Created by kleber on 25/04/16.
 */
public class UserController {

    public static void signUp(final User user, final IResultUser<User> result) {
        IUserDao userDao = new UserParse();
        userDao.signUp(user, new IResultUser<User>() {
            @Override
            public void onSuccess(User obj) {
                result.onSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

    public static void update(final User user, final IResultUser<User> result) {
        IUserDao userDao = new UserParse();
        userDao.update(user, new IResultUser<User>() {
            @Override
            public void onSuccess(User obj) {
                result.onSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

    public static void registerAddress(final Address address, final IResult<Address> result) {
        IAddressDao userDao = new UserAddressParse();
        userDao.register(address, new IResult<Address>() {
            @Override
            public void onSuccess(List<Address> list) {

            }

            @Override
            public void onSuccess(Address obj) {
                result.onSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

    public static void findAddress(final IResult<Address> result) {
        IAddressDao userDao = new UserAddressParse();
        userDao.find(new IResult<Address>() {
            @Override
            public void onSuccess(List<Address> list) {
                result.onSuccess(list);
            }

            @Override
            public void onSuccess(Address obj) {

            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }
}
