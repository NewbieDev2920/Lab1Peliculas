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
public class JSONHandler {

    protected ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    protected ArrayList<Cliente> listaClientes = new ArrayList<>();
    protected ArrayList<Compra> listaCompras = new ArrayList<>();
    private Query consultant;
    
    public void eliminarUltimoCliente(){
        this.listaClientes.remove(listaClientes.size()-1);
    }

    public JSONHandler() {
        try {
            leerDatos("pelicula");
            leerDatos("compra");
            leerDatos("cliente");
        } catch (Exception ex) {
            Logger.getLogger(JSONHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Compra> getListaCompras() {
        return listaCompras;
    }

    public Query getConsultant() {
        return consultant;
    }

    public void actualizar(String tipoDeDato) throws Exception {
        tipoDeDato = tipoDeDato.toLowerCase();
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(tipoDeDato + "s.json"));
            JSONArray arr = (JSONArray) obj.get("lista");
            if (tipoDeDato.toUpperCase().equals("PELICULA")) {
                for (int j = 0; j < arr.size(); j++) {
                    boolean add = true;
                    JSONObject o = (JSONObject) arr.get(j);
                    for (int k = 0; k < this.listaPeliculas.size(); k++) {
                        Pelicula comparison = listaPeliculas.get(k);
                        if (comparison.getIdPelicula().equals(o.get("idPelicula"))) {
                            add = false;
                        }
                    }

                    if (add) {
                        Pelicula pSaved = new Pelicula(o.get("idPelicula").toString(), o.get("title").toString(), o.get("director").toString(), o.get("year").toString(), o.get("genre").toString(), o.get("price").toString());
                        this.listaPeliculas.add(pSaved);
                    }

                }
            } else if (tipoDeDato.toUpperCase().equals("COMPRA")) {
                for (int j = 0; j < arr.size(); j++) {
                    boolean add = true;
                    JSONObject o = (JSONObject) arr.get(j);
                    for (int k = 0; k < this.listaCompras.size(); k++) {
                        Compra comparison = listaCompras.get(k);
                        if (comparison.getIdCompra().equals(o.get("idCompra"))) {
                            add = false;
                        }
                    }

                    if (add) {
                        Compra pSaved = new Compra(o.get("idCompra").toString(), o.get("idClient").toString(), o.get("idMovie").toString(), o.get("fechaCompra").toString());
                        this.listaCompras.add(pSaved);
                    }

                }
            } else if (tipoDeDato.toUpperCase().equals("CLIENTE")) {
                for (int j = 0; j < arr.size(); j++) {
                    JSONObject o = (JSONObject) arr.get(j);
                    Cliente cSaved = new Cliente(o.get("idCliente").toString(), o.get("name").toString(), o.get("email").toString(), o.get("address").toString());
                    boolean add = true;
                    for (int k = 0; k < this.listaClientes.size(); k++) {

                        Cliente comparison = listaClientes.get(k);
                        if (comparison.getIdCliente().equals(o.get("idCliente"))) {
                            add = false;
                        }
                    }

                    if (add) {
                        this.listaClientes.add(cSaved);
                        for (Compra c : this.listaCompras) {
                            if (c.getIdClient().equals(cSaved.getIdCliente())) {
                                cSaved.addCompra(c);
                            }
                        }
                    }

                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {

        } catch (ParseException ex) {
            Logger.getLogger(JSONHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String displayPelicula(ArrayList<Pelicula> lista){
        String res = "";
        for(Pelicula p : lista){
            res += p.toString()+"\n";
        }
        return res;
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
     
    public Cliente buscarCliente(String idCedula){
        for(Cliente c : this.listaClientes){
            if(c.getIdCliente().equals(idCedula)){
                return c;
            }
        }
        return null;
    }
    
    public Compra buscarCompra(String fecha){
        for(Compra c: this.listaCompras){
            if(c.getFechaCompra().equals(fecha)){
                return c;
            }
        }
        return null;
    }

    public void setConsultant(Query q) {
        this.consultant = q;
    }

    public void logPeliculas() {
        for (Pelicula p : listaPeliculas) {
            System.out.println("---");
            System.out.println(p.getIdPelicula());
        }
    }

    public void addPelicula(Pelicula p) {
        this.listaPeliculas.add(p);
    }

    public void addClientes(Cliente c) {
        this.listaClientes.add(c);
    }

    public void addCompras(Compra c) {
        this.listaCompras.add(c);
    }

    //tipoDeObjeto(pelicula, compra, cliente)
    //Aunque se llame escribirDatos, lo que hace en realidad es sobreescribir el archivo JSON.
    public void escribirDatos(String tipoDeObjeto) throws IllegalArgumentException, Exception {

        String path;

        JSONObject fileObject = new JSONObject();
        JSONArray list = new JSONArray();
        if (tipoDeObjeto.toUpperCase().equals("PELICULA") && !listaPeliculas.isEmpty()) {
            path = "peliculas.json";
            for (Pelicula p : listaPeliculas) {
                JSONObject JSONTemp = new JSONObject();
                JSONTemp.put("idPelicula", p.getIdPelicula());
                JSONTemp.put("title", p.getTitle());
                JSONTemp.put("director", p.getDirector());
                JSONTemp.put("year", p.getYear());
                JSONTemp.put("genre", p.getGenre());
                JSONTemp.put("price", p.getPrice());
                list.add(JSONTemp);
            }

        } else if (tipoDeObjeto.toUpperCase().equals("COMPRA") && !listaCompras.isEmpty()) {
            path = "compras.json";
            for (Compra c : listaCompras) {
                JSONObject JSONTemp = new JSONObject();
                JSONTemp.put("idCompra", c.getIdCompra());
                JSONTemp.put("idClient", c.getIdClient());
                JSONTemp.put("idMovie", c.getIdMovie());
                JSONTemp.put("fechaCompra", c.getFechaCompra());
                list.add(JSONTemp);
            }
        } else if (tipoDeObjeto.toUpperCase().equals("CLIENTE") && !listaClientes.isEmpty()) {
            path = "clientes.json";
            for (Cliente c : listaClientes) {
                JSONObject JSONTemp = new JSONObject();
                JSONTemp.put("idCliente", c.getIdCliente());
                JSONTemp.put("name", c.getName());
                JSONTemp.put("email", c.getEmail());
                JSONTemp.put("address", c.getAddress());
                list.add(JSONTemp);
            }
        } else {
            System.out.println("ERROR");
            throw new IllegalArgumentException();
        }
        System.out.println("path : (" + path + ")");
        fileObject.put("lista", list);
        try (FileWriter f = new FileWriter(path)) {
            f.write(fileObject.toString());
            f.flush();
            actualizar(tipoDeObjeto);
            this.consultant.leerDatos(tipoDeObjeto);
        } catch (IOException ex) {
            System.out.println("IO EXCEPTION" + ex);
        }

    }

    //igualmente pelicula, cliente, compra
    public void leerDatos(String tipoDeDato) throws Exception {
        tipoDeDato = tipoDeDato.toLowerCase();
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(tipoDeDato + "s.json"));
            JSONArray arr = (JSONArray) obj.get("lista");
            if (tipoDeDato.toUpperCase().equals("PELICULA")) {
                for (int j = 0; j < arr.size(); j++) {
                    JSONObject o = (JSONObject) arr.get(j);
                    Pelicula pSaved = new Pelicula(o.get("idPelicula").toString(), o.get("title").toString(), o.get("director").toString(), o.get("year").toString(), o.get("genre").toString(), o.get("price").toString());
                    this.listaPeliculas.add(pSaved);
                }
            } else if (tipoDeDato.toUpperCase().equals("COMPRA")) {
                for (int j = 0; j < arr.size(); j++) {
                    JSONObject o = (JSONObject) arr.get(j);
                    Compra cSaved = new Compra(o.get("idCompra").toString(), o.get("idClient").toString(), o.get("idMovie").toString(), o.get("fechaCompra").toString());
                    this.listaCompras.add(cSaved);
                }
            } else if (tipoDeDato.toUpperCase().equals("CLIENTE")) {
                if (this.listaCompras.isEmpty()) {
                    throw new Exception("Se debe llenar primero la lista de compras para saber la informacion de los clientes");
                }
                for (int j = 0; j < arr.size(); j++) {
                    JSONObject o = (JSONObject) arr.get(j);
                    Cliente cSaved = new Cliente(o.get("idCliente").toString(), o.get("name").toString(), o.get("email").toString(), o.get("address").toString());
                    //Agregar Historial de compras
                    for (Compra c : this.listaCompras) {
                        if (c.getIdClient().equals(cSaved.getIdCliente())) {
                            cSaved.addCompra(c);
                        }
                    }
                    this.listaClientes.add(cSaved);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {

        } catch (ParseException ex) {
            Logger.getLogger(JSONHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarDatos(String tipoDeDato, String id, String clave, String valor) {

        if (tipoDeDato.equalsIgnoreCase("pelicula")) {
            for (Pelicula p : listaPeliculas) {
                if (p.getIdPelicula().equals(id)) {
                    p.setAttribute(clave, valor);
                }
            }
        } else if (tipoDeDato.equalsIgnoreCase("cliente")) {
            for (Cliente c : listaClientes) {
                if (c.getIdCliente().equalsIgnoreCase(id)) {
                    c.setAttribute(clave, valor);
                }
            }
        } else if (tipoDeDato.equalsIgnoreCase("compra")) {
            for (Compra c : listaCompras) {
                if (c.getIdCompra().equalsIgnoreCase(id)) {
                    c.setAttribute(clave, valor);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }

        try {
            escribirDatos(tipoDeDato);
        } catch (Exception ex) {
            Logger.getLogger(JSONHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarDatos(String tipoDeDato, String id) throws Exception {

        if (tipoDeDato.equalsIgnoreCase("pelicula")) {
            for (int i = 0; i < this.listaPeliculas.size(); i++) {
                if (this.listaPeliculas.get(i).getIdPelicula().equals(id)) {
                    this.listaPeliculas.remove(i);
                }
            }
        } else if (tipoDeDato.equalsIgnoreCase("cliente")) {
            for (int i = 0; i < this.listaClientes.size(); i++) {
                if (this.listaClientes.get(i).getIdCliente().equals(id)) {
                    this.listaClientes.remove(i);
                }
            }
        } else if (tipoDeDato.equalsIgnoreCase("compra")) {
            for (int i = 0; i < this.listaCompras.size(); i++) {
                if (this.listaCompras.get(i).getIdCompra().equals(id)) {
                    this.listaCompras.remove(i);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }

        try {
            escribirDatos(tipoDeDato);
        } catch (Exception e) {
            throw e;
        }

    }
}
