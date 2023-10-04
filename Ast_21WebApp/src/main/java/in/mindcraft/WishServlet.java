package in.mindcraft;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/wish")  for this comment mapping in index.html
public class WishServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String  a=(String) request.getAttribute("a");
		
		//int sq=c*c;
		response.getWriter().println("Welcome "+a);
	}

}
