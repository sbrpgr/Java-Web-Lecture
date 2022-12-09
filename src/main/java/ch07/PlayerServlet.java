package ch07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/ch07/listPlayer")
public class PlayerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PlayerDao dao = new PlayerDao();
			List<Player> list = dao.getPlayers();
			
			response.setCharacterEncoding("utf-8");		// 굳이 안해도 인코딩 오류 발생하지 않음
			response.setContentType("text/html; charset=utf-8");	// 반드시 세팅해주어야 함
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
			
			for (Player c: list) {
				out.print("	    <tr>");
				out.print("       <td>" + c.getBackNo() + "</td>");
				out.print("       <td>" + c.getPname() + "</td>");
				out.print("       <td>" + c.getPosition() + "</td>");
				out.print("       <td>" + c.getBirthDay().toString() + "</td>");
				out.print("       <td>" + c.getHeight() + "</td>");
				out.print("       <td>" + "<a href=\"/jw/ch07/updatePlayer?backNo="+ c.getBackNo() +"\">수정</a>&nbsp;" + 
						"<a href=\"/jw/ch07/deletePlayer?backNo=" + c.getBackNo() +"\">삭제</a>" + "</td>");
				out.print("	    <tr>");
			}
			
			out.print("	</table>");
			out.print(" <br>");
			out.print(" <a href=\"/jw/ch07/insertPlayer.html\">선수등록</a>");
			out.print("</body>");
			out.print("</html>");
		}
			
			
			
			
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
