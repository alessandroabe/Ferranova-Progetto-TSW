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
                Prodotto p = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), BigDecimal.valueOf(rs.getDouble(7)));
                prodottoList.add(p);
            }
            return prodottoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Prodotto doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            Prodotto prodotto = null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto WHERE  id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                prodotto = new Prodotto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), BigDecimal.valueOf(rs.getDouble(7)));

            }
            return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

