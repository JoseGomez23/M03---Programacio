package arxiusDades;

import vista.Vista;

import java.sql.*;


public class generarBDAleatoria {

    public static void generarBD() throws SQLException {

        generarJugador();
        generarEquips();
        generarEstadistiques();
        partitsRandom();

        System.out.println("Proces de generacio aleatori finalitzat, ja pots treure aquest metode del main.");
    }

    public static void generarJugador() throws SQLException {
        System.out.println("Generant jugadors...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nbav2", "perepi", "pastanaga");


        String[] nomsPerRandomizar = {
                "Aaron", "Adam", "Adrian", "Aiden", "Alexander", "Andrew", "Anthony", "Asher", "Austin", "Benjamin",
                "Blake", "Brandon", "Brian", "Bryce", "Caleb", "Cameron", "Carlos", "Carter", "Charles", "Christian",
                "Christopher", "Cole", "Connor", "Daniel", "David", "Dominic", "Dylan", "Ethan", "Evan", "Felix",
                "Gabriel", "Gavin", "Grayson", "Henry", "Hunter", "Ian", "Isaac", "Isaiah", "Jack", "Jackson",
                "Jacob", "James", "Jason", "Jayden", "Jeremiah", "John", "Jonathan", "Jordan", "Jose", "Joseph",
                "Joshua", "Julian", "Justin", "Kevin", "Kyle", "Landon", "Leo", "Levi", "Liam", "Logan",
                "Lucas", "Luke", "Mason", "Matthew", "Michael", "Nathan", "Nicholas", "Noah", "Nolan", "Oliver",
                "Owen", "Parker", "Patrick", "Paul", "Ryan", "Samuel", "Sebastian", "Thomas", "Timothy", "Tristan",
                "Tyler", "Victor", "Vincent", "William", "Wyatt", "Xavier", "Zachary", "Zane", "Alec", "Alfred",
                "Amos", "Arthur", "Beau", "Colton", "Damien", "Declan", "Edward", "Finn", "George", "Grant"
        };
        String[] cognomsPerRandomizar = {
                "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
                "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
                "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
                "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
                "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts",
                "Gomez", "Phillips", "Evans", "Turner", "Diaz", "Parker", "Cruz", "Edwards", "Collins", "Reyes",
                "Stewart", "Morris", "Morales", "Murphy", "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper",
                "Peterson", "Bailey", "Reed", "Kelly", "Howard", "Ramos", "Kim", "Cox", "Ward", "Richardson",
                "Watson", "Brooks", "Chavez", "Wood", "James", "Bennett", "Gray", "Mendoza", "Ruiz", "Hughes",
                "Price", "Alvarez", "Castillo", "Sanders", "Patel", "Myers", "Long", "Ross", "Foster", "Jimenez"
        };

        String[] posicioRandomizar = {
                "Forward", "Guard", "Pivot", "Aler"};

        int jugador_id = 0;
        String nom;
        String cognom;
        String data_naixement;
        float alcada;
        float pes;
        int dorsal;
        String posicio;
        int equip_id;

        PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS jugadors(" +
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

        for (int i = 0; i <= 100; ++i) {

            int tmpnom;
            int tmpcognom;
            int tmpany;
            int tmpmes;
            int tmpdia;
            int tmpposicio;

            jugador_id = jugador_id;
            tmpnom = (int) (Math.random() * 100);
            tmpcognom = (int) (Math.random() * 100);
            nom = nomsPerRandomizar[tmpnom];
            cognom = cognomsPerRandomizar[tmpcognom];
            tmpany = (int) (Math.random() * (2025 - 1985 + 1)) + 1985;
            tmpmes = (int) (Math.random() * 12 + 1);
            tmpdia = (int) (Math.random() * 31 + 1);

            data_naixement = tmpany + "-" + tmpmes + "-" + tmpdia;

            alcada = (float) (Math.random() * 230);
            pes = (float) (Math.random() * 130 + 1);
            dorsal = (int) (Math.random() * 99 + 1);
            tmpposicio = (int) (Math.random() * 4);
            posicio = posicioRandomizar[tmpposicio];
            equip_id = (int) (Math.random() * 1000 + 1);


            PreparedStatement ps2 = null;try {
                ps2 = connection.prepareStatement(
                        "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, dorsal, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                ps2.setInt(1, jugador_id);
                ps2.setString(2, nom);
                ps2.setString(3, cognom);
                ps2.setDate(4, Date.valueOf(data_naixement));
                ps2.setFloat(5, alcada);
                ps2.setFloat(6, pes);
                ps2.setInt(7, dorsal);
                ps2.setString(8, posicio);
                ps2.setInt(9, equip_id);

                ps2.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                Vista.missatgeError();
            }

            ++jugador_id;

        }
    }

    public static void generarEstadistiques() throws SQLException {
        System.out.println("Generant estadistiques...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nbav2", "perepi", "pastanaga");

        PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS estadistiques_jugadors(" +
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

        int jugador_id;
        int partit_id;
        float minuts_jugats;
        int punts;
        int tirs_anotats;
        int tirs_tirats;
        int tirs_triples_anotats;
        int tirs_triples_tirats;
        int tirs_lliures_anotats;
        int tirs_lliures_tirats;
        int rebots_ofensius;
        int rebots_defensius;
        int assistencies;
        int robades;
        int bloqueigs;


        for (int i = 0; i <= 1000; ++i) {

            jugador_id = (int) (Math.random() * 100 + 1);
            partit_id = (int) (Math.random() * 1000 + 1);
            minuts_jugats = (float) (Math.random() * 48 + 1);
            punts = (int) ((Math.random() * 73 + 1));
            tirs_anotats = (int) (Math.random() * 65 + 1);
            tirs_tirats = (int) (Math.random() * 70 + 1);
            tirs_triples_anotats = (int) (Math.random() * 25 + 1);
            tirs_triples_tirats = (int) (Math.random() * 30 + 1);
            tirs_lliures_anotats = (int) (Math.random() * 35 + 1);
            tirs_lliures_tirats = (int) (Math.random() * 40 + 1);
            rebots_ofensius = (int) (Math.random() * 15 + 1);
            rebots_defensius = (int) (Math.random() * 15 + 1);
            assistencies = (int) (Math.random() * 15 + 1);
            robades = (int) (Math.random() * 10 + 1);
            bloqueigs = (int) (Math.random() * 10 + 1);

            try {
                ps = connection.prepareStatement(
                        "INSERT INTO estadistiques_jugadors (jugador_id, partit_id, minuts_jugats, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );

                ps.setInt(1, jugador_id);
                ps.setInt(2, partit_id);
                ps.setFloat(3, minuts_jugats);
                ps.setInt(4, punts);
                ps.setInt(5, tirs_anotats);
                ps.setInt(6, tirs_tirats);
                ps.setInt(7, tirs_triples_anotats);
                ps.setInt(8, tirs_triples_tirats);
                ps.setInt(9, tirs_lliures_anotats);
                ps.setInt(10, tirs_lliures_tirats);
                ps.setInt(11, rebots_ofensius);
                ps.setInt(12, rebots_defensius);
                ps.setInt(13, assistencies);
                ps.setInt(14, robades);
                ps.setInt(15, bloqueigs);

                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
                Vista.missatgeError();
            }
        }
    }

    public static void generarEquips() throws SQLException {
        System.out.println("Generant equips...");


        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nbav2", "perepi", "pastanaga");

        PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS equips(" +
                "equip_id INT UNSIGNED," +
                "ciutat VARCHAR(40), " +
                "nom VARCHAR(30), " +
                "acronim CHAR(3), " +
                "divisio VARCHAR(50), " +
                "guanyades TINYINT, " +
                "perdudes TINYINT );"
        );
        ps.executeUpdate();
        ps.close();

        String[] equipsRandom = {
                "Bulls", "Lakers", "Celtics", "Warriors",
                "Spurs", "Knicks", "Heat", "Rockets",
                "Nets", "Mavericks", "Clippers", "Suns",
                "76ers", "Bucks", "Raptors", "Jazz",
                "Nuggets", "Pacers", "Thunder", "Trail Blazers",
                "Pelicans", "Timberwolves", "Kings", "Wizards",
                "Pistons", "Hornets", "Magic", "Grizzlies",
                "Hawks", "Cavaliers"
        };

        String[] ciutatsRandom = {
                "Chicago", "Los Angeles", "Boston", "San Francisco",
                "San Antonio", "New York", "Miami", "Houston",
                "Brooklyn", "Dallas", "Los Angeles", "Phoenix",
                "Philadelphia", "Milwaukee", "Toronto", "Salt Lake City",
                "Denver", "Indianapolis", "Oklahoma City", "Portland",
                "New Orleans", "Minneapolis", "Sacramento", "Washington D.C.",
                "Detroit", "Charlotte", "Orlando", "Memphis",
                "Atlanta", "Cleveland"
        };
        String[] divisioRandom = {
                "Atlantic","Central","Northwest","Pacific","Southeast","Southwest"
        };

        int cont = 1;

        int equip_id;
        String ciutat;
        String nom;
        String acronim;
        String divisio;
        int guanyades;
        int perdudes;

        for (int i = 0; i <= 30; ++i){

            int tmpciutat;
            int tmpnom;
            int tmpdivisio;


            equip_id = cont;
            tmpciutat = (int) (Math.random() * 30);
            ciutat = ciutatsRandom[tmpciutat];
            tmpnom = (int) (Math.random() * 30);
            nom = equipsRandom[tmpnom];
            acronim = ciutat.substring(0,3);
            tmpdivisio = (int) (Math.random() * 6);
            divisio = divisioRandom[tmpdivisio];
            guanyades = (int) (Math.random() * 100 + 1);
            perdudes = (int) (Math.random() * 100 + 1);


            try {
                ps = connection.prepareStatement(
                        "INSERT INTO equips (equip_id, ciutat, nom, acronim, divisio, guanyades, perdudes) VALUES (?, ?, ?, ?, ?, ?, ?)"
                );

                ps.setInt(1, equip_id);
                ps.setString(2, ciutat);
                ps.setString(3, nom);
                ps.setString(4, acronim);
                ps.setString(5, divisio);
                ps.setInt(6, guanyades);
                ps.setInt(7, perdudes);

                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
                Vista.missatgeError();
            }

            ++cont;
        }
    }

    public static void partitsRandom() throws SQLException {
        System.out.println("Generant partits...");

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nbav2", "perepi", "pastanaga");

        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS partits (" +
                        "partit_id INT," +
                        "equip_id INT," +
                        "data_partit DATE," +
                        "matx VARCHAR(30)," +
                        "result CHAR(1)" +
                        ");"
        );
        ps.executeUpdate();
        ps.close();

        String[] acrRandoms = {
                "Chi", "Los", "Bos", "San",
                "San", "New", "Mia", "Hou",
                "Bro", "Dal", "Los", "Pho",
                "Phi", "Mil", "Tor", "Sal",
                "Den", "Ind", "Okl", "Por",
                "New", "Min", "Sac", "Was",
                "Det", "Cha", "Orl", "Mem",
                "Atl", "Cle"
        };

        String[] tipusPartit = {
                "VS.","@"
        };
        String[] winloose = {
                "W","L"
        };

        int cont = 0;

        int partit_id;
        int equip_id;
        Date data_partit;
        String matx;
        String result;

        for (int i = 0; i <=1000; ++i){

            int tmpany;
            int tmpmes;
            int tmpdia;
            int tmpacr1;
            int tmpacr2;
            int tmptipus;
            int tmpresult;

            partit_id = cont;
            equip_id = (int) (Math.random() * 30 + 1);

            tmpany = (int) (Math.random() * (2025 - 1985 + 1)) + 1985;
            tmpmes = (int) (Math.random() * 12 + 1);
            tmpdia = (int) (Math.random() * 31 + 1);

            data_partit = Date.valueOf(tmpany + "-" + tmpmes + "-" + tmpdia);

            tmpacr1 = (int) (Math.random() * 30);
            tmpacr2 = (int) (Math.random() * 30);
            tmptipus = (int) (Math.random() * 2);

            matx = acrRandoms[tmpacr1] + " " +  tipusPartit[tmptipus] + " "+ acrRandoms[tmpacr2];

            tmpresult = (int) (Math.random() * 2);
            result = winloose[tmpresult];

            try {
                ps = connection.prepareStatement(
                        "INSERT INTO partits (partit_id, equip_id, data_partit, matx, result) VALUES (?, ?, ?, ?, ?)"
                );

                ps.setInt(1, partit_id);
                ps.setInt(2, equip_id);
                ps.setDate(3, (data_partit));
                ps.setString(4, matx);
                ps.setString(5, result);
                ps.executeUpdate();
                ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Vista.missatgeError();
            }

            ++cont;
        }

    }
}
