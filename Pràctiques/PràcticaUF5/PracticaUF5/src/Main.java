import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Producte> productes = new ArrayList<>();
    static Map<String, String> carrito = new HashMap<>();
    static Map<String, Float> caixa = new HashMap<>();
    static ArrayList<String>codisBarresTextil = new ArrayList<>();
    static int contadorProductes = 0;

    public static void main(String[] args) {


        productes.add(new Electronica(1002, "Tv", "129567", 24));

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

            if (nom.length() > 15) {

                System.out.println("Nom massa llarg, torna a introduir un mes curt.");
                --contadorProductes;
                afegirProducteAlimentacio();
            }

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();
            if (preu > 100) {

                System.out.println("Preu massa gran per aquest tipus de producte, introdueix un coherent.");
                afegirProducteAlimentacio();
                --contadorProductes;
            }

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Data de caducitat: ");
            dataCad = scan.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataCaducitat = sdf.parse(dataCad);

            productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            carrito.put(codiBarres, nom);
            caixa.put(nom, preu);

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
            if (nom.length() > 15) {

                System.out.println("Nom massa llarg, torna a introduir un mes curt.");
                --contadorProductes;
                afegirProducteElectronica();
            }

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();
            if (preu > 1000000) {

                System.out.println("Preu massa gran per aquest tipus de producte, introdueix un coherent.");
                afegirProducteAlimentacio();
                --contadorProductes;
            }

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Dies de garantia: ");
            diesGarantia = scan.nextInt();

            productes.add(new Electronica(preu, nom, codiBarres, diesGarantia));
            carrito.put(codiBarres, nom);
            caixa.put(nom, preu);

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
            boolean condicioBucle;


            ++contadorProductes;

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
            if (preu > 10000) {

                System.out.println("Preu massa gran per aquest tipus de producte, introdueix un coherent.");
                afegirProducteTextil();
                --contadorProductes;
            }

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();


            condicioBucle = false;

            do {

                //TODO: Arreglar bug, cuan es mostra carro amb un producte textil torna a entrar a aquest metode.

                if (codisBarresTextil.contains(codiBarres)){

                    System.out.println("No pots tenir dos tipus productes de aquest tipus amb el mateix codi de barres! ");
                    afegirProducteTextil();

                } else {

                    codisBarresTextil.add(codiBarres);
                    condicioBucle = true;
                }

            } while (!condicioBucle);

            System.out.print("Composicio textil: ");
            composicioTextil = scan.nextLine();

            productes.add(new Textil(preu, nom, codiBarres, composicioTextil));
            carrito.put(codiBarres, nom);
            caixa.put(nom, preu);

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


                System.out.println("-------------------");
                System.out.println("Total: " + preuF);

                productes.clear();
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("S'ha produit un error, reexecutant");
            cobrarCaixa();

        }


    }

    // Metode per mostrar els productes del carro
    public static void mostrarCarro() {

        LinkedHashMap<Integer, String> codis = new LinkedHashMap<>();
        for (int i = 0; i < productes.size(); ++i) {


            int codidef;

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

    }


}