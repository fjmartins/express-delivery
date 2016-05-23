package views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        this.name = (EditText) findViewById(R.id.txt_register_user_activity_name);
        this.email = (EditText) findViewById(R.id.txt_register_user_activity_email);
        this.password = (EditText) findViewById(R.id.txt_register_user_activity_password);
        this.passwordConfirm = (EditText) findViewById(R.id.txt_register_user_activity_password_confirm);
        this.btnRegister = (Button) findViewById(R.id.btn_register_user_activity_register);
    }

    public void registerUser(View view) {
        if (validate()) {
            final User user = new User(this.name.getText().toString(), this.email.getText().toString(),
                    this.password.getText().toString());

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            dialog.setMessage(R.string.add_address)
                  .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Bundle extras = new Bundle();
                          extras.putSerializable("user", user);
                          redirect(UserRegisterActivity.this, UserRegisterAddressActivity.class, extras);
                      }
                  })
                  .setNegativeButton(R.string.after, new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          UserController.signUp(user, new IResultUser<User>() {
                              @Override
                              public void onSuccess(User obj) {
                                  UserAuthController.logIn(obj, new IResultUser<User>() {
                                      @Override
                                      public void onSuccess(User obj) {
                                          redirect(UserRegisterActivity.this, MainActivity.class);
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
                  })
                  .show();
        }
    }

    private boolean validate() {
        boolean result = true;

        String name = this.name.getText().toString();
        if (name.length() < 5 || name.length() <= 5) {
            this.name.setError("Nome de usuário deve ser entre 5 e 20");
            result = false;
        } else {
            this.name.setError(null);
        }

        String email = this.email.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.email.setError("Email inválido");
            result = false;
        } else if (email.length() < 5 || email.length() > 45) {
            this.email.setError("Email deve conter entrer 5 e 45 caraceres");
            result = false;
        } else {
            this.email.setError(null);
        }

        String password = this.password.getText().toString();
        if (password.trim().isEmpty()) {
            this.password.setError("Senha inválida");
            result = false;
        } else if (password.length() < 5 || password.length() > 20) {
            this.password.setError("Password deve conter entrer 5 e 20 caracteres");
            result = false;
        }else {
            this.password.setError(null);
        }

        String passwordConfirm = this.passwordConfirm.getText().toString();
        if (!password.trim().equals(passwordConfirm.trim())) {
            this.passwordConfirm.setError("Senhas não conferem");
            result = false;
        } else {
            this.passwordConfirm.setError(null);
        }

        return result;
    }
}
