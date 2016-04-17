package utils;

import models.Anuncio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morte on 16/04/2016.
 */
public class AnuncioData {

    public static List<Anuncio> getAnuncio() {
        List<Anuncio> list = new ArrayList<>();
        list.add(new Anuncio("TITULO ANUNCIO 1", "DESCRIÇÃO DO ANUNCIO", "1"));
        list.add(new Anuncio("TITULO ANUNCIO 2",  "DESCRIÇÃO DO ANUNCIO", "2"));
        list.add(new Anuncio("TITULO ANUNCIO 3 ",  "DESCRIÇÃO DO ANUNCIO", "3"));
        list.add(new Anuncio("TITULO ANUNCIO 4",  "DESCRIÇÃO DO ANUNCIO", "4"));
        return list;
    }
}