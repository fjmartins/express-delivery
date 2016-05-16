package views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

/**
 * Created by morte on 15/05/2016.
 */
public class ProposalViewHolder extends RecyclerView.ViewHolder{

    protected TextView viewTitle, viewDescription, viewUserFrom, viewValue;

    public ProposalViewHolder(View itemView) {
        super(itemView);

        viewTitle = (TextView) itemView.findViewById(R.id.text_title_proposal);
        viewDescription = (TextView) itemView.findViewById(R.id.text_description_proposal);
        viewUserFrom = (TextView) itemView.findViewById(R.id.txt_user_from_proposal);
        viewValue = (TextView) itemView.findViewById(R.id.txt_value_proposal);
    }
}
