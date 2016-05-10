package views;

import android.os.Bundle;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

/**
 * Created by Allan-PC on 08/05/2016.
 */
public class ProposalSubmitActivity extends GenericActivity {

    private EditText title, description, valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_proposal_activity);

        this.isUserAuth(this);

        this.title = (EditText) findViewById(R.id.txtEnvioPropostaTitulo);
        this.description = (EditText) findViewById(R.id.txtEnvioPropostaDesc);
        this.valor = (EditText) findViewById(R.id.txtEnvioPropostaValor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.isUserAuth(this);
    }
}
