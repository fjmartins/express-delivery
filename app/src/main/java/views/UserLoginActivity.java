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

public class UserLoginActivity extends GenericActivity {

    protected EditText username;
    protected EditText password;
    protected ProgressDialog progressDialog;

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
            new LoginTask().execute();
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
}
