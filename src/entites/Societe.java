package entites;

public class Societe {
    int id;
    int siret;
    String name;
    String adress;

    public Societe(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
