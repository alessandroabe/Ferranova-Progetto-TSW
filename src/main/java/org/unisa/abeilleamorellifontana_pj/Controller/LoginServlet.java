package org.unisa.abeilleamorellifontana_pj.Controller;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.unisa.abeilleamorellifontana_pj.Model.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false );

        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        String address;
        UtenteDAO U = new UtenteDAO();
        Utente input = U.doRetrieveByEmail(mail);

        if (input != null && input.getEmail().equals(mail) && SHA1PasswordVerifier.verifyPassword(password, input.getPasswordhash())) {
            // Imposta l'utente connesso nella sessione

            session.setAttribute("UtenteConnesso", input);

            //scarichiamo

               if(session.getAttribute("prodotti_presi") != null) {
                       Map<Integer, Integer> idOccorrenzeMap = new HashMap<>();
                       ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) session.getAttribute("prodotti_presi_prima_del_logout");
                       // Popolazione della Map con le occorrenze degli ID prodotto
                       for (Prodotto prodotto : prodotti) {
                           int id = prodotto.getId();
                           idOccorrenzeMap.put(id, idOccorrenzeMap.getOrDefault(id, 0) + 1);
                       }

                       for (Map.Entry<Integer, Integer> entry : idOccorrenzeMap.entrySet()) { //scarica tutto qui
                           Utente utenteConnesso = (Utente) session.getAttribute("UtenteConnesso");

                           try {
                               CarrelloDAO.insertCarrello(utenteConnesso.getId(), entry.getKey(), entry.getValue());
                           } catch (SQLException e) {
                               try {
                                   CarrelloDAO.updateCarrello(entry.getValue(), utenteConnesso.getId(), entry.getKey());
                               } catch (SQLException ex) {
                                   throw new RuntimeException(ex);
                               }
                           }
                       }
                   }

            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }


            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?error=1");
            dispatcher.forward(request, response);
        }

    public void destroy() {

    }
}