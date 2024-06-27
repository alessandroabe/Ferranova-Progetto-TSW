package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;

public class Prodotto {
    private int id;
    private String titolo;
    private String descrizione;
    private int quantita;
    private int idPromozione;
    private String categoria;
    private BigDecimal prezzo;

    public Prodotto() {
        super();
    }

    public Prodotto(int id, String titolo, String descrizione, int quantita, int idPromozione, String categoria, BigDecimal prezzo) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.idPromozione = idPromozione;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getIdPromozione() {
        return idPromozione;
    }

    public void setIdPromozione(int idPromozione) {
        this.idPromozione = idPromozione;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}
