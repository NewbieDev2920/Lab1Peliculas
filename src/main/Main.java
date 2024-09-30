package main;

import classes.JSONHandler;
import classes.Pelicula;
import classes.Query;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        //CARLOS
        JSONHandler file = new JSONHandler();
        Query consult = new Query();
        file.setConsultant(consult);
        Pelicula p = new Pelicula("4", "la opera", "asd", "asdds", "qasd", "asde");
        file.addPelicula(p);
        try {
            file.escribirDatos("pelicula");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
}
