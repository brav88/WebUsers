/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pck.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samuel
 */
public class insertUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            //Codigo con clases
            /*User user = new User(
                    Integer.parseInt(request.getParameter("txtIdn")),
                    request.getParameter("txtName"),
                    request.getParameter("txtLastName"),
                    Integer.parseInt(request.getParameter("txtPhone")),
                    request.getParameter("txtEmail"),
                    request.getParameter("txtAddress")
            );
            
            if (user.CreateUser(user)){
                out.println("User created.");
            }
            else{
                out.println("Unhandled error.");
            }*/

            //Codigo sin clase
            int txtIdn = Integer.parseInt(request.getParameter("txtIdn"));
            String txtName = request.getParameter("txtName");
            String txtLastName = request.getParameter("txtLastName");
            int txtPhone = Integer.parseInt(request.getParameter("txtPhone"));
            String txtEmail = request.getParameter("txtEmail");
            String txtAddress = request.getParameter("txtAddress");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/WebUsers", "root", "Admin$1234");
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where Email = '" + txtEmail + "'");

            if (resultSet.next()) {
                out.println("<script type='text/javascript'>alert('User already created');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                rd.include(request, response);
            } else {
                String sql = "insert into users (Id, Name, LastName, Phone, Email, Address) "
                        + "values (" + txtIdn + ", '" + txtName + "', '" + txtLastName + "', " + txtPhone + ", '" + txtEmail + "', '" + txtAddress + "')";

                statement2.executeUpdate(sql);
                statement2.close();

                out.println("<script type='text/javascript'>alert('User created');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("/getUsersServlet");
                rd.include(request, response);
            }

            statement.close();
        } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
            out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
