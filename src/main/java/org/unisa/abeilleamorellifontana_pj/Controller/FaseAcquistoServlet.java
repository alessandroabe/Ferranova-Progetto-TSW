package org.unisa.abeilleamorellifontana_pj.Controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.Carrello;
import org.unisa.abeilleamorellifontana_pj.Model.Prodotto;
import org.unisa.abeilleamorellifontana_pj.Model.ProdottoDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "preAcquisto", value = "/preAcquisto")
public class FaseAcquistoServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false); //creiamo una nuova sessione

        if(session.getAttribute("UtenteConnesso") == null) {
            response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
            return;
        }

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


        BigDecimal spedizione = new BigDecimal("10.00");
        if(sum.compareTo(new BigDecimal("100.00")) <= 0) {
            sum = sum.add(spedizione);
            request.setAttribute("sum", sum);
        }else {
            request.setAttribute("sum", sum);
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/faseAcquisto.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        doGet(request,response);
    }

    public void destroy() {
    }

}