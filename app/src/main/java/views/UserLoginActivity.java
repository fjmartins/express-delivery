package views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import controllers.UserAuthController;
import controllers.UserController;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    private void getUserDetailsFromFB() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            User user = new User();
                            user.setUsername(response.getJSONObject().getString("name"));
                            user.setEmail(response.getJSONObject().getString("email"));

                            UserController.signUp(user, new IResultUser<User>() {
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    public void logInFacebook(View v) {
        List<String> permissions = Arrays.asList("email", "public_profile");

        ParseFacebookUtils.logInWithReadPermissionsInBackground(
            UserLoginActivity.this,
            permissions,
            new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user == null) {
                        if(e != null) {
                            Log.i("ERRO_FB", e.getMessage());
                            showToastMessage(UserLoginActivity.this, "Erro na conexão com o Facebook");
                        } else {
                            showToastMessage(UserLoginActivity.this, "Usuário sem acesso");
                        }
                    } else if (user.isNew()) {
                        getUserDetailsFromFB();
                    } else {
                        GraphRequest request = GraphRequest.newMeRequest(
                                AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        try {
                                            ParseUser user = ParseUser.getCurrentUser();
                                            user.setEmail(object.getString("email"));
                                            user.setUsername(object.getString("name"));
                                            user.saveEventually();
                                        } catch(JSONException e) {
                                            e.printStackTrace();
                                        }
                                        redirect(UserLoginActivity.this, MainActivity.class);
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }
                }
            });
    }
}
