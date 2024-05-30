package org.unisa.abeilleamorellifontana_pj.Model;

import java.sql.*;

import static org.unisa.abeilleamorellifontana_pj.Model.SHA1PasswordVerifier.sha1Hash;

public class  UtenteDAO {


        public Utente doRetrieveByEmail(String mail) {
            Utente utente = null;


            String query = "SELECT * FROM Utente WHERE email = ?";

            try (Connection connection = ConPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                preparedStatement.setString(1, mail);


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {

                        int id = resultSet.getInt("id");
                        String nome = resultSet.getString("nome");
                        String email = resultSet.getString("email");
                        String passwordhash = resultSet.getString("passwordhash");
                        String telefono = resultSet.getString("telefono");
                        boolean isAdmin = resultSet.getBoolean("is_admin");


                        utente = new Utente(id, nome, email, passwordhash, telefono, isAdmin);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return utente;
        }

        public boolean doInsert(Utente utente) {
            String query = "INSERT INTO Utente (nome, email, passwordhash, telefono, is_admin) VALUES (?, ?, ?, ?, ?)";

            boolean esistente = false;

            try (Connection conn = ConPool.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, utente.getNome());
                pstmt.setString(2, utente.getEmail());
                pstmt.setString(3, sha1Hash(utente.getPasswordhash()));// Cripta la password prima di inserirla con sha1Hash che abbiamo creato nella classe verifier ce la spostiamo
                //nel dao per comodita'
                pstmt.setString(4, utente.getTelefono());
                pstmt.setBoolean(5, utente.isAdmin());

                pstmt.executeUpdate();
                System.out.println("Utente inserito con successo!");

            } catch (SQLException e) {
                System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
                esistente = true;
            }

            return esistente;
        }

    }



