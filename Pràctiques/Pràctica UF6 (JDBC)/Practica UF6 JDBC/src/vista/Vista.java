package vista;

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

}
