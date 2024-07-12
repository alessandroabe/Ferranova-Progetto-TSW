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

        String referer = request.getHeader("Referer");

        HttpSession session = request.getSession(); //creiamo una nuova sessione
        Carrello c = (Carrello) session.getAttribute("Carrello");
        if(referer != null && (referer.contains("prodotto")|| referer.contains("catalogo"))){//faccio con ajax si spera
                String resp = request.getParameter("quantity");
                int quantity = Integer.parseInt(resp);

                String prod_id = request.getParameter("prod");
                int prod_id_d = Integer.parseInt(prod_id); //prende il parametro dalla richiesta ajax



                c.aggiungiProdotto(prod_id_d, quantity);
                return;

            }
        else{
//TODO: mettere controlli
        ArrayList<Prodotto> prodotti_carrelo= new ArrayList<>();
        for ( Map.Entry<Integer, Integer>  entry : c.getProdottiQuantita().entrySet()){
           prodotti_carrelo.add( ProdottoDAO.doRetrieveById(entry.getKey()));
        }

        request.setAttribute("prodotti_carrelo", prodotti_carrelo);



                RequestDispatcher dispatcher = request.getRequestDispatcher("/carrello.jsp");
                dispatcher.forward(request, response);
            }
        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {
    }
}