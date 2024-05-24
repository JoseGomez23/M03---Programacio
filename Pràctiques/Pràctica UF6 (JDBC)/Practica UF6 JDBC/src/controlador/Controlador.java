package controlador;
import model.Jugador;
import model.JugadorDAO;
import vista.Vista;
import model.Model;

import java.sql.Date;
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
                Model.inserirJugador();
                break;
            case "5":
                Model.canviarJugadorEquip();
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

    public static Jugador inserirDades(){

        int jugadorId;
        String nom;
        String cognom;
        Date dataNaix;
        float alcada;
        float pes;
        int dorsal;
        String posicio;
        int equipId;

        System.out.print("Jugador Id: ");
        jugadorId = scan.nextInt();
        scan.nextLine();
        System.out.print("Nom: ");
        nom = scan.nextLine();
        System.out.print("Cognom: ");
        cognom = scan.nextLine();
        System.out.print("Data naixement: ");
        dataNaix = Date.valueOf(scan.nextLine());
        System.out.print("Alçada: ");
        alcada = scan.nextFloat();
        System.out.print("Pes: ");
        pes = scan.nextFloat();
        System.out.print("Dorsal: ");
        dorsal = scan.nextInt();
        scan.nextLine();
        System.out.print("Posicio: ");
        posicio = scan.nextLine();
        System.out.print("Equip Id: ");
        equipId = scan.nextInt();
        scan.nextLine();

        Jugador j2 = new Jugador(jugadorId,equipId,nom,cognom,dorsal,posicio,pes,alcada,dataNaix);

        Model.j1 = j2;

        return Model.j1;
    }

    public static void cambiarEquip (){
        String nom;
        String cognom;
        int equipId;

        System.out.println("Quin jugador vols canviar de equip, introdueix nom i cognom: ");
        System.out.print("Nom: ");
        nom = scan.nextLine();
        System.out.print("Cognom: ");
        cognom = scan.nextLine();
        System.out.println("EquipId: ");
        equipId = scan.nextInt();
        scan.nextLine();

        Model.nouEquipId = equipId;
        Model.nomTemp = nom;
        Model.cognomTemp = cognom;
    }

    public static void modificarEstadistiques(){

        String nom;
        String cognom;


        System.out.println("Quin jugador vols canviar de equip, introdueix nom i cognom: ");
        System.out.print("Nom: ");
        nom = scan.nextLine();
        System.out.print("Cognom: ");
        cognom = scan.nextLine();

        scan.nextLine();
        Model.nomTemp = nom;
        Model.cognomTemp = cognom;

    }

    public static void introduirDades(){


    }
}