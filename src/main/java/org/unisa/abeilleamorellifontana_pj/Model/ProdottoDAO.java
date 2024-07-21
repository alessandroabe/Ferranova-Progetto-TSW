package org.unisa.abeilleamorellifontana_pj.Model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    public static List<Prodotto> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto");
            ResultSet rs = ps.executeQuery();
            List<Prodotto> prodottoList = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), BigDecimal.valueOf(rs.getDouble(8)));

                prodottoList.add(p);
            }
            return prodottoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Prodotto> doRetrieveAllBySottocategoria(String sottocategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto WHERE sottocategoria = ?");
            ps.setString(1, sottocategoria);
            ResultSet rs = ps.executeQuery();
            List<Prodotto> prodottoList = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), BigDecimal.valueOf(rs.getDouble(8)));
                prodottoList.add(p);
            }
            return prodottoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Prodotto> doRetrieveAllByMacrocategoria(String macrocategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto WHERE macrocategoria = ?");
            ps.setString(1, macrocategoria);
            ResultSet rs = ps.executeQuery();
            List<Prodotto> prodottoList = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), BigDecimal.valueOf(rs.getDouble(8)));
                prodottoList.add(p);
            }
            return prodottoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Prodotto doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            Prodotto p = null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto WHERE  id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), BigDecimal.valueOf(rs.getDouble(8)));
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: capire se usare cartelle opppure media singoli e finire quando usata in admin
    public static void doInsert(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Prodotto (titolo, descrizione, quantità, id_promozione, macrocategoria, sottocategoria, prezzo) VALUES ( ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, prodotto.getTitolo());
            ps.setString(2, prodotto.getDescrizione());
            ps.setInt(3, prodotto.getQuantita());
            ps.setInt(4, prodotto.getIdPromozione());
            ps.setString(5, prodotto.getMacrocategoria());
            ps.setString(6, prodotto.getSottocategoria());
            ps.setBigDecimal(7, prodotto.getPrezzo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void aggiornaQuantitaProdotto(int idProdotto, int nuovaQuantita) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Prodotto SET quantità = ? WHERE id = ?");
            ps.setInt(1, nuovaQuantita);
            ps.setInt(2, idProdotto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Prodotto> doRetrieveByCriteria(String searchString, BigDecimal minPrice, BigDecimal maxPrice, String macrocategoria, String sottocategoria) {
        StringBuilder query = new StringBuilder("SELECT * FROM Prodotto WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        if (minPrice != null) {
            query.append(" AND prezzo >= ?");
            parameters.add(minPrice);
        }

        if (maxPrice != null) {
            query.append(" AND prezzo <= ?");
            parameters.add(maxPrice);
        }

        if (macrocategoria != null && !macrocategoria.isEmpty()) {
            query.append(" AND macrocategoria = ?");
            parameters.add(macrocategoria);
        }

        if (sottocategoria != null && !sottocategoria.isEmpty()) {
            query.append(" AND sottocategoria = ?");
            parameters.add(sottocategoria);
        }

        if (searchString != null && !searchString.isEmpty()) {
            query.append(" AND (titolo LIKE ? OR descrizione LIKE ?)");
            parameters.add("%" + searchString + "%");
            parameters.add("%" + searchString + "%");
        }

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query.toString());

            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof BigDecimal) {
                    ps.setBigDecimal(i + 1, (BigDecimal) parameters.get(i));
                } else if (parameters.get(i) instanceof String) {
                    ps.setString(i + 1, (String) parameters.get(i));
                }
            }

            ResultSet rs = ps.executeQuery();
            List<Prodotto> prodottoList = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getBigDecimal(8));
                prodottoList.add(p);
            }
            return prodottoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void aggiornaPrezzoProdotto(int idProdotto, BigDecimal nuovoPrezzo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Prodotto SET prezzo = ? WHERE id = ?");
            ps.setBigDecimal(1, nuovoPrezzo);
            ps.setInt(2, idProdotto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


