import java.util.Scanner;
public class Main {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {



    }

    public static void menu(){

        int opcio = 0;


        System.out.println("Que vols fer: ");
        System.out.println("1 - Introduir producte");
        System.out.println("2 - Passar per caixa");
        System.out.println("Opcio: ");
        opcio = scan.nextInt();
        scan.nextLine();

        switch (opcio){

            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 0:

                break;
            default:
                System.out.println("Opcio incorrecte, torna a introduir-ne una de valida");
                menu();
                break;

        }

    }

}