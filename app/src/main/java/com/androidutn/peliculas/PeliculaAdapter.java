package com.androidutn.peliculas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.androidutn.peliculas.model.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder> {

    public interface PeliculaListener {
        void onPeliculaSeleccionada(int id);
    }

    private List<Pelicula> datos;
    private PeliculaListener listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setPelicula(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos != null ? datos.size() : 0;
    }

    public void setDatos(List<Pelicula> datos) {
        this.datos = datos;
        this.notifyDataSetChanged();
    }

    public void setListener(PeliculaListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_titulo) TextView mTitulo;
        @BindView(R.id.item_anio) TextView mAnio;
        @BindView(R.id.item_rating) RatingBar mRating;
        @BindView(R.id.item_rating_valor) TextView mRatingValor;
        @BindView(R.id.item_poster) ImageView mPoster;
        @BindView(R.id.item_resumen) TextView mResumen;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void setPelicula(Pelicula pelicula) {
            mTitulo.setText(pelicula.getTitulo());
            mAnio.setText(pelicula.getFecha());
            mRating.setRating((float) (pelicula.getPuntaje() / 2));
            mRatingValor.setText("" + (pelicula.getPuntaje() / 2));
            mResumen.setText(pelicula.getResumen());

            Picasso.with(itemView.getContext()).load(pelicula.getTmdbImagenUrl()).placeholder(R.drawable.movie).into(mPoster);
        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onPeliculaSeleccionada(datos.get(getAdapterPosition()).getId());
        }
    }
}
