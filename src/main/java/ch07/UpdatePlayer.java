package ch07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ch07/updatePlayer")
public class UpdatePlayer extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String backNo_ = request.getParameter("backNo");
		int backNo = Integer.parseInt(backNo_);
		
		PlayerDao dao = new PlayerDao();
		Player c = dao.getPlayer(backNo);
		
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
				+ "    <form action=\"/jw/ch07/updatePlayer\" method=\"post\">";
		data += "<input type=\"hidden\" name=\"backNo\" value=\"" + c.getBackNo() + "\">";
		data += "        <table>"
				+ "            <tr>"
				+ "                <td>번호</td>";
		data += "<td><input type=\"text\" name=\"backNo\" value=\"" + c.getBackNo() + "\" disabled ></td>";
		data += "            </tr>"
				+ "            <tr>"
				+ "                <td>선수명</td>";
		data += "<td><input type=\"text\" name=\"Pname\" value=\"" + c.getPname() + "\" ></td>";
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
		String pname = request.getParameter("name");
		pname = "조지네";
		System.out.println(pname);
		
		String position = request.getParameter("position");
		String birthDay = request.getParameter("birthDay");
		String height_ = request.getParameter("height");
		int height = Integer.parseInt(height_);
		
		Player c = new Player(backNo, pname, position, birthDay, height, 0);
		PlayerDao dao = new PlayerDao();
		dao.updatePlayer(c);
		System.out.println(c.toString());
		System.out.println(dao.getPlayer(backNo));
		response.sendRedirect("/jw/ch07/listPlayer");
	}

}
