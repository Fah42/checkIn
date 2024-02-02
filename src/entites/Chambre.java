package entites;

import dao.HotelDAO;

public class Chambre {
    int id;
    int chamberNumber;
    int nbSimpleBed;
    int nbDoubleBed;
    int chamberArea;
    int id_hotel;
    String bathroom;
    String tv;
    String balcony;
    String fridge;
    String bathtub;
    String soundproof;
    double pricePerNight;
    HotelDAO hotelDAO = new HotelDAO();
    public Chambre(int id, int id_hotel, int chamberNumber, int nbSimpleBed, int nbDoubleBed, int chamberArea, String bathroom, String tv, String balcony, String fridge, String bathtub, String soundproof, double pricePerNight) {
        this.id = id;
        this.id_hotel = id_hotel;
        this.chamberNumber = chamberNumber;
        this.nbSimpleBed = Chambre.this.nbSimpleBed;
        this.nbDoubleBed = Chambre.this.nbDoubleBed;
        this.chamberArea = chamberArea;
        this.bathroom = bathroom;
        this.tv = tv;
        this.balcony = balcony;
        this.fridge = fridge;
        this.bathtub = bathtub;
        this.soundproof = soundproof;
        this.pricePerNight = pricePerNight;
    }
    public Chambre(int chamberNumber, int nbSimpleBed, int nbDoubleBed, int chamberArea, String bathroom, String tv, String balcony, String fridge, String bathtub, String soundproof, double pricePerNight) {
        this.chamberNumber = chamberNumber;
        this.nbSimpleBed = Chambre.this.nbSimpleBed;
        this.nbDoubleBed = Chambre.this.nbDoubleBed;
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

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
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

    public int getnbSimpleBed() {
        return nbSimpleBed;
    }

    public void setnbSimpleBed(int nbSimpleBed) {
        this.nbSimpleBed = Chambre.this.nbSimpleBed;
    }

    public int getnbDoubleBed() {
        return nbDoubleBed;
    }

    public void setnbDoubleBed(int nbDoubleBed) {
        this.nbDoubleBed = Chambre.this.nbDoubleBed;
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

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return id +
                ", Chambre numero [" + chamberNumber + "]" +
                ", Lit Simple [" + nbSimpleBed + "]" +
                ", Lit Double [" + nbDoubleBed + "]" +
                ", Superficie [" + chamberArea + "]" +
                ", Salle de bain [" + bathroom + "]" +
                ", TV [" + tv + "]" +
                ", Balcon [" + balcony + ']' +
                ", Frigo [" + fridge + ']' +
                ", Baignoire [" + bathtub + ']' +
                ", Insonorise [" + soundproof + ']' +
                ", Prix par nuit [" + pricePerNight + ']' +
                ", Hotel [" + hotelDAO.getById(id_hotel).getNom() + "]";
    }
}
