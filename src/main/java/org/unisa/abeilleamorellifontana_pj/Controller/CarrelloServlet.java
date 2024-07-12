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
        Carrello carrello = (Carrello) session.getAttribute("Carrello");
        //TODO: mettere controlli
        ArrayList<Prodotto> lista = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : carrello.getProdottiQuantita().entrySet()) {
            lista.add(ProdottoDAO.doRetrieveById(entry.getKey()));
        }

        request.setAttribute("lista", lista);


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {
    }
}