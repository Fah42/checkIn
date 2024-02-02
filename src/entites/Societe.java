package entites;

public class Societe {
    int id;
    String siret;
    String name;
    String adress;

    public Societe(int id, String siret, String name, String adress) {
        this.id = id;
        this.siret = siret;
        this.name = name;
        this.adress = adress;
    }

    public Societe(String siret, String name, String adress) {
        this.siret = siret;
        this.name = name;
        this.adress = adress;
    }

    public Societe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  id + " siret [" + siret + "], nom [" + name  + "], adresse [" + adress + ']';
    }
}
