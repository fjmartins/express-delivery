package views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.anderson.expressdelivery.R;

public class DetalheAnuncioActivity extends AppCompatActivity {

    private String titulo, descricao, telefone, endereco;
    private Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_anuncio_activity);

        this.titulo = ((TextView)findViewById(R.id.txtDetAnuncTitulo)).getText().toString();
        this.descricao = ((TextView)findViewById(R.id.txtDetAnuncDesc)).getText().toString();
        this.telefone = ((TextView)findViewById(R.id.txtDetAnuncTelefone)).getText().toString();
        this.endereco = ((TextView)findViewById(R.id.txtDetAnuncEndereco)).getText().toString();
        this.foto = (findViewById(R.id.imgDetAnuncImagem)).getDrawingCache();
    }

    public void atualizar(View v) {
//        Intent intent = new Intent(this, CadastroAnuncioActivity.class);
//        intent.putExtra("titulo", this.titulo);
//        intent.putExtra("descricao", this.descricao);
//        intent.putExtra("telefone", this.telefone);
//        intent.putExtra("endereco", this.endereco);
//        intent.putExtra("fotoBitmap", this.foto);
//        startActivity(intent);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void carregaDados() {
        
    }
}
