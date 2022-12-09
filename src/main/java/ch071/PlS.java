package ch071;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch07.Player;
import ch07.PlayerDao;

/**
 * Servlet implementation class Pl
 */
@WebServlet("/ch071/listPlayer")
public class PlS extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlDAO dao = new PlDAO();
		List<Pl> list = dao.getPlayers();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"ko\">");
		out.print("<head>");
		out.print("	<meta charset=\"UTF-8\">");
		out.print("	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("	<title>KBO</title>");
		out.print("</head>");
		out.print("<body style=\"margin: 40px;\">");
		out.print("	<h1>KBO</h1>");
		out.print("	<hr>");
		out.print("	  <table border=\"1\">");
		out.print("	    <tr>");
		out.print("	      <th>Bno</th><th>Name</th><th>Position</th><th>Birthday</th><th>Height</th>");
		out.print("	    </tr>");
		
		
		for (Pl c: list) {
			out.print("	    <tr>");
			out.print("       <td>" + c.getBackNo() + "</td>");
			out.print("       <td>" + c.getPname() + "</td>");
			out.print("       <td>" + c.getPosition() + "</td>");
			out.print("       <td>" + c.getBirthDay().toString() + "</td>");
			out.print("       <td>" + c.getHeight() + "</td>");
			out.print("       <td>" + "<a href=\"/jw/ch071/updatePlayer?backNo="+ c.getBackNo() +"\">수정</a>&nbsp;" + 
					"<a href=\"/jw/ch071/deletePlayer?backNo=" + c.getBackNo() +"\">삭제</a>" + "</td>");
			out.print("	    <tr>");
		}
		
		out.print("	</table>");
		out.print(" <br>");
		out.print(" <a href=\"/jw/ch071/insertPlayer.html\">선수등록</a>");
		out.print("</body>");
		out.print("</html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
