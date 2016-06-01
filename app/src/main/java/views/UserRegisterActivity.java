package views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;
import controllers.UserAuthController;
import controllers.UserController;
import models.User;
import services.IResultUser;
import utils.Validate;

public class UserRegisterActivity extends GenericActivity {

    private Button btnRegister;
    private EditText name, email, password, passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_activity);

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        this.name = (EditText) findViewById(R.id.txt_register_user_activity_name);
        this.email = (EditText) findViewById(R.id.txt_register_user_activity_email);
        this.password = (EditText) findViewById(R.id.txt_register_user_activity_password);
        this.passwordConfirm = (EditText) findViewById(R.id.txt_register_user_activity_password_confirm);
        this.btnRegister = (Button) findViewById(R.id.btn_register_user_activity_register);
    }

    public void registerUser(View view) {
        if(Validate.validarCampoUsuario(name) && Validate.validarCampoEmail(email) &&
                Validate.validarCampoSenha(password) && Validate.validarCampoConfirm(password, passwordConfirm)){
            final User user = new User(this.name.getText().toString(), this.email.getText().toString(),
                    this.password.getText().toString());

            final Bundle extras = new Bundle();
            extras.putSerializable("user", user);
            extras.putBoolean("isNew", true);

            UserController.signUp(user, new IResultUser<User>() {
                    @Override
                    public void onSuccess(User obj) {
                        UserAuthController.logIn(obj, new IResultUser<User>() {
                            @Override
                            public void onSuccess(User obj) {
                                redirect(UserRegisterActivity.this, MainActivity.class, extras);
                                finish();
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
}
