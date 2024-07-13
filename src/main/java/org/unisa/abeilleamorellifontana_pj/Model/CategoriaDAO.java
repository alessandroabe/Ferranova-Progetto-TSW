package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
/*  FIXME: vedi bene cosa ritornano, in teoria tutti ritornano null non viene trovato una tupla, invece le liste di oggetti ritornano una lista vuota   */
    public static Categoria doRetrieveBySigla(String sigla) {
        Categoria categoria = null;
        String query = "SELECT * FROM Categoria WHERE sigla = ?";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, sigla);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String descrizione = resultSet.getString("descrizione");
                    String macrocategoria = resultSet.getString("macrocategoria");
                    categoria = new Categoria(sigla, descrizione, macrocategoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    public static boolean doInsert(Categoria categoria) {
        String query = "INSERT INTO Categoria (macrocategoria, sigla, descrizione, ) VALUES (?, ?, ?)";
        boolean inserito = false;

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, categoria.getMacrocategoria());
            pstmt.setString(2, categoria.getSigla());
            pstmt.setString(3, categoria.getDescrizione());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                inserito = true;
                System.out.println("Categoria inserita con successo!");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento della categoria: " + e.getMessage());
            e.printStackTrace();
        }

        return inserito;
    }

    public static List<Categoria> doRetrieveAll() {
        List<Categoria> categorie = new ArrayList<>();
        String query = "SELECT * FROM Categoria";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                String sigla = resultSet.getString("sigla");
                String descrizione = resultSet.getString("descrizione");
                String macrocategoria = resultSet.getString("macrocategoria");
                Categoria categoria = new Categoria(sigla, descrizione, macrocategoria);
                categorie.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero di tutte le categorie: " + e.getMessage());
            e.printStackTrace();
        }

        return categorie;
    }
    public static List<Categoria> doRetrieveAllRoot() {
        List<Categoria> categorie = new ArrayList<>();
        String query = "SELECT * FROM Categoria where macrocategoria = NULL";

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                String sigla = resultSet.getString("sigla");
                String descrizione = resultSet.getString("descrizione");
                Categoria categoria = new Categoria(sigla, descrizione);
                categorie.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero di tutte le categorie: " + e.getMessage());
            e.printStackTrace();
        }

        return categorie;
    }
//TODO: finire di modificare
    public static boolean doUpdate(Categoria categoria) {
        String query = "UPDATE Categoria SET descrizione = ? WHERE sigla = ?";
        boolean aggiornato = false;

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, categoria.getDescrizione());
            pstmt.setString(2, categoria.getSigla());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                aggiornato = true;
                System.out.println("Categoria aggiornata con successo!");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento della categoria: " + e.getMessage());
            e.printStackTrace();
        }

        return aggiornato;
    }

    public static boolean doDelete(String sigla) {
        String query = "DELETE FROM Categoria WHERE sigla = ?";
        boolean cancellato = false;

        try (Connection conn = ConPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, sigla);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                cancellato = true;
                System.out.println("Categoria cancellata con successo!");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la cancellazione della categoria con sigla: " + e.getMessage());
            e.printStackTrace();
        }

        return cancellato;
    }
}
