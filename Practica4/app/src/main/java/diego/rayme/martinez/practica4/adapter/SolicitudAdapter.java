package diego.rayme.martinez.practica4.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import diego.rayme.martinez.practica4.R;
import diego.rayme.martinez.practica4.model.Solicitud;

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.ViewHolder>{


    private List<Solicitud> solicitudes;

    public SolicitudAdapter() {
        this.solicitudes = new ArrayList<>();
    }

    public void setSolicitudes (List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView emailText;
        public TextView tipoText;


        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_image);
            tipoText = itemView.findViewById(R.id.tipo_text);
            emailText = itemView.findViewById(R.id.email_text);

        }
    }

    @Override
    public SolicitudAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solicitud, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SolicitudAdapter.ViewHolder viewHolder, int position) {

        Solicitud solicitud = this.solicitudes.get(position);
        viewHolder.tipoText.setText(solicitud.getTipo());
        viewHolder.emailText.setText(solicitud.getCorreo());

        String url = solicitud.getCorreo();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.solicitudes.size();
    }


}
