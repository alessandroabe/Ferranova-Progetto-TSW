package org.unisa.abeilleamorellifontana_pj.Controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Carrello;

@WebServlet(name = "carrelloAjax", value = "/carrelloAjax")
public class CarrelloAjaxServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("Carrello");

        String resp = request.getParameter("quantity");
        int quantity = Integer.parseInt(resp);

        String prod_id = request.getParameter("prod");
        int prod_id_d = Integer.parseInt(prod_id);

        carrello.aggiungiProdotto(prod_id_d, quantity);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }

    public void destroy() {
    }

}
