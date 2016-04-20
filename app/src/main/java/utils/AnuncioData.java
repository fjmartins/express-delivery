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

    private List<Anuncio> list = new ArrayList<>();
    private static AnuncioData instance;

    private AnuncioData() {
        this.criaAnuncios();
    }

    public static AnuncioData getInstance() {
        return (instance == null) ? new AnuncioData() : instance;
    }

    private void criaAnuncios() {
        Bitmap foto = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_menu_camera);
        for (int i = 1; i <= 4; i++){
            Anuncio anuncio = new Anuncio(null, "TÍTULO ANUNCIO "+i, "DESCRIÇÃO DO ANUNCIO "+i,
                    "Rua dos Lobos, N zero"+i, "(81)912345678", foto);
            this.list.add(anuncio);
        }
    }

    public List<Anuncio> getAnuncios() {
        return this.list;
    }

    public void insertAnuncio(Anuncio anuncio) {
        this.list.add(anuncio);
    }
}