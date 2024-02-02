package entites;
import java.sql.Date;

public class Paiement {
    Date dateP;
    double montant;
    String methode;
    int id_reservation;
    int id;

    public Paiement(Date dateP, double montant, String methode, int id_reservation, int id) {
        this.dateP = dateP;
        this.montant = montant;
        this.methode = methode;
        this.id_reservation = id_reservation;
        this.id = id;
    }

    public Paiement(Date dateP, double montant, String methode, int id_reservation) {
        this.dateP = dateP;
        this.montant = montant;
        this.methode = methode;
        this.id_reservation = id_reservation;
    }

    public Paiement() {
    }
    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  id +
                "date du paiement [" + dateP + "]" +
                ", montant [" + montant + "]" +
                ", methode [" + methode + ']' +
                ", id_reservation=" + id_reservation +
                ']';
    }
}
