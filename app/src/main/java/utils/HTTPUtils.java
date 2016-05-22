package utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by kleber on 22/05/16.
 */
public class HTTPUtils {

    public static JSONObject getAddress(String cep) {
        try {
            String strUrl = "http://viacep.com.br/ws/"+ cep +"/json/";
            URL url = new URL(strUrl);

            URLConnection con = url.openConnection();

            InputStream is = con.getInputStream();

            Scanner scan = new Scanner(is);

            String content = scan.useDelimiter("\\A").next();

            scan.close();

            JSONObject jsonData = new JSONObject(content);

            return jsonData;
        } catch (IOException e) {
            Log.i("HTTP_ERROR", e.getMessage());
            return null;
        } catch (JSONException e) {
            Log.i("JSON_ERROR", e.getMessage());
            return null;
        }
    }

}
