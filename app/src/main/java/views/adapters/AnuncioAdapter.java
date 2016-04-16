package views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;
import models.Anuncio;

import java.util.List;

/**
 * Created by morte on 16/04/2016.
 */
public class AnuncioAdapter extends RecyclerView.Adapter<AnuncioAdapter.AnuncioViewHolder> {

    private List<Anuncio> mList;

    public AnuncioAdapter(List<Anuncio> list){
        this.mList = list;
    }
    @Override
    public AnuncioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.principal_adapter, viewGroup, false);
        return new AnuncioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnuncioViewHolder viewHolder, int i) {
        Anuncio anuncio = mList.get(i);
        viewHolder.viewTitulo.setText(anuncio.getTitulo());
        viewHolder.viewDescricao.setText(anuncio.getDescricao());
        viewHolder.viewTelefone.setText(anuncio.getTelefone());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected static class AnuncioViewHolder extends RecyclerView.ViewHolder{

        protected TextView viewTitulo;
        protected TextView viewDescricao;
        protected TextView viewTelefone;

        public AnuncioViewHolder(View itemView) {
            super(itemView);


            viewTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            viewDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            viewTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);
        }
    }
}