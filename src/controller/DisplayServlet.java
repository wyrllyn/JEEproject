package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7468405028901137391L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String displayMode = request.getParameter("display_mode");
		String group = request.getParameter("group");
		String displayPage = "DisplayCompact.jsp";
		switch (displayMode) {
		case "compact":
			displayPage = "DisplayCompact.jsp";
			break;
		case "summary":
			displayPage = "DisplaySummary.jsp";
			break;
		case "full":
			displayPage = "DisplayFull.jsp";
			break;
		}
		
		request.getSession().setAttribute("group", group);
		RequestDispatcher dispatcher = request.getRequestDispatcher(displayPage);
		dispatcher.forward(request, response);
	}
}
