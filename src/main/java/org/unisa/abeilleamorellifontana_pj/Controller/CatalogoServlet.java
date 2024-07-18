package org.unisa.abeilleamorellifontana_pj.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.unisa.abeilleamorellifontana_pj.Model.Prodotto;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO.*;

@WebServlet(name = "catalogo", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ArrayList<Prodotto> catalogo;
//TODO: spostare tutto nell'init forse, così da avere tutto nella servletcontext
//TODO: gestire il caso di errrore, in cui si mette una categoria che non esiste
        if (request.getParameter("categoria") != null) {
            String categoria = request.getParameter("categoria");

            catalogo = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveAllByMacrocategoria(categoria);
            request.setAttribute("catalogo", catalogo);

            ServletContext context = request.getServletContext();
            if (context.getAttribute("Categorie") != null) {
                HashMap<String, ArrayList<String>> categorie;
                categorie = (HashMap<String, ArrayList<String>>) context.getAttribute("Categorie");
                List<String> subCategorie = categorie.get(categoria);
                request.setAttribute("subCategorie", subCategorie);
            } else {

                catalogo = (ArrayList<Prodotto>) doRetrieveAll();
                request.setAttribute("catalogo", catalogo);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/catalogo.jsp");
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