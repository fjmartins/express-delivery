package views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;
import controllers.UserAuthController;
import models.User;
import services.IResultUser;
import utils.Validate;

public class UserLoginActivity extends GenericActivity {

    protected EditText username;
    protected EditText password;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        username = (EditText) this.findViewById(R.id.edt_loginactivity_email);
        password = (EditText) this.findViewById(R.id.edt_loginactivity_password);

        if (UserAuthController.getCurrentUser() != null) {
            this.goToMainIfUserAuth();
            finish();
        }
    }

    public void logIn(View view) {
        if (Validate.validarCampoUsuario(username) && Validate.validarCampoSenha(password)) {
            new LoginTask().execute();
        }
    }

    public void visitar(View view) {
        redirect(this, MainActivity.class);
        finish();
    }

    public void cadastrar(View view) {
        redirect(this, UserRegisterActivity.class);
        finish();
    }

    public void forgotPassword(View v) {
        redirect(this, ResetPasswordActivity.class);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected class LoginTask extends AsyncTask<Void, Void, Void> {

        String name = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(UserLoginActivity.this);
            progressDialog.setTitle("Entrando");
            progressDialog.setMessage("Aguarde ...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            User user = new User(name, pass);
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressDialog.hide();
        }
    }

    @Override
    public void finish(){
        super.finish();

        overridePendingTransition(R.anim.main_activity_enter, R.anim.register_activity_exit);
    }
}
