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
import java.util.ArrayList;

@WebServlet(name = "carrello", value = "/carrello")

public class CarrelloServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//TODO: ancora da finire

        HttpSession session = request.getSession(false);

        String address = null;
        if (session == null) {
            //TODO: metti un errore tipo login servlet
            response.sendRedirect("login");

        } else {
            if (session.getAttribute("Carrello") == null)
                session.setAttribute("Carrello", new ArrayList<Prodotto>());

            ArrayList<Prodotto> carrello = (ArrayList<Prodotto>) session.getAttribute("Carrello");
            session.setAttribute("Carrello", carrello.add(ProdottoDAO.doRetrieveById(Integer.parseInt(request.getParameter("prod")))));
            //FIXME: migliorare bellezza del codice e falla con ajax
            address = getServletContext().getContextPath() + "/login.jsp?error=1";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

    public void destroy() {
    }
}