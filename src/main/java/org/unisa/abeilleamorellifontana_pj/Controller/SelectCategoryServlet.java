package org.unisa.abeilleamorellifontana_pj.Controller;

import com.mysql.cj.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "select-catagory", urlPatterns = "/select-category")
public class SelectCategoryServlet extends HttpServlet {

    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ricerca = !request.getParameter("ricerca").equalsIgnoreCase("") ? request.getParameter("ricerca") : null;

        String categoriaParam = request.getParameter("categoria");

        ServletContext context = getServletContext();
        Map<String, ArrayList<String>> categorie = (Map<String, ArrayList<String>>) context.getAttribute("Categorie");

        String category = "Unknown";
        String subcategory = "Unknown";

        if (categoriaParam != null) {
            for (Map.Entry<String, ArrayList<String>> entry : categorie.entrySet()) {
                for (String subCat : entry.getValue()) {
                    if (categoriaParam.equalsIgnoreCase(entry.getKey() + "-" + subCat)) {
                        category = entry.getKey();
                        subcategory = subCat;
                        break;
                    }
                }
            }
        }

        ArrayList<Prodotto>prodotti = (ArrayList<Prodotto>) ProdottoDAO.doRetrieveByCriteria(ricerca, null, null, category, subcategory);
        request.setAttribute("prodotti",prodotti);

        request.getRequestDispatcher("admin").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {

    }


}