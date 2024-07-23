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
import java.time.LocalDate;

@WebServlet(name = "modificaOrdine", urlPatterns = "/modificaOrdine")
public class ModificaOrdineServlet extends HttpServlet {

    public void init() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("UtenteConnesso") == null || !((Utente) session.getAttribute("UtenteConnesso")).isAdmin()) {
            response.sendRedirect("login.jsp");
            return;
        }

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


        if (request.getParameter("stato") != null) {
            Ordine.StatoOrdine statoOrdine = Ordine.StatoOrdine.fromString(request.getParameter("stato"));

            if (statoOrdine.equals(Ordine.StatoOrdine.SPEDITO) && request.getParameter("dataSpedizione") != null) {

                try {
                    OrdineDAO.updateOrderStatus(idOrdine, statoOrdine);
                    OrdineDAO.updateShipmentDate(idOrdine, LocalDate.parse(dataSped));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


            if (statoOrdine.equals(Ordine.StatoOrdine.CONSEGNATO) && request.getParameter("dataConsegna") != null) {

                try {
                    OrdineDAO.updateOrderStatus(idOrdine, statoOrdine);
                    OrdineDAO.updateShipmentDate(idOrdine, LocalDate.parse(dataConsegna));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (statoOrdine.equals(Ordine.StatoOrdine.DISPERSO)) {
                OrdineDAO.updateOrderStatus(idOrdine, statoOrdine);
            }


        }
        response.sendRedirect(request.getContextPath() + "/admin");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {

    }


}