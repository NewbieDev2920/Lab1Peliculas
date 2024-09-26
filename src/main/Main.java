package main;

import classes.JSONHandler;
import classes.Pelicula;

public class Main {
    public static void main(String[] args) {
        //CARLOS
        JSONHandler file = new JSONHandler();
        file.logPeliculas();
        file.leerDatos("PELICULA");
        file.logPeliculas();
        
        
        
    }
}
