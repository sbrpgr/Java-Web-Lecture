package ch071;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch07.CustomerDao;

/**
 * Servlet implementation class DeletePlayer
 */
@WebServlet("/ch071/deletePlayer")
public class DP extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String backNo_ = request.getParameter("backNo");
		int backNo = Integer.parseInt(backNo_);
		PlDAO dao = new PlDAO();
		dao.deletePlayer(backNo);
		
		response.sendRedirect("/jw/ch071/listPlayer");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
