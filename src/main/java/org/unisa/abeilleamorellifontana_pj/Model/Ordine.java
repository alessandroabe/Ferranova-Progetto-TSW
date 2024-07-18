package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Ordine {

    private int idOrdine;
    private int idUtente;
    private StatoOrdine statoOrdine;

    private BigDecimal prezzoOrdine;

    private BigDecimal prezzoSpedizione;
    private Map<Integer, Integer> prodottiQuantitaOrdine;
    private LocalDate dataOrdine;
    private LocalDate dataSpedizione;
    private LocalDate dataConsegna;
    private String tipoPagamento;

    public Ordine(int id, Map<Integer, Integer> prodottiQuantitaOrdine, BigDecimal prezzoOrdine) {
        this.idOrdine = id;
        this.prodottiQuantitaOrdine = prodottiQuantitaOrdine;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.dataOrdine = LocalDate.now();
        this.prezzoOrdine = prezzoOrdine;
    }

    public Ordine(int id_utente,int id, Map<Integer, Integer> prodottiQuantitaOrdine, BigDecimal prezzoOrdine) {
        this.idUtente = id_utente;
        this.idOrdine = id;
        this.prodottiQuantitaOrdine = prodottiQuantitaOrdine;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.dataOrdine = LocalDate.now();
        this.prezzoOrdine = prezzoOrdine;
    }

    public Ordine(int idUtente, BigDecimal prezzoOrdine, BigDecimal prezzoSpedizione) {
        this.idUtente = idUtente;
        this.statoOrdine = StatoOrdine.ORDINATO;
        this.prezzoOrdine = prezzoOrdine;
        this.prezzoSpedizione = prezzoSpedizione;
        this.dataOrdine = LocalDate.now();
        this.dataSpedizione = null;
        this.dataConsegna = null;
        this.tipoPagamento = "carta di credito";
    }



    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Map<Integer, Integer> getProdottiQuantitaOrdine() {
        return prodottiQuantitaOrdine;
    }

    public StatoOrdine getStatoOrdine(){
        return statoOrdine;
    }

    public void setStatoOrdine(StatoOrdine statoOrdine){
        this.statoOrdine = statoOrdine;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public LocalDate getDataSpedizione() {
        return dataSpedizione;
    }

    public void setDataSpedizione(LocalDate dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public String getTipoPagamento(){
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento){
        this.tipoPagamento = tipoPagamento;
    }

    public BigDecimal getPrezzoOrdine() {
        return prezzoOrdine;
    }

    public void setPrezzoOrdine(BigDecimal prezzoOrdine) {
        this.prezzoOrdine = prezzoOrdine;
    }

    public BigDecimal getPrezzoSpedizione() {
        return prezzoSpedizione;
    }

    public void setPrezzoSpedizione(BigDecimal prezzoSpedizione) {
        this.prezzoSpedizione = prezzoSpedizione;
    }

    // Enum StatoOrdine definito all'interno della classe Ordine
    public enum StatoOrdine {
        CONSEGNATO("consegnato"),
        SPEDITO("spedito"),
        DISPERSO("disperso"),
        ORDINATO("ordinato");

        private final String stato;

        StatoOrdine(String stato) {
            this.stato = stato;
        }

        public String getStato() {
            return stato;
        }

        @Override
        public String toString() {
            return this.stato;
        }
    }
}
