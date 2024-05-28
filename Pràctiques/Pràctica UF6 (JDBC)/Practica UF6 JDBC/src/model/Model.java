package model;

import controlador.Controlador;
import vista.Vista;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.sql.*;

public class Model {

    //Definir variables globals
    static Scanner scan = new Scanner(System.in);
    static Connection con;
    public static int[] statsTemp = new int[14];
    public static double tempsTemp;


    //Conexió a la base de dades
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

            PreparedStatement stmt = con.prepareStatement("SELECT equip_id FROM equips WHERE nom = \"" + nomEquip + "\"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                bool = resultSet.getString("equip_id");
            }

            if (bool.isBlank()) {

                System.out.println("Equip inexistent!");
            } else {

                System.out.println("Equip trobat, llistant jugadors...");
                PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM jugadors WHERE equip_id = " + bool);

                ResultSet resultSet2 = stmt2.executeQuery();

                Vista.imprimirJugadorsEquips(resultSet2);
            }
        } catch (SQLException e) {
            System.out.println("Hi ha hagut en error amb la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        } finally {

            Controlador.menu();
        }
    }
    /*************************** EX 2 ***************************/

    public static void consultarEstadistiquesJugadors(String nomJugador, String cognomJugador) throws SQLException {

        try {

            String bool = "";

            PreparedStatement stmt = con.prepareStatement("SELECT jugador_id FROM jugadors WHERE nom = \"" + nomJugador + "\" AND cognom = \"" + cognomJugador + "\"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                bool = resultSet.getString("jugador_id");
            }

            if (bool.isBlank()) {

                System.out.println("Jugador inexistent!");

            } else {

                System.out.println("Jugador trobat, consultant estadistiques...");

                PreparedStatement stmt2 = con.prepareStatement("SELECT j.jugador_id, j.nom , avg(ej.punts) AS \"Punts promig\", \n" +
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
            System.out.println("Hi ha hagut en error amb la base de dades");
        } catch (Exception e) {
            Vista.missatgeError();
        } finally {

            Controlador.menu();
        }

    }

    /*************************** EX 3 ***************************/
    public static void mostrarEstadistiquesPartits(String nomEquip) throws SQLException {

        String bool = "";
        String acronim = "";

        PreparedStatement stmt = con.prepareStatement("SELECT equip_id, acronim FROM equips WHERE nom = \"" + nomEquip + "\";");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){

            bool = resultSet.getString("equip_id");
            acronim = resultSet.getString("acronim");
        }

        if (bool.isBlank()){

            System.out.println("Equip inexistent!");
        } else{

            System.out.println("Equip trobat, buscant partits...");
            mostrarPartitsEquipsPart2(acronim);
        }
    }

    public static void mostrarPartitsEquipsPart2(String acronim) throws SQLException {


        PreparedStatement stmt = con.prepareStatement("SELECT e.ciutat, e.nom, p.resultat, p.matx\n" +
                " FROM partits p\n" +
                " INNER JOIN equips e ON p.equip_id = e.equip_id\n" +
                " WHERE p.matx LIKE \""+ acronim +"%\"" +
                " ORDER BY partit_id;");


        PreparedStatement stmt2 = con.prepareStatement("SELECT e.ciutat, e.nom, p.resultat, p.matx\n" +
                "FROM partits p\n" +
                " INNER JOIN equips e ON p.equip_id = e.equip_id\n" +
                " WHERE p.matx LIKE \"%"+ acronim +"\"" +
                " ORDER BY partit_id;");
        ResultSet resultSet = stmt.executeQuery();
        ResultSet resultSet2 = stmt2.executeQuery();

        Vista.imprimirPartits(resultSet,resultSet2);

    }
    /*************************** EX 4 ***************************/
    public static void inserirJugador(Jugador j1) throws SQLException {

        try {
            String resultsetv2 = "";

            int jugadorId;

            jugadorId = j1.getJugador_id();

            JugadorDAO daojug = new JugadorDAO(con);


            PreparedStatement stmt = con.prepareStatement("SELECT * FROM jugadors WHERE jugador_id = " + jugadorId + ";");

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

    /*************************** EX 5 ***************************/
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

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM jugadors WHERE nom = \"" + nom1 + "\" AND cognom = \"" + cognom2 + "\";");

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

                bool = resultSet.getString("jugador_id");

            }

            if (bool.isBlank()) {
                System.out.println("Jugador inexistent!");
            } else {

                Jugador j2 = new Jugador(jugadorId, equipId, nom, cognom, dorsal, posicio, pes, alcada, data_naix);
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

    /*************************** EX 6 ***************************/
    public static void actualitzarDadesEstadistiques() {

        File arxiuDades = new File("./Pràctiques/Pràctica UF6 (JDBC)/Practica UF6 JDBC/src/arxiusDades/actualitzarDades.csv");
        int jugador_id = 0;
        int partit_id = 0;
        float minuts_jugats = 0;
        int punts = 0;
        int tirs_anotats =0;
        int tirs_tirats = 0;
        int tirs_triples_anotats = 0;
        int tirs_triples_tirats = 0;
        int tirs_lliures_anotats = 0;
        int tirs_lliures_tirats = 0;
        int rebots_ofensius = 0;
        int rebots_defensius = 0;
        int assistencies = 0;
        int robades = 0;
        int bloqueigs = 0;


        try {
            FileReader fr = new FileReader(arxiuDades);
            BufferedReader br = new BufferedReader(fr);
            String linia = "";

            while ((linia = br.readLine()) != null) {
                String [] camps = linia.split(";");

                jugador_id = Integer.parseInt(camps[0]);
                partit_id = Integer.parseInt(camps[1]);
                minuts_jugats = Float.parseFloat(camps[2]);
                punts = Integer.parseInt(camps[3]);
                tirs_anotats = Integer.parseInt(camps[4]);
                tirs_tirats = Integer.parseInt(camps[5]);
                tirs_triples_anotats = Integer.parseInt(camps[6]);
                tirs_triples_tirats = Integer.parseInt(camps[7]);
                tirs_lliures_anotats = Integer.parseInt(camps[8]);
                tirs_lliures_tirats = Integer.parseInt(camps[9]);
                rebots_ofensius = Integer.parseInt(camps[10]);
                rebots_defensius = Integer.parseInt(camps[11]);
                assistencies = Integer.parseInt(camps[12]);
                robades = Integer.parseInt(camps[13]);
                bloqueigs = Integer.parseInt(camps[14]);

                estadistiques_jugadors ej = new estadistiques_jugadors(jugador_id, partit_id, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs, minuts_jugats);
                estadistiques_jugadorsDAO ejupdate = new estadistiques_jugadorsDAO();
                ejupdate.update(ej);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*************************** EX 7 ***************************/
    public static void modEstadistiques(String nom, String cognom) throws SQLException {

        try {

            int jugadorId = 0;
            String bool = "";


            PreparedStatement stmt = con.prepareStatement("SELECT jugador_id FROM jugadors WHERE nom = \"" + nom + "\" AND cognom = \"" + cognom + "\";");

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

                estatsjug.update(e1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }

    /*************************** EX 9 ***************************/
    public static void canviarFranquicia(String nom, String nouNomCiutat) throws SQLException {

        try {

            int equip_id = 0;
            int perdudes = 0;
            int guanyades = 0;
            String acronim = "";
            String divisio = "";

            String bool = "";

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM equips WHERE nom = \"" + nom + "\"");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                equip_id = resultSet.getInt("equip_id");
                guanyades = resultSet.getInt("guanyades");
                perdudes = resultSet.getInt("perdudes");
                acronim = resultSet.getString("acronim");
                nom = resultSet.getString("nom");
                divisio = resultSet.getString("divisio");

                bool = resultSet.getString("equip_id");
            }

            if (bool.isBlank()) {
                System.out.println("Equip inexistent!");
                Controlador.menu();
            } else {
                System.out.println("Equip trobat, canviant franquicia...");
                EquipDAO equipsFranc = new EquipDAO();
                Equip e1 = new Equip(equip_id, guanyades, perdudes, nouNomCiutat, nom, divisio, acronim);

                equipsFranc.update(e1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            Controlador.menu();
        }
    }

    /*************************** EX 8 ***************************/
    public static void crearTaulaHistoric() throws SQLException {

        PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS historic(" +
                "jugador_id INT UNSIGNED," +
                "partit_id INT UNSIGNED, " +
                "minuts_jugats DECIMAL, " +
                "punts TINYINT, " +
                "tirs_anotats TINYINT, " +
                "tirs_tirats TINYINT, " +
                "tirs_triples_anotats TINYINT, " +
                "tirs_triples_tirats TINYINT, " +
                "tirs_lliures_anotats TINYINT, " +
                "tirs_lliures_tirats TINYINT, " +
                "rebots_ofensius TINYINT, " +
                "rebots_defensius TINYINT, " +
                "assistencies TINYINT, " +
                "robades TINYINT, " +
                "bloqueigs TINYINT);"
        );
        ps.executeUpdate();
        ps.close();

    }

    public static void eliminarJugadorEstadistiques(String nom, String cognom) throws SQLException {

        int jugador_id = 0;
        int partit_id = 0;
        float minuts_jugats = 0;
        int punts = 0;
        int tirs_anotats = 0;
        int tirs_tirats = 0;
        int tirs_lliures_anotats = 0;
        int tirs_triples_anotats = 0;
        int tirs_triples_tirats = 0;
        int tirs_lliures_tirats = 0;
        int rebots_ofensius = 0;
        int rebots_defensius = 0;
        int assistencies = 0;
        int robades = 0;
        int bloqueigs = 0;
        String bool = "";

        PreparedStatement stmt = con.prepareStatement("SELECT jugador_id FROM jugadors WHERE nom = \"" + nom + "\" AND cognom = \"" + cognom + "\";");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){

            jugador_id = resultSet.getInt("jugador_id");
            bool = resultSet.getString("jugador_id");
        }

        if (bool.isBlank()){

            System.out.println("Jugador inexistent!");
        } else {

            System.out.println("Jugador trobat, retirant-lo de estadistiques...");

            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM estadistiques_jugadors WHERE jugador_id = " + jugador_id +";");
            ResultSet resultSet2 = stmt2.executeQuery();

            HistoricDAO hdaomove = new HistoricDAO();

            while (resultSet2.next()){

                partit_id = resultSet2.getInt("partit_id");
                minuts_jugats = resultSet2.getFloat("minuts_jugats");
                punts = resultSet2.getInt("punts");
                tirs_anotats = resultSet2.getInt("tirs_anotats");
                tirs_tirats = resultSet2.getInt("tirs_tirats");
                tirs_triples_anotats = resultSet2.getInt("tirs_triples_anotats");
                tirs_triples_tirats = resultSet2.getInt("tirs_triples_tirats");
                tirs_lliures_anotats = resultSet2.getInt("tirs_lliures_anotats");
                tirs_lliures_tirats = resultSet2.getInt("tirs_lliures_anotats");
                rebots_ofensius = resultSet2.getInt("rebots_ofensius");
                rebots_defensius = resultSet2.getInt("rebots_defensius");
                assistencies = resultSet2.getInt("assistencies");
                robades = resultSet2.getInt("robades");
                bloqueigs = resultSet2.getInt("bloqueigs");

                Historic h1 = new Historic(jugador_id, partit_id, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs, minuts_jugats);
                hdaomove.create(h1);
            }

            estadistiques_jugadorsDAO ejeliminar = new estadistiques_jugadorsDAO();
            estadistiques_jugadors ej1 = new estadistiques_jugadors(jugador_id, partit_id, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs, minuts_jugats);
            ejeliminar.delete(ej1);

        }

    }

    public static void moureJugadorHistoric(String nom, String cognom) throws SQLException {


        int jugador_id = 0;
        int equip_id = 0;
        int dorsal = 0;
        String posicio = "";
        double pes = 0;
        double alcada = 0;
        Date data_naixement = Date.valueOf("1990-10-10");;

        PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS jugadorHistoric(" +
                " jugador_id INT," +
                " equip_id INT," +
                " dorsal INT," +
                " nom VARCHAR(150)," +
                " cognom VARCHAR(150)," +
                " posicio VARCHAR(25)," +
                " pes DECIMAL," +
                " alcada DECIMAL," +
                " data_naixement DATE);"
        );
        ps.executeUpdate();
        ps.close();


        PreparedStatement stmt = con.prepareStatement("SELECT * FROM jugadors WHERE nom = \"" + nom + "\" AND cognom = \"" + cognom + "\";");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){

            jugador_id = resultSet.getInt("jugador_id");
            data_naixement = resultSet.getDate("data_naixement");
            alcada = resultSet.getInt("alcada");
            pes = resultSet.getInt("pes");
            dorsal = resultSet.getInt("dorsal");
            posicio = resultSet.getString("posicio");

        }

        JugadorHistoricDAO daojughistoric = new JugadorHistoricDAO();

        JugadorHistoric j1 = new JugadorHistoric(jugador_id,equip_id,dorsal,nom,cognom,posicio,pes,alcada,data_naixement);
        daojughistoric.create(j1);

    }

    public static void eliminarJugador(String nom, String cognom) throws SQLException {

        int jugador_id = 0;
        int equip_id = 0;
        int dorsal = 0;
        String posicio = "";
        double pes = 0;
        double alcada = 0;
        Date data_naixement = Date.valueOf("1990-01-10");;
        String bool = "";

        PreparedStatement stmt = con.prepareStatement("SELECT jugador_id FROM jugadors WHERE nom = \"" + nom + "\" AND cognom = \"" + cognom + "\";");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){

            jugador_id = resultSet.getInt("jugador_id");
            bool = resultSet.getString("jugador_id");
        }

        if (bool.isBlank()){

            System.out.println("Jugador inexistent!");
        } else {

            System.out.println("Jugador trobat, retirant-lo de la taula jugadors...");

            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM jugadors WHERE jugador_id = " + jugador_id +";");
            ResultSet resultSet2 = stmt2.executeQuery();

            while (resultSet2.next()){

                jugador_id = resultSet2.getInt("jugador_id");
                equip_id = resultSet2.getInt("equip_id");
                dorsal = resultSet2.getInt("dorsal");
                posicio = resultSet2.getString("posicio");
                pes = resultSet2.getFloat("pes");
                alcada = resultSet2.getFloat("alcada");
                data_naixement = resultSet2.getDate("data_naixement");

            }

            JugadorDAO jeliminar = new JugadorDAO(con);
            Jugador j1 = new Jugador(jugador_id, equip_id, nom, cognom, dorsal, posicio, pes, alcada, data_naixement);
            jeliminar.delete(j1);

        }




    }
}
