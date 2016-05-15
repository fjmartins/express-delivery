package views;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.ProposalController;
import models.Proposal;
import services.IResult;

/**
 * Created by Allan-PC on 08/05/2016.
 */
public class ProposalSubmitActivity extends GenericActivity {

    private EditText title, description, valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proposal_submit_activity);

        this.title = (EditText) findViewById(R.id.txtEnvioPropostaTitulo);
        this.description = (EditText) findViewById(R.id.txtEnvioPropostaDesc);
        this.valor = (EditText) findViewById(R.id.txtEnvioPropostaValor);

        this.isUserAuth(this);

    }

    public void send(View view) {
        if (validate()) {
            Bundle extras = getIntent().getExtras();

            String announcementID = extras.get("id").toString();
            Proposal proposal = new Proposal(this.title.getText().toString(), this.description.getText().toString(),
                    Double.parseDouble(this.valor.getText().toString())).withAnnouncementId(announcementID);

<<<<<<< HEAD
            proposal.setUserFrom(this.getUsername());

            ProposalController.send(proposal , new IResult<Proposal>() {
                @Override
                public void onSuccess(List<Proposal> list) {
=======
        String announcementID = extras.get("id").toString();
        Proposal proposal = new Proposal(this.title.getText().toString(), this.description.getText().toString(),
                Double.parseDouble(this.valor.getText().toString()));
        proposal.setAnnouncementId(announcementID);
        proposal.setUserFrom(this.getUsername());
>>>>>>> 7b6ad52c7e48f6b603dc2872ba1204025602a173

                }

                @Override
                public void onSuccess(Proposal obj) {

                }

                @Override
                public void onError(String msg) {

                }
            });
            redirect(this, MainActivity.class);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.isUserAuth(this);
    }

    private boolean validate() {
        boolean result = true;

        String title = this.title.getText().toString();
        if (title.trim().isEmpty()) {
            this.title.setError("Não pode ser vazio");
            result = false;
        } else if (title.length() < 2) {
            this.title.setError("Pelo menos 5 caracteres");
            result = false;
        } else {
            this.title.setError(null);
        }

        String description = this.description.getText().toString();
        if (description.trim().isEmpty()) {
            this.description.setError("Deve conter uma descrição");
            result = false;
        } else if (description.length() < 2) {
            this.description.setError("Pelo menos 5 caracteres");
            result = false;
        } else {
            this.description.setError(null);
        }

        String valor = this.valor.getText().toString();
        if (valor.trim().isEmpty()) {
            this.valor.setError("Deve conter um valor");
            result = false;
        } else {
            this.valor.setError(null);
        }

        return result;
    }
}
