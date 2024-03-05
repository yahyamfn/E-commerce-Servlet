package pkg;

import java.io.*;
import java.util.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;

public class EnregistrerCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Connection connexion = null;
	//Statement stmt = null;
	//PreparedStatement pstmt = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nom = null;
		int nbreProduit = 0;
		Cookie[] cookies = request.getCookies();
		//boolean connu = false;
		nom = Identification.chercheNom(cookies);
		//OuvreBase();
		//AjouteNomBase(nom);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> votre commande </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<style>.va{background-color:rgb(236 131 41)} body{background-color:rgb(250 245 228);padding-top:0;} table{justify-content: center;align-items: center;display: flex;}</style>");
		out.println("<h1 style='text-align: center;'>" + "Bonjour " + nom + " voici ta nouvelle commande :" + "</h1>");
		out.println("<table border='1'>");
		out.println("<tr class='va'><th><b>Nombre de disques dans le panier : </b></th></tr>");
		out.println("<tr><td>Votre panier est vide suite a la commande</td></tr>");
		out.println("</table>");
		out.println("<h1 style='text-align: center;'>Voici ta commande complete :</h1>");
		HttpSession session = request.getSession();
		List<Disque> panier = (List<Disque>) session.getAttribute("panier");
		out.println("<table border='1'>");
		out.println("<tr class='va'><th><b>Ordre</b></th><th><b>Prix</b></th></tr>");
		for (Disque disqueRecu : panier) {
			nbreProduit++;
			out.println("<tr><td>" + disqueRecu.getOrdre() + "</td><td>" + disqueRecu.getPrix() + " Euros</td></tr>");
		}
		out.println("</table>");
		//AjouteCommandeBase(nom, session);
		//MontreCommandeBase(nom, out);
		out.println("<A HREF=vider> Effectuer un autre achat </A><br> ");
		out.println("</body>");
		out.println("</html>");
	}

	/*protected void OuvreBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", "");
			connexion.setAutoCommit(true);
			stmt = connexion.createStatement();
		} catch (Exception E) {
			log(" -------- probleme " + E.getClass().getName());
			E.printStackTrace();
		}
	}

	protected void fermeBase() {
		try {
			stmt.close();
			connexion.close();
		} catch (Exception E) {
			log(" -------- probleme " + E.getClass().getName());
			E.printStackTrace();
		}
	}

	protected void AjouteNomBase(String nom) {
		try {
			ResultSet rset = null;
			pstmt = connexion.prepareStatement("select numero from personnel where nom=?");
			pstmt.setString(1, nom);
			rset = pstmt.executeQuery();
			if (!rset.next())
				stmt.executeUpdate("INSERT INTO personnel(nom) VALUES ('" + nom + "' )");
		} catch (Exception E) {
			log(" - probeme " + E.getClass().getName());
			E.printStackTrace();
		}
	}

	protected void AjouteCommandeBase(String nom, HttpSession session) {
// ajoute le contenu du panier dans la base
		int idUtilisateur = getIdUtilisateur(nom);

		List<String> panier = (List<String>) session.getAttribute("panier");

		if (idUtilisateur != -1 && panier != null && !panier.isEmpty()) {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", "")) {
				String insertCommandeQuery = "INSERT INTO commande (article, qui) VALUES (?, ?)";
				try (PreparedStatement preparedStatement = conn.prepareStatement(insertCommandeQuery)) {
					for (String disque : panier) {
						preparedStatement.setString(1, disque);
						preparedStatement.setInt(2, idUtilisateur);
						preparedStatement.executeUpdate();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected int getIdUtilisateur(String nomUtilisateur) {
		int idUtilisateur = -1;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", "")) {
			String selectUtilisateurQuery = "SELECT numero FROM personnel WHERE nom = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(selectUtilisateurQuery)) {
				preparedStatement.setString(1, nomUtilisateur);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						idUtilisateur = resultSet.getInt("numero");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idUtilisateur;
	}

	protected void MontreCommandeBase(String nom, PrintWriter out) {
// affiche les produits présents dans la base
	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}