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
        if(Validate.validarCampoUsuario(name) && Validate.validarCampoEmail(email) && Validate.validarCampoSenha(password) && Validate.validarCampoConfirm(password, passwordConfirm)){
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

    @Override
    public void finish(){
        super.finish();

        overridePendingTransition(R.anim.main_activity_enter, R.anim.register_activity_exit);
    }
}
