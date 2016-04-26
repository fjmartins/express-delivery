package services;

/**
 * Created by kleber on 25/04/16.
 */
public interface IResultUser<T> {

    void onSuccess(T obj);
    void onError(String msg);
}
