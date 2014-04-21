package controller;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBeanControl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String u = request.getParameter("username");		
		String p = request.getParameter("password");
		System.out.println("u="+u+"p="+p);
		
		UserBeanControl ubc = new UserBeanControl();
		String type = ubc.checkUser(u, p);
		if(type.equals("prof")){
			request.getRequestDispatcher("ModifierTable.jsp").forward(request, response);
		}
		else if(type.equals("etudiant")){
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
			JOptionPane.showMessageDialog(null,"Name or password wrong!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
