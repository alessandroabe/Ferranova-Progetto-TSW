package org.unisa.abeilleamorellifontana_pj.Controller;

import com.mysql.cj.Session;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "modificaOrdine", urlPatterns = "/modificaOrdine")
public class ModificaOrdineServlet extends HttpServlet {

    public void init() {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("UtenteConnesso") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String stato = request.getParameter("stato");
        String idOrdineStr = request.getParameter("idOrdine");
        String dataSped = request.getParameter("dataSpedizione");
        String dataConsegna = request.getParameter("dataConsegna");



        int idOrdine;
        try {
            idOrdine = Integer.parseInt(idOrdineStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid order ID.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Utente u = (Utente) session.getAttribute("UtenteConnesso");


        Ordine.StatoOrdine statoOrdine = Ordine.StatoOrdine.fromString(stato);
        OrdineDAO.updateOrderStatus(idOrdine, statoOrdine);

        try {
            OrdineDAO.updateShipmentDate(idOrdine, LocalDate.parse(dataSped));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            OrdineDAO.updateDeliveryDate(idOrdine, LocalDate.parse(dataConsegna));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        response.sendRedirect(request.getContextPath() + "/admin");
        return;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {

    }


}