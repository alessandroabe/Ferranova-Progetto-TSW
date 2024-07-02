package org.unisa.abeilleamorellifontana_pj.Model;

public class Categoria {
    private String sigla;
    private String descrizione;

    // Costruttore senza parametri
    public Categoria() {
    }

    // Costruttore con parametri
    public Categoria(String sigla, String descrizione) {
        this.sigla = sigla;
        this.descrizione = descrizione;
    }

    // TODO : fare testing più approfondito
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    // Getter e Setter per descrizione
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}