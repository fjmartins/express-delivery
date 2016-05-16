package views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

import controllers.UserAuthController;
import controllers.UserController;
import models.User;
import services.IResultUser;

public class UserLoginActivity extends GenericActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);

        username = (EditText) this.findViewById(R.id.edt_loginactivity_email);
        password = (EditText) this.findViewById(R.id.edt_loginactivity_password);

        if (UserAuthController.getCurrentUser() != null) {
            this.goToMainIfUserAuth();
            finish();
        }
    }

    public void logIn(View view) {
        if (validate()) {
            User user = new User(username.getText().toString(), password.getText().toString());
            UserAuthController.logIn(user, new IResultUser<User>() {
                @Override
                public void onSuccess(User obj) {
                    redirect(UserLoginActivity.this, MainActivity.class);
                    finish();
                }

                @Override
                public void onError(String msg) {
                    showToastMessage(UserLoginActivity.this, msg);
                }
            });
        }
    }

    public void visitar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void cadastrar(View view) {
        Intent intent = new Intent(this, UserRegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validate() {
        boolean result = true;

        String username = this.username.getText().toString();
        if (username.length() < 5 || username.length() > 20) {
            this.username.setError("Nome de usuário deve ser entre 5 e 20 caracteres");
            result = false;
        } else {
            this.username.setError(null);
        }

        String password = this.password.getText().toString();
        if (password.length() < 5 || password.length() > 20) {
            this.password.setError("Senha de usuário deve ser entre 5 e 20 caracteres");
            result = false;
        } else {
            this.password.setError(null);
        }

        return result;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
