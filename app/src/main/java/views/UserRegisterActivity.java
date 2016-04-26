package views;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.UserAuthController;
import controllers.UserController;
import services.IResult;
import models.User;
import services.IResultUser;

public class UserRegisterActivity extends GenericActivity {

    private Button btnRegister;
    private EditText name, email, password, passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_activity);

        this.name = (EditText)findViewById(R.id.txt_register_user_activity_name);
        this.email = (EditText)findViewById(R.id.txt_register_user_activity_email);
        this.password = (EditText)findViewById(R.id.txt_register_user_activity_password);
        this.passwordConfirm = (EditText)findViewById(R.id.txt_register_user_activity_password_confirm);
        this.btnRegister = (Button) findViewById(R.id.btn_register_user_activity_register);
    }

    public void registerUser(View view) {
        if (validate()) {
            User user = new User(this.name.getText().toString(), this.email.getText().toString(),
                    this.password.getText().toString());
            UserController.signUp(user, new IResultUser<User>() {
                @Override
                public void onSuccess(User obj) {
                    UserAuthController.logIn(obj, new IResultUser<User>() {
                        @Override
                        public void onSuccess(User obj) {
                            redirect(UserRegisterActivity.this, MainActivity.class);
                        }

                        @Override
                        public void onError(String msg) {
                            showToastMessage(UserRegisterActivity.this, msg);
                        }
                    });
                }

                @Override
                public void onError(String msg) {
                    showToastMessage(UserRegisterActivity.this, msg);
                }
            });
        }
    }

    private boolean validate() {
        boolean result = true;

        String name = this.name.getText().toString();
        if (name.trim().isEmpty() || name.length() < 2){
            this.name.setError("Pelo menos 2 caracteres");
            result = false;
        } else {
            this.name.setError(null);
        }

        String email = this.email.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            this.email.setError("Email inválido");
            result = false;
        } else {
            this.email.setError(null);
        }

        String password = this.password.getText().toString();
        if (password.trim().isEmpty()){
            this.password.setError("Senha inválida");
            result = false;
        } else {
            this.password.setError(null);
        }

        String passwordConfirm = this.passwordConfirm.getText().toString();
        if (!password.trim().equals(passwordConfirm.trim())){
            this.passwordConfirm.setError("Senhas não conferem");
            result = false;
        } else {
            this.passwordConfirm.setError(null);
        }
        return result;
    }
}
