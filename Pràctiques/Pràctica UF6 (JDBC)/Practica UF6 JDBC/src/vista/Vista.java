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



    public static void imprimirPartits(ResultSet resultSet, ResultSet resultSet2) throws SQLException {



        while (resultSet.next()){

            System.out.println("Ciutat|Equip|Resultat|VS|Resultat|Ciutat|Equip");

            System.out.print(resultSet.getString("ciutat"));
            System.out.print(" ");
            System.out.print(resultSet.getString("nom"));
            System.out.print(" ");
            System.out.print(resultSet.getString("resultat"));
            System.out.print(" ");
            System.out.print(resultSet.getString("matx"));
            System.out.print(" ");

            while (resultSet2.next()) {

                System.out.print(resultSet2.getString("resultat"));
                System.out.print(" ");
                System.out.print(resultSet2.getString("ciutat"));
                System.out.print(" ");
                System.out.print(resultSet2.getString("nom"));
                System.out.println();
                break;
            }
        }
    }

    public static void imprimirmecagoentodo(){


    }

    public static void mostrarMissatgesFitxers() {
        System.out.println("S'actualitzaran les dades del jugador_id = 200768 i partit_id = 22300003");
        System.out.println("i també s'actualitzaran les dades del jugadoor_id = 201142 i partit_id = 22300236");
        System.out.println("a la taula de estadistiues_jugadors");
    }

    public static void missatgeError() {
        System.out.println("Hi ha hagut un error, torna a introduir la teva opció");
    }

    public static void arxiuCSV() {
        System.out.println("Arxiu CSV generat correctament.");
    }
}