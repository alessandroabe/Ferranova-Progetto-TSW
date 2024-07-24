import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.unisa.abeilleamorellifontana_pj.Model.*;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String mail = request.getParameter("email");
        String password = request.getParameter("password");

        // Definizione delle regex
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";

        if (!Pattern.matches(emailRegex, mail) || !Pattern.matches(passwordRegex, password)) {
            // Se le regex non corrispondono, imposta l'errore e reindirizza
            request.setAttribute("error", "Formato email o password non validi");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        UtenteDAO U = new UtenteDAO();
        Utente input = U.doRetrieveByEmail(mail);

        Carrello carrello = (Carrello) session.getAttribute("Carrello");
        if (input != null && input.getEmail().equals(mail) && SHA1PasswordVerifier.verifyPassword(password, input.getPasswordhash())) {
            // Imposta l'utente connesso nella sessione
            session.setAttribute("UtenteConnesso", input);
            carrello.setIdUtente(input.getId());
            carrello.mergeProdotti(CarrelloDAO.doRetrieve(input.getId()));
            response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?error=1");
        dispatcher.forward(request, response);
    }

    public void destroy() {

    }
}
