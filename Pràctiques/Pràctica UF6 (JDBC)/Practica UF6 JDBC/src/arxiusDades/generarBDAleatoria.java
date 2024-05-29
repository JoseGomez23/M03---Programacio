package arxiusDades;

import vista.Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class generarBDAleatoria {

    public static void generarJugador() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");


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

        for (int i = 0; i <= 100; ++i) {

            int tmpnom;
            int tmpcognom;
            int tmpany;
            int tmpmes;
            int tmpdia;
            int tmpposicio;

            jugador_id = jugador_id;
            tmpnom = (int) (Math.random() * 100 + 1);
            tmpcognom = (int) (Math.random() * 100 + 1);
            nom = nomsPerRandomizar[tmpnom];
            cognom = cognomsPerRandomizar[tmpcognom];
            tmpany = (int) (Math.random() * (2025 - 1985 + 1)) + 1985;
            tmpmes = (int) (Math.random() * 12 + 1);
            tmpdia = (int) (Math.random() * 31 + 1);

            data_naixement = tmpany + "-" + tmpmes + "-" + tmpdia;

            alcada = (float) (Math.random() * 2.50);
            pes = (float) (Math.random() * 130 + 1);
            dorsal = (int) (Math.random() * 99 + 1);
            tmpposicio = (int) (Math.random() * 4 + 1);
            posicio = posicioRandomizar[tmpposicio];
            equip_id = (int) (Math.random() * 1000 + 1);


            PreparedStatement ps2 = null;
            try {
                ps2 = connection.prepareStatement(
                        "INSERT INTO jugadors VALUES(" + jugador_id + "," + nom + "," + cognom + "," + data_naixement + "," + alcada + "," + pes + "," + dorsal + "," + posicio + "," + equip_id + ";");
                ps2.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                Vista.missatgeError();
            }

            ++jugador_id;

        }
    }

    public static void generarEstadistiques() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");

        PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS historic(" +
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
                        "INSERT INTO estadistiques_jugadors VALUES(" + jugador_id + "," + partit_id + "," + minuts_jugats + "," + punts + "," +
                                tirs_anotats + "," + tirs_tirats + "," + tirs_triples_anotats + "," + tirs_triples_tirats + "," + tirs_lliures_anotats + "," +
                                tirs_lliures_tirats + "," + tirs_tirats + "," + rebots_ofensius + "," + rebots_defensius + "," + assistencies + "," + robades + "," + bloqueigs + ";");

                ps.executeUpdate();
            } catch (Exception ex) {

                ex.printStackTrace();
                Vista.missatgeError();
            }
        }
    }
}
