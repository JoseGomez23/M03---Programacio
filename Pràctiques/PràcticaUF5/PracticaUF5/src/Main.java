import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Producte> productes = new ArrayList<>(100);
    static Map<String, String> carrito = new HashMap<>();
    static Map<String, Float> caixa = new HashMap<>();
    static int contadorProductes = 0;

    public static void main(String[] args) {

        productes.add(new Textil(12,"Camisa","12","Coto"));
        productes.add(new Electronica(1002,"Tv","129567",24));

        menu();

    }

    public static void menu() {

        if (contadorProductes <= 100) {

            String opcio;

            System.out.println("----------------------------");
            System.out.println("-------- Benvingut! --------");
            System.out.println("----------------------------");
            System.out.println("1 - Introduir producte");
            System.out.println("2 - Passar per caixa");
            System.out.println("3 - Mostrar carro: ");
            System.out.println("0 - Sortir");
            System.out.print("Opcio: ");
            opcio = scan.nextLine();


            switch (opcio) {

                case "1":
                    afegirProducte();
                    break;
                case "2":
                    cobrarCaixa();
                    break;
                case "3":
                    mostrarCarro();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcio incorrecte, torna a introduir-ne una de valida");
                    menu();
                    break;

            }
        } else {
            System.out.println("Has introduit el màxim de productes, passa per caixa!!!");
            cobrarCaixa();
        }
    }

    // Metodes per afegir productes
    public static void afegirProducte() {

        String opcio;

        System.out.println("----------------");
        System.out.println("--- Producte ---");
        System.out.println("----------------");
        System.out.println("1 - Alimentacio");
        System.out.println("2 - Textil");
        System.out.println("3 - Electronica");
        System.out.println("4 - Tornar");
        System.out.print("Opcio: ");

        opcio = scan.nextLine();


        switch (opcio) {

            case "1":
                afegirProducteAlimentacio();
                break;
            case "2":
                afegirProducteTextil();
                break;
            case "3":
                afegirProducteElectronica();
                break;
            case "4":
                menu();
                break;
            default:
                System.out.println("Opcio incorrecte, torna a introduir-ne una valida");
                afegirProducte();
                break;
        }

    }

    public static void afegirProducteAlimentacio() {
        try {


            String nom;
            float preu;
            String codiBarres;
            String dataCad;
            Date dataCaducitat;

            ++contadorProductes;

            System.out.println("Introdueix les dades per un producte d'Alimentacio: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Data de caducitat: ");
            dataCad = scan.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataCaducitat = sdf.parse(dataCad);

            productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            carrito.put(codiBarres, nom);
            caixa.put(nom,preu);

        } catch (Exception e) {

            System.out.println("S'ha produit un error de enregistrament de dades, torna a provar");
            --contadorProductes;
            afegirProducteAlimentacio();

        } finally {
            menu();
        }


    }

    public static void afegirProducteElectronica() {

        try {

            String nom;
            float preu;
            String codiBarres;
            int diesGarantia;

            ++contadorProductes;

            System.out.println("Introdueix les dades per un producte d'Electronica: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Dies de garantia: ");
            diesGarantia = scan.nextInt();

            productes.add(new Electronica(preu, nom, codiBarres, diesGarantia));
            carrito.put(codiBarres, nom);
            caixa.put(nom,preu);

        } catch (Exception e) {

            System.out.println("S'ha produit un error de enregistrament de dades, torna a provar");
            --contadorProductes;
            afegirProducteElectronica();

        } finally {
            menu();
        }

    }

    public static void afegirProducteTextil() {
        try {

            String nom;
            float preu;
            String codiBarres;
            String composicioTextil;

            ++contadorProductes;

            System.out.println("Introdueix les dades per un producte d'Tèxtil: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Composicio textil: ");
            composicioTextil = scan.nextLine();

            productes.add(new Textil(preu, nom, codiBarres, composicioTextil));
            carrito.put(codiBarres, nom);
            caixa.put(nom,preu);

        } catch (Exception e) {

            System.out.println("S'ha produit un error de enregistrament de dades, torna a provar");
            --contadorProductes;
            afegirProducteTextil();

        } finally {

            menu();
        }


    }

    // Metode per cobrar els productes que estan a la llista
    public static void cobrarCaixa() {

        try {

            if (productes.isEmpty()) {

                System.out.println("No tens cap producte a la llista!");
                afegirProducte();
            } else {

                float preuF = 0;
                Date dataCaducitat;
                String dataCad;
                String nom = "------------------" + "\n" +
                             "--- SAPAMERCAT ---" + "\n" +
                             "------------------";

                System.out.print("Introdueix el dia d'avui(dd/mm/yyyy): ");
                dataCad = scan.nextLine();


                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataCaducitat = sdf.parse(dataCad);


                System.out.println("TIQUET:");
                System.out.println(nom);
                System.out.println(dataCaducitat);
                System.out.println("-------------------");


                String format = "%-" + 15 + "s%" + 10 + "s\n";

                for (int i = 0; i < productes.size(); i++) {

                    float preuTmp;


                    String nomP = productes.get(i).toString().split("\\*")[0];
                    String preu = productes.get(i).toString().split("\\*")[1];

                    System.out.printf(format, nomP, preu);

                    preuTmp = Float.parseFloat(preu.substring(6).trim());

                    preuF = preuTmp + preuF;

                }

                System.out.println(preuF);


            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("S'ha produit un error, reexecutant");
            cobrarCaixa();

        }


    }

    // Metode per mostrar els productes del carro
    public static void mostrarCarro() {

        //TODO No funciona com hauria de fer-ho, els identifica pero no els compta si hi han repetits. (arreglar-ho)

        HashMap<String, Integer> codiDebarresC = new HashMap<>();

        for (String key : carrito.keySet()) {
            String codi = carrito.get(key);
            codiDebarresC.put(codi, codiDebarresC.getOrDefault(codi, 0) + 1);
        }

        System.out.println("Productes: ");
        for (String codiBarres : codiDebarresC.keySet()) {
            System.out.println("Codi de barres: " + codiBarres + " --> " + codiDebarresC.get(codiBarres));
        }

    }


}