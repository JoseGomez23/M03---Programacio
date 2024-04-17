import java.util.Date;

public class Alimentacio extends Producte{


    Date dataCad;

    public Alimentacio(float preu, String nom, String codiBarres, Date dataCad) {
        super(preu, nom, codiBarres);
        this.dataCad = dataCad;

    }


    public float setPreuDataCad() {
        // TODO: 17/04/2024 El calcul no esta ben fet 

        float preuDataCad;
        Date dataActual = new Date(System.currentTimeMillis());
        preuDataCad = (float) (preu - preu*(1/(dataCad.getTime()- dataActual.getTime()  +1)) - (preu * 0.1));
        super.preu = preuDataCad;
        return super.preu;
    }

    @Override
    public float getPreu() {
        return setPreuDataCad();
    }

    @Override
    public String toString() {
        return  "Nom: " + nom + "\n" +
                "Preu: " + getPreu() + "\n" +
                "Data caducitat: " + dataCad + "\n" +
                "Codi de barres: " + codiBarres;
    }


}
