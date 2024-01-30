package entites;

public class Chambre {
    int id;
    int chamberNumber;
    int numberOfSimple;
    int numberOfDouble;
    int chamberArea;
    String bathroom;
    String tv;
    String balcony;
    String fridge;
    String bathtub;
    String soundproof;
    String pricePerNight;

    public Chambre(int id, int chamberNumber, int numberOfSimple, int numberOfDouble, int chamberArea, String bathroom, String tv, String balcony, String fridge, String bathtub, String soundproof, String pricePerNight) {
        this.id = id;
        this.chamberNumber = chamberNumber;
        this.numberOfSimple = numberOfSimple;
        this.numberOfDouble = numberOfDouble;
        this.chamberArea = chamberArea;
        this.bathroom = bathroom;
        this.tv = tv;
        this.balcony = balcony;
        this.fridge = fridge;
        this.bathtub = bathtub;
        this.soundproof = soundproof;
        this.pricePerNight = pricePerNight;
    }
    public Chambre(int chamberNumber, int numberOfSimple, int numberOfDouble, int chamberArea, String bathroom, String tv, String balcony, String fridge, String bathtub, String soundproof, String pricePerNight) {
        this.chamberNumber = chamberNumber;
        this.numberOfSimple = numberOfSimple;
        this.numberOfDouble = numberOfDouble;
        this.chamberArea = chamberArea;
        this.bathroom = bathroom;
        this.tv = tv;
        this.balcony = balcony;
        this.fridge = fridge;
        this.bathtub = bathtub;
        this.soundproof = soundproof;
        this.pricePerNight = pricePerNight;
    }
    public Chambre() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChamberNumber() {
        return chamberNumber;
    }

    public void setChamberNumber(int chamberNumber) {
        this.chamberNumber = chamberNumber;
    }

    public int getNumberOfSimple() {
        return numberOfSimple;
    }

    public void setNumberOfSimple(int numberOfSimple) {
        this.numberOfSimple = numberOfSimple;
    }

    public int getNumberOfDouble() {
        return numberOfDouble;
    }

    public void setNumberOfDouble(int numberOfDouble) {
        this.numberOfDouble = numberOfDouble;
    }

    public int getChamberArea() {
        return chamberArea;
    }

    public void setChamberArea(int chamberArea) {
        this.chamberArea = chamberArea;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getFridge() {
        return fridge;
    }

    public void setFridge(String fridge) {
        this.fridge = fridge;
    }

    public String getBathtub() {
        return bathtub;
    }

    public void setBathtub(String bathtub) {
        this.bathtub = bathtub;
    }

    public String getSoundproof() {
        return soundproof;
    }

    public void setSoundproof(String soundproof) {
        this.soundproof = soundproof;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", chamberNumber=" + chamberNumber +
                ", numberOfSimple=" + numberOfSimple +
                ", numberOfDouble=" + numberOfDouble +
                ", chamberArea=" + chamberArea +
                ", bathroom='" + bathroom + '\'' +
                ", tv='" + tv + '\'' +
                ", balcony='" + balcony + '\'' +
                ", fridge='" + fridge + '\'' +
                ", bathtub='" + bathtub + '\'' +
                ", soundproof='" + soundproof + '\'' +
                ", pricePerNight='" + pricePerNight + '\'' +
                '}';
    }
}