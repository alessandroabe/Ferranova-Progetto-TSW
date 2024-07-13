package org.unisa.abeilleamorellifontana_pj.Model;

import java.util.HashMap;
import java.util.Map;

public class Carrello {
    private int idUtente;
    private final Map<Integer, Integer> prodottiQuantita; // Mappa di idProdotto e quantità

    public Carrello() {
        this.prodottiQuantita = new HashMap<>();
    }

    public Carrello(int idUtente) {
        this.idUtente = idUtente;
        this.prodottiQuantita = new HashMap<>();
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public Map<Integer, Integer> getProdottiQuantita() {
        return prodottiQuantita;
    }

    public void aggiungiProdotto(int idProdotto, int quantita) {
        if (idProdotto > 0 && quantita > 0) {
            prodottiQuantita.merge(idProdotto, quantita, Integer::sum);
        }
    }

    public int getNumeroProdotti() {
        return prodottiQuantita.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void rimuoviProdotto(int idProdotto) {
        prodottiQuantita.remove(idProdotto);
    }

    public void abbassaQuantitaProdotto(int idProdotto, int quantitaDiminuita) {
        if (prodottiQuantita.containsKey(idProdotto)) {
        aggiungiProdotto(idProdotto, -quantitaDiminuita);
            if (quantitaDiminuita <= 0) {
                prodottiQuantita.remove(idProdotto);
            }
        }
    }


    public void mergeProdotti( Carrello c ){

        //TODO:tieni conto degli id
        for ( Map.Entry<Integer, Integer>  entry : c.getProdottiQuantita().entrySet()) {
            aggiungiProdotto(entry.getKey(), entry.getValue());
        }

    }
}
