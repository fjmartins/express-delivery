package utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.anderson.expressdelivery.R;

import models.Anuncio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morte on 16/04/2016.
 */
public class AnuncioData {

    public static List<Anuncio> getAnuncio() {
        List<Anuncio> list = new ArrayList<>();
        Bitmap[] fotos = new Bitmap[1];
        Bitmap btm = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_menu_camera);
        fotos[0] = btm;
        for (int i = 1; i <= 10; i++){
            Anuncio anuncio = new Anuncio(null, "TÍTULO ANUNCIO "+i, "DESCRIÇÃO DO ANUNCIO "+i,
                    "Rua dos Lobos, N zero"+i, "(81)912345678", fotos);
            list.add(anuncio);
        }
        return list;
    }
}