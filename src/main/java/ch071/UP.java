package ch071;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch07.Player;
import ch07.PlayerDao;

/**
 * Servlet implementation class UP
 */
@WebServlet("/ch071/updatePlayer")
public class UP extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String backNo_ = request.getParameter("backNo");
		int backNo = Integer.parseInt(backNo_);
		
		PlDAO dao = new PlDAO();
		Pl c = dao.getPlayer(backNo);
		
		response.setCharacterEncoding("utf-8");		
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = response.getWriter();
		String data = "<!DOCTYPE html>"
				+ "<html lang=\"ko\">"
				+ "<head>"
				+ "    <meta charset=\"UTF-8\">"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "    <title>선수수정</title>"
				+ "    <style>"
				+ "        td { text-align: center; padding: 3px;}"
				+ "    </style>"
				+ "</head>"
				+ "<body style=\"margin: 40px;\">"
				+ "    <h1>선수수정</h1>"
				+ "    <hr>"
				+ "    <form action=\"/jw/ch071/updatePlayer\" method=\"post\">";
		data += "<input type=\"hidden\" name=\"backNo\" value=\"" + c.getBackNo() + "\">";
		data += "        <table>"
				+ "            <tr>"
				+ "                <td>번호</td>";
		data += "<td><input type=\"text\" name=\"backNo\" value=\"" + c.getBackNo() + "\" disabled ></td>";
		data += "            </tr>"
				+ "            <tr>"
				+ "                <td>선수명</td>";
		data += "<td><input type=\"text\" name=\"pname\" value=\"" + c.getPname() + "\" ></td>";
		data += "            </tr>"
				+ "            <tr>"
				+ "                <td>포지션</td>";
		data += "<td><input type=\"text\" name=\"position\" value=\"" + c.getPosition() + "\" ></td>";
		data += "            </tr>"
				+ "            <tr>"
				+ "                <td>생일</td>";
		data += "<td><input type=\"text\" name=\"birthDay\" value=\"" + c.getBirthDay() + "\" ></td>";
		data += "            </tr>"
				+ "            <tr>"
				+ "                <td>키</td>";
		data += "<td><input type=\"text\" name=\"height\" value=\"" + c.getHeight() + "\" ></td>";
		data += "            </tr>"
				+ "            <tr>"
				+ "                <td colspan=\"2\"><input type=\"submit\" value=\"수정\"></td>"
				+ "            </tr>"
				+ "        </table>"
				+ "    </form>"
				+ "</body>"
				+ "</html>";
		out.print(data);
	}
		
		
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String backNo_ = request.getParameter("backNo");
		int backNo = Integer.parseInt(backNo_);
		String pname = request.getParameter("pname");
		System.out.println(pname);
		
		String position = request.getParameter("position");
		String birthDay = request.getParameter("birthDay");
		String height_ = request.getParameter("height");
		int height = Integer.parseInt(height_);
		
		Pl c = new Pl(backNo, pname, position, birthDay, height, 0);
		PlDAO dao = new PlDAO();
		dao.updatePlayer(c);
		System.out.println(c.toString());
		System.out.println(dao.getPlayer(backNo));
		response.sendRedirect("/jw/ch071/listPlayer");
		System.out.println(dao.getPlayer(backNo));
	
	}

}
