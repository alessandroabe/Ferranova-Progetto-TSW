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
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),  rs.getString(6), rs.getString(7) , BigDecimal.valueOf(rs.getDouble(8)));

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
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),  rs.getString(6), rs.getString(7) , BigDecimal.valueOf(rs.getDouble(8)));
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
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),  rs.getString(6), rs.getString(7) , BigDecimal.valueOf(rs.getDouble(8)));
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
                 p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),  rs.getString(6), rs.getString(7) , BigDecimal.valueOf(rs.getDouble(8)));
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
}

