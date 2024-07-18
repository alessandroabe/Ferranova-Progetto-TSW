package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;

public class OrdineProdotto {
    private int idOrdine;
    private int idProdotto;
    private int quantita;
    private BigDecimal prezzoFinale;

    public OrdineProdotto(int idOrdine, int idProdotto, int quantita, BigDecimal prezzoFinale) {
        this.idOrdine = idOrdine;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
        this.prezzoFinale = prezzoFinale;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public BigDecimal getPrezzoFinale() {
        return prezzoFinale;
    }

    public void setPrezzoFinale(BigDecimal prezzoFinale) {
        this.prezzoFinale = prezzoFinale;
    }
}
