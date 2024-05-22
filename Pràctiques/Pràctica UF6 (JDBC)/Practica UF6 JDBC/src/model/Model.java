package model;
import controlador.Controlador;
import vista.Vista;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Model {

    public static Jugador j1 = new Jugador(0,0,null,null,0,null,0,0,null);
    static Scanner scan = new Scanner(System.in);
    static Connection con;
    static {
        try {con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
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

        } catch  (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }

    }

    public static void mostrarEstadistiquesPartits(){



    }

    public static void inserirJugador() throws SQLException {

        try {
            String resultsetv2 = "";

            Controlador.inserirDades();

            int jugadorId;

            jugadorId = j1.getJugador_id();

            JugadorDAO daojug = new JugadorDAO(con);



            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM jugadors WHERE jugador_id = " + jugadorId +";" );

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                resultsetv2 = resultSet.getString("jugador_id");
            }

            if (resultsetv2.isBlank()){

                daojug.create(j1);
                System.out.println("Jugador inserit a la BD.");

            } else {

                int nouEquip = 0;
                int bool;
                System.out.print("Aquest jugador ja existeix a la BD, vols canviar-lo d'equip?:(1=Si,0=No) ");
                bool = scan.nextInt();
                scan.nextLine();
                if (bool == 1){

                   canviarJugadorEquip(nouEquip,jugadorId);



                } else {

                    Controlador.menu();

                }

            }

        } catch  (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }

    public static void canviarJugadorEquip(int equipId, int jugadorId) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        PreparedStatement stmt = connection.prepareStatement("UPDATE jugadors SET equip_id = "+equipId+ " WHERE jugador_id = "+jugadorId+";" );

        if(equipId == 0){

            String nom;
            String cognom;
            int equipId2;

            System.out.print("Quin jugador vols canviar de equip, introdueix nom i cognom: ");
            System.out.print("Nom");
            nom = scan.nextLine();
            System.out.print("Cognom;");
            cognom = scan.nextLine();

            /*PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM jugadors" +
                                                                            "WHERE nom = " + nom + "AND cognom = " + cognom + ";");
*/




        } else {


        }

    }

}
