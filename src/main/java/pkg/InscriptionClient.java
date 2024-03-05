package pkg;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class InscriptionClient extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		/*if (cookie.getName().equals("nom")) {
			nom = cookie.getValue();*/
		//Cookie[] tjriba = request.getCookies();
		String nomCookie = null;
		String motPasseCookie = null;
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("nom")) {
                	nomCookie = cookie.getValue();
                	motPasseCookie = cookie.getValue();
                    break;
                }
                if (cookie.getName().equals("motdepasse")) {
                	motPasseCookie = cookie.getValue();
                    break;
                }
            }
        }
		String nomRecu =request.getParameter("nom");
		String motPasseRecu =request.getParameter("motdepasse");
// initialisation cookies et paramètres
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (nomCookie == null && nomRecu == null) {
// Cas 1 : cas où il n'y a ni de cookies ni de parametres
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title> inscription d'un client </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("\n"+nomRecu + " | " + motPasseRecu + " | " + nomCookie + " | " + motPasseCookie);
			//out.println("‎‎‎‎‎‎‎‎‎ ‎‎‎tjriba : "+tjriba);
			out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
			out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
			out.print(" <form action='sinscrire' method='GET' > ");
			out.println("nom");
			out.println("<input type='text' size='20' name='nom' >");
			out.println("<br>");
			out.println("mot de passe");
			out.println("<input type='password' size='20' name='motdepasse'> <br>");
			out.println("<input type='submit' value='inscription'>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} else if (nomCookie == null && nomRecu != null) {
// Cas 2 : cas où il n'y a pas de cookies mais les paramètres nom et mot de passes sont présents :
			Cookie nom = new Cookie("nom", nomRecu);
			Cookie motPasse = new Cookie("motdepasse", motPasseRecu);
			response.addCookie(nom);
			response.addCookie(motPasse);
			// Rediriger vers la page d'accueil ou une autre page
			response.sendRedirect("achat");
		} else if (identique(nomRecu, nomCookie) && identique(motPasseRecu, motPasseCookie)) {
// Cas 4 : cas où le nom et le mot passe sont correctes, appel à la servlet achat
			response.sendRedirect("achat");
		} else {
// Cas 3 : les cookies sont présents demande de s'identifier
			
			//tari9a lwla khdama kat3mr lih lblassa ta3 nom o mdps bdata li fl cookie 
			/*out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title> identification d'un client </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println(nomRecu + " | " + motPasseRecu + " | " + nomCookie + " | " + motPasseCookie);
			out.println("<h3>" + "Veuillez vous identifier" + "</h3>");
			out.print(" <form action='sinscrire' method='POST' > ");
			out.println("nom");
			out.println("<input type='text' size='20' name='nom' value='" + nomCookie + "'>");
			out.println("<br>");
			out.println("mot de passe");
			out.println("<input type='password' size='20' name='motdepasse' value='" + motPasseCookie + "'> <br>");
			out.println("<input type='submit' value='identification'>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");*/
			
			//tari9a tanya katsifto direct l achat (AfficherLesDisques)
			response.sendRedirect("achat");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	boolean identique(String recu, String cookie) {
		return ((recu != null) && (recu.length() > 3) && (cookie != null) && (recu.equals(cookie)));
	}
}