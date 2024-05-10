import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    
    public static void main(String[] args) {
    try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/rrhh","perepi","pastanaga");
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from empleats");

            while (resultSet.next()){
                System.out.println(resultSet.getString("salari"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}