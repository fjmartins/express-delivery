package dao;

import java.util.List;

/**
 * Created by kleber on 21/04/16.
 */
public interface IResult<T> {

    void onSuccess(T obj);
    void onSuccess(List<T> list);
    void onError(String msg);

}
