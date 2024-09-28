/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author cande
 */
public class ValidateId {

    private ArrayList<Integer> idPeliculas = new ArrayList<>();
    private ArrayList<Integer> idClientes = new ArrayList<>();
    private ArrayList<Integer> idCompras = new ArrayList<>();

    public ValidateId(ArrayList<Pelicula> p, ArrayList<Cliente> cl, ArrayList<Compra> co) {
        updateLists(p,cl,co);
    }
    
    public void updateLists(ArrayList<Pelicula> p, ArrayList<Cliente> cl, ArrayList<Compra> co){
        idPeliculas.clear();
        idClientes.clear();
        idCompras.clear();
        for (Pelicula i : p) {
            idPeliculas.add(Integer.valueOf(i.getIdPelicula()));
        }

        for (Cliente i : cl) {
            idPeliculas.add(Integer.valueOf(i.getIdCliente()));
        }

        for (Compra i : co) {
            idPeliculas.add(Integer.valueOf(i.getIdCompra()));
        }
    }
    
    public void validateIds() throws RepeatedIdException{
       for(int i = 0; i < idPeliculas.size(); i++){
           for(int j = 0; j < idPeliculas.size(); j++){
               if(idPeliculas.get(j) == idPeliculas.get(i)){
                   throw new RepeatedIdException("ID de peliculas repetido");
               }
           }
           
       }
       
        for(int i = 0; i < idClientes.size(); i++){
           for(int j = 0; j < idClientes.size(); j++){
               if(idClientes.get(j) == idClientes.get(i)){
                   throw new RepeatedIdException("ID de clientes repetido");
               }
           }
           
       }
        
         for(int i = 0; i < idCompras.size(); i++){
           for(int j = 0; j < idCompras.size(); j++){
               if(idCompras.get(j) == idCompras.get(i)){
                   throw new RepeatedIdException("ID de peliculas repetido");
               }
           }
           
       }
       
    }

    public int generateId(String tipoDeDato) {
        int mayor, id = -1;

        switch (tipoDeDato.toLowerCase()) {
            case "pelicula":
                if (!idPeliculas.isEmpty()) {
                    mayor = idPeliculas.get(0);
                    for (int i = 0; i < idPeliculas.size(); i++) {
                        if (mayor < idPeliculas.get(i)) {
                            mayor = idPeliculas.get(i);
                        }

                    }
                    id = mayor++;
                } else {
                    id = 1;
                }

                break;
            case "cliente":
                if (!idPeliculas.isEmpty()) {
                    mayor = idClientes.get(0);
                    for (int i = 0; i < idClientes.size(); i++) {
                        if (mayor < idClientes.get(i)) {
                            mayor = idClientes.get(i);
                        }

                    }
                    id = mayor++;
                } else {
                    id = 1;
                }
                break;
            case "compra":
                if (!idPeliculas.isEmpty()) {
                    mayor = idCompras.get(0);
                    for (int i = 0; i < idCompras.size(); i++) {
                        if (mayor < idCompras.get(i)) {
                            mayor = idCompras.get(i);
                        }
                    }
                    id = mayor++;
                } else {
                    id = 1;
                }

                break;
            default:
                throw new IllegalArgumentException();
        }

        return id;

    }

}