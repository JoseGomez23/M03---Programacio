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
            System.out.println("Introdueix el equip que vols llistar tots els jugadors");
            equipId = BigInteger.valueOf(scan.nextInt());

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nom, cognom FROM jugadors WHERE equip_id = " + equipId + ";");

            Vista.imprimirJugadorsEquips(resultSet);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
