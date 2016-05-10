package services;

import java.util.List;

/**
 * Created by kleber on 21/04/16.
 */
public interface IResult<T> extends IResultUser<T> {


    void onSuccess(List<T> list);

}
