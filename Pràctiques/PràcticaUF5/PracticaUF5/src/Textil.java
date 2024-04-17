public class Textil extends Producte {

    String composicioTextil;

    public Textil(float preu, String nom, String codiBarres, String composicioTextil) {
        super(preu, nom, codiBarres);
        this.composicioTextil = composicioTextil;
    }

    @Override
    public float getPreu() {
        return 0;
    }

    @Override
    public String toString() {
        return  "Nom: " + nom + "\n" +
                "Preu: " + preu + "\n" +
                "Composicio textil: " + composicioTextil + "\n" +
                "Codi de barres: " + codiBarres;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
