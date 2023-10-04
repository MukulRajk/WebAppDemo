package in.mindcraft;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet  extends HttpServlet {

	
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		int a=Integer.parseInt(request.getParameter("num1"));   //Add servlet
		int b=Integer.parseInt(request.getParameter("num2"));
		int c=a+b;
		
//request.setAttribute("c",c);  //(name,value)
		//System.out.println(c);
		//response.getWriter().println("Addition = "+c);
		
//RequestDispatcher rd=request.getRequestDispatcher("sq"); // one servlet connects to another servlet
//response.sendRedirect("sq?c="+c);  // url rewritting	
 //rd.forward(request, response);	
 
		
		//HttpSession session=request.getSession();   // using HTTPSession object
 //session.setAttribute("c",c);
 //response.sendRedirect("sq");

		Cookie cookie=new Cookie("c",c+"");
		response.addCookie(cookie);
		response.sendRedirect("sq");
		
 
 
 
	}
}
