package views;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

public class DetalheAnuncioActivity extends Activity {

    private String titulo, descricao, telefone, endereco;
    private Image foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_anuncio_activity);

        this.titulo = ((TextView)findViewById(R.id.txtDetAnuncTitulo)).getText().toString();
        this.descricao = ((TextView)findViewById(R.id.txtDetAnuncDesc)).getText().toString();
        this.telefone = ((TextView)findViewById(R.id.txtDetAnuncTelefone)).getText().toString();
        this.endereco = ((TextView)findViewById(R.id.txtDetAnuncEndereco)).getText().toString();
    }

    public void atualizar(View v) {
//        Intent intent = new Intent(this, CadastroAnuncioActivity.class);
//        intent.putExtra("titulo", this.titulo);
//        intent.putExtra("descricao", this.descricao);
//        intent.putExtra("telefone", this.telefone);
//        intent.putExtra("endereco", this.endereco);
//        startActivity(intent);
    }
}
