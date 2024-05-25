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
                buscarEquipJugadors();
                break;
            case "2":
                buscarStatsJugador();
                break;
            case "3":
                //Cridar a llistar els partits d'un equio
                break;
            case "4":
                Model.inserirJugador();
                break;
            case "5":
                canviarEquip();
                break;
            case "6":
                //Cridar a actualitzar dades
                break;
            case "7":
                modificarEstadistiques();
                break;
            case "8":
                //Cridar a eliminar jugador
                break;
            case "9":
                preguntarEquip();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                Vista.missatgeError();
                menu();

        }

    }

    public static void buscarEquipJugadors() throws SQLException {

        String nom;
        System.out.print("Introdueix el nom del equip que vols llistar tots els jugadors: ");
        nom = scan.nextLine();
        System.out.println("Buscant equip a la bd...");
        Model.consultarJugadorsEquip(nom);

    }

    public static void buscarStatsJugador() throws SQLException {

        String jugadorCognom;
        String jugadorNom;
        System.out.println("Introdueix el jugador del que vols trobar les estadistiques");
        System.out.print("Nom: ");
        jugadorNom = scan.nextLine();
        System.out.print("Cognom: ");
        jugadorCognom = scan.nextLine();

        System.out.println("Buscant jugador a la bd...");
        Model.consultarEstadistiquesJugadors(jugadorNom,jugadorCognom);

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

    public static void canviarEquip () throws SQLException {
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

        Model.canviarJugadorEquip(nom, cognom,equipId);
    }

    public static void modificarEstadistiques() throws SQLException {

        String nom;
        String cognom;

        System.out.println("A quin jugador vols modificar-li les estadistiques: ");
        System.out.print("Nom: ");
        nom = scan.nextLine();
        System.out.print("Cognom: ");
        cognom = scan.nextLine();

        System.out.println("Buscant jugador a la bd...");
        Model.modEstadistiques(nom,cognom);

    }

    public static void introduirDades(int jugador_id){

        Model.statsTemp[0] = jugador_id;
        System.out.print("Introdueix l'id del partit que ha jugat el jugador: ");
        Model.statsTemp[1] = scan.nextInt();
        System.out.println("Auogenerant dades aleatories...");
        Model.statsTemp[2] = (int)(Math.random()*73+1);
        Model.statsTemp[3] = (int)(Math.random()*25+1);
        Model.statsTemp[4] = (int)(Math.random()*47+1);
        Model.statsTemp[5] = (int)(Math.random()*12+1);
        Model.statsTemp[6] = (int)(Math.random()*32+1);
        Model.statsTemp[7] = (int)(Math.random()*24+1);
        Model.statsTemp[8] = (int)(Math.random()*32+1);
        Model.statsTemp[9] = (int)(Math.random()*15+1);
        Model.statsTemp[10] = (int)(Math.random()*20+1);
        Model.statsTemp[11] = (int)(Math.random()*23+1);
        Model.statsTemp[12] = (int)(Math.random()*7+1);
        Model.statsTemp[13] = (int)(Math.random()*10+1);

        Model.tempsTemp = (Math.random()*48+1);
    }

    public static void preguntarEquip() throws SQLException {

        String nomEquip;
        String nouNomEquip;

        System.out.print("Introdueix el nom de l'equip que vols canviar: ");
        nomEquip = scan.nextLine();
        System.out.print("Nom que substituira a l'equip: ");
        nouNomEquip = scan.nextLine();
        System.out.println("Buscant l'equip a la bd...");
        Model.canviarFranquicia(nomEquip, nouNomEquip);

    }
}