package controlador;
import model.Jugador;
import model.JugadorDAO;
import vista.Vista;
import model.Model;

import java.sql.SQLException;
import java.util.Scanner;

public class Controlador {

    static Scanner scan =new Scanner(System.in);

    public static void menu() throws SQLException {

        Vista.mostrarMenu();
        String opcio;
        opcio = scan.nextLine();

        switch (opcio) {
            case "1":
                Model.consultarJugadorsEquip();
                break;
            case "2":
                Model.consultarEstadistiquesJugadors();
                break;
            case "3":
                //Cridar a llistar els partits d'un equio
                break;
            case "4":
                Jugador j = new Jugador(34567,1,"1","1","1","1",1,1, null);
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
                System.exit(0);
                break;
            default:
                Vista.missatgeError();
                menu();

        }

    }

}