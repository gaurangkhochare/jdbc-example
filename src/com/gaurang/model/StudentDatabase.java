/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gaurang.model;

import java.sql.*;

/**
 * Uses the MySQL JDBC Driver to communicate with the MySQL Server.
 *
 * @author Gaurang Khochare
 */
public class StudentDatabase {

    static Connection con;
    String url = "jdbc:mysql://localhost:3306/college", username = "root", password = "gaurang";

    String insertQuery = "INSERT INTO student_details (rollno, student_name, department, semester, mobileno, course)"
            + " VALUES (?, ?, ?, ?, ?, ?);";
    
    String deleteQuery = "DELETE FROM student_details WHERE rollno = ?;";

    public StudentDatabase() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
    }

    public void addStudent(String rollNo, String name, String deparment, String semester, String mobileNo, String course) 
            throws SQLException {
        PreparedStatement ps = con.prepareStatement(insertQuery);
        
        ps.setString(1, rollNo);
        ps.setString(2, name);
        ps.setString(3, deparment);
        ps.setString(4, semester);
        ps.setString(5, mobileNo);
        ps.setString(6, course);
        
        ps.executeUpdate();
        ps.close();
    }
    
    public void deleteStudent(String rollNo) throws SQLException {
        PreparedStatement ps = con.prepareStatement(deleteQuery);
        
        ps.setString(1, rollNo);
        ps.executeUpdate();
        ps.close();
    }
    
    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
