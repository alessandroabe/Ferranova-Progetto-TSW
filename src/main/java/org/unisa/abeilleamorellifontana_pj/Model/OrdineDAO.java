package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdineDAO {

    public static void inserisciOrdine(Ordine ordine) {
        String insertOrderSQL = "INSERT INTO Ordine (id_utente, stato_ordine, prezzo_totale, spese_spedizione, data_ordine, data_spedizione, data_consegna, tipo_pagamento) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertOrderSQL)) {

            pstmt.setInt(1, ordine.getIdUtente());
            pstmt.setString(2, ordine.getStatoOrdine().toString());
            pstmt.setBigDecimal(3, ordine.getPrezzoOrdine());
            pstmt.setBigDecimal(4, ordine.getPrezzoSpedizione());
            pstmt.setDate(5, java.sql.Date.valueOf(ordine.getDataOrdine()));
            pstmt.setDate(6, ordine.getDataSpedizione() != null ? java.sql.Date.valueOf(ordine.getDataSpedizione()) : null);
            pstmt.setDate(7, ordine.getDataConsegna() != null ? java.sql.Date.valueOf(ordine.getDataConsegna()) : null);
            pstmt.setString(8, ordine.getTipoPagamento());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getIdOrdineByIdUtente(int idUtente) throws SQLException {
        int idOrdine = -1; // Default value if no order found
        String sql = "SELECT id FROM Ordine WHERE id_utente = ? ORDER BY id DESC LIMIT 1";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idUtente);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idOrdine = resultSet.getInt("id");
                }
            }
        }

        return idOrdine;
    }

    public static void insert(OrdineProdotto ordineProdotto) {
        try (Connection conn = ConPool.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO Ordine_Prodotto (id_ordine, id_prodotto, quantità, prezzo_finale) VALUES (?, ?, ?, ?)")) {

            statement.setInt(1, ordineProdotto.getIdOrdine());
            statement.setInt(2, ordineProdotto.getIdProdotto());
            statement.setInt(3, ordineProdotto.getQuantita());
            statement.setBigDecimal(4, ordineProdotto.getPrezzoFinale());

            // Esecuzione della query di insert
            statement.executeUpdate();

            System.out.println("Inserimento completato con successo.");

        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento in Ordine_Prodotto: " + e.getMessage());
        }
    }
}





