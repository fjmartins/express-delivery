package views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;
import models.Announcement;

import java.util.List;

/**
 * Created by morte on 16/04/2016.
 */
public class AnuncioAdapter extends RecyclerView.Adapter<AnuncioAdapter.AnuncioViewHolder> {

    private List<Announcement> mList;

    public AnuncioAdapter(List<Announcement> list){
        this.mList = list;
    }
    @Override
    public AnuncioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.principal_activity_adapter, viewGroup, false);
        return new AnuncioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnuncioViewHolder viewHolder, int i) {
        Announcement announcement = mList.get(i);
        viewHolder.viewTitulo.setText(announcement.getTitle());
        viewHolder.viewDescricao.setText(announcement.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected static class AnuncioViewHolder extends RecyclerView.ViewHolder{

        protected TextView viewTitulo;
        protected TextView viewDescricao;

        public AnuncioViewHolder(View itemView) {
            super(itemView);


            viewTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            viewDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
        }
    }
}