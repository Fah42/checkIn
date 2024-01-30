package entites;

import java.sql.Date;

public class Reservation {
    Date heure_arrive;
    Date heure_depart;
    int nb_personne;
    int id_client;
    int id_chambre;
    int id;

    public Reservation(Date heure_arrive, Date heure_depart, int nb_personne, int id_client, int id_chambre, int id) {
        this.heure_arrive = heure_arrive;
        this.heure_depart = heure_depart;
        this.nb_personne = nb_personne;
        this.id_client = id_client;
        this.id_chambre = id_chambre;
        this.id = id;
    }
    public Reservation(Date heure_arrive, Date heure_depart, int nb_personne, int id_client, int id_chambre) {
        this.heure_arrive = heure_arrive;
        this.heure_depart = heure_depart;
        this.nb_personne = nb_personne;
        this.id_client = id_client;
        this.id_chambre = id_chambre;
    }
    public Reservation() {
    }

    public Date getHeure_arrive() {
        return heure_arrive;
    }

    public void setHeure_arrive(Date heure_arrive) {
        this.heure_arrive = heure_arrive;
    }

    public Date getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(Date heure_depart) {
        this.heure_depart = heure_depart;
    }

    public int getNb_personne() {
        return nb_personne;
    }

    public void setNb_personne(int nb_personne) {
        this.nb_personne = nb_personne;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_chambre() {
        return id_chambre;
    }

    public void setId_chambre(int id_chambre) {
        this.id_chambre = id_chambre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "heure_arrive=" + heure_arrive +
                ", heure_depart=" + heure_depart +
                ", nb_personne=" + nb_personne +
                ", id_client=" + id_client +
                ", id_chambre=" + id_chambre +
                ", id=" + id +
                '}';
    }
}
