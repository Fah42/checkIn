package dao;

import entites.Database;
import entites.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservationDAO {
    public static void save (Reservation reservation) {
        try {
            if (reservation.getId() != 0) {
                PreparedStatement ps = Database.connexion.prepareStatement("UPDATE reservation SET id_chambre = ?, id_client = ?, nb_personne = ?, jour_arrive = ?, jour_depart = ?, nombre_de_nuit = ? WHERE id = ?");
                ps.setInt(1, reservation.getId_chambre());
                ps.setInt(2, reservation.getId_client());
                ps.setInt(3, reservation.getNb_personne());
                ps.setDate(4, reservation.getJour_arrive());
                ps.setDate(5, reservation.getJour_depart());
                ps.setInt(6, reservation.getNbNight());
                ps.setInt(7, reservation.getId());
                ps.executeUpdate();
                System.out.println("Update Ok !");
            } else {
                PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO reservation (id_chambre, id_client, nb_personne, jour_arrive, jour_depart, nombre_de_nuit) VALUES (?,?,?,?,?,?)");
                ps.setInt(1, reservation.getId_chambre());
                ps.setInt(2, reservation.getId_client());
                ps.setInt(3, reservation.getNb_personne());
                ps.setDate(4, reservation.getJour_arrive());
                ps.setDate(5, reservation.getJour_depart());
                ps.setInt(6,reservation.getNbNight());
                ps.executeUpdate();
                System.out.println("Insert Ok !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Reservation getById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            Reservation reservation = new Reservation();
            reservation.setId(resultat.getInt("id"));
            reservation.setId_chambre(resultat.getInt("id_chambre"));
            reservation.setId_client(resultat.getInt("id_client"));
            reservation.setNb_personne(resultat.getInt("nb_personne"));
            reservation.setJour_arrive(resultat.getDate("jour_arrive"));
            reservation.setJour_depart(resultat.getDate("jour_depart"));
            reservation.setNbNight(resultat.getInt("nombre_de_nuit"));
            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation");
            ResultSet resultat = ps.executeQuery();
            while(resultat.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultat.getInt("id"));
                reservation.setId_chambre(resultat.getInt("id_chambre"));
                reservation.setId_client(resultat.getInt("id_client"));
                reservation.setNb_personne(resultat.getInt("nb_personne"));
                reservation.setJour_arrive(resultat.getDate("jour_arrive"));
                reservation.setJour_depart(resultat.getDate("jour_depart"));
                reservation.setNbNight(resultat.getInt("nombre_de_nuit"));
                reservations.add(reservation);
            }
            return reservations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reservation Deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Reservation> searchReservations(String searchTerm) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id_chambre LIKE ? OR id_client LIKE ? OR nb_personne LIKE ? OR jour_arrive LIKE ? OR jour_depart LIKE ? OR nombre_de_nuit LIKE ?");
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ps.setString(3, "%" + searchTerm + "%");
            ps.setString(4, "%" + searchTerm + "%");
            ps.setString(5, "%" + searchTerm + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultat.getInt("id"));
                reservation.setId_chambre(resultat.getInt("id_chambre"));
                reservation.setId_client(resultat.getInt("id_client"));
                reservation.setNb_personne(resultat.getInt("nb_personne"));
                reservation.setJour_arrive(resultat.getDate("jour_arrive"));
                reservation.setJour_depart(resultat.getDate("jour_depart"));
                reservation.setNbNight(resultat.getInt("nombre_de_nuit"));
                reservations.add(reservation);
            }
            return reservations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Reservation getByIdChambre(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id_chambre = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            Reservation reservation = new Reservation();
            reservation.setId(resultat.getInt("id"));
            reservation.setId_chambre(resultat.getInt("id_chambre"));
            reservation.setId_client(resultat.getInt("id_client"));
            reservation.setNb_personne(resultat.getInt("nb_personne"));
            reservation.setJour_arrive(resultat.getDate("jour_arrive"));
            reservation.setJour_depart(resultat.getDate("jour_depart"));
            reservation.setNbNight(resultat.getInt("nombre_de_nuit"));
            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Reservation getByIdClient(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id_client = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            Reservation reservation = new Reservation();
            reservation.setId(resultat.getInt("id"));
            reservation.setId_chambre(resultat.getInt("id_chambre"));
            reservation.setId_client(resultat.getInt("id_client"));
            reservation.setNb_personne(resultat.getInt("nb_personne"));
            reservation.setJour_arrive(resultat.getDate("jour_arrive"));
            reservation.setJour_depart(resultat.getDate("jour_depart"));
            reservation.setNbNight(resultat.getInt("nombre_de_nuit"));
            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteByIdChambre(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_chambre = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reservation Deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteByIdClient(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_client = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reservation Deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
