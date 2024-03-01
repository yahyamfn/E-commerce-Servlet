package pkg;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;

public class FiltreAutorisation implements Filter {

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String nom = null;
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse hresponse = (HttpServletResponse) response;
        Cookie[] cookies = hrequest.getCookies();

        // Test s'il existe un cookie dont l'attribut est "nom"
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("nom")) {
                    nom = cookie.getValue();
                    break;
                }
            }
        }

        // Cas où le cookie "nom" n'existe pas
        if (nom == null) {
            // Appel de la servlet inscrire
            RequestDispatcher dispatcher = request.getRequestDispatcher("/inscrire");
            dispatcher.forward(request, response);
        } else {
            // Cookie "nom" trouvé, on laisse la requête passer
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        this.filterConfig = null;
    }
}
