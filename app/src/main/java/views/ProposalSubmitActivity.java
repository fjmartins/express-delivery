package views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.ProposalController;
import models.Announcement;
import models.Proposal;
import services.IResult;
import utils.AnnouncementUtils;
import utils.Validate;

public class ProposalSubmitActivity extends GenericActivity {

    private static final String TAG = "ProposalSubmitActivity";
    private EditText editTextDescription, editTextValue;
    private Button btnSendProposal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proposal_submit_activity);

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        this.editTextDescription = (EditText) findViewById(R.id.txtEnvioPropostaDesc);
        this.editTextValue = (EditText) findViewById(R.id.txtEnvioPropostaValor);
        this.btnSendProposal = (Button) findViewById(R.id.btnSendProposal);

        this.isUserAuth(this);

    }

    public void sendProposal(View view) {
        Log.d(TAG, "Send Proposal");
        if (!validate()) {
            sendProposalFailed("Os dados são inválidos");
            return;
        }

        String description = editTextDescription.getText().toString();
        String value = editTextValue.getText().toString();

        Bundle extras = getIntent().getExtras();

        Announcement announcement = AnnouncementUtils.getExtras(extras);

        Proposal proposal = new Proposal(announcement.getTitle(), description,
                Double.parseDouble(value));
        proposal.setAnnouncementId(announcement.getId());
        proposal.setUserFrom(getUsername());

        ProposalController.send(proposal, new IResult<Proposal>() {
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

        sendProposalSuccess();
    }


    public void sendProposalSuccess() {
        btnSendProposal.setEnabled(true);
        setResult(RESULT_OK, null);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void sendProposalFailed(String erro) {
        Toast.makeText(getBaseContext(), erro, Toast.LENGTH_LONG).show();
        btnSendProposal.setEnabled(true);

    }

    public boolean validate() {
        boolean valid = true;

        String description = editTextDescription.getText().toString();
        String value = editTextValue.getText().toString();


        if (description.isEmpty() || description.length() < 10) {
            editTextDescription.setError("Descrição minima de 10 caracteres.");
            valid = false;
        } else {
            editTextDescription.setError(null);
        }

        if (value.isEmpty()) {
            editTextValue.setError("Algum valor deve ser inserido.");
            valid = false;
        } else {
            editTextValue.setError(null);
        }

        return valid;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
