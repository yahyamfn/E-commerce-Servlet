package pkg;

import java.io.*;
import java.util.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

public class CommanderUnDisque extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nom = null;
		int nbreProduit = 0;
		Cookie[] cookies = request.getCookies();
		nom = Identification.verifier(cookies);	
		String ordre = request.getParameter("ordre");
		String prix = request.getParameter("prix");
		Disque disquee = new Disque(ordre, prix);
		//Cookie panierCookie = new Cookie("panier", null);
		//response.addCookie(panierCookie);
		
		HttpSession session = request.getSession();
		List<String> panier = new ArrayList<>();
		// Ajouter des éléments au panier
		panier.add(disquee.toString());

		// Ajouter le panier à la session
		session.setAttribute("panier", panier);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> votre commande </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<h3>" + "Bonjour " + nom + " voici votre commande" + "</h3>");
		
		// Affichage de tous les disques présents dans le panier (éléments de la session)
		List<String> panierr = (List<String>) session.getAttribute("panier");
		if (panierr != null) {
			for (String disque : panierr) {
				out.println("<p>" + disque + "</p>");
			}
		}
		
		// Si le paramètre ordre == ajouter, affichage du disque à ajouter au panier
		//String ordre = request.getParameter("ordre");
		if ("ajouter".equals(ordre)) {
			String disqueAAjouter = request.getParameter("disque");
			if (disqueAAjouter != null) {
				out.println("<p>Ajouté au panier: " + disqueAAjouter + "</p>");
				if (panierr == null) {
					panierr = new ArrayList<>();
					session.setAttribute("panier", panierr);
				}
				panierr.add(disqueAAjouter);
			}
		}
		
		out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
		out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
		out.println("</body>");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
