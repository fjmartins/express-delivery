package views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

import models.Announcement;

public class AnnouncementDetailActivity extends GenericActivity {

    private TextView titulo, descricao, telefone, endereco;
    private ImageView foto;
    private Announcement announcement;

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
        Bundle extras = new Bundle();
        extras.putSerializable("announcement", this.announcement);
        redirect(this, AnnouncementRegisterActivity.class, extras);
    }

    private void carregaDados() {
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            this.announcement = (Announcement)extras.get("announcement");

            this.titulo.setText(announcement.getTitle());
            this.descricao.setText(announcement.getDescription());
            this.telefone.setText(announcement.getTelefone());
            this.endereco.setText(announcement.getEndereco());
            this.foto.setImageBitmap(announcement.getPicture());
        }
    }
}
