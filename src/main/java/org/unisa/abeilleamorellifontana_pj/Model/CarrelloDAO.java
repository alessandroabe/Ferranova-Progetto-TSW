package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CarrelloDAO {

    public static void insertCarrello(int idUtente, int idProdotto, int quantita) throws SQLException {
        String query = "INSERT INTO Carrello (id_utente, id_prodotto, quantità) VALUES (?, ?, ?)";
        Connection connection = ConPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUtente);
            preparedStatement.setInt(2, idProdotto);
            preparedStatement.setInt(3, quantita);
            preparedStatement.executeUpdate();
        }
    }


    public static void updateCarrello(int nuovaQuantita, int utenteId, int prodottoId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn =  ConPool.getConnection();
            stmt = conn.prepareStatement("UPDATE carrello SET quantità = ? WHERE id_utente = ? AND id_prodotto = ?");
            stmt.setInt(1, nuovaQuantita);
            stmt.setInt(2, utenteId);
            stmt.setInt(3, prodottoId);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static Map<Integer, Integer> getCarrelloByUtenteId(int idUtente) {
        Map<Integer, Integer> carrello = new HashMap<>();

        String query = "SELECT id_prodotto, quantità FROM Carrello WHERE id_utente = ?";

        try (PreparedStatement preparedStatement = ConPool.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, idUtente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idProdotto = resultSet.getInt("id_prodotto");
                    int quantita = resultSet.getInt("quantità");
                    carrello.put(idProdotto, quantita);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carrello;
    }
}
