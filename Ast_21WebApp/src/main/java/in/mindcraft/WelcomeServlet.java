package in.mindcraft;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 //@WebServlet("/wel")
public class WelcomeServlet  extends HttpServlet {

	
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		String a=request.getParameter("Name here");   
		request.setAttribute("a",a);  
		RequestDispatcher rd=request.getRequestDispatcher("wish"); 
	
 rd.forward(request, response);	
 }
}
