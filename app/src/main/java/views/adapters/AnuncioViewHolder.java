package views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

/**
 * Created by morte on 13/05/2016.
 */
public class AnuncioViewHolder extends RecyclerView.ViewHolder {

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
