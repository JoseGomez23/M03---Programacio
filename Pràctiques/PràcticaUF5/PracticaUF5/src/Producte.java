import java.util.Comparator;

public abstract class Producte {

    float preu;
    String nom;
    String codiBarres;

    public Producte(float preu, String nom, String codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
    }

    public abstract float getPreu();

    @Override
    public String toString() {
        return  "Preu: " + preu + "\n" +
                "Nom: " + nom + "\n" +
                "Codi de barres: " + codiBarres;
    }
}
