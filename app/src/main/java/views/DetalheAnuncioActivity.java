package views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

import models.Anuncio;

public class DetalheAnuncioActivity extends Activity {

    private TextView titulo, descricao, telefone, endereco;
    private ImageView foto;
    private Anuncio anuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_anuncio_activity);

        this.titulo = (TextView)findViewById(R.id.txtDetAnuncTitulo);
        this.descricao = (TextView)findViewById(R.id.txtDetAnuncDesc);
        this.telefone = (TextView)findViewById(R.id.txtDetAnuncTelefone);
        this.endereco = (TextView)findViewById(R.id.txtDetAnuncEndereco);
        this.foto = (ImageView)findViewById(R.id.imgDetAnuncImagem);

        carregaDados();
    }

    public void atualizar(View v) {
//        Intent intent = new Intent(this, CadastroAnuncioActivity.class);
//        intent.putExtra("titulo", this.titulo.getText().toString());
//        intent.putExtra("descricao", this.descricao.getText().toString());
//        intent.putExtra("telefone", this.telefone.getText().toString());
//        intent.putExtra("endereco", this.endereco.getText().toString());
//        intent.putExtra("fotoBitmap", this.foto.getDrawingCache());
//        startActivity(intent);
    }

    public void carregaDados() {
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            this.anuncio = (Anuncio)extras.get("anuncio");

            this.titulo.setText(anuncio.getTitulo());
            this.descricao.setText(anuncio.getDescricao());
            this.telefone.setText(anuncio.getTelefone());
            this.endereco.setText(anuncio.getEndereco());
            this.foto.setImageBitmap(anuncio.getFotos()[0]);
        }
    }
}
