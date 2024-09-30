package classes;

import java.time.LocalDate;

public class Pelicula {
    
    private String idPelicula;
    private String title;
    private String director;
    private String year;
    private String genre;
    private String price;

    @Override
    public String toString() {
        return "Pelicula{" + "idPelicula=" + idPelicula + ", title=" + title + ", director=" + director + ", year=" + year + ", genre=" + genre + ", price=" + price + '}';
    }
    
    

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
    
    public void setAttribute(String key, String value){
        if(key.equalsIgnoreCase("title")){
            this.title  = value;
        }
        else if(key.equalsIgnoreCase("director")){
            this.director = value;
        }
        else if(key.equalsIgnoreCase("year")){
            this.year = value;
        }
        else if(key.equalsIgnoreCase("genre")){
            this.genre = value;
        }
        else if(key.equalsIgnoreCase("price")){
            this.price = value;
        }else{
            System.out.println("No existe este atributo");
        }
    }
    
}
