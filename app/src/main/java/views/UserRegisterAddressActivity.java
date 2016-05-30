package views;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import controllers.UserAuthController;
import controllers.UserController;
import models.Address;
import models.User;
import services.IResultUser;
import utils.HTTPUtils;
import utils.Mask;
import utils.Validate;

/**
 * Created by anderson on 21/05/16.
 */
public class UserRegisterAddressActivity extends GenericActivity {

    private EditText street, number, complement, zipcode, district, city, state;
    private Button btnRegister;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_address_activity);

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        this.street = (EditText) findViewById(R.id.txt_register_user_activity_street);
        this.number = (EditText) findViewById(R.id.txt_register_user_activity_number);
        this.complement = (EditText) findViewById(R.id.txt_register_user_activity_complement);
        this.zipcode = (EditText) findViewById(R.id.txt_register_user_activity_zipcode);
        zipcode.addTextChangedListener(Mask.insert(Mask.CEP_MASK, zipcode));
        this.district = (EditText) findViewById(R.id.txt_register_user_activity_district);
        this.city = (EditText) findViewById(R.id.txt_register_user_activity_city);
        this.state = (EditText) findViewById(R.id.txt_register_user_activity_uf);
        this.btnRegister = (Button) findViewById(R.id.btn_register_user_activity_register);
    }

    public void registerUserAddress(View view) {
        this.btnRegister.setEnabled(false);
        if (Validate.validarCampoStreet(street) &&
                Validate.validarCampoNumber(number) &&
                Validate.validarCampoCity(city) &&
                Validate.validarCampoZipcod(zipcode) &&
                Validate.validarCampoDistrict(district)) {
            Bundle extras = getIntent().getExtras();
            User user = (User)extras.getSerializable("user");

            Address address = new Address();
            address.setCity(this.city.getText().toString());
            address.setZipCode(this.zipcode.getText().toString());
            address.setNumber(this.number.getText().toString());
            address.setComplement(this.complement.getText().toString());
            address.setDistrict(this.district.getText().toString());

            user.setAddresses(Arrays.asList(address));

            UserController.signUp(user, new IResultUser<User>() {
                @Override
                public void onSuccess(User obj) {
                    UserAuthController.logIn(obj, new IResultUser<User>() {
                        @Override
                        public void onSuccess(User obj) {
                            redirect(UserRegisterAddressActivity.this, MainActivity.class);
                            finish();
                        }

                        @Override
                        public void onError(String msg) {
                            showToastMessage(UserRegisterAddressActivity.this, msg);
                            UserRegisterAddressActivity.this.btnRegister.setEnabled(true);
                        }
                    });
                }

                @Override
                public void onError(String msg) {
                    showToastMessage(UserRegisterAddressActivity.this, msg);
                    UserRegisterAddressActivity.this.btnRegister.setEnabled(true);
                }
            });
        }
    }

    public void searchAddress(View v) {
        if (!this.zipcode.getText().toString().isEmpty()) {
            new AddressTask(this.zipcode.getText().toString()).execute();
        } else {
            showToastMessage(this, "Insira um CEP para consulta");
        }
    }

    private class AddressTask extends AsyncTask<Void, Void, Void> {

        private String cep;

        public AddressTask(String cep) {
            this.cep = cep;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(UserRegisterAddressActivity.this);
            progressDialog.setTitle("Buscando Endereço");
            progressDialog.setMessage("Aguarde ...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        JSONObject address;

        @Override
        protected Void doInBackground(Void... params) {
            address = HTTPUtils.getAddress(this.cep);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (address != null) {
                if (address.has("logradouro")) {
                    try {
                        street.setText(address.getString("logradouro"));
                        complement.setText(address.getString("complemento"));
                        district.setText(address.getString("bairro"));
                        city.setText(address.getString("localidade"));
                        state.setText(address.getString("uf"));
                    } catch (JSONException e) {
                        Log.i("JSON_ERROR", e.getMessage());
                    }
                }
            } else {
                showToastMessage(UserRegisterAddressActivity.this, "CEP inválido");
            }

            progressDialog.hide();
        }
    }

}
