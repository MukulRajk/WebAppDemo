
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

@WebServlet("/displayData")
public class DisplayServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootroot";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String selectQuery = "SELECT * FROM weblaptop";
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            ResultSet rs = pstmt.executeQuery();

            out.println("<html><body>");
            out.println("<h2>Stored Laptop Data:</h2>");
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + ", Make: " + rs.getString("make") + ", Cost: " + rs.getDouble("cost") + "<br>");
            }
            out.println("<a href=\"index.html\">Go back to input page</a>");
            out.println("</body></html>");

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            out.println("Error: " + e.getMessage());
        }
    }
}

