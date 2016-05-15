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

        Bundle extras = getIntent().getExtras();

        String announcementID = extras.get("id").toString();
        Proposal proposal = new Proposal(this.title.getText().toString(), this.description.getText().toString(),
                Double.parseDouble(this.valor.getText().toString())).withAnnouncementId(announcementID);

        proposal.setUserFrom(this.getUsername());

        ProposalController.send(proposal , new IResult<Proposal>() {
            @Override
            public void onSuccess(List<Proposal> list) {

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

    @Override
    protected void onResume() {
        super.onResume();
        this.isUserAuth(this);
    }
}
