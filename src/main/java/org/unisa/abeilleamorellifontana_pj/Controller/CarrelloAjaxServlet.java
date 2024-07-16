package org.unisa.abeilleamorellifontana_pj.Controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Carrello;

import java.io.IOException;

@WebServlet(name = "carrelloAjax", value = "/carrelloAjax")
public class CarrelloAjaxServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Carrello carrello = (Carrello) session.getAttribute("Carrello");

        String resp = request.getParameter("quantity");
        int quantity = Integer.parseInt(resp);

        String prod_id = request.getParameter("prod");
        int prod_id_d = Integer.parseInt(prod_id);

        carrello.aggiungiProdotto(prod_id_d, quantity);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int productId = Integer.parseInt(request.getParameter("prod"));
        String action = request.getParameter("action");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("Carrello");


        if (action.equals("update")) {
            carrello.aggiungiProdotto(productId, quantity);
            int newQuantity = carrello.getProdottiQuantita().get(productId);
            System.out.println(newQuantity);
            response.getWriter().write(String.valueOf(newQuantity));
        } else if (action.equals("remove")) {
            carrello.rimuoviProdotto(productId);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void destroy() {
    }

}
