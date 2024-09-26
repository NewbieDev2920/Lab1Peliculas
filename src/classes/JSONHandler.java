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
              JSONTemp.put("titulo", p.getTitulo());
              JSONTemp.put("director", p.getDirector());
              JSONTemp.put("año", p.getAño());
              list.add(JSONTemp);
          }
          
       }
       else if(tipoDeObjeto.toUpperCase().equals("COMPRA") && !listaCompras.isEmpty()){
          path = "compras.json";
          for(Compra c : listaCompras){
            JSONObject JSONTemp = new JSONObject();
            //datos compra
            list.add(JSONTemp);
          }
       }
       else if(tipoDeObjeto.toUpperCase().equals("CLIENTE") && !listaCompras.isEmpty()){
           path = "clientes.json";
           for(Cliente c : listaClientes){
               JSONObject JSONTemp = new JSONObject();
               //datos cliente
               list.add(JSONTemp);
           }
       }
       else{
           System.out.println("ERROR");
           throw new IllegalArgumentException();
       }
                  
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
               JSONObject obj = (JSONObject) parser.parse(new FileReader(tipoDeDato+".json"));
               JSONArray arr = (JSONArray) obj.get("lista");
               if(tipoDeDato.toUpperCase().equals("PELICULA")){
                  for(int j = 0; j < arr.size(); j++){
                      JSONObject o = (JSONObject) arr.get(j);
                      Pelicula pSaved = new Pelicula( o.get("idPelicula").toString(), o.get("titulo").toString(), o.get("director").toString(), o.get("año").toString());
                      this.listaPeliculas.add(pSaved);
                  }
               }
               else if(tipoDeDato.toUpperCase().equals("COMPRA")){
                   
               }
               else if(tipoDeDato.toUpperCase().equals("CLIENTE")){
                   
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
   

