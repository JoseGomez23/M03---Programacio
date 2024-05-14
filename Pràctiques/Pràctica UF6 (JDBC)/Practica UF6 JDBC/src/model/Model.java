package model;
import controlador.Controlador;
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
        } finally {

            Controlador.menu();
        }
    }

    public static void consultarEstadistiquesJugadors() throws SQLException {

        try {
            String jugadorNom;
            System.out.print("Introdueix el jugador(nom) del que vols trobar les dades:  ");
            jugadorNom = scan.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT j.jugador_id, j.nom , avg(ej.punts) AS \"Punts promig\", \n" +
                    "\t\t\t\t\t\t\t avg(ej.rebots_ofensius + ej.rebots_defensius) AS \"Rebots promig\",\n" +
                    "\t\t\t\t\t\t\t avg(ej.assistencies) AS \"Assistencies promig\"\n" +
                    "\tFROM estadistiques_jugadors ej\n" +
                    "INNER JOIN jugadors j ON j.jugador_id = ej.jugador_id\n" +
                    "WHERE j.nom = \"" + jugadorNom + "\"" +
                    "GROUP BY ej.jugador_id;");

            ResultSet resultSet = stmt.executeQuery();

            Vista.imprimirEstadisticsJugador(resultSet);
        } catch  (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }

    }

}
