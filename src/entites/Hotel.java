package entites;
import java.sql.Date;

public class Hotel {
    String nom;
    String adress;
    String city;
    String desc;
    String parking;
    String wifi;
    Date checkIn;
    Date checkOut;
    String pool;
    String shuttle;
    String animals;
    int star;
    int id_societe;
    int id;

    public Hotel(int id, String nom, String adress, String city, String desc, String parking, String wifi, Date checkIn, Date checkOut, String pool, String shuttle, String animals, int star, int id_societe) {
        this.id = id;
        this.nom = nom;
        this.adress = adress;
        this.city = city;
        this.desc = desc;
        this.parking = parking;
        this.wifi = wifi;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.pool = pool;
        this.shuttle = shuttle;
        this.animals = animals;
        this.star = star;
        this.id_societe = id_societe;
    }

    public Hotel(String nom, String adress, String city, String desc, String parking, String wifi, Date checkIn, Date checkOut, String pool, String shuttle, String animals, int star, int id_societe) {
        this.nom = nom;
        this.adress = adress;
        this.city = city;
        this.desc = desc;
        this.parking = parking;
        this.wifi = wifi;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.pool = pool;
        this.shuttle = shuttle;
        this.animals = animals;
        this.star = star;
        this.id_societe = id_societe;
    }

    public Hotel() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getShuttle() {
        return shuttle;
    }

    public void setShuttle(String shuttle) {
        this.shuttle = shuttle;
    }

    public String getAnimals() {
        return animals;
    }

    public void setAnimals(String animals) {
        this.animals = animals;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getId_societe() {
        return id_societe;
    }

    public void setId_societe(int id_societe) {
        this.id_societe = id_societe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nom='" + nom + '\'' +
                ", adress='" + adress + '\'' +
                ", city='" + city + '\'' +
                ", desc='" + desc + '\'' +
                ", parking='" + parking + '\'' +
                ", wifi='" + wifi + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", pool='" + pool + '\'' +
                ", shuttle='" + shuttle + '\'' +
                ", animals='" + animals + '\'' +
                ", star=" + star +
                ", id_societe=" + id_societe +
                ", id=" + id +
                '}';
    }
}
