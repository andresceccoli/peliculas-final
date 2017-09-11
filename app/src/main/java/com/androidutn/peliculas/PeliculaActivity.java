package com.androidutn.peliculas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.androidutn.peliculas.api.Api;
import com.androidutn.peliculas.model.Pelicula;
import com.androidutn.peliculas.model.TmdbVideosResult;
import com.androidutn.peliculas.model.Video;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PELICULA = "idPelicula";

    @BindView(R.id.pelicula_titulo) TextView mTitulo;
    @BindView(R.id.pelicula_poster) ImageView mPoster;
    @BindView(R.id.pelicula_fecha) TextView mFecha;
    @BindView(R.id.pelicula_rating_valor) TextView mRatingValor;
    @BindView(R.id.pelicula_rating) RatingBar mRating;
    @BindView(R.id.pelicula_resumen) TextView mResumen;
    @BindView(R.id.pelicula_idioma) TextView mIdioma;
    @BindView(R.id.pelicula_duracion) TextView mDuracion;
    @BindView(R.id.pelicula_trailer) Button mTrailer;
    private int mId;
    private Pelicula mPelicula;
    private String mVideoYoutubeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        ButterKnife.bind(this);

        mId = getIntent().getIntExtra(EXTRA_ID_PELICULA, -1);

        if (mId == -1) {
            finish();
            return;
        }

//        mPelicula = SampleData.getPeliculas().get(mId);
//        cargarValores();

        buscarPelicula();
    }

    private void buscarPelicula() {
        Api.getMovieService().buscarPelicula(mId)
                .enqueue(new Callback<Pelicula>() {
                    @Override
                    public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                        if (response.isSuccessful()) {
                            mPelicula = response.body();
                            cargarValores();
                        } else {
                            try {
                                Log.e("peliculas", "Error al buscar pelicula: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Pelicula> call, Throwable t) {
                        Log.e("peliculas", "Error al buscar pelicula", t);
                    }
                });

        Api.getMovieService().buscarVideos(mId)
                .enqueue(new Callback<TmdbVideosResult>() {
                    @Override
                    public void onResponse(Call<TmdbVideosResult> call, Response<TmdbVideosResult> response) {
                        if (response.isSuccessful()) {
                            TmdbVideosResult result = response.body();
                            for (Video video : result.getResults()) {
                                if (video.getSite().equalsIgnoreCase("youtube") && video.getType().equalsIgnoreCase("trailer")) {
                                    mVideoYoutubeKey = video.getKey();
                                    break;
                                }
                            }

                            if (mVideoYoutubeKey != null) {
                                mTrailer.setVisibility(View.VISIBLE);
                            } else {
                                mTrailer.setVisibility(View.GONE);
                            }
                        } else {
                            mTrailer.setVisibility(View.GONE);

                            try {
                                Log.e("peliculas", "Error buscando videos: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TmdbVideosResult> call, Throwable t) {
                        mTrailer.setVisibility(View.GONE);
                        Log.e("peliculas", "Error buscando videos", t);
                    }
                });
    }

    private void cargarValores() {
        mTitulo.setText(mPelicula.getTitulo());
        mFecha.setText(mPelicula.getFecha());
        mRating.setRating((float) mPelicula.getPuntaje() / 2);
        mRatingValor.setText("" + mPelicula.getPuntaje() / 2);
        mResumen.setText(mPelicula.getResumen());
        mIdioma.setText(mPelicula.getIdioma());
        mDuracion.setText(mPelicula.getDuracion() + " mins");
        Picasso.with(this).load(mPelicula.getTmdbImagenUrl()).placeholder(R.drawable.movie).into(mPoster);
    }

    @OnClick(R.id.pelicula_trailer)
    public void onTrailer() {
        if (mVideoYoutubeKey != null) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + mVideoYoutubeKey));
            startActivity(i);
        }
    }
}
