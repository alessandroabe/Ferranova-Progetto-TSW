package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "ordineServlet", value = "/ordineServlet")
public class OrdineServlet extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        Carrello carrello = (Carrello) session.getAttribute("Carrello");

        // Verifica se il carrello è vuoto
        if (carrello == null || carrello.getProdottiQuantita().isEmpty()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //riprende il prezzo totale
        ArrayList<Prodotto> lista = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : carrello.getProdottiQuantita().entrySet()) {
            lista.add(ProdottoDAO.doRetrieveById(entry.getKey()));
        }

        // Aggiornare le quantità dei prodotti nel database
        for (Prodotto p : lista) {
            int quantitaAcquistata = carrello.getProdottiQuantita().get(p.getId());
            int nuovaQuantita = p.getQuantita() - quantitaAcquistata;
            ProdottoDAO.aggiornaQuantitaProdotto(p.getId(), nuovaQuantita);
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (Prodotto p : lista) {
            BigDecimal b = p.getPrezzo();
            sum = sum.add(b.multiply(BigDecimal.valueOf(carrello.getProdottiQuantita().get(p.getId()))));
        }

        Utente u = (Utente) session.getAttribute("UtenteConnesso");

        Ordine ordine= new Ordine(u.getId());
        ordine.addCarrello( carrello, lista );
        OrdineDAO.inserisciOrdine(ordine);

        CarrelloDAO.doDelete(u.getId());
        session.removeAttribute("Carrello");


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AcquistoConSuccesso.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {

    }
}