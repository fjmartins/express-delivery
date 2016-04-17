package views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.anderson.expressdelivery.R;

public class DetalheAnuncioActivity extends AppCompatActivity {

    private TextView titulo, descricao, telefone, endereco;
    private ImageView foto;

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

    public void goBack(View v) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void carregaDados() {
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            this.titulo.setText(extras.getString("titulo"));
            this.descricao.setText(extras.getString("descricao"));
            this.telefone.setText(extras.getString("telefone"));
            this.endereco.setText(extras.getString("endereco"));
            Bitmap bitmap = (Bitmap)extras.getParcelable("fotoBitmap");
            this.foto.setImageBitmap(bitmap);
        }
    }
}
