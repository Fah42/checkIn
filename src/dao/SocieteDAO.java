package dao;

import entites.Client;
import entites.Societe;
import entites.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SocieteDAO {
    public void save(Societe societe) {
        try {
            if(societe.getId() != 0) {
                PreparedStatement ps = Database.connexion.prepareStatement("UPDATE societe SET siret = ?, nom = ?, adresse = ?, WHERE id = ?");
                ps.setInt(1, societe.getSiret());
                ps.setString(2, societe.getName());
                ps.setString(3, societe.getAdress());
                ps.setInt(5, societe.getId());
                ps.executeUpdate();
                System.out.println("Update Ok !");
            } else {
                PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO societe (siret, nom, adresse) VALUES (?,?,?)");
                ps.setInt(1, societe.getSiret());
                ps.setString(2, societe.getName());
                ps.setString(3, societe.getAdress());
                ps.executeUpdate();
                System.out.println("Insert Ok !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    public ArrayList<Societe> getAll() {
        ArrayList<Societe> societes = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM societe");
            ResultSet resultat = ps.executeQuery();
            while(resultat.next()) {
                Societe u = new Societe();
                u.setId(resultat.getInt("id"));
                u.setName(resultat.getString("nom"));
                u.setSiret(resultat.getInt("siret"));
                u.setAdress(resultat.getString("adresse"));
                societes.add(u);
            }
            return societes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Societe getById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM societe WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();

            Societe u = new Societe();
            u.setId(resultat.getInt("id"));
            u.setName(resultat.getString("nom"));
            u.setSiret(resultat.getInt("siret"));
            u.setAdress(resultat.getString("adresse"));
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM societe WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Societe Deleted");
        } catch (Exception e) {
            return;
        }
    }
    public ArrayList<Societe> searchSocietes(String searchTerm) {
        ArrayList<Societe> societes = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM societe WHERE siret LIKE ? OR nom LIKE ? OR adress LIKE ?");
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ps.setString(3, "%" + searchTerm + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Societe societe = new Societe();
                societe.setId(resultat.getInt("id"));
                societe.setSiret(resultat.getInt("siret"));
                societe.setName(resultat.getString("nom"));
                societe.setAdress(resultat.getString("adresse"));
                societes.add(societe);
            }
            return societes;
        } catch (Exception e) {
            return null;
        }
    }

}
