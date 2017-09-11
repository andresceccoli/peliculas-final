package com.androidutn.peliculas.sample;

import com.androidutn.peliculas.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class SampleData {

    public static List<Pelicula> getPeliculas() {
        List<Pelicula> datos = new ArrayList<>();
        datos.add(new Pelicula("Transformers", 3.8,
                "https://upload.wikimedia.org/wikipedia/en/6/66/Transformers07.jpg", "04/07/2007",
                "An ancient struggle between two Cybertronian races, the heroic Autobots and the evil Decepticons, comes to Earth, with a clue to the ultimate power held by a teenager."));
        datos.add(new Pelicula("The Matrix", 4.5,
                "https://image.tmdb.org/t/p/w1280/hEpWvX6Bp79eLxY1kX5ZZJcme5U.jpg", "01/07/1999",
                "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers."));
        datos.add(new Pelicula("The Dark Knight", 4.6,
                "https://images-na.ssl-images-amazon.com/images/I/81AJdOIEIhL._SY550_.jpg", "18/07/2008",
                "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, the Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice."));
        return datos;
    }

}
