package views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anderson.expressdelivery.R;

public class UsuarioCadastroActivity extends AppCompatActivity {

    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_cadastro_activity);

        this.btnCadastrar = (Button) findViewById(R.id.btnCadCadastrar);
    }

    public void cadastrarUser(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
