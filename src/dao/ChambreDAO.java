package dao;

import entites.Database;
import entites.Chambre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChambreDAO {
    public static void save (Chambre chambre) {
        try {
            if (chambre.getId() != 0) {
                PreparedStatement ps = Database.connexion.prepareStatement("UPDATE chambre SET numero = ?, superficie = ?, sdb = ?, tv = ?, balcon = ?, frigo = ?, baignoire = ?, insonorise = ?, prixN = ?, simple = ?, double = ?, id_hotel = ? WHERE id = ?");
                ps.setInt(1, chambre.getChamberNumber());
                ps.setInt(2, chambre.getChamberArea());
                ps.setString(3, chambre.getBathroom());
                ps.setString(4, chambre.getTv());
                ps.setString(5, chambre.getBalcony());
                ps.setString(6, chambre.getFridge());
                ps.setString(7, chambre.getBathtub());
                ps.setString(8, chambre.getSoundproof());
                ps.setDouble(9, chambre.getPricePerNight());
                ps.setInt(10, chambre.getNumberOfSimple());
                ps.setInt(11, chambre.getNumberOfDouble());
                ps.setInt(12, chambre.getId_hotel());
                ps.setInt(13, chambre.getId());
                ps.executeUpdate();
                System.out.println("Update Ok !");
            } else {
                PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO chambre (numero, superficie, sdb, tv, balcon, frigo, baignoire, insonorise, prixN, simple, double, id_hotel) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, chambre.getChamberNumber());
                ps.setInt(2, chambre.getChamberArea());
                ps.setString(3, chambre.getBathroom());
                ps.setString(4, chambre.getTv());
                ps.setString(5, chambre.getBalcony());
                ps.setString(6, chambre.getFridge());
                ps.setString(7, chambre.getBathtub());
                ps.setString(8, chambre.getSoundproof());
                ps.setDouble(9, chambre.getPricePerNight());
                ps.setInt(10, chambre.getNumberOfSimple());
                ps.setInt(11, chambre.getNumberOfDouble());
                ps.setInt(12, chambre.getId_hotel());
                ps.executeUpdate();
                System.out.println("Insert Ok !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Chambre getById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();

            Chambre chambre = new Chambre();
            chambre.setId(resultat.getInt("id"));
            chambre.setChamberNumber(resultat.getInt("numero"));
            chambre.setChamberArea(resultat.getInt("superficie"));
            chambre.setBathroom(resultat.getString("sdb"));
            chambre.setTv(resultat.getString("tv"));
            chambre.setBalcony(resultat.getString("balcon"));
            chambre.setFridge(resultat.getString("frigo"));
            chambre.setBathtub(resultat.getString("baignoire"));
            chambre.setSoundproof(resultat.getString("insonorise"));
            chambre.setPricePerNight(resultat.getDouble("prixN"));
            chambre.setNumberOfSimple(resultat.getInt("simple"));
            chambre.setNumberOfDouble(resultat.getInt("double"));
            chambre.setId_hotel(resultat.getInt("id_hotel"));
            return chambre;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Chambre> getAll() {
        ArrayList<Chambre> chambres = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre");
            ResultSet resultat = ps.executeQuery();
            while(resultat.next()) {
                Chambre chambre = new Chambre();
                chambre.setId(resultat.getInt("id"));
                chambre.setChamberNumber(resultat.getInt("numero"));
                chambre.setChamberArea(resultat.getInt("superficie"));
                chambre.setBathroom(resultat.getString("sdb"));
                chambre.setTv(resultat.getString("tv"));
                chambre.setBalcony(resultat.getString("balcon"));
                chambre.setFridge(resultat.getString("frigo"));
                chambre.setBathtub(resultat.getString("baignoire"));
                chambre.setSoundproof(resultat.getString("insonorise"));
                chambre.setPricePerNight(resultat.getDouble("prixN"));
                chambre.setNumberOfSimple(resultat.getInt("simple"));
                chambre.setNumberOfDouble(resultat.getInt("double"));
                chambre.setId_hotel(resultat.getInt("id_hotel"));
                chambres.add(chambre);
            }
            return chambres;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM chambre WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Chambre Deleted");
        } catch (Exception e) {
            return;
        }
    }
    public ArrayList<Chambre> searchChambres(String searchTerm) {
        ArrayList<Chambre> chambres = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE numero LIKE ? OR superficie LIKE ? OR sdb LIKE ? OR tv LIKE ? OR balcon LIKE ? OR frigo LIKE ? OR baignoire LIKE ? OR insonorise LIKE ? OR prixN LIKE ? OR 'simple' LIKE ? OR 'double' LIKE ? OR id_hotel LIKE ?");
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ps.setString(3, "%" + searchTerm + "%");
            ps.setString(4, "%" + searchTerm + "%");
            ps.setString(5, "%" + searchTerm + "%");
            ps.setString(6, "%" + searchTerm + "%");
            ps.setString(7, "%" + searchTerm + "%");
            ps.setString(8, "%" + searchTerm + "%");
            ps.setString(9, "%" + searchTerm + "%");
            ps.setString(10, "%" + searchTerm + "%");
            ps.setString(11, "%" + searchTerm + "%");
            ps.setString(12, "%" + searchTerm + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Chambre chambre = new Chambre();
                chambre.setId(resultat.getInt("id"));
                chambre.setChamberNumber(resultat.getInt("numero"));
                chambre.setChamberArea(resultat.getInt("superficie"));
                chambre.setBathroom(resultat.getString("sdb"));
                chambre.setTv(resultat.getString("tv"));
                chambre.setBalcony(resultat.getString("balcon"));
                chambre.setFridge(resultat.getString("frigo"));
                chambre.setBathtub(resultat.getString("baignoire"));
                chambre.setSoundproof(resultat.getString("insonorise"));
                chambre.setPricePerNight(resultat.getDouble("prixN"));
                chambre.setNumberOfSimple(resultat.getInt("simple"));
                chambre.setNumberOfDouble(resultat.getInt("double"));
                chambre.setId_hotel(resultat.getInt("id_hotel"));
                chambres.add(chambre);
            }
            return chambres;
        } catch (Exception e) {
            return null;
        }
    }
    public Chambre getByIdHotel(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id_hotel = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();

            Chambre chambre = new Chambre();
            chambre.setId(resultat.getInt("id"));
            chambre.setChamberNumber(resultat.getInt("numero"));
            chambre.setChamberArea(resultat.getInt("superficie"));
            chambre.setBathroom(resultat.getString("sdb"));
            chambre.setTv(resultat.getString("tv"));
            chambre.setBalcony(resultat.getString("balcon"));
            chambre.setFridge(resultat.getString("frigo"));
            chambre.setBathtub(resultat.getString("baignoire"));
            chambre.setSoundproof(resultat.getString("insonorise"));
            chambre.setPricePerNight(resultat.getDouble("prixN"));
            chambre.setNumberOfSimple(resultat.getInt("simple"));
            chambre.setNumberOfDouble(resultat.getInt("double"));
            chambre.setId_hotel(resultat.getInt("id_hotel"));
            return chambre;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteByIdHotel(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM chambre WHERE id_hotel = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Chambre Deleted");
        } catch (Exception e) {
            return;
        }
    }
}
