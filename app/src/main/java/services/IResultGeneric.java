package services;


/**
 * Created by morte on 09/05/2016.
 */
public interface IResultGeneric {
    void onSuccess(Integer value);
    void onError(String msg);
}
