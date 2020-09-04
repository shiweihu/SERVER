package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBC.DBManager;

/**
 * Servlet implementation class privateInfo
 */
@WebServlet("/privateInfo")
public class privateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public privateInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String gender = request.getParameter("gender");
		String age = request.getParameter("Age");
		String postcode = request.getParameter("postcode");
		
		if(gender.isEmpty() || age.isEmpty() || postcode.isEmpty()) 
		{
			response.getWriter().append("0");
			return ;
		}
		
		if(DBManager.getInstance().storeUserInfo(gender, age, postcode)) 
		{
			response.getWriter().append("1");
		}else 
		{
			response.getWriter().append("0");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
