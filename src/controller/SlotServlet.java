package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SlotControl;

/**
 * Servlet implementation class SlotServlet
 */
@WebServlet("/SlotServlet")
public class SlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String flag = request.getParameter("flag");
		
		if(flag.equals("delSlot")){
			
			int id = Integer.parseInt(request.getParameter("id"));
		
			SlotControl sc = new SlotControl();
		
			if(sc.delSlotById(id)){
				//del success
				request.getRequestDispatcher("suc.jsp").forward(request, response);
			}else{
				//fail
				request.getRequestDispatcher("err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("addSlot")){
		
			String name = request.getParameter("name");
			
			SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, HH:mm");
			Date beginning=null;
			try {
				beginning = (Date) formatter.parse(request.getParameter("beginning"));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
			int duration = Integer.parseInt(request.getParameter("duration"));
			String type = request.getParameter("type");
		
			SlotControl sc = new SlotControl();
		    
			if(sc.addSlot(name, beginning, duration, type)){
				//add success
				request.getRequestDispatcher("suc.jsp").forward(request, response);
			}else{
				//fail
				request.getRequestDispatcher("err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("modifySlot")){
			
			String name = request.getParameter("name");
			
			SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, HH:mm");
			Date beginning=null;
			try {
				beginning = (Date) formatter.parse(request.getParameter("beginning"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int duration = Integer.parseInt(request.getParameter("duration"));
			String type = request.getParameter("type");
		
			SlotControl sc = new SlotControl();
		    
			if(sc.modifierSlot(name, beginning, duration, type)){
				//add success
				request.getRequestDispatcher("suc.jsp").forward(request, response);
			}else{
				//fail
				request.getRequestDispatcher("err.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}

}
