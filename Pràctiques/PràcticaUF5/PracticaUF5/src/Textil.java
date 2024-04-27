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
        return "Nom: " + nom + " * " +
                "Preu: " + preu + " * " +
                "Composicio textil: " + composicioTextil + " * " +
                "Codi de barres: " + codiBarres;
    }
}
