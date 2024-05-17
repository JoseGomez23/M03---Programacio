package vista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vista {
    public static void mostrarMenu() {
        System.out.println("| ------------------------------ |");
        System.out.println("| ---------    MENU    --------- |");
        System.out.println("| ------------------------------ |");
        System.out.println("| 1. Llistar jugador a un equip  |");
        System.out.println("| 2. Calcular mitjana (Punts)    |");
        System.out.println("| 3. Llistar partits (Equip)     |");
        System.out.println("| 4. Inserir jugador a un equip  |");
        System.out.println("| 5. Trasspassar jugador d'equip |");
        System.out.println("| 6. Actualitzar dades           |");
        System.out.println("| 7. Modificar estadistiques     |");
        System.out.println("| 8. Eliminar jugador            |");
        System.out.println("| 9. Cambi de franquicia (Equip) |");
        System.out.println("| 0. Sortir <-- Default option   |");
        System.out.println("| ------------------------------ |");
        System.out.print("Introdueix la opció: ");
    }


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

    public static void missatgeError() {
        System.out.println("Hi ha hagut un error, torna a introduir la teva opció");
    }

}