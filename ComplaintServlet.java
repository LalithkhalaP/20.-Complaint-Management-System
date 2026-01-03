package complaint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/complaintdb",
                    "root",
                    "lalli@2007"   
            );

            if (request.getParameter("issue") != null) {

                String issue = request.getParameter("issue");

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO complaints(issue, status) VALUES (?, ?)");
                ps.setString(1, issue);
                ps.setString(2, "Pending");
                ps.executeUpdate();

                request.setAttribute("msg", "Complaint submitted successfully");

                RequestDispatcher rd =
                        request.getRequestDispatcher("complaint.jsp");
                rd.forward(request, response);
                return;
            }

            if (request.getParameter("cid") != null) {

                int cid = Integer.parseInt(request.getParameter("cid"));

                PreparedStatement ps = con.prepareStatement(
                        "SELECT status FROM complaints WHERE id=?");
                ps.setInt(1, cid);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    request.setAttribute("status", rs.getString("status"));
                } else {
                    request.setAttribute("status", "Complaint ID not found");
                }

                RequestDispatcher rd =
                        request.getRequestDispatcher("viewStatus.jsp");
                rd.forward(request, response);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
