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


    public static void consultarJugadorsEquip(String nomEquip) throws SQLException {

        try {

            String bool = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT equip_id FROM equips WHERE nom = \"" + nomEquip + "\"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                bool = resultSet.getString("equip_id");
            }

            if (bool.isBlank()) {

                System.out.println("Equip inexistent!");
            } else {

                System.out.println("Equip trobat, llistant jugadors...");
                PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM jugadors WHERE equip_id = " + bool);

                ResultSet resultSet2 = stmt2.executeQuery();

                Vista.imprimirJugadorsEquips(resultSet2);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }

    public static void consultarEstadistiquesJugadors(String nomJugador, String cognomJugador) throws SQLException {

        try {

            String bool = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT jugador_id FROM jugadors WHERE nom = \"" + nomJugador + "\" AND cognom = \"" + cognomJugador + "\"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                bool = resultSet.getString("jugador_id");
            }

            if (bool.isBlank()) {

                System.out.println("Jugador inexistent!");

            } else {

                System.out.println("Jugador trobat, consultant estadistiques...");

                PreparedStatement stmt2 = connection.prepareStatement("SELECT j.jugador_id, j.nom , avg(ej.punts) AS \"Punts promig\", \n" +
                        "\t\t\t\t\t\t\t avg(ej.rebots_ofensius + ej.rebots_defensius) AS \"Rebots promig\",\n" +
                        "\t\t\t\t\t\t\t avg(ej.assistencies) AS \"Assistencies promig\"\n" +
                        "\tFROM estadistiques_jugadors ej\n" +
                        "INNER JOIN jugadors j ON j.jugador_id = ej.jugador_id\n" +
                        "WHERE j.nom = \"" + nomJugador + "\" AND j.cognom = \"" + cognomJugador + "\"" +
                        " GROUP BY ej.jugador_id;");

                ResultSet resultSet2 = stmt2.executeQuery();

                Vista.imprimirEstadisticsJugador(resultSet2);
            }


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

                System.out.println("Inserint jugador a la bd...");
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

        try {
            int jugadorId = 0;
            String nom = "";
            String cognom = "";
            Date data_naix = Date.valueOf("1990-01-10");
            float alcada = 0;
            float pes = 0;
            int dorsal = 0;
            String posicio = "";
            String bool = "";


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

            if (bool.isBlank()) {
                System.out.println("Jugador inexistent!");
            } else {

                Jugador j2 = new Jugador(jugadorId, nouEquipId, nom, cognom, dorsal, posicio, pes, alcada, data_naix);
                JugadorDAO daojug = new JugadorDAO(con);
                System.out.println("Canviant al jugador d'equip...");
                daojug.update(j2);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }

    // Meterselo en el readme (explicar el procedimiento que sigue)
    public static void modEstadistiques(String nom, String cognom) throws SQLException {

        try {

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

                System.out.println("Jugador trobat!");
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }

    public static void canviarFranquicia(String nom, String nouNomEquip) throws SQLException {

        try {

            int equip_id = 0;
            int perdudes = 0;
            int guanyades = 0;
            String ciutat = "";
            String acronim = "";
            String divisio = "";

            String bool = "";

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM equips WHERE nom = \"" + nom + "\"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                equip_id = resultSet.getInt("equip_id");
                guanyades = resultSet.getInt("guanyades");
                perdudes = resultSet.getInt("perdudes");
                ciutat = resultSet.getString("ciutat");
                acronim = resultSet.getString("acronim");
                divisio = resultSet.getString("divisio");

                bool = resultSet.getString("equip_id");
            }

            if (bool.isBlank()) {
                System.out.println("Equip inexistent!");
                Controlador.menu();
            } else {
                System.out.println("Equip trobat, canviant nom...");
                EquipDAO equipsFranc = new EquipDAO();
                Equip e1 = new Equip(equip_id, guanyades, perdudes, ciutat, nouNomEquip, divisio, acronim);

                equipsFranc.update(e1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }
    public static void moureJugadorHistoric(String nom, String cognom) throws SQLException {

        PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS historic(" +
                "jugador_id INT NOT NULL PRIMARY KEY," +
                "partit_id INT," +
                "minuts_jugats DECIMAL," +
                "punts TINYINT" +
                "tirs_anotats TINYINT" +
                "tirs_tirats TINYINT" +
                "tris_triples_anotats TINYINT" +
                "tris_triples_anotats TINYINT" +
                "tirs_lliures_anotats TINYINT" +
                "tirs_lliures_tirats TINYINT" +
                "rebots_ofensius TINYINT" +
                "rebots_defensius TINYINT" +
                "assistencies TINYINT" +
                "robades TINYINT" +
                "bloqueigs TINYINT);"
        );
        ps.executeUpdate();
        ps.close();

    }
}
