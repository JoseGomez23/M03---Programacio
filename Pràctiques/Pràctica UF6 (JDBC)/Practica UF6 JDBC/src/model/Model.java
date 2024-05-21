package model;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import controlador.Controlador;
import vista.Vista;

import javax.sound.midi.Soundbank;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.sql.*;
import java.util.spi.AbstractResourceBundleProvider;

public class Model {
    static Scanner scan = new Scanner(System.in);

    static {
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
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
            String jugadorNom;
            System.out.print("Introdueix el jugador(nom) del que vols trobar les dades:  ");
            jugadorNom = scan.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT j.jugador_id, j.nom , avg(ej.punts) AS \"Punts promig\", \n" +
                    "\t\t\t\t\t\t\t avg(ej.rebots_ofensius + ej.rebots_defensius) AS \"Rebots promig\",\n" +
                    "\t\t\t\t\t\t\t avg(ej.assistencies) AS \"Assistencies promig\"\n" +
                    "\tFROM estadistiques_jugadors ej\n" +
                    "INNER JOIN jugadors j ON j.jugador_id = ej.jugador_id\n" +
                    "WHERE j.nom = \"" + jugadorNom + "\"" +
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
            int jugadorId;
            String nom;
            String cognom;
            Date dataNaix;
            float alcada;
            float pes;
            int dorsal;
            String posicio;
            int equipId;

            String resultsetv2 = "";

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

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM jugadors WHERE jugador_id = " + jugadorId +";" );

            ResultSet resultSet = stmt.executeQuery();


            while (resultSet.next()) {
                resultsetv2 = resultSet.getString("jugador_id");
            }

            if (resultsetv2.isBlank()){

                PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO jugadors VALUES ("+ jugadorId +"," +
                        "\"" + nom + "\"," +
                        "\"" + cognom + "\"," +
                        "\"" + dataNaix + "\"," +
                               pes+"," +
                               alcada+"," +
                               dorsal+"," +
                        "\"" + posicio + "\"," +
                               equipId+");");

                insertStmt.executeUpdate();
                System.out.println("Jugador inserit a la BD.");

            } else {

                int nouEquip;
                int bool;
                System.out.print("Aquest jugador ja existeix a la BD, vols canviar-lo d'equip?:(1=Si,0=No) ");
                bool = scan.nextInt();
                scan.nextLine();
                if (bool == 1){

                    System.out.print("A quin equip el vols canviar?: ");
                    nouEquip = scan.nextInt();
                    scan.nextLine();


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


    }

}