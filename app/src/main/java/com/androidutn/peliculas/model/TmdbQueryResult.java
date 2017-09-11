package com.androidutn.peliculas.model;

import java.util.List;

public class TmdbQueryResult {

    private int page;
    private List<Pelicula> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Pelicula> getResults() {
        return results;
    }

    public void setResults(List<Pelicula> results) {
        this.results = results;
    }
}
