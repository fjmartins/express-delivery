package views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;

public class UsuarioCadastroActivity extends GenericActivity {

    private Button btnCadastrar;
    private EditText nome, email, senha, confSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_cadastro_activity);

        this.nome = (EditText)findViewById(R.id.txtCadUser);
        this.email = (EditText)findViewById(R.id.txtCadMail);
        this.senha = (EditText)findViewById(R.id.txtCadPassword);
        this.confSenha = (EditText)findViewById(R.id.txtCadConfirmPassword);
        this.btnCadastrar = (Button) findViewById(R.id.btnCadCadastrar);
    }

    public void cadastrarUser(View view) {
        if (valida())
            redirect(this, LoginActivity.class);
        else
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
    }

    private boolean valida() {
        if (!this.nome.getText().toString().equals("") && !this.email.getText().toString().equals("") &&
                !this.senha.getText().toString().equals("") && !this.confSenha.getText().toString().equals(""))
            return true;
        return false;
    }
}
