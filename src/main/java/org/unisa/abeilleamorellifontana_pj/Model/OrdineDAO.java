package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdineDAO {

    public static int  inserisciOrdine(Ordine ordine) {
        int id=-1;
        ResultSet rs= null;
        String insertOrderSQL = "INSERT INTO Ordine (id_utente, stato_ordine, prezzo_totale, spese_spedizione, data_ordine, data_spedizione, data_consegna, tipo_pagamento) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertProdottoOrdineSQL = "INSERT INTO Ordine_Prodotto(id_ordine,id_prodotto,quantità,prezzo_finale) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS) ){

            pstmt.setInt(1, ordine.getIdUtente());
            pstmt.setString(2, ordine.getStatoOrdine().toString());
            pstmt.setBigDecimal(3, ordine.getPrezzoOrdine());
            pstmt.setBigDecimal(4, ordine.getPrezzoSpedizione());
            pstmt.setDate(5, java.sql.Date.valueOf(ordine.getDataOrdine()));
            pstmt.setDate(6, ordine.getDataSpedizione() != null ? java.sql.Date.valueOf(ordine.getDataSpedizione()) : null);
            pstmt.setDate(7, ordine.getDataConsegna() != null ? java.sql.Date.valueOf(ordine.getDataConsegna()) : null);
            pstmt.setString(8, ordine.getTipoPagamento());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {

                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertProdottoOrdineSQL)) {

            HashMap<Integer, OrdineProdotto> ordineProdottoHashMap = (HashMap<Integer, OrdineProdotto>) ordine.getProdottiQuantitaOrdine();
            for (Map.Entry<Integer, OrdineProdotto> entry : ordineProdottoHashMap.entrySet()) {

                pstmt.setInt(1, id);
                pstmt.setInt(2, entry.getKey());
                pstmt.setInt(3, entry.getValue().getQuantita());
                pstmt.setBigDecimal(4, entry.getValue().getPrezzoFinale());
                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }


}





