package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.Prodotto;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "catalogo", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ArrayList<Prodotto> catalogo;

        String categoria = request.getParameter("categoria");

        if (categoria != null) {
            catalogo = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveAllByCategoria(categoria);
            request.setAttribute("catalogo", catalogo);

        }
        else {
            catalogo = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveAll();
            request.setAttribute("catalogo", catalogo);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/catalogo.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {
    }
}