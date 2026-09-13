import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

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

    String searchQuery = "SELECT * FROM student_details WHERE rollno = ?;";
    
    /**
     * Creates new Connection with MySQL Server
     * 
     * @throws SQLException 
     */
    public StudentDatabase() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
    }

    /**
     * Inserts data of new student in the database table
     * 
     * @param rollno Roll Number
     * @param name Student's Name
     * @param deparment
     * @param semester
     * @param mobileNo
     * @param course 
     */
    public void addStudent(String rollno, String name, String deparment, int semester, String mobileNo, String course) {
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setString(1, rollno);
            ps.setString(2, name);
            ps.setString(3, deparment);
            ps.setInt(4, semester);
            ps.setString(5, mobileNo);
            ps.setString(6, course);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Deletes data of student having roll number 'rollno'
     * 
     * @param rollno Student's Roll Number
     */
    public void deleteStudent(String rollno) {
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            ps.setString(1, rollno);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Get data of student of given roll number
     * 
     * @param rollno Student's Roll Number
     * @return details of student in form of Java Swing DefaultTableModel
     */
    public DefaultTableModel getStudentData(String rollno) {
        try (PreparedStatement ps = con.prepareStatement(searchQuery)) {
            ps.setString(1, rollno);

            ResultSet rs = ps.executeQuery();
            int cols = rs.getMetaData().getColumnCount();
            
            Vector<String> columns = new Vector<String>();
            for (int i = 1; i <= cols; i++)
                columns.add(rs.getMetaData().getColumnName(i));
            
            Vector<Vector<Object>> data = new Vector<>();
            while(rs.next()) {
                Vector<Object> rows = new Vector<>();
                for (int i = 1; i <= cols; i++) {
                    rows.add(rs.getObject(i));
                }
                
                data.add(rows);
            }
            
            return new DefaultTableModel(data, columns);
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return null;
    }

    /**
     * Get data of all students
     * 
     * @return details of all students in form of Java Swing DefaultTableModel
     */
    public DefaultTableModel getAllData() {
        String query = "SELECT * FROM student_details;";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            int cols = rs.getMetaData().getColumnCount();
            
            Vector<String> columns = new Vector<>();
            for (int i = 1; i <= cols; i++) {
                columns.add(rs.getMetaData().getColumnName(i));
            }
            
            Vector<Vector<Object>> data = new Vector<>();
            while(rs.next()) {
                Vector<Object> rows = new Vector<>();
                for (int i = 1; i <= cols; i++) {
                    rows.add(rs.getObject(i));
                }
                
                data.add(rows);
            }
            
            return new DefaultTableModel(data, columns);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return null;
    }

    /**
     * Closes the MySQL Server connection
     */
    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
