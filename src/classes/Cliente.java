package classes;

import java.util.ArrayList;

public class Cliente {

    private String idCliente;
    private String name;
    private String email;
    private String address;
    private ArrayList<Compra> historial;
  
    public Cliente(String idCliente, String name, String email, String address) {
        this.idCliente = idCliente;
        this.name = name;
        this.email = email;
        this.address = address;
        this.historial = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", name=" + name + ", email=" + email + ", address=" + address + ", historial=" + historial + '}';
    }

    
    public String getIdCliente() {
        return idCliente;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Compra> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Compra> historial) {
        this.historial = historial;
    }
    
    public void addCompra(Compra c){
        this.historial.add(c);
    }
    
    public void setAttribute(String key, String value){
        
        if(key.equalsIgnoreCase("name")){
            this.name = value;
        }
        else if(key.equalsIgnoreCase("email")){
            this.email = value;
        }
        else if(key.equalsIgnoreCase("address")){
            this.address = value;
        }
        else if(key.equalsIgnoreCase("idCliente")){
            this.idCliente = value;
        }
        
    }
    
    
    
    
    
    
    
}
