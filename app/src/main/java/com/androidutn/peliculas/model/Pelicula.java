package com.androidutn.peliculas.model;

import com.google.gson.annotations.SerializedName;

public class Pelicula {

    private int id;
    @SerializedName("title")
    private String titulo;
    @SerializedName("vote_average")
    private double puntaje;
    @SerializedName("poster_path")
    private String imagenUrl;
    @SerializedName("release_date")
    private String fecha;
    @SerializedName("overview")
    private String resumen;
    @SerializedName("original_language")
    private String idioma;
    @SerializedName("runtime")
    private int duracion;

    public Pelicula(String titulo, double puntaje, String imagenUrl, String fecha, String resumen) {
        this.titulo = titulo;
        this.puntaje = puntaje;
        this.imagenUrl = imagenUrl;
        this.fecha = fecha;
        this.resumen = resumen;
    }

    public Pelicula() {

    }

    public String getTmdbImagenUrl() {
        return "http://image.tmdb.org/t/p/" + "w342" + imagenUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
