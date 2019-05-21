package com.thyoun.casino.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URL;
import java.util.Map;
import java.util.Collections;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
//import com.google.common.base.Strings;
import com.thyoun.casino.*;

/**
 * Servlet implementation class BaccaratGame
 */
@WebServlet("/simulation")
public class BaccaratGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Deck deck;
	
	public BaccaratGame() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query =decode(request.getQueryString());
		String spQuery[]=new String[2];
		if (query != null) spQuery=query.split("=");
		response.setContentType("text/html");
		PrintWriter print =response.getWriter();
		print.println("<html><head>");
		print.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
		print.println("<link rel=\"stylesheet\" href=\"/baccarat.css\">");
		print.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>");
		print.println("<script src=\"/p5.min.js\"></script>");
		print.println("</head><body><div class='w3-container w3-card-4'style=\"width:400px; margin: 10px 10px 10px 10px\">" +
				"<div class=\"w3-container\" style=\"padding: 20px 20px 20px 20px\"><h2> Baccarat Simulation </h2>");

		try {
			BaccaratPlay bp = new BaccaratPlay();
			long shoeNum = Long.parseLong(spQuery[1]);
			if (shoeNum > 1) {
				print.println("<p>"+request.getQueryString() + " shoes:");
				int sim[] = bp.playSimulation(shoeNum);
				double bwp = 100.0* sim[0]/sim[3]; //bankwin,playwin,tie,total,bankernatural,playernatural,three89,any89,badbeat,any87,panda,dr
				double pwp = 100.0* sim[1]/sim[3];
				double tp = 100.0* sim[2]/sim[3];
				double bn = 100.0* sim[4]/sim[3];
				double pn = 100.0* sim[5]/sim[3];
				double t89 = 100.0* sim[6]/sim[3];
				double a89 = 100.0* sim[7]/sim[3];
				double bd = 100.0* sim[8]/sim[3];
				double a87 = 100.0* sim[9]/sim[3];
				double pan = 100.0* sim[10]/sim[3];
				double dg = 100.0* sim[11]/sim[3];
				double pp = 100.0* sim[12]/sim[3];
				double bkp = 100.0* sim[13]/sim[3];
				print.format("<p>banker wins " + sim[0] + " times: %.3f%n%%<br>", bwp);
				print.format("player wins " + sim[1] + " times: %.3f%n%%<br>", pwp);
				print.format("tie " + sim[2]+ " times: %.3f%%<br>", tp);
				print.format("banker natural " + sim[4]+ " times: %.3f%%<br>", bn);
				print.format("player natural " + sim[5]+ " times: %.3f%%<br>", pn);
				print.format("three card 8 and 9 " + sim[6]+ " times: %.3f%%<br>", t89);
				print.format("natural 8 and 9 " + sim[7]+ " times: %.3f%%<br>", a89);
				print.format("bad beat " + sim[8]+ " times: %.3f%%<br>", bd);
				print.format("any 8 and 7 " + sim[9]+ " times: %.3f%%<br>", a87);
				print.format("panda " + sim[10]+ " times: %.3f%%<br>", pan);
				print.format("dragon " + sim[11]+ " times: %.3f%%<br>", dg);
				print.format("player pair " + sim[12]+ " times: %.3f%%<br>", pp);
				print.format("banker pair" + sim[13]+ " times: %.3f%%<br>", bkp);
			} else if (shoeNum == 1) {
				List<String> oneshoe = bp.playOneShoe();
				print.println("<ul class='w3-ul w3-border'>");
				for (String round : oneshoe) {
					print.println("<li>"+ round);
				}
				print.println("</ul>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		print.println("<form action='/simulation' method='GET'>");
		print.println("<input class='w3-input' 'type='text' name='number'><button type='submit'>submit</button></form></div></div>");
		print.println("</body></html>");
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static String decode(final String encoded) {
	    try {
	        return encoded == null ? null : URLDecoder.decode(encoded, "UTF-8");
	    } catch(final UnsupportedEncodingException e) {
	        throw new RuntimeException("Impossible: UTF-8 is a required encoding", e);
	    }
	}

}
