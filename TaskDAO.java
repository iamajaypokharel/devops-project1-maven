import java.sql.*;

public class TaskDAO {

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/todoapp",
                "root",
                "your_password"
            );

            System.out.println("Connected to MySQL successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
