package views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

public class UserLoginActivity extends GenericActivity {

    private EditText email;
    private EditText password;

    View.OnClickListener mOCListener;
    View.OnFocusChangeListener mFCListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        email = (EditText) this.findViewById(R.id.edt_loginactivity_email);
        password = (EditText) this.findViewById(R.id.edt_loginactivity_password);

        mFCListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!isValidEmail(email.getText())){
                    showWarning(R.string.aviso_login_email_invalido);
                }
            }
        };

        email.setOnFocusChangeListener(mFCListener);
    }

    public void logar(View view){
        Intent intent = new Intent(this, MainActivity.class);

        if(email.getText().toString().equals("")){
            showWarning(R.string.aviso_login_email_nao_preenchido);
            email.requestFocus();
        }else if(password.getText().toString().equals("")){
            showWarning(R.string.aviso_login_senha_nao_preenchida);
            password.requestFocus();
        } else {
            startActivity(intent);
        }
    }

    public void visitar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cadastrar(View view){
        Intent intent = new Intent(this, UserRegisterActivity.class);
        startActivity(intent);
    }

    public void showWarning(int text){
        Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(R.string.fechar, mOCListener)
                .setActionTextColor(Color.RED)
                .show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
