package com.androidutn.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.androidutn.peliculas.api.Api;
import com.androidutn.peliculas.model.TmdbQueryResult;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PeliculaAdapter.PeliculaListener {

    @BindView(R.id.lista) RecyclerView mLista;
    @BindView(R.id.busqueda_text) EditText mBusquedaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final PeliculaAdapter adapter = new PeliculaAdapter();
        adapter.setListener(this);
        mLista.setAdapter(adapter);
    }

    @Override
    public void onPeliculaSeleccionada(int id) {
        Intent i = new Intent(this, PeliculaActivity.class);
        i.putExtra(PeliculaActivity.EXTRA_ID_PELICULA, id);
        startActivity(i);
    }

    @OnClick(R.id.buscar_icon)
    public void onBuscar() {
        if (!TextUtils.isEmpty(mBusquedaText.getText())) {
            String texto = mBusquedaText.getText().toString();

            Api.getMovieService().buscarPeliculas(texto)
                    .enqueue(new Callback<TmdbQueryResult>() {
                        @Override
                        public void onResponse(Call<TmdbQueryResult> call, Response<TmdbQueryResult> response) {
                            if (response.isSuccessful()) {
                                TmdbQueryResult result = response.body();
                                ((PeliculaAdapter) mLista.getAdapter()).setDatos(result.getResults());
                            } else {
                                try {
                                    Log.e("peliculas", "Error al buscar peliculas: " + response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<TmdbQueryResult> call, Throwable t) {
                            Log.e("peliculas", "Error al buscar peliculas", t);
                        }
                    });
        }
    }


}
