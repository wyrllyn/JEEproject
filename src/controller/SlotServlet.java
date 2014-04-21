package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DateUsed;
import model.Days;
import model.Person;
import model.SlotControl;
import model.UserBeanControl;
import util.DateUtils;

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
			Days beginday = DateUtils.getDay(request.getParameter("beginday"));
			int beginhours = Integer.parseInt(request.getParameter("beginhours"));
			int beginminutes = Integer.parseInt(request.getParameter("beginminutes"));
			DateUsed beginning = new DateUsed(beginday,beginhours,beginminutes);				
			int duration = Integer.parseInt(request.getParameter("duration"));
			String teacherName = request.getParameter("teachername");
			UserBeanControl ubc = new UserBeanControl();
			Person teacher = ubc.checkTeacher(teacherName);
			String type = request.getParameter("type");
		
			SlotControl sc = new SlotControl();
		    
			if(sc.addSlot(name, beginning, duration, teacher, type)){
				//add success
				request.getRequestDispatcher("suc.jsp").forward(request, response);
			}else{
				//fail
				request.getRequestDispatcher("err.jsp").forward(request, response);
			}
		}
		
		else if(flag.equals("modifySlot")){
			int id = Integer.parseInt(request.getParameter("id"));
			
			String name = request.getParameter("name");
			Days beginday = DateUtils.getDay(request.getParameter("beginday"));
			int beginhours = Integer.parseInt(request.getParameter("beginhours"));
			int beginminutes = Integer.parseInt(request.getParameter("beginminutes"));
			DateUsed beginning = new DateUsed(beginday,beginhours,beginminutes);	
			int duration = Integer.parseInt(request.getParameter("duration"));
			String teacherName = request.getParameter("teachername");
			
			UserBeanControl ubc = new UserBeanControl();
			Person teacher = ubc.checkTeacher(teacherName);
			
			String type = request.getParameter("type");
			
			SlotControl sc = new SlotControl();
		    
			if(sc.modifierSlot(id, name, beginning, duration, teacher, type)){
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
