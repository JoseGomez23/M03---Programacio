import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Producte> productes= new ArrayList<>(100);
    public static void main(String[] args) {

        menu();

    }

    public static void menu(){

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


        switch (opcio){

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

    }

    // Metodes per afegir productes
    public static void afegirProducte(){

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


        switch (opcio){

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
    public static void afegirProducteAlimentacio(){
        try {

            String nom;
            float preu;
            String codiBarres;
            String dataCad;
            Date dataCaducitat;

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

            productes.add(new Alimentacio(preu,nom,codiBarres,dataCaducitat));

        } catch (Exception e){

            System.out.println("S'ha produit un error de enregistrament de dades, torna a provar");
            afegirProducteAlimentacio();

        }


    }

    public static void afegirProducteElectronica(){

        try {

            String nom;
            float preu;
            String codiBarres;
            int diesGarantia;

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

            productes.add(new Electronica(preu,nom,codiBarres,diesGarantia));


        } catch (Exception e){

            System.out.println("S'ha produit un error de enregistrament de dades, torna a provar");
            afegirProducteElectronica();

        }

    }

    public static void afegirProducteTextil(){
        try {

            String nom;
            float preu;
            String codiBarres;
            String composicioTextil;

            System.out.println("Introdueix les dades per un producte d'Electronica: ");
            System.out.print("Nom: ");
            nom = scan.nextLine();

            System.out.print("Preu: ");
            preu = scan.nextInt();
            scan.nextLine();

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Composicio textil: ");
            composicioTextil = scan.nextLine();

            productes.add(new Textil(preu,nom,codiBarres,composicioTextil));

        } catch (Exception e){

            System.out.println("S'ha produit un error de enregistrament de dades, torna a provar");
            afegirProducteTextil();

        }


    }

    // Metode per cobrar els productes que estan a la llista
    public static void cobrarCaixa(){

    }

    public static void  mostrarCarro(){


    }

}