package vista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vista {

    public static void imprimirJugadorsEquips(ResultSet result) throws SQLException {

        while (result.next()) {
            System.out.print(result.getString("nom"));
            System.out.print(" ");
            System.out.println(result.getString("cognom"));
        }

    }

    public static void imprimirEstadisticsJugador(ResultSet result) throws SQLException {

        while (result.next()) {
            System.out.println("JugadorId: " + result.getString("jugador_id"));
            System.out.println("Punts promig: " + result.getString("Punts promig"));
            System.out.println("Rebots promig: " + result.getString("Rebots promig"));
            System.out.println("Assistencies promig: " + result.getString("Assistencies promig"));
        }

    }

}
