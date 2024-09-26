package classes;

import java.time.LocalDate;

public class Pelicula {
    
    private String idPelicula;
    private String title;
    private String director;
    private String year;
    private String genre;
    private String price;

    public Pelicula(String idPelicula, String title, String director, String year, String genre, String price) {
        this.idPelicula = idPelicula;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.price = price;
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPrice() {
        return price;
    }
    
    
    
}
