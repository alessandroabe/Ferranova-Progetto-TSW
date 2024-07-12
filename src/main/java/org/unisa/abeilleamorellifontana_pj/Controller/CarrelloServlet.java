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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "carrello", value = "/carrello")

public class CarrelloServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(); //creiamo una nuova sessione

        String resp = request.getParameter("quantity");
        int quantity = Integer.parseInt(resp);

        String prod_id = request.getParameter("prod");
        int prod_id_d = Integer.parseInt(prod_id); //prende il parametro dalla richiesta ajax

        ArrayList<Prodotto>prodotti_presi_non_in_sessione = new ArrayList<>(); //inizializza una lista
        Prodotto curr_product= ProdottoDAO.doRetrieveById(prod_id_d);

        //ogni volta che viene richiamata la servlet sono importanti queste due condizioni:
        //la prima se non c è sessione e se non è presente un carrello gia all interno della sessione,mettiamo il prodotto e risettiamo l'attributo(lista dei prodotti
        if(session.getAttribute("prodotti_presi") == null && session.getAttribute("UtenteConnesso") == null) {

                for(int i=0;i<quantity;i++) {
                    prodotti_presi_non_in_sessione.add(curr_product);
                }
            session.setAttribute("prodotti_presi",prodotti_presi_non_in_sessione);


        //la seconda se non c'è sessione ma esistono gia dei prodotti nella sessione quindi nel carrelo,alla lista esistente aggiungiamo
            //il prodotto alla lista e risettiamo l attributo
        }else if(session.getAttribute("prodotti_presi") != null && session.getAttribute("UtenteConnesso") == null) {
            prodotti_presi_non_in_sessione = (ArrayList<Prodotto>) session.getAttribute("prodotti_presi");


                for(int i=0;i<quantity;i++) {
                    prodotti_presi_non_in_sessione.add(curr_product);
                }

            session.setAttribute("prodotti_presi",prodotti_presi_non_in_sessione);
        }


        //Condizione in modo che quando si è loggati si scarica tutto
        if(session.getAttribute("UtenteConnesso") != null && session.getAttribute("prodotti_presi") == null) {


                    for(int i=0;i<quantity;i++) {
                        prodotti_presi_non_in_sessione.add(curr_product);
                    }

            session.setAttribute("prodotti_presi",prodotti_presi_non_in_sessione);

            Map<Integer, Integer> idOccorrenzeMap = new HashMap<>();

            //scarichiamo

            // Popolazione della Map con le occorrenze degli ID prodotto
            for (Prodotto prodotto : prodotti_presi_non_in_sessione) {
                int id = prodotto.getId();
                idOccorrenzeMap.put(id, idOccorrenzeMap.getOrDefault(id, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : idOccorrenzeMap.entrySet()) { //scarica tutto qui
                Utente utenteConnesso = (Utente) session.getAttribute("UtenteConnesso");
                try {
                    CarrelloDAO.insertCarrello(utenteConnesso.getId(),entry.getKey(), entry.getValue());
                } catch (SQLException e) {
                    try {
                        CarrelloDAO.updateCarrello(entry.getValue(), utenteConnesso.getId(), entry.getKey());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }



//caso in cui ci sono gia elementi nel carrello
        }else if(session.getAttribute("prodotti_presi") != null && session.getAttribute("UtenteConnesso") != null) {
            prodotti_presi_non_in_sessione = (ArrayList<Prodotto>) session.getAttribute("prodotti_presi"); //sostituisce la lista inizializzata con un carrello gia presente

                    for(int i=0;i<quantity;i++) {
                        prodotti_presi_non_in_sessione.add(curr_product);
                    }

            session.setAttribute("prodotti_presi",prodotti_presi_non_in_sessione);

            //scarichiamo

            Map<Integer, Integer> idOccorrenzeMap = new HashMap<>();

            // Popolazione della Map con le occorrenze degli ID prodotto
            for (Prodotto prodotto : prodotti_presi_non_in_sessione) {
                int id = prodotto.getId();
                idOccorrenzeMap.put(id, idOccorrenzeMap.getOrDefault(id, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : idOccorrenzeMap.entrySet()) { //scarica tutto qui
                Utente utenteConnesso = (Utente) session.getAttribute("UtenteConnesso");

                try {
                    CarrelloDAO.insertCarrello(utenteConnesso.getId(), entry.getKey(), entry.getValue());
                } catch (SQLException e) {
                    try {
                        CarrelloDAO.updateCarrello(entry.getValue(), utenteConnesso.getId(), entry.getKey());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {
    }
}