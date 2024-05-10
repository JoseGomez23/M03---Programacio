package controlador;
import java.util.Scanner;

import model.Model;

import java.sql.SQLException;

public class Controlador {

    static Scanner scan =new Scanner(System.in);

    public static void menu() throws SQLException {

        String opcio;
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
        System.out.print("Opcio: ");
        opcio = scan.nextLine();

        switch (opcio) {
            case "1":
                Model.consultarJugadorsEquip();
                break;
            case "2":
                //Cridr a calcular la mitjana
                break;
            case "3":
                //Cridar a llistar els partits d'un equio
                break;
            case "4":
                //Cridar a inserir un jugador
                break;
            case "5":
                //Cridar a Trasspassar jugaror d'equip
                break;
            case "6":
                //Cridar a actualitzar dades
                break;
            case "7":
                //Cridar a modificar estadistiques
                break;
            case "8":
                //Cridar a eliminar jugador
                break;
            case "9":
                //Cridar a cambi de franquicia d'un eequip
                break;
            case "0":
                //Sortir del programa
                break;
            default:
                System.out.println("Opcio inexistent, torna a introduir una de valida");
                menu();
                break;
        }
    }
}
