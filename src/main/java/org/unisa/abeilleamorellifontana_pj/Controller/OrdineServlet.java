package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Carrello;
import org.unisa.abeilleamorellifontana_pj.Model.CarrelloDAO;
import org.unisa.abeilleamorellifontana_pj.Model.Prodotto;
import org.unisa.abeilleamorellifontana_pj.Model.Utente;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ordineServlet", value = "/ordineServlet")
public class OrdineServlet extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        BigDecimal prezzo_totale = (BigDecimal) session.getAttribute("sum");
        System.out.println(prezzo_totale);
        if((boolean) session.getAttribute("spedizione_eff") == true) {
            BigDecimal spedizione = new BigDecimal("10.00");
            System.out.println(spedizione);
        }

        /*TODO:FARE L'INSERT POI IN ORDINI ALL'ACCOUNT COLLEGATO,IL PAGAMENTO è ANDATO A BUON FINE,FARE UN DISPATCHER CHE SI RIFERISCE A UNA NUOVA JSP PER LA VISUALIZZAZIONE
        *  DEI DATI*/



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {

    }
}
