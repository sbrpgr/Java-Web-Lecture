package ch071;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertPlayer
 */
@WebServlet("/ch071/insertPlayer")
public class IP extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String backNo_ = request.getParameter("backNo");
		int backNo = Integer.parseInt(backNo_);
		String pname = request.getParameter("pname");
		String position = request.getParameter("position");
		String birthDay = request.getParameter("birthDay");
		String height_ = request.getParameter("height");
		int height = Integer.parseInt(height_);
		
		System.out.println(backNo +","+ pname + ""+ position + "" + birthDay + ""+ height );
		
		PlDAO dao = new PlDAO();
		// 유효성 검증
		Pl c = dao.getPlayer(backNo);
		
		System.out.println(c);
		
		if (c.getBackNo() > 0)								// 기존 사용자가 있는 경우
			response.sendRedirect("/jw/ch071/insertPlayer.html");
		else {												// 기존 사용자가 없는 경우
			c = new Pl(backNo, pname, position, birthDay, height, 0);
			dao.insertPlayer(c);
			
			response.sendRedirect("/jw/ch071/listPlayer");
		}
		
		
	}

}
