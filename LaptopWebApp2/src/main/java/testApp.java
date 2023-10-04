
//public class testApp {}
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testAp")
public class testApp extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test"; //"jdbc:mysql://hostname:port/database"
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootroot";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        // Display the form to input values
        out.println("<form method=\"post\">");
        out.println("ID: <input type=\"number\" name=\"id\"><br>");
        out.println("Make: <input type=\"text\" name=\"make\"><br>");
        out.println("Cost: <input type=\"number\" name=\"cost\"><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get user input from the form
        int id = Integer.parseInt(request.getParameter("id"));
        String make = request.getParameter("make");
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Insert user input into the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String insertQuery = "INSERT INTO weblaptop (id, make, cost) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, id);
            pstmt.setString(2, make);
            pstmt.setDouble(3, cost);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            out.println("Error: " + e.getMessage());
        }
        
        

        // Retrieve and display stored values
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String selectQuery = "SELECT * FROM weblaptop";
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            ResultSet rs = pstmt.executeQuery();

         // Display user input values
            out.println("<html><body>");
            out.println("<h2>Stored Laptop Data:</h2>");
            out.println("ID: " + id + ", Make: " + make + ", Cost: " + cost + "<br>");
            out.println("<form method=\"post\">");
            out.println("ID: <input type=\"number\" name=\"id\"><br>");
            out.println("Make: <input type=\"text\" name=\"make\"><br>");
            out.println("Cost: <input type=\"number\" name=\"cost\"><br>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");
            out.println("</body></html>");

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
