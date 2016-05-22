package views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.util.Log;

import java.util.List;

import controllers.UserAuthController;
import controllers.UserController;
import models.User;
import services.IResultUser;

public class UserLoginActivity extends GenericActivity {

    protected EditText username;
    protected EditText password;
    protected ProgressDialog progressDialog;
    private LoginButton buttonLoginFacebook;
    private LoginManager loginManager;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
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

    public void logInFace(View view) {

        buttonLoginFacebook = (LoginButton) findViewById(R.id.fbLogin);
        buttonLoginFacebook.setReadPermissions("email");
        loginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        //buttonLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //getIntent().putExtra(/*getUsername()*/, loginResult.getAccessToken());
                //setResult(Activity.RESULT_OK);
                //finish();

                Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("data",data.toString());
    }
*/
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
                    progressDialog.hide();
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}
