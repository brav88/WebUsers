/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Samuel
 */
public class User {

    int Idn;
    String Name;
    String LastName;
    int Phone;
    String Email;
    String Address;

    public User(int pIdn, String pName, String pLastName, int pPhone, String pEmail, String pAddress) {
        this.Idn = pIdn;
        this.Name = pName;
        this.LastName = pLastName;
        this.Phone = pPhone;
        this.Email = pEmail;
        this.Address = pAddress;
    }

    public Boolean CreateUser(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/WebUsers", "root", "Admin$1234");
            Statement statement = connection.createStatement();

            String sql = "insert into users (Id, Name, LastName, Phone, Email, Address) "
                    + "values (" + user.Idn + ", '" + user.Name + "', '" + user.LastName + "', " + user.Phone + ", '" + user.Email + "', '" + user.Address + "')";

            statement.execute(sql);
            statement.close();
            
            return true;
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            return false;
        }
    }        
}
