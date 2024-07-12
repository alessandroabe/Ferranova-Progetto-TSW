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

        Carrello carrello = null;
        if (input != null && input.getEmail().equals(mail) && SHA1PasswordVerifier.verifyPassword(password, input.getPasswordhash())) {
            // Imposta l'utente connesso nella sessione




            session.setAttribute("UtenteConnesso", input);




            if((Object) session.getAttribute("Carrello") == null){
                carrello= new Carrello(input.getId());
                session.setAttribute("Carrello", carrello);
            }

            if (carrello != null) {
                carrello.mergeProdotti(CarrelloDAO.doRetrieve(input.getId()));
            }

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