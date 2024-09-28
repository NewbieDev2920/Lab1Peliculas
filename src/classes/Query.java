/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cande
 */
public class Query extends JSONHandler {
    
    public Query(){
        super();
        try {
            leerDatos("pelicula");
            leerDatos("compra");
            leerDatos("cliente");
        } catch (Exception ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Pelicula> buscarPelicula(String clave, String valor){
        valor = valor.toLowerCase();
        ArrayList<Pelicula> response = new ArrayList<>();
        if(clave.equalsIgnoreCase("titulo")){
            for(Pelicula p : this.listaPeliculas){
                if(p.getTitle().equals(valor)){
                    response.add(p);
                }
            }
        }else if(clave.equalsIgnoreCase("director")){
             for(Pelicula p : this.listaPeliculas){
                if(p.getDirector().equals(valor)){
                    response.add(p);
                }
            }
        }else if(clave.equalsIgnoreCase("genero")){
             for(Pelicula p : this.listaPeliculas){
                if(p.getGenre().equals(valor)){
                    response.add(p);
                }
            }
        }
        
        if(response.isEmpty()){
            System.out.println("No existen peliculas con las siguientes caracteristicas ( "+clave+" : "+valor+" )");
            return null;
        }else{
            return response;
        }
        
    }
    

    public ArrayList<Compra> comprasPorCliente(String id){
       
        for(Cliente c : this.listaClientes){
            
            if(id.equals(c.getIdCliente())){
                 System.out.println(c.getName()+" ha hecho un total de "+c.getHistorial().size());
                 return c.getHistorial();
            }
           
        }
        
        return null;
    }
    
}
