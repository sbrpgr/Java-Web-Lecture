package ch07;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertPlayer
 */
@WebServlet("/ch07/insertPlayer")
public class InsertPlayer extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String backNo_ = request.getParameter("backNo");
		int backNo = Integer.parseInt(backNo_);
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String birthDay = request.getParameter("birthDay");
		String height_ = request.getParameter("height");
		int height = Integer.parseInt(height_);
		PlayerDao dao = new PlayerDao();
		// 유효성 검증
//		Player c = dao.getPlayer(backNo);
//		if (c.getBackNo() <= 1)								// 기존 사용자가 있는 경우
//			response.sendRedirect("/jw/ch07/insertPlayer.html");
//		else {												// 기존 사용자가 없는 경우
//			c = new Player(backNo, name, position, birthDay, height, 0);
//			dao.insertPlayer(c);
//			
//			response.sendRedirect("/jw/ch07/listPlayer");
//		}
		
		
	}

}
