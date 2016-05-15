package views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

/**
 * Created by morte on 15/05/2016.
 */
public class ProposalViewHolder extends RecyclerView.ViewHolder{

    protected TextView viewTitulo;
    protected TextView viewDescricao;

    public ProposalViewHolder(View itemView) {
        super(itemView);

        viewTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
        viewDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
    }
}
