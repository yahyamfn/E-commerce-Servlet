package pkg;

import jakarta.servlet.http.*;

public class Identification {

	static String chercheNom(Cookie[] cookies) {
		// On parcourt les cookies
		for (Cookie cookie : cookies) {
			// Si le nom du cookie est "nom"
			if (cookie.getName().equals("nom")) {
				// On retourne la valeur du cookie
				return cookie.getValue();
			}
		}
		// Si le cookie "nom" n'a pas été trouvé
		return "inconnu";
	}

	public static String verifier(Cookie[] cookies) {
		// On récupère le nom de l'utilisateur
		String nom = chercheNom(cookies);

		// Si le nom est "inconnu"
		if (nom.equals("inconnu")) {
			HttpServletResponse response;
			// L'utilisateur n'est pas authentifié
			return null;
		} else {
			// L'utilisateur est authentifié
			return nom;
		}
	}
}
