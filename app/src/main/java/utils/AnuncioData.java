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
        list.add(new Anuncio("ANUNCIO 1", "ESSE ANUNCIO É FODA", "1"));
        list.add(new Anuncio("ANUNCIO 2", "ESSE ANUNCIO É FODA", "2"));
        list.add(new Anuncio("ANUNCIO 3 ", "ESSE ANUNCIO É FODA", "3"));
        list.add(new Anuncio("ANUNCIO 4", "ESSE ANUNCIO É FODA", "4"));
        return list;
    }
}