package classes;

public class Cliente {

    private String idCliente;
    private String name;
    private String email;
    private String address;

    public Cliente(String idCliente, String name, String email, String address) {
        this.idCliente = idCliente;
        this.name = name;
        this.email = email;
        this.address = address;
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
    
    
    
}
