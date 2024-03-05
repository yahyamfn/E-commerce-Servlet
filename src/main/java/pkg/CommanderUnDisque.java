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
		Disque disque = new Disque(ordre, prix);
		//Cookie panierCookie = new Cookie("panier", null);
		//response.addCookie(panierCookie);
		
		HttpSession session = request.getSession();
		List<Disque> panier = (List<Disque>) session.getAttribute("panier");
		// Ajouter des éléments au panier
		if (panier == null) {
            panier = new ArrayList<>();
            session.setAttribute("panier", panier);
        }
        panier.add(disque);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> votre commande </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<style>#va{background-color:rgb(236 131 41)} body{background-color:rgb(250 245 228);padding: 40%;padding-top:0;} table{margin-left: -50px;margin-left:20px}</style>");
		out.println("<h1>" + "Bonjour " + nom + " voici votre commande : </h1>");
		out.println("<h5><b> (Votre panier) </b></h5>");
		
		// Affichage de tous les disques présents dans le panier (éléments de la session)
		//List<String> panierRecue = (List<String>) session.getAttribute("panier");
		 out.println("<table border='1'>");
		 out.println("<tr id='va'><th><b>Ordre</b></th><th><b>Prix</b></th></tr>");
			for (Disque disqueRecu : panier) {
	            nbreProduit++;
	            out.println("<tr><td>" + disqueRecu.getOrdre() + "</td><td>" + disqueRecu.getPrix() + " Euros</td></tr>");
	        }
			out.println("</table>");
	        out.println("<p> Votre panier contient : "+ nbreProduit +" disque(s) </p>");
			out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
			out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
			out.println("</body>");
			out.println("</html>");
			
		// Si le paramètre ordre == ajouter, affichage du disque à ajouter au panier
		//String ordre = request.getParameter("ordre");
		//had lblock makhdamch
		/*if ("ajouter".equals(ordre)) {
			String disqueAAjouter = request.getParameter("disque");
			if (disqueAAjouter != null) {
				out.println("<p>Ajouté au panier: " + disqueAAjouter + "</p>");
				if (panierr == null) {
					panierr = new ArrayList<>();
					session.setAttribute("panier", panierr);
				}
				panierr.add(disqueAAjouter);
			}
		}*/
		//7tal lhna o kay7bss hadchi li lt7t khdam
		
		}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
