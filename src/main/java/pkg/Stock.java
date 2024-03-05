package pkg;

import java.io.*;

public class Stock {
	static String[][] leStock = { { "Disque CD - AMOR TICINES", "15", "disque897TR566" },
			{ "Disque CD - Los Mayas", "19", "disque78UUNYT67" }, { "Disque CD - Dick Anglas", "25", "disque87YHG564" },
			{ "Disque CD - Frederic Angonas", "35", "disque98HUYU56" } };

	public static void vente(PrintWriter out) {
		out.println("<table border=1>");
		out.println("<tr id='va'><th id='hh'>Disques</th><th>Commander</th></tr>");
		out.println("<style>#va{background-color:rgb(236 131 41)} body{background-color:rgb(250 245 228);padding:40%;padding-top:0;}table{margin-top:40%;margin-left: -90px}</style>");
		for (int i = 0; i < leStock.length; i++) {
			out.println("<tr> <td>" + leStock[i][0] + " " + leStock[i][1] + " Euros </td>");
			out.println(" <td><A style='align-items:center;' HREF=\"commande?element=disque&code=");
			out.println(leStock[i][2] + "&ordre=" +leStock[i][0]+ "&prix=" + leStock[i][1] + "\">");
			out.println("<IMG SRC=\"/fcexemple/images/panier.gif\" BORDER=0></A><br> </td> </tr>");
		}
		out.println("</table> </form>");
	}
}