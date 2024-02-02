package entites;

public class Client {
    private int id;
    private String nom;
    private String ville;
    private int age;
    private String prenom;
    private String adress;
    private String email;
    private String sexe;
    private String noTel;
    private String country;
    
    public Client() {
    }

    public Client(String nom, String ville, int age, String prenom, String adress, String email, String sexe, String noTel, String country) {
        this.nom = nom;
        this.ville = ville;
        this.age = age;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.sexe = sexe;
        this.noTel = noTel;
        this.country = country;
    }

    public Client(int id, String nom, String ville, int age, String prenom, String adress, String email, String sexe, String noTel, String country) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.age = age;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.sexe = sexe;
        this.noTel = noTel;
        this.country = country;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public boolean setCountry(String country) {
        if (country.length() > 50 || country.length() <= 0 ||country == null) {
            return false;
        } else {
            this.country = country;
            return true;
        }
    }
    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public String getNotel() {
        return this.noTel;
    }

    public boolean setNotel(String noTel) {
        if (noTel.length() > 10 || noTel.length() <= 0 ||noTel == null) {
            return false;
        } else {
            this.noTel = noTel;
            return true;
        }
    }

    public String getEmail() {
        return this.email;
    }

    public boolean setEmail(String email) {
        if (email.length() > 50 || email.length() <= 0 ||email == null) {
            return false;
        } else {
            this.email = email;
            return true;
        }
    }

    public String getAdress() {
        return this.adress;
    }

    public boolean setAdress(String adress) {
        if (adress.length() > 50 || adress.length() <= 0) {
            return false;
        } else {
            this.adress = adress;
            return true;
        }
    }

    public String getNom() {
        return this.nom;
    }

    public boolean setNom(String nom) {
        if (nom.length() > 50 || nom.length() <= 0 ||nom == null){
            return false;
        } else {
            this.nom = nom;
            return true;
        }
    }

    public String getVille() {
        return this.ville;
    }

    public boolean setVille(String ville) {
        if (ville.length() > 50 || ville.length() <= 0 ||ville == null) {
            return false;
        } else {
            this.ville = ville;
            return true;
        }
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public boolean setPrenom(String prenom) {
        if (prenom.length() > 50 || prenom.length() <= 0 ||prenom == null) {
            return false;
        } else {
            this.prenom = prenom;
            return true;
        }
    }

    @Override
    public String toString() {
        return  id +
                ", nom [" + nom + ']' +
                ", ville [" + ville + ']' +
                ", age [" + age + "]" +
                ", prenom [" + prenom + ']' +
                ", adress [" + adress + ']' +
                ", email [" + email + ']' +
                ", sexe [" + sexe + ']' +
                ", noTel [" + noTel + ']' +
                ", country [" + country + ']';
    }
}
