package views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;
import models.Announcement;

import java.util.List;

/**
 * Created by morte on 16/04/2016.
 */
public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnuncioViewHolder> {

    private List<Announcement> mList;

    public AnnouncementAdapter(List<Announcement> list){
        this.mList = list;
    }
    @Override
    public AnuncioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_activity_adapter, viewGroup, false);
        return new AnuncioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnuncioViewHolder viewHolder, int i) {
        Announcement announcement = mList.get(i);
        viewHolder.imageAnuncio.setImageBitmap(announcement.getPicture());
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
        protected ImageView imageAnuncio;

        public AnuncioViewHolder(View itemView) {
            super(itemView);

            imageAnuncio = (ImageView) itemView.findViewById(R.id.img_main_announcment);
            viewTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            viewDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
        }
    }
}