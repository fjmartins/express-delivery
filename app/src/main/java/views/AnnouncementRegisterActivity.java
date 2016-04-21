package views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;

import models.Announcement;
import utils.AnuncioData;

/**
 * Created by Allan-PC on 17/04/2016.
 */
public class AnnouncementRegisterActivity extends GenericActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private EditText titulo, descricao, telefone, endereco;
    private ImageButton foto;
    private Announcement announcement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anuncio_cadastro_activity);

        this.titulo = (EditText)findViewById(R.id.txtCadAnuncioTitulo);
        this.descricao = (EditText)findViewById(R.id.txtCadAnuncioDesc);
        this.telefone = (EditText)findViewById(R.id.txtCadAnuncioTelefone);
        this.endereco = (EditText)findViewById(R.id.txtCadAnuncioEndereco);
        this.foto = (ImageButton)findViewById(R.id.btnCadAnuncioImagem);

        carregaDados();
    }

    public void tirarFoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Bundle b = data.getExtras();
                    Bitmap btm = b.getParcelable("data");
                    btm = Bitmap.createScaledBitmap(btm, 200, 200, true);
                    AnnouncementRegisterActivity.this.foto.setImageBitmap(btm);
                }
            }
        }
    }

    public void salvar(View v) {
        if (valida()) {
            Bitmap foto = this.foto.getDrawingCache();
            Announcement announcement = new Announcement(null, this.titulo.getText().toString(), this.descricao.getText().toString(),
                    this.endereco.getText().toString(), this.telefone.getText().toString(), foto);
            AnuncioData.getInstance().insertAnuncio(announcement);

            redirect(this, MainActivity.class);
        } else {
            Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean valida() {
        if (!this.titulo.getText().toString().equals("") && !this.telefone.getText().toString().equals(""))
            return true;
        return false;
    }

    private void carregaDados() {
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            Button btnCad = (Button)findViewById(R.id.btnCadAnuncioCadastrar);
            btnCad.setText("Atualizar");

            this.announcement = (Announcement)extras.get("announcement");

            this.titulo.setText(announcement.getTitle());
            this.descricao.setText(announcement.getDescription());
            this.telefone.setText(announcement.getTelefone());
            this.endereco.setText(announcement.getEndereco());
            this.foto.setImageBitmap(announcement.getPicture());
        }
    }
 }
