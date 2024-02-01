package entites;

import java.sql.Date;

public class Reservation {
    int id;
    Date jour_arrive;
    Date jour_depart;
    int nb_personne;
    int id_client;
    int id_chambre;
    int nbNight;
    public Reservation(Date jour_arrive, Date jour_depart, int nb_personne, int id_client, int id_chambre, int id) {
        this.jour_arrive = jour_arrive;
        this.jour_depart = jour_depart;
        this.nb_personne = nb_personne;
        this.id_client = id_client;
        this.id_chambre = id_chambre;
        this.id = id;
    }
    public Reservation(Date jour_arrive, Date jour_depart, int nb_personne, int id_client, int id_chambre) {
        this.jour_arrive = jour_arrive;
        this.jour_depart = jour_depart;
        this.nb_personne = nb_personne;
        this.id_client = id_client;
        this.id_chambre = id_chambre;
    }
    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getJour_arrive() {
        return jour_arrive;
    }

    public void setJour_arrive(Date jour_arrive) {
        this.jour_arrive = jour_arrive;
    }

    public Date getJour_depart() {
        return jour_depart;
    }

    public void setJour_depart(Date jour_depart) {
        this.jour_depart = jour_depart;
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

    public int getNbNight() {
        return nbNight;
    }

    public void setNbNight(int nbNight) {
        this.nbNight = nbNight;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", jour_arrive=" + jour_arrive +
                ", jour_depart=" + jour_depart +
                ", nb_personne=" + nb_personne +
                ", id_client=" + id_client +
                ", id_chambre=" + id_chambre +
                ", nbNight=" + nbNight +
                '}';
    }
}
