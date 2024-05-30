package org.unisa.abeilleamorellifontana_pj.Model;

public class Utente {

    private int id;
    private String nome;
    private String email;
    private String passwordhash;
    private String telefono;
    private boolean isAdmin;

    public Utente(int id, String nome, String email, String passwordhash, String telefono, boolean isAdmin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.passwordhash = passwordhash;
        this.telefono = telefono;
        this.isAdmin = isAdmin;
    }

    public Utente() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
