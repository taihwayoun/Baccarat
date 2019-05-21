package com.thyoun.casino.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thyoun.casino.*;
/**
 * Servlet implementation class Scoreboard
 */
@WebServlet("/Scoreboard")
public class Scoreboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BaccaratList board;
	private BaccaratPlay play;
	private int round;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scoreboard() throws Exception{
        super();
        board = new BaccaratList();
        play = new BaccaratPlay();
        //board.addToPlayRecords(play);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter print =response.getWriter();
		String message="Current shoe in progress";
		String shoe = request.getParameter("shoe");
		int wins = Integer.parseInt(request.getParameter("wins"));
		int bets = Integer.parseInt(request.getParameter("bets"));
		String winner = request.getParameter("winner");
		try {
			if (shoe.equals("next")) {
				play.playOneRound();
				board.addToPlayRecords(play);
				++round;
			}
			else if (shoe.equals("new")){
				play.getNewShoe();
				board.clearBoard();
				round=1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		print.println("<html><head>");
		print.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
		print.println("<link rel=\"stylesheet\" href=\"/baccarat.css\">");
		print.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>");
		print.println("<script src=\"/p5.min.js\"></script>");
		print.println("</head><body>");
		print.println(play.toString());
		print.println("<script>");
		print.println("let round=" + round);
		print.println ("let wins=" + wins);
		print.println("let bets=" + bets);
		print.println("let RED=\"<div class='redcircle'></div>\";");
		print.println("let BLUE=\"<div class='bluecircle'></div>\";");
		print.println("let GREEN=\"<div class='greencircle'></div>\";");
		print.println("let SRED=\"<div class='smallred'></div>\";");
		print.println("let SBLUE=\"<div class='smallblue'></div>\";");
		print.println("let CRED=\"<div class='cred'>/</div>\";");
		print.println("let CBLUE=\"<div class='cblue'>/</div>\";");
		print.println("let FRED=\"<div class='fred'></div>\";");
		print.println("let FBLUE=\"<div class='fblue'></div>\";");
		print.println("let NONE='<div></div>'");
		print.println("let winner='"+play.getPlayResult() + "';");
		print.println("let choice ='" + winner + "'");
		print.println("let lens="+board.getLengthList() + ";");
		print.println("let bead="+board.getBeadPlate() + ";");
		print.println("let big="+board.getBigRoad() + ";");
		print.println("let eye="+board.getBigEyeBoy() + ";");
		print.println("let small="+board.getSmallRoad() + ";");
		print.println("let cock="+board.getCockRoachPig() + ";");
		print.println("let pred="+Arrays.toString(board.getPrediction())+ ";");
		
		String infoStr = "";
		if (play.isDragon()) infoStr += "Dragon<br>";
		if (play.isPanda()) infoStr += "Panda<br>";
		if (play.isThree8And9()) infoStr += "Three Card 8 and 9<br>";
		if (play.isAny8And9()) infoStr += "8 and 9<br>";
		if (play.isAny8And7()) infoStr += "8 and 7<br>";
		if (play.isBankerPair()) infoStr += "Banker pair<br>";
		if (play.isPlayerPair()) infoStr += "Player pair<br>";
		print.println("let infoStr='" + infoStr + "';</script>");
		
		print.println(readResourceFile("static/board.tpl"));

				
		//print.println("<ul><li>Round: " + round +"</li></ul></div>");
		print.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String readResourceFile(String fn) throws IOException {
		ClassLoader loader = getClass().getClassLoader();
		InputStream stream = loader.getResourceAsStream(fn);
		
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")))) {	
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}
	 
		return stringBuilder.toString();
	}

}
