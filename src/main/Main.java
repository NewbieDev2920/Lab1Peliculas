package main;

import classes.JSONHandler;
import classes.Pelicula;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        //CARLOS
        JSONHandler file = new JSONHandler();
        Pelicula p = new Pelicula("4", "la opera", "asd", "asdds", "qasd", "asde");
        file.addPelicula(p);
        file.escribirDatos("pelicula");
        
        
        
        
        
    }
}
