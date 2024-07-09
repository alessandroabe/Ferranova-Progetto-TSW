package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
