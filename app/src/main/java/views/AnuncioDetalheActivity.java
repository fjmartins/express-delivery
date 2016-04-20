package views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

import models.Anuncio;

public class AnuncioDetalheActivity extends AppCompatActivity {

    private TextView titulo, descricao, telefone, endereco;
    private ImageView foto;
    private Anuncio anuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anuncio_detalhe_activity);

        this.titulo = (TextView)findViewById(R.id.txtDetAnuncTitulo);
        this.descricao = (TextView)findViewById(R.id.txtDetAnuncDesc);
        this.telefone = (TextView)findViewById(R.id.txtDetAnuncTelefone);
        this.endereco = (TextView)findViewById(R.id.txtDetAnuncEndereco);
        this.foto = (ImageView)findViewById(R.id.imgDetAnuncImagem);

        carregaDados();
    }

    public void atualizar(View v) {
        Intent intent = new Intent(this, AnuncioCadastroActivity.class);
        intent.putExtra("anuncio", this.anuncio);
        startActivity(intent);
    }

    public void carregaDados() {
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            this.anuncio = (Anuncio)extras.get("anuncio");

            this.titulo.setText(anuncio.getTitulo());
            this.descricao.setText(anuncio.getDescricao());
            this.telefone.setText(anuncio.getTelefone());
            this.endereco.setText(anuncio.getEndereco());
//            this.foto.setImageBitmap(anuncio.getFoto());
        }
    }
}
