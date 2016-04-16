package models;

import android.widget.ImageView;

/**
 * Created by morte on 16/04/2016.
 */
public class Anuncio {

    private String titulo;
    private String descricao;
    private String endereco;
    private String telefone;
    private ImageView[] fotos;
    private Usuario usuario;

    public Anuncio(Usuario usuario, String titulo, String descricao, String endereco, String telefone, ImageView[] fotos) {
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

    public ImageView[] getFotos() {
        return fotos;
    }

    public void setFotos(ImageView[] fotos) {
        this.fotos = fotos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
