package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ordineServlet", value = "/ordineServlet")
public class OrdineServlet extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        int ordid=0;
        //ORDINE

        //riprendere il prezzo totale
        Carrello carrello = (Carrello) session.getAttribute("Carrello");
        ArrayList<Prodotto> lista = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : carrello.getProdottiQuantita().entrySet()) {
            lista.add(ProdottoDAO.doRetrieveById(entry.getKey()));
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (Prodotto p : lista) {
            BigDecimal b = p.getPrezzo();
            sum = sum.add(b.multiply(BigDecimal.valueOf(carrello.getProdottiQuantita().get(p.getId()))));
        }

        //prendiamo l utente dalla sessione
        Utente u = (Utente) session.getAttribute("UtenteConnesso");

        //Fare un controllo sul fatto se ci sia la spedizione o meno

        BigDecimal spedizione = new BigDecimal("10.00");
        BigDecimal no_spedizione = new BigDecimal("0.00");
        if(sum.compareTo(new BigDecimal("100.00")) <= 0) {
            sum = sum.add(spedizione);
            OrdineDAO.inserisciOrdine(new Ordine(u.getId(),sum,spedizione));
        }else {
            OrdineDAO.inserisciOrdine(new Ordine(u.getId(),sum,no_spedizione));
        }








    // Ottieni l'ID dell'ordine appena inserito :la query va a prendersi l ultimo ordine che abbiamo inserito
        try {
            ordid = OrdineDAO.getIdOrdineByIdUtente(u.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare l'ID ordine per l'utente", e);
        }

// Aggiungi i prodotti all'ordine nella tabella Ordine_Prodotto
        for (Map.Entry<Integer, Integer> entry : carrello.getProdottiQuantita().entrySet()) {
            int idProdotto = entry.getKey();
            int quantita = entry.getValue();

            // Recupera il prodotto dal database
            Prodotto p = ProdottoDAO.doRetrieveById(idProdotto);
            BigDecimal prezzoUnitario = p.getPrezzo();

            // Calcola il prezzo finale per la quantità specificata
            BigDecimal prezzoFinale = prezzoUnitario.multiply(BigDecimal.valueOf(quantita));

            // Inserisci il prodotto nell'ordine corrente
            OrdineProdotto ordineProdotto = new OrdineProdotto(ordid, idProdotto, quantita, prezzoFinale);
            OrdineDAO.insert(ordineProdotto);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AcquistoConSuccesso.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {

    }
}
