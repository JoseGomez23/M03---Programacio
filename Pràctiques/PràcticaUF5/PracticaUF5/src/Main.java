import java.util.Scanner;
public class Main {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        menu();

    }

    public static void menu(){

        int opcio = 0;

        System.out.println("----------------------------");
        System.out.println("-------- Benvingut! --------");
        System.out.println("----------------------------");
        System.out.println("1 - Introduir producte");
        System.out.println("2 - Passar per caixa");
        System.out.println("3 - Mostrar carro: ");
        System.out.println("0 - Sortir");
        System.out.print("Opcio: ");
        opcio = scan.nextInt();
        scan.nextLine();

        switch (opcio){

            case 1:
                afegirProducte();
                break;
            case 2:
                cobrarCaixa();
                break;
            case 3:
                mostrarCarro();
                break;
            case 0:
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

    }

    public static void afegirProducteElectronica(){

    }

    public static void afegirProducteTextil(){

    }

    // Metode per cobrar els productes que estan a la llista
    public static void cobrarCaixa(){


    }

    public static void  mostrarCarro(){


    }




}