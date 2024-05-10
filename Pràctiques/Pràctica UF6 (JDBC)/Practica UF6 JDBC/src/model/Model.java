package model;
import vista.Vista;
import java.math.BigInteger;
import java.util.Scanner;
import java.sql.*;

public class Model {
    static Scanner scan = new Scanner(System.in);

    public static void consultarJugadorsEquip() throws SQLException {

        try {

            BigInteger equipId;
            System.out.print("Introdueix el equip que vols llistar tots els jugadors: ");
            equipId = BigInteger.valueOf(scan.nextInt());

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT nom, cognom FROM jugadors WHERE equip_id = " + equipId + ";");

            ResultSet resultSet = stmt.executeQuery();

            Vista.imprimirJugadorsEquips(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
