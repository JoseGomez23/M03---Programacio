import java.util.Date;

public class Alimentacio extends Producte{


    Date dataCad;

    public Alimentacio(float preu, String nom, String codiBarres, Date dataCad) {
        super(preu, nom, codiBarres);

        this.dataCad = dataCad;
    }

    public void setDataCad() {

        int preuDataCad;
        Date dataActual = new Date(System.currentTimeMillis());
        preuDataCad = (int) (preu - preu*(1/(dataCad.getTime()- dataActual.getTime()  +1)) + (preu * 0.1));
        preu = preuDataCad;
        System.out.println(preu);

    }
}
