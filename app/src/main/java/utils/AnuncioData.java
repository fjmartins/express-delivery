package utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.anderson.expressdelivery.R;

import models.Announcement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morte on 16/04/2016.
 */
public class AnuncioData {

    private List<Announcement> list = new ArrayList<>();
    private static AnuncioData instance;

    private AnuncioData(Resources res) {
        this.criaAnuncios(res);
    }

    public static AnuncioData getInstance(Resources res) {
        return (instance == null) ? new AnuncioData(res) : instance;
    }

    ImageView image;
    private void criaAnuncios(Resources res) {
        Bitmap foto = BitmapFactory.decodeResource(res ,(R.drawable.ic_menu_camera));
        for (int i = 1; i <= 4; i++){
            Announcement announcement = new Announcement(null, "TÍTULO ANUNCIO "+i, "DESCRIÇÃO DO ANUNCIO "+i,
                    "Rua dos Lobos, N zero"+i, "(81)912345678", foto);
            this.list.add(announcement);
        }
    }

    public List<Announcement> getAnuncios() {
        return this.list;
    }

    public void insertAnuncio(Announcement announcement) {
        this.list.add(announcement);
    }
}