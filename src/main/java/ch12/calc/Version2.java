package ch12.calc;

import java.io.IOException;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Version2
 */
@WebServlet("/ch12/calc/ver2")
public class Version2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("eval", "0");
		RequestDispatcher rd = request.getRequestDispatcher("/ch12/calc/ver2.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("stack");
		Stack<Object> stack = (obj != null) ? (Stack) obj : new Stack<>();
		RequestDispatcher rd = null;
		String num_ = request.getParameter("num");
		String op_ = request.getParameter("op");
		
		if (num_ != null) {						// 숫자를 눌렀을 때
			stack.push(num_);
			session.setAttribute("stack", stack);
			request.setAttribute("eval", getEval(stack));
			rd = request.getRequestDispatcher("/ch12/calc/ver2.jsp");
			rd.forward(request, response);
		} else if (op_ != null && !op_.equals("=")) {	// 연산자를 눌렀을 때
			if (op_.equals("C")) {
				stack.pop();
			} else {
				stack.push(op_);
			}
			session.setAttribute("stack", stack);
			request.setAttribute("eval", getEval(stack));
			rd = request.getRequestDispatcher("/ch12/calc/ver2.jsp");
			rd.forward(request, response);
		} else {								// = 을 눌렀을 때
			int result = 0;
			int num2 = Integer.parseInt((String) stack.pop());
			String op = (String) stack.pop();
			int num1 = Integer.parseInt((String) stack.pop());
			switch(op) {
				case "+": result = num1 + num2; break;
				case "-": result = num1 - num2; break;
				case "*": result = num1 * num2; break;
				case "/": result = num1 / num2; break;
				default: result = Integer.MAX_VALUE;
			}
			session.invalidate();
			request.setAttribute("eval", result);
			rd = request.getRequestDispatcher("/ch12/calc/ver2.jsp");
			rd.forward(request, response);
		}
	}

	String getEval(Stack<Object> stack) {
		String eval = "";
		for (Object s: stack)
			eval += (String) s + " ";
		return eval;
	}
}