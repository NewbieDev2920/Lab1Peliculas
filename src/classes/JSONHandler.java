/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cande
 */
public class JSONHandler{
    
    private ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Compra> listaCompras = new ArrayList<>();
    
    public void logPeliculas(){
        for(Pelicula p : listaPeliculas){
            System.out.println("---");
            System.out.println(p.getIdPelicula());
        }
    }
    
    public void addPelicula(Pelicula p){
        this.listaPeliculas.add(p);
    }
    
    public void addClientes(Cliente c){
        this.listaClientes.add(c);
    }
    
    public void addCompras(Compra c){
        this.listaCompras.add(c);
    }
    
    //tipoDeObjeto(pelicula, compra, cliente)
   public void escribirDatos(String tipoDeObjeto) throws IllegalArgumentException {
       
       String path;
       
       JSONObject fileObject = new JSONObject();
       JSONArray list = new JSONArray();
       if(tipoDeObjeto.toUpperCase().equals("PELICULA") && !listaPeliculas.isEmpty()){
          path = "peliculas.json";
          for(Pelicula p : listaPeliculas){
              JSONObject JSONTemp = new JSONObject();
              JSONTemp.put("idPelicula", p.getIdPelicula());
              JSONTemp.put("title", p.getTitle());
              JSONTemp.put("director", p.getDirector());
              JSONTemp.put("year", p.getYear());
              JSONTemp.put("genre", p.getGenre());
              JSONTemp.put("price", p.getPrice());
              list.add(JSONTemp);
          }
          
       }
       else if(tipoDeObjeto.toUpperCase().equals("COMPRA") && !listaCompras.isEmpty()){
          path = "compras.json";
          for(Compra c : listaCompras){
            JSONObject JSONTemp = new JSONObject();
            JSONTemp.put("idCompra", c.getIdCompra());
            JSONTemp.put("idClient", c.getIdClient());
            JSONTemp.put("idMovie", c.getIdMovie());
            JSONTemp.put("fechaCompra", c.getFechaCompra());
            list.add(JSONTemp);
          }
       }
       else if(tipoDeObjeto.toUpperCase().equals("CLIENTE") && !listaCompras.isEmpty()){
           path = "clientes.json";
           for(Cliente c : listaClientes){
               JSONObject JSONTemp = new JSONObject();
               JSONTemp.put("idCliente", c.getIdCliente());
               JSONTemp.put("name", c.getName());
               JSONTemp.put("email", c.getEmail());
               JSONTemp.put("address", c.getAddress());
               list.add(JSONTemp);
           }
       }
       else{
           System.out.println("ERROR");
           throw new IllegalArgumentException();
       }
       System.out.println("path" + path);
        fileObject.put("lista", list);
           try(FileWriter f = new FileWriter(path)){
               f.write(fileObject.toString());
               f.flush();

           }
           catch(IOException ex){
               System.out.println("IO EXCEPTION"+ ex);
           }
                  
          
           
       }
       
       //igualmente pelicula, cliente, compra
       public void leerDatos(String tipoDeDato){
           tipoDeDato = tipoDeDato.toLowerCase();
           JSONParser parser = new JSONParser();
           try{
               JSONObject obj = (JSONObject) parser.parse(new FileReader(tipoDeDato+"s.json"));
               JSONArray arr = (JSONArray) obj.get("lista");
               if(tipoDeDato.toUpperCase().equals("PELICULA")){
                  for(int j = 0; j < arr.size(); j++){
                      JSONObject o = (JSONObject) arr.get(j);
                      Pelicula pSaved = new Pelicula( o.get("idPelicula").toString(), o.get("title").toString(), o.get("director").toString(), o.get("year").toString(), o.get("genre").toString(), o.get("price").toString());
                      this.listaPeliculas.add(pSaved);
                  }
               }
               else if(tipoDeDato.toUpperCase().equals("COMPRA")){
                   for(int j = 0; j < arr.size(); j++){
                      JSONObject o = (JSONObject) arr.get(j);
                      Compra cSaved = new Compra(o.get("idCompra").toString(), o.get("idClient").toString(), o.get("idMovie").toString(), o.get("fechaCompra").toString());
                      this.listaCompras.add(cSaved);
                  }
               }
               else if(tipoDeDato.toUpperCase().equals("CLIENTE")){
                    for(int j = 0; j < arr.size(); j++){
                      JSONObject o = (JSONObject) arr.get(j);
                      Cliente cSaved = new Cliente(o.get("idCliente").toString(), o.get("name").toString(), o.get("email").toString(), o.get("address").toString());
                      this.listaClientes.add(cSaved);
                  }
               }
               else{
                   throw new IllegalArgumentException();
               }
           }
           catch(IOException e){
               
           } catch (ParseException ex) {
            Logger.getLogger(JSONHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       
       
       
   } 
   

