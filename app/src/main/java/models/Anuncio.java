package models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by morte on 16/04/2016.
 */
public class Anuncio implements Serializable {

    private String titulo;
    private String descricao;
    private String endereco;
    private String telefone;
    private Bitmap[] fotos;
    private Usuario usuario;

    public Anuncio(Usuario usuario, String titulo, String descricao, String endereco, String telefone, Bitmap[] fotos) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.telefone = telefone;
        this.fotos = fotos;
    }

    public Anuncio(String titulo, String descricao, String telefone) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.telefone = telefone;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Bitmap[] getFotos() {
        return fotos;
    }

    public void setFotos(Bitmap[] fotos) {
        this.fotos = fotos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
