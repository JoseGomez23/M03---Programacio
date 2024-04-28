/**
 * Importacio de les llibreries necessaries
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Classe main, aqui s'executen la majoria de processos
 */
public class Main {
    /**
     * Variables globals, necessaries per la majoria dels processos
     */
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Producte> productes = new ArrayList<>();
    static Map<String, String> carrito = new HashMap<>();
    static Map<String, String> caixa = new HashMap<>();
    static HashMap<String, String> doc = new HashMap<>(); //Guarda els valors del UpdateTextilPrices.dat
    static ArrayList<String> codisBarresTextil = new ArrayList<>();
    static int contadorProductes = 0;

    /**
     * Metode main, metode qeu desencadena l'execucio dels altres metodes
     *
     * @param args Arguments del main
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    public static void main(String[] args) throws IOException {

        menu();

    }

    /**
     * Menu principal, utilitzat per escollir l'opcio desitjada
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    public static void menu() throws IOException {

        if (contadorProductes <= 100) {

            String opcio = "0";

            System.out.println("----------------------------");
            System.out.println("-------- Benvingut! --------");
            System.out.println("----------------------------");
            System.out.println("1 - Introduir producte");
            System.out.println("2 - Passar per caixa");
            System.out.println("3 - Mostrar carro");
            System.out.println("4 - Buscar producte");
            System.out.println("0 - Sortir");
            System.out.print("Opcio: ");
            opcio = scan.nextLine();


            switch (opcio) {

                case "1":
                    afegirProducte();
                    break;
                case "2":
                    arreglarPreus();
                    cobrarCaixa();
                    break;
                case "3":
                    mostrarCarro();
                    break;
                case "4":
                    buscadorDeNoms();
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

    /**
     * Metode per escollir quin tipus de producte es vol escollir
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    // Metodes per afegir productes
    public static void afegirProducte() throws IOException {

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

    /**
     * Metode per afegir productes del tipus alimentacio
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    public static void afegirProducteAlimentacio() throws IOException {
        try {

            String nom;
            float preu;
            String codiBarres;
            String dataCad;
            Date dataCaducitat;

            ++contadorProductes;

            //Llegir dades introduides
            System.out.println("Introdueix les dades per un producte d'Alimentacio: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();

            if (nom.length() > 15) {

                System.out.println("Nom massa llarg, torna a introduir un mes curt.");
                --contadorProductes;
                afegirProducteAlimentacio();
            }

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();
            if (preu > 100 || preu < 0) {

                System.out.println("Preu massa gran o petit per aquest tipus de producte, introdueix un coherent.");
                afegirProducteAlimentacio();
                --contadorProductes;
            }

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Data de caducitat: ");
            dataCad = scan.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataCaducitat = sdf.parse(dataCad);

            //Afegir les dades introduides al array i hashmaps
            productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            carrito.put(codiBarres, nom);
            caixa.put(nom, String.valueOf(preu));


        } catch (Exception e) {

            exceptionsHandler(e);

        } finally {
            menu();
        }
    }

    /**
     * Metode per afegir productes de tipus electronica
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    public static void afegirProducteElectronica() throws IOException {

        try {

            String nom = "";
            float preu = 0;
            String codiBarres = "";
            int diesGarantia = 0;

            ++contadorProductes;

            //Introduir dades
            System.out.println("Introdueix les dades per un producte d'Electronica: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            if (nom.length() > 15) {

                System.out.println("Nom massa llarg, torna a introduir un mes curt.");
                --contadorProductes;
                afegirProducteElectronica();
            }

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();
            if (preu > 1000000 || preu < 0) {

                System.out.println("Preu massa gran o petit per aquest tipus de producte, introdueix un coherent.");
                afegirProducteAlimentacio();
                --contadorProductes;
            }

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Dies de garantia: ");
            diesGarantia = scan.nextInt();

            //Afegir les dades introduides pel teclat al array i hashmaps
            productes.add(new Electronica(preu, nom, codiBarres, diesGarantia));
            carrito.put(codiBarres, nom);
            caixa.put(nom, String.valueOf(preu));


        } catch (Exception e) {

            exceptionsHandler(e);
        } finally {
            menu();
        }
    }

    /**
     * Metode per afegir productes de tipus textil
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    public static void afegirProducteTextil() throws IOException {
        try {

            String nom;
            float preu;
            String codiBarres;
            String composicioTextil;
            boolean condicioBucle;


            ++contadorProductes;

            //Introduir dades per teclat
            System.out.println("Introdueix les dades per un producte d'Tèxtil: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            if (nom.length() > 15) {

                System.out.println("Nom massa llarg, torna a introduir un mes curt.");
                --contadorProductes;
                afegirProducteTextil();
            }

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();
            if (preu > 10000 || preu < 0) {

                System.out.println("Preu massa gran o petit per aquest tipus de producte, introdueix un coherent.");
                afegirProducteTextil();
                --contadorProductes;
            }

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();


            condicioBucle = false;

            do {

                if (codisBarresTextil.contains(codiBarres)) {

                    System.out.println("No pots tenir dos tipus productes de aquest tipus amb el mateix codi de barres! ");
                    menu();

                } else {

                    codisBarresTextil.add(codiBarres);
                    condicioBucle = true;
                }

            } while (!condicioBucle);

            System.out.print("Composicio textil: ");
            composicioTextil = scan.nextLine();

            //Afegir les dades introduides anteriorment al array i hashmaps
            productes.add(new Textil(preu, nom, codiBarres, composicioTextil));
            carrito.put(codiBarres, nom);
            caixa.put(nom, String.valueOf(preu));

        } catch (Exception e) {

            exceptionsHandler(e);

        } finally {
            menu();
        }
    }

    /**
     * @throws FileNotFoundException Excepcio que es llançara en cas de no haver trobat el fitxer
     */
    // Metode per cobrar els productes que estan a la llista
    public static void cobrarCaixa() throws FileNotFoundException {

        try {

            //Condicional per comprovar si hi han productes
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

                    //Separar el nom i el preu de l'arraylist
                    String nomP = productes.get(i).toString().split("\\*")[0];
                    String preu = productes.get(i).toString().split("\\*")[1];

                    System.out.printf(format, nomP, preu);

                    preuTmp = Float.parseFloat(preu.substring(6).trim());

                    preuF = preuTmp + preuF;
                }

                System.out.println("-------------------");
                System.out.println("Total: " + preuF);

                productes.clear();
                caixa.clear();
                codisBarresTextil.clear();
                carrito.clear();
            }

        } catch (Exception e) {

            exceptionsHandler(e);
            cobrarCaixa();

        }
    }

    /**
     * Metode per mostrar els productes trobats al carro
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    // Metode per mostrar els productes del carro
    public static void mostrarCarro() throws IOException {

        try {

            LinkedHashMap<Integer, String> codis = new LinkedHashMap<>();

            if (!productes.isEmpty()) {

                for (int i = 0; i < productes.size(); ++i) {

                    int codidef;

                    //Separar el nom i codi del arraylist
                    String nomP = productes.get(i).toString().split("\\*")[0];
                    String codi = productes.get(i).toString().split("\\*")[3];

                    codidef = Integer.parseInt(codi.substring(17).trim());

                    if (!codis.containsKey(codidef)) {

                        //Si no existeix el registre, afegeix el primer amb quantitat 1
                        codis.put(codidef, nomP + "*1");
                        System.out.println();

                    } else {

                        //Si existeix el codi s'afegeix 1 registre mes!
                        codis.replace(codidef, nomP + "*" + (Integer.parseInt(codis.get(codidef).split("\\*")[1]) + 1));
                    }

                }
                // utilitzem v.split en dos casos per treure el nom i separar la quantitat
                codis.forEach((k, v) -> System.out.printf("%s %s %s \n", v.split("\\*")[0].substring(4).trim(), "-->", v.split("\\*")[1]));
            } else {
                System.out.println("El carro esta buit!!!");
                menu();
            }
        } catch (Exception e) {

            exceptionsHandler(e);
            menu();

        } finally {
            menu();
        }
    }

    /**
     * Metode per gestionar totes les excepcions que arribin
     *
     * @param e Variabel per les excepcions
     * @throws FileNotFoundException Excepcio que es llançara en cas de no trobar el fitxer
     */
    public static void exceptionsHandler(Exception e) throws FileNotFoundException {

        try {

            //definir variables i el buffered reader juntament amb el outputStream
            String ruta = "./logs/Exceptions.dat";
            System.out.println("Excepcio provocada!!!");
            System.out.println("Fes una ullada a Excepcions.dat per obtenir-ne mes informacio.");
            FileOutputStream errors = new FileOutputStream(ruta, true);
            PrintStream logWriter = new PrintStream(errors);

            //Printar al document la excepcio provocada
            logWriter.println(" Excepcio: " + e.getClass());
            logWriter.close();
            errors.close();

        } catch (Exception x) {

            System.out.println("Error al escriure al fitxer de logs");
            exceptionsHandler(x);
        }
    }

    /**
     * Metode per arreglar els preus depenent del codi de barres que trobem a UpdateTextilPrices.dat
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    public static void arreglarPreus() throws IOException {

        try {

            String ruta = "./updates/UpdateTextilPrices.dat";
            File prices = new File(ruta);

            FileReader fr = new FileReader(prices);
            BufferedReader br = new BufferedReader(fr);

            //Condicional del bucle
            String brData;

            while ((brData = br.readLine()) != null) {

                String[] valors = brData.split(";"); //Per llegir separat el codi i el preu
                doc.put(valors[0], valors[1]);

            }

            for (Map.Entry<String, String> doc : doc.entrySet()) {
                String valorDoc = doc.getValue();  //Aconseguir el codi de barres del document

                for (Map.Entry<String, String> carro : caixa.entrySet()) {
                    String[] valuesCarro = new String[]{carro.getValue()}; //Guardar el codi de barres del document en un string[]

                    for (int i = 0; i < valuesCarro.length; i++) {
                        if (!valuesCarro[i].equals(valorDoc)) {
                            valuesCarro[i] = valorDoc; //Substituir el valor del codi de barres actual per el del document
                            caixa.replace(carro.getKey(), Arrays.toString(valuesCarro)); // Reemplaçar el codi de barres del document a l'array
                        }
                    }
                }
            }
        } catch (Exception e) {

            exceptionsHandler(e);
        }
    }

    /**
     * Buscar nom del producte introduint el codi de barres
     *
     * @throws IOException Excepcio que es llançara en cas de haver-hi un in/out incorrecte
     */
    public static void buscadorDeNoms() throws IOException {

        if (carrito.isEmpty()) {

            System.out.println("El carrito esta buit, no trobaras res");
            menu();

        } else

            System.out.print("Introdueix el codi de barres per cercar el nom del producte: ");
        String codiDeBarres = scan.nextLine().trim();

        // Buscar el producte per codi de barres utilitzant streams
        List<String> producteTrobat = carrito.entrySet().stream()
                .filter(entry -> entry.getKey().equals(codiDeBarres)) // Comparar les claus
                .map(Map.Entry::getValue) // Obtenir valor de la entrada
                .toList(); // Guardar resultat a la llista , "producteTrobat"

        if (producteTrobat.isEmpty()) {
            System.out.println("No s'ha trobat cap producte amb aquest codi de barres.");
            menu();
        } else {
            System.out.println("Producte trobat: " + producteTrobat.get(0));
            menu();
        }
    }
}
