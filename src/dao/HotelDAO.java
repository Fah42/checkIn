package dao;
import entites.Hotel;
import entites.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HotelDAO {
    public static void save (Hotel hotel) {
    try {
        if (hotel.getId() != 0) {
            PreparedStatement ps = Database.connexion.prepareStatement("UPDATE hotel SET nom = ?, adresse = ?, ville = ?, description = ?, parking = ?, wifi = ?, checkIn = ?, checkOut = ?, piscine = ?, navette = ?, cat = ?, id_societe = ? WHERE id = ?");
            ps.setString(1, hotel.getNom());
            ps.setString(2, hotel.getAdress());
            ps.setString(3, hotel.getCity());
            ps.setString(4, hotel.getDesc());
            ps.setString(5, hotel.getParking());
            ps.setString(6, hotel.getWifi());
            ps.setDate(7, hotel.getCheckIn());
            ps.setDate(8, hotel.getCheckOut());
            ps.setString(9, hotel.getPool());
            ps.setString(10, hotel.getShuttle());
            ps.setString(11, hotel.getAnimals());
            ps.setInt(12, hotel.getStar());
            ps.setInt(13, hotel.getId_societe());
            ps.setInt(14, hotel.getId());
            ps.executeUpdate();
            System.out.println("Update Ok !");
        } else {
            PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO hotel (nom, adresse, ville, description, parking, wifi, checkIn, checkOut, piscine, navette, cat, id_categorie) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, hotel.getNom());
            ps.setString(2, hotel.getAdress());
            ps.setString(3, hotel.getCity());
            ps.setString(4, hotel.getDesc());
            ps.setString(5, hotel.getParking());
            ps.setString(6, hotel.getWifi());
            ps.setDate(7, hotel.getCheckIn());
            ps.setDate(8, hotel.getCheckOut());
            ps.setString(9, hotel.getPool());
            ps.setString(10, hotel.getShuttle());
            ps.setString(11, hotel.getAnimals());
            ps.setInt(12, hotel.getStar());
            ps.setInt(13, hotel.getId_societe());
            ps.executeUpdate();
            System.out.println("Insert Ok !");
        }
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
    public Hotel getById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();

            Hotel hotel = new Hotel();
            hotel.setId(resultat.getInt("id"));
            hotel.setNom(resultat.getString("nom"));
            hotel.setAdress(resultat.getString("adresse"));
            hotel.setCity(resultat.getString("ville"));
            hotel.setDesc(resultat.getString("description"));
            hotel.setParking(resultat.getString("parking"));
            hotel.setWifi(resultat.getString("wifi"));
            hotel.setCheckIn(resultat.getDate("checkIn"));
            hotel.setCheckOut(resultat.getDate("checkOut"));
            hotel.setPool(resultat.getString("piscine"));
            hotel.setShuttle(resultat.getString("navette"));
            hotel.setAnimals(resultat.getString("animaux"));
            hotel.setStar(resultat.getInt("cat"));
            hotel.setId_societe(resultat.getInt("id_societe"));
            return hotel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Hotel> getAll() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel");
            ResultSet resultat = ps.executeQuery();
            while(resultat.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultat.getInt("id"));
                hotel.setNom(resultat.getString("nom"));
                hotel.setAdress(resultat.getString("adresse"));
                hotel.setCity(resultat.getString("ville"));
                hotel.setDesc(resultat.getString("description"));
                hotel.setParking(resultat.getString("parking"));
                hotel.setWifi(resultat.getString("wifi"));
                hotel.setCheckIn(resultat.getDate("checkIn"));
                hotel.setCheckOut(resultat.getDate("checkOut"));
                hotel.setPool(resultat.getString("piscine"));
                hotel.setShuttle(resultat.getString("navette"));
                hotel.setAnimals(resultat.getString("animaux"));
                hotel.setStar(resultat.getInt("cat"));
                hotel.setId_societe(resultat.getInt("id_societe"));
                hotels.add(hotel);
            }
            return hotels;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteById(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM hotel WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Hotel Deleted");
        } catch (Exception e) {
            return;
        }
    }
    public ArrayList<Hotel> searchHotels(String searchTerm) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE nom LIKE ? OR adresse LIKE ? OR ville LIKE ? OR description LIKE ? OR parking LIKE ? OR wifi LIKE ? OR checkIn LIKE ? OR checkOut LIKE ? OR piscine LIKE ? OR navette LIKE ? OR cat LIKE ? OR id_societe LIKE ?");
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
                Hotel hotel = new Hotel();
                hotel.setId(resultat.getInt("id"));
                hotel.setNom(resultat.getString("nom"));
                hotel.setAdress(resultat.getString("adresse"));
                hotel.setCity(resultat.getString("ville"));
                hotel.setDesc(resultat.getString("description"));
                hotel.setParking(resultat.getString("parking"));
                hotel.setWifi(resultat.getString("wifi"));
                hotel.setCheckIn(resultat.getDate("checkIn"));
                hotel.setCheckOut(resultat.getDate("checkOut"));
                hotel.setPool(resultat.getString("piscine"));
                hotel.setShuttle(resultat.getString("navette"));
                hotel.setAnimals(resultat.getString("animaux"));
                hotel.setStar(resultat.getInt("cat"));
                hotel.setId_societe(resultat.getInt("id_societe"));
                hotels.add(hotel);
            }
            return hotels;
        } catch (Exception e) {
            return null;
        }
    }

    public Hotel getByIdCompany(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE id_societe= ?");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            resultat.next();

            Hotel hotel = new Hotel();
            hotel.setId(resultat.getInt("id"));
            hotel.setNom(resultat.getString("nom"));
            hotel.setAdress(resultat.getString("adresse"));
            hotel.setCity(resultat.getString("ville"));
            hotel.setDesc(resultat.getString("description"));
            hotel.setParking(resultat.getString("parking"));
            hotel.setWifi(resultat.getString("wifi"));
            hotel.setCheckIn(resultat.getDate("checkIn"));
            hotel.setCheckOut(resultat.getDate("checkOut"));
            hotel.setPool(resultat.getString("piscine"));
            hotel.setShuttle(resultat.getString("navette"));
            hotel.setAnimals(resultat.getString("animaux"));
            hotel.setStar(resultat.getInt("cat"));
            hotel.setId_societe(resultat.getInt("id_societe"));
            return hotel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteByIdCompany(int id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM hotel WHERE id_societe = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Hotel Deleted");
        } catch (Exception e) {
            return;
        }
    }
}
