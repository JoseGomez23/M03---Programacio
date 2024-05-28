package controlador;
import model.*;
import vista.Vista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controlador {

    static Scanner scan =new Scanner(System.in);

    public static void menu() throws SQLException {


        try {
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
                    buscarPartitsEquip();
                    break;
                case "4":
                    inserirDades();
                    break;
                case "5":
                    canviarEquip();
                    break;
                case "6":
                    actualitzarDadesFitxer();
                    break;
                case "7":
                    modificarEstadistiques();
                    break;
                case "8":
                    moureAHistoric();
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
        } catch (Exception e){

            System.out.println(e.getMessage());
        }

    }


    /******************** Excercici 1 *********************/
    public static void buscarEquipJugadors() throws SQLException {

        try {
            String nom;
            System.out.print("Introdueix el nom del equip que vols llistar tots els jugadors: ");
            nom = scan.nextLine();
            System.out.println("Buscant equip a la bd...");
            Model.consultarJugadorsEquip(nom);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }

    /******************* Excercici 2 ************************/
    public static void buscarStatsJugador() throws SQLException {

        try {
            String jugadorCognom;
            String jugadorNom;
            System.out.println("Introdueix el jugador del que vols trobar les estadistiques");
            System.out.print("Nom: ");
            jugadorNom = scan.nextLine();
            System.out.print("Cognom: ");
            jugadorCognom = scan.nextLine();

            System.out.println("Buscant jugador a la bd...");
            Model.consultarEstadistiquesJugadors(jugadorNom, jugadorCognom);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }

    /******************** Excercici 3 *********************/
    public static void buscarPartitsEquip() throws SQLException {

        try {
            String nomEquip;
            System.out.println("A quin equips vols llistar els partits?: ");
            System.out.println("Introdueix el nom de l'equip(ex. Bulls): ");
            nomEquip = scan.nextLine();

            System.out.println("Buscant equip a la bd...");
            Model.mostrarEstadistiquesPartits(nomEquip);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }



    /******************* Excercici 4 ********************/
    public static void inserirDades() throws SQLException {

        try {
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

            Jugador j2 = new Jugador(jugadorId, equipId, nom, cognom, dorsal, posicio, pes, alcada, dataNaix);

            System.out.println("Inserint jugador...");
            Model.inserirJugador(j2);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }

    /*********************** Excercici 5 *****************/
    public static void canviarEquip () throws SQLException {

        try {
            String nom;
            String cognom;
            int equipId;

            System.out.println("Quin jugador vols canviar de equip, introdueix nom i cognom: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            System.out.print("Cognom: ");
            cognom = scan.nextLine();
            System.out.print("EquipId: ");
            equipId = scan.nextInt();
            scan.nextLine();
            System.out.println("Buscant el jugador a la bd...");

            Model.canviarJugadorEquip(nom, cognom, equipId);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }

    /******************** Excercici 6 *********************/
    public static void actualitzarDadesFitxer() {

        Vista.mostrarMissatgesFitxers();
        Model.actualitzarDadesEstadistiques();
        //TODO: El alvaro es una maquina

    }

    /******************** Excercici 7 *********************/
    public static void modificarEstadistiques() throws SQLException {

        try {
            String nom;
            String cognom;

            System.out.println("A quin jugador vols modificar-li les estadistiques: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            System.out.print("Cognom: ");
            cognom = scan.nextLine();

            System.out.println("Buscant jugador a la bd...");
            Model.modEstadistiques(nom, cognom);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }


    public static void introduirDades(int jugador_id){

        try {
            Model.statsTemp[0] = jugador_id;
            System.out.print("Introdueix l'id del partit que ha jugat el jugador: ");
            Model.statsTemp[1] = scan.nextInt();
            System.out.print("Introdueix els punts: ");
            Model.statsTemp[2] = scan.nextInt();
            System.out.print("Introdueix els minuts: ");
            Model.statsTemp[3] = scan.nextInt();
            System.out.print("Introdueix els tirs anotats: ");
            Model.statsTemp[4] = scan.nextInt();
            System.out.print("Introdueix els tirs tirats: ");
            Model.statsTemp[5] = scan.nextInt();
            System.out.print("Introdueix els tirs triples anotats: ");
            Model.statsTemp[6] = scan.nextInt();
            System.out.print("Introdueix els tirs triples tirats: ");
            Model.statsTemp[7] = scan.nextInt();
            System.out.print("Introdueix els tirs lliures anotats: ");
            Model.statsTemp[8] = scan.nextInt();
            System.out.print("Introdueix els tirs lliures tirats: ");
            Model.statsTemp[9] = scan.nextInt();
            System.out.print("Introdueix els rebots ofensius: ");
            Model.statsTemp[10] = scan.nextInt();
            System.out.print("Introdueix els rebots defensius: ");
            Model.statsTemp[11] = scan.nextInt();
            System.out.print("Introdueix les assistencies: ");
            Model.statsTemp[12] = scan.nextInt();
            System.out.print("Introdueix les robades: ");
            Model.statsTemp[13] = scan.nextInt();
            System.out.print("Introdueix els bloqueigs: ");
            Model.tempsTemp = scan.nextInt();
        } catch (InputMismatchException e){

            System.out.println("S'ha produit un error entrant dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }

    /****************** Excercici 8 *********************/
    public static void moureAHistoric() throws SQLException {

        try {
            String nom, cognom;
            System.out.println("Quin jugador vols cambiar a la taula històric? ");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            System.out.print("Cognom: ");
            cognom = scan.nextLine();
            System.out.println("Buscant jugador a la bd...");

            Model.crearTaulaHistoric();
            Model.moureJugadorHistoric(nom, cognom);
            Model.eliminarJugadorEstadistiques(nom, cognom);
            Model.eliminarJugador(nom, cognom);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }

    }

    /*************** Excercici 9 *********************/
    public static void preguntarEquip() throws SQLException {

        try {
            String nomEquip;
            String nouNomEquip;

            System.out.print("Introdueix el nom de l'equip que vols canviar: ");
            nomEquip = scan.nextLine();
            System.out.print("Franquicia que substituira a l'actual(Nom de la ciutat): ");
            nouNomEquip = scan.nextLine();
            System.out.println("Buscant l'equip a la bd...");
            Model.canviarFranquicia(nomEquip, nouNomEquip);
        } catch (SQLException e){
            System.out.println("Error al conectar a la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        }
    }


}