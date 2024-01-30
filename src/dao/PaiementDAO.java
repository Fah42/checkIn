package dao;

import entites.Database;
import entites.Paiement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PaiementDAO {
    public static void save (Paiement paiement) {
        try {
            if (paiement.getId() != 0) {
                PreparedStatement ps = Database.connexion.prepareStatement("UPDATE paiement SET dateP = ?, montant = ?, methode = ?, id_reservation = ?  WHERE id = ?");
                ps.setDate(1, paiement.getDateP());
                ps.setDouble(2, paiement.getMontant());
                ps.setString(3, paiement.getMethode());
                ps.setInt(4, paiement.getId_reservation());
                ps.setInt(5, paiement.getId());
                ps.executeUpdate();
                System.out.println("Update Ok !");
            } else {
                PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO paiement (dateP, montant, methode, id_reservation) VALUES (?,?,?,?)");
                ps.setDate(1, paiement.getDateP());
                ps.setDouble(2, paiement.getMontant());
                ps.setString(3, paiement.getMethode());
                ps.setInt(4, paiement.getId_reservation());
                ps.executeUpdate();
                System.out.println("Insert Ok !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Paiement getById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            Paiement paiement = new Paiement();
            paiement.setId(resultat.getInt("id"));
            paiement.setDateP(resultat.getDate("dateP"));
            paiement.setMontant(resultat.getDouble("montant"));
            paiement.setMethode(resultat.getString("methode"));
            paiement.setId_reservation(resultat.getInt("id_reservation"));
            return paiement;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Paiement> getAll() {
        ArrayList<Paiement> paiements = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement");
            ResultSet resultat = ps.executeQuery();
            while(resultat.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(resultat.getInt("id"));
                paiement.setDateP(resultat.getDate("dateP"));
                paiement.setMontant(resultat.getDouble("montant"));
                paiement.setMethode(resultat.getString("methode"));
                paiement.setId_reservation(resultat.getInt("id_reservation"));
                paiements.add(paiement);
            }
            return paiements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Paiement Deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Paiement> searchPaiements(String searchTerm) {
        ArrayList<Paiement> paiements = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE dateP LIKE ? OR montant LIKE ? OR methode LIKE ?");
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ps.setString(3, "%" + searchTerm + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(resultat.getInt("id"));
                paiement.setDateP(resultat.getDate("dateP"));
                paiement.setMontant(resultat.getDouble("montant"));
                paiement.setMethode(resultat.getString("methode"));
                paiement.setId_reservation(resultat.getInt("id_reservation"));
                paiements.add(paiement);
            }
            return paiements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Paiement getByIdReservation(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE id_reservation = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            Paiement paiement = new Paiement();
            paiement.setId(resultat.getInt("id"));
            paiement.setDateP(resultat.getDate("dateP"));
            paiement.setMontant(resultat.getDouble("montant"));
            paiement.setMethode(resultat.getString("methode"));
            paiement.setId_reservation(resultat.getInt("id_reservation"));
            return paiement;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteByIdReservation(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id_reservation = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Paiement Deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
