package model;

import controlador.Controlador;
import vista.Vista;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Model {

    public static Jugador j1 = new Jugador(0, 0, null, null, 0, null, 0, 0, null);
    static Scanner scan = new Scanner(System.in);
    static Connection con;
    public static int nouEquipId;
    public static String nomTemp;
    public static String cognomTemp;
    public static int[] statsTemp = new int[14];
    public static double tempsTemp;


    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


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
            String jugadorCognom;
            String jugadorNom;
            System.out.print("Introdueix el jugador del que vols trobar les dades");
            System.out.print("Nom: ");
            jugadorNom = scan.nextLine();
            System.out.print("Cognom: ");
            jugadorCognom = scan.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT j.jugador_id, j.nom , avg(ej.punts) AS \"Punts promig\", \n" +
                    "\t\t\t\t\t\t\t avg(ej.rebots_ofensius + ej.rebots_defensius) AS \"Rebots promig\",\n" +
                    "\t\t\t\t\t\t\t avg(ej.assistencies) AS \"Assistencies promig\"\n" +
                    "\tFROM estadistiques_jugadors ej\n" +
                    "INNER JOIN jugadors j ON j.jugador_id = ej.jugador_id\n" +
                    "WHERE j.nom = \"" + jugadorNom + "\" AND j.cognom = \"" + jugadorCognom +
                    "GROUP BY ej.jugador_id;");

            ResultSet resultSet = stmt.executeQuery();

            Vista.imprimirEstadisticsJugador(resultSet);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }

    }

    public static void mostrarEstadistiquesPartits() {


    }

    public static void inserirJugador() throws SQLException {

        try {
            String resultsetv2 = "";

            Controlador.inserirDades();

            int jugadorId;

            jugadorId = j1.getJugador_id();

            JugadorDAO daojug = new JugadorDAO(con);


            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM jugadors WHERE jugador_id = " + jugadorId + ";");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                resultsetv2 = resultSet.getString("jugador_id");
            }

            if (resultsetv2.isBlank()) {

                daojug.create(j1);
                System.out.println("Jugador inserit a la BD.");

            } else {

                int nouEquip = 0;
                int bool;
                System.out.print("Aquest jugador ja existeix a la BD, vols canviar-lo d'equip?:(1=Si,0=No) ");
                bool = scan.nextInt();
                scan.nextLine();
                if (bool == 1) {

                    Controlador.canviarEquip();

                } else {

                    Controlador.menu();

                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }

    public static void canviarJugadorEquip(String nom1, String cognom2, int equipId) throws SQLException {

        int jugadorId = 0;
        String nom = "";
        String cognom = "";
        Date data_naix = Date.valueOf("1990-01-10");
        float alcada = 0;
        float pes = 0;
        int dorsal = 0;
        String posicio = "";
        String bool= "";


        Controlador.canviarEquip();

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM jugadors WHERE nom = \"" + nomTemp + "\" AND cognom = \"" + cognomTemp + "\";");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            jugadorId = resultSet.getInt("jugador_id");
            nom = resultSet.getString("nom");
            cognom = resultSet.getString("cognom");
            data_naix = Date.valueOf(resultSet.getString("data_naixement"));
            alcada = resultSet.getInt("alcada");
            pes = resultSet.getInt("pes");
            dorsal = resultSet.getInt("dorsal");
            posicio = resultSet.getString("posicio");

        }

        if (bool.isBlank()){
            System.out.println("Jugador inexistent!");
        } else {

            Jugador j2 = new Jugador(jugadorId, nouEquipId, nom, cognom, dorsal, posicio, pes, alcada, data_naix);
            JugadorDAO daojug = new JugadorDAO(con);
            System.out.println("Canviant al jugador d'equip...");
            daojug.update(j2);
        }

    }

    // Meterselo en el readme (explicar el procedimiento que sigue)
    public static void modEstadistiques(String nom, String cognom) throws SQLException {

        int jugadorId = 0;
        String bool = "";


        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        PreparedStatement stmt = connection.prepareStatement("SELECT jugador_id FROM jugadors WHERE nom = \"" + nom + "\" AND cognom = \"" + cognom + "\";");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            jugadorId = resultSet.getInt("jugador_id");
            bool = resultSet.getString("jugador_id");
        }

        if (bool.isBlank()) {
            System.out.println("Jugador inexistent!");
            Controlador.menu();
        } else {
            Controlador.introduirDades(jugadorId);

            estadistiques_jugadors e1 = new estadistiques_jugadors(statsTemp[0],
                    statsTemp[1],
                    statsTemp[2],
                    statsTemp[3],
                    statsTemp[4],
                    statsTemp[5],
                    statsTemp[6],
                    statsTemp[7],
                    statsTemp[8],
                    statsTemp[9],
                    statsTemp[10],
                    statsTemp[11],
                    statsTemp[12],
                    statsTemp[13],
                    tempsTemp);

            estadistiques_jugadorsDAO estatsjug = new estadistiques_jugadorsDAO();

            estatsjug.create(e1);
        }
    }
    public static void canviarFranquicia(String nomEquip, String nouNomEquip) throws SQLException {

        int equipId = 0;
        int guanyats = 0;
        int perduts = 0;
        String ciutat = "";
        String divisio = "";
        String acronim = "";



        String bool = "";

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM equips WHERE nom = \"" + nomEquip +"\"");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            equipId = resultSet.getInt("equip_id");
            guanyats = resultSet.getInt("guanyades");
            perduts = resultSet.getInt("perdudes");
            ciutat = resultSet.getString("ciutat");
            divisio = resultSet.getString("divisio");
            acronim = resultSet.getString("acronim");

            bool = resultSet.getString("equip_id");
        }

        if (bool.isBlank()){

            System.out.println("Equip inexistent!");
        } else {

            EquipDAO equipsFranc = new EquipDAO();
            Equip e1 = new Equip(equipId,guanyats,perduts,ciutat,nouNomEquip,divisio,acronim);

            equipsFranc.update(e1);

        }


    }
}
