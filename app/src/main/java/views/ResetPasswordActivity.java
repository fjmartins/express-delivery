package views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

import controllers.UserAuthController;
import utils.Validate;

public class ResetPasswordActivity extends GenericActivity {

    private EditText email;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_activity);

        this.email = (EditText)findViewById(R.id.txt_reset_password_activity_email);
        this.send = (Button)findViewById(R.id.btn_reset_password_activity_send);
    }

    public void sendMail(View v) {
        this.send.setEnabled(false);
        if(Validate.validarCampoEmail(this.email)) {
            if (UserAuthController.resetPassword(this.email.getText().toString())) {
                showToastMessage(this, "O link de redefinição foi enviado para o email informado.");
                redirect(this, UserLoginActivity.class);
            } else {
                showToastMessage(this, "Um erro ocorreu ao tentar enviar o email.");
            }
        }
    }
}
