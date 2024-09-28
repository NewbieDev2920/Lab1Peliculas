package classes;

import java.time.LocalDate;

public class Compra {

    private String idCompra;
    private String idClient;
    private String idMovie;
    private String fechaCompra;

    public Compra(String idCompra, String idClient, String idMovie, String fechaCompra) {
        this.idCompra = idCompra;
        this.idClient = idClient;
        this.idMovie = idMovie;
        this.fechaCompra = fechaCompra;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setAttribute(String key, String value) {
        
        if (key.equalsIgnoreCase("idClient")) {
            this.idClient = value;
        } else if (key.equalsIgnoreCase("idMovie")) {
            this.idMovie = value;
        } else if (key.equalsIgnoreCase("fechaCompra")) {
            this.fechaCompra = value;
        }
        
    }

}
