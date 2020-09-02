package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class downloadServlet
 */
@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filename = request.getParameter("filename");
		OutputStream os =  response.getOutputStream();
		if(filename == null) 
		{
			
			os.write("file name is missing".getBytes());
			os.close();
			return;
		}
		InputStream in = getServletContext().getResourceAsStream("WEB-INF/"+filename);
		if(in != null) 
		{
			int length = in.available();
			response.setContentType("application/force-download");
			response.setHeader("Content-Length",String.valueOf(length));
			response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");
			int bytesRead = 0;
			byte[] buffer = new byte[512];
			while( (bytesRead = in.read(buffer)) !=  -1 ) 
			{
				os.write(buffer,0,bytesRead);
			}
			in.close();
			os.close();
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
