package views;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

/**
 * Created by anderson on 21/05/16.
 */
public class UserRegisterAddressActivity extends GenericActivity {

    private EditText street, number, complement, zipcode, district, city;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_address_activity);

        this.street = (EditText) findViewById(R.id.txt_register_user_activity_street);
        this.number = (EditText) findViewById(R.id.txt_register_user_activity_number);
        this.complement = (EditText) findViewById(R.id.txt_register_user_activity_complement);
        this.zipcode = (EditText) findViewById(R.id.txt_register_user_activity_zipcode);
        this.district = (EditText) findViewById(R.id.txt_register_user_activity_district);
        this.city = (EditText) findViewById(R.id.txt_register_user_activity_city);
        this.btnRegister = (Button) findViewById(R.id.btn_register_user_activity_register);
    }

    public void registerUserAddress(View view) {
        if (validate()) {

        }

    }

    private boolean validate() {
        boolean result = true;

        String street = this.street.getText().toString();
        if (street.length() <= 5) {
            this.street.setError("Rua deve conter mais de 5 caracteres");
            result = false;
        } else {
            this.street.setError(null);
        }

        String number = this.number.getText().toString();
        if (number == null || number.isEmpty()) {
            this.number.setError("Número é obrigatório");
            result = false;
        } else {
            this.number.setError(null);
        }

        String zipcode = this.zipcode.getText().toString();
        if (zipcode == null || zipcode.isEmpty()) {
            this.zipcode.setError("CEP é obrigatório");
            result = false;
        } else {
            this.zipcode.setError(null);
        }

        String district = this.district.getText().toString();
        if (district == null || district.isEmpty()) {
            this.district.setError("Bairro é obrigatório");
            result = false;
        } else {
            this.district.setError(null);
        }

        String city = this.city.getText().toString();
        if (city == null || city.isEmpty()) {
            this.city.setError("Cidade é obrigatório");
            result = false;
        } else {
            this.city.setError(null);
        }

        return result;
    }

}
