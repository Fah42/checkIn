package vues;

import dao.*;
import entites.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Database.connect();

        int userChoice = 99;
        while (userChoice != 0) {
            userChoice = menu();
            if (userChoice == 1) {
                displayCompany();
            } else if (userChoice == 2) {
                addCompany();
            } else if (userChoice == 3) {
                modifyCompany();
            } else if (userChoice == 4) {
                deleteCompany();
            } else if (userChoice == 5) {
                searchCompany();
            } else if (userChoice == 6) {
                displayClient();
            } else if (userChoice == 7) {
                addClient();
            } else if (userChoice == 8) {
                modifyClient();
            } else if (userChoice == 9) {
                deleteClient();
            } else if (userChoice == 10) {
                searchClient();
            } else if (userChoice == 11) {
                displayHotel();
            } else if (userChoice == 12) {
                addHotel();
            } else if (userChoice == 13) {
                modifyHotel();
            } else if (userChoice == 14) {
                deleteHotel();
            } else if (userChoice == 15) {
                searchHotel();
            } else if (userChoice == 16) {
                addOrder();
            } else if (userChoice == 17) {
                deleteOrder();
            } else if (userChoice == 18) {
                displaySupplier();
            } else if (userChoice == 19) {
                addSupplier();
            } else if (userChoice == 20) {
                modifySupplier();
            } else if (userChoice == 21) {
                deleteSupplier();
            } else if (userChoice == 22) {
                searchSupplier();
            } else if (userChoice == 23) {
                displayStock();
            } else if (userChoice == 24) {
                addStock();
            } else if (userChoice == 25) {
                deleteStock();
            } else if (userChoice == 26) {
                displayPaiement();
            } else if (userChoice == 27) {
                addPaiement();
            } else if (userChoice == 28) {
                modifyPaiement();
            } else if (userChoice == 29) {
                deletePaiement();
            }
        }
    }

    public static boolean isDateFormatValid(String userInput, DateTimeFormatter timeFormatter) {
        try {
            LocalDate.parse(userInput, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Double alreadyPaid(int id_commande) {
        return 0.0;
    }

    public static boolean isStringValid(@org.jetbrains.annotations.NotNull String stringToCheck) {
        return stringToCheck.length() <= 50 && !stringToCheck.isEmpty();
    }

    public static void displayCompany() {
        ArrayList<Societe> societes = new ArrayList<>();
        societes = new SocieteDAO().getAll();

        for (Societe societe : societes) {
            System.out.println(societe);
        }
    }

    public static void addCompany() {
        SocieteDAO societeDAO = new SocieteDAO();
        Societe societe = new Societe();
        String name;
        String adress;
        String siret;
        String response;
        boolean isInputValid = false;

        do {
            displayCompany();
            System.out.println("------ Ajout de Societe ------");
            while (!isInputValid) {
                System.out.println("Veuillez entrer le numero de siret de la societe : ");
                siret = scanner.nextLine();
                if (isStringValid(siret)) {
                    societe.setSiret(siret);
                    isInputValid = true;
                } else {
                    System.out.println("Le numero de siret entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer le nom de la societe : ");
                name = scanner.nextLine();
                if (isStringValid(name)) {
                    societe.setName(name);
                    isInputValid = true;
                } else {
                    System.out.println("Le nom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'adresse de la societe : ");
                adress = scanner.nextLine();
                if (isStringValid(adress)) {
                    societe.setAdress(adress);
                    isInputValid = true;
                } else {
                    System.out.println("L'adresse entré n'est pas valide. Veuillez réessayer.");
                }
            }

            societeDAO.save(societe);
            System.out.println("Voulez-vous ajouter une autre societe ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void modifyCompany() {
        SocieteDAO societeDAO = new SocieteDAO();
        Societe societe = new Societe();
        String name;
        String adress;
        String siret;
        String response;
        int userChoice;
        boolean isInputValid = false;

        do {
            System.out.println("------ Modifier une Societe ------");
            while (true) {
                displayCompany();
                System.out.println("Veuillez entrer l'id de la societe à modifier, uniquement en valeur numérique : ");
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    scanner.nextLine();
                    societe = societeDAO.getById(userChoice);
                    if (societe != null) {
                        break;
                    } else {
                        System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le numero de siret de la societe : ");
                siret = scanner.nextLine();
                if (isStringValid(siret)) {
                    societe.setSiret(siret);
                    isInputValid = true;
                } else {
                    System.out.println("Le numero de siret entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer le nom de la societe : ");
                name = scanner.nextLine();
                if (isStringValid(name)) {
                    societe.setName(name);
                    isInputValid = true;
                } else {
                    System.out.println("Le nom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'adresse de la societe : ");
                adress = scanner.nextLine();
                if (isStringValid(adress)) {
                    societe.setAdress(adress);
                    isInputValid = true;
                } else {
                    System.out.println("L'adresse entré n'est pas valide. Veuillez réessayer.");
                }
            }

            societeDAO.save(societe);
            System.out.println("Voulez-vous modifier une autre societe ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void deleteCompany() {
        SocieteDAO societeDAO = new SocieteDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotel;
        Societe societe;
        String response;
        String areYouSure;
        int userChoice;
        do {
            System.out.println("Suppression d'une Societe");
            displayCompany();
            System.out.println("Veuillez entrer l'id de la societe à supprimer, uniquement en valeur numérique : ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                societe = societeDAO.getById(userChoice);
                if (societe != null) {
                    hotel = hotelDAO.getByIdCompany(userChoice);
                    System.out.println("Si La societe dispose d'un ou plusieurs hotels qui seront aussi TOTALEMENT \uD83D\uDCA5DETRUIT\uD83D\uDCA5, t'es sur de toi (repondre par oui ou non)?");
                    areYouSure = scanner.nextLine();
                    while (!"Oui".equalsIgnoreCase(areYouSure) && !"Non".equalsIgnoreCase(areYouSure)) {
                        System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                        areYouSure = scanner.nextLine();
                    }
                    if ("Oui".equalsIgnoreCase(areYouSure)) {
                        if (hotel != null) {
                            chambreDAO.deleteByIdHotel(hotel.getId());
                            hotelDAO.deleteByIdCompany(userChoice);
                        }
                        societeDAO.deleteById(userChoice);
                    }
                } else {
                    System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                }
            } else {
                System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }

            System.out.println("Voulez-vous supprimer une autre societe ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void searchCompany() {
        SocieteDAO societeDAO = new SocieteDAO();
        ArrayList<Societe> searchResults = new ArrayList<>();
        String search;
        String response;

        do {
            System.out.println("------ Recherche d'une societe ------");
            System.out.println("Veuillez entrer le terme de la recherche ");
            search = scanner.nextLine();
            searchResults = societeDAO.searchSocietes(search);
            if (searchResults != null) {
                System.out.println("Resultat de la recherche : ");
                for (Societe societe : searchResults) {
                    System.out.println(societe);
                }
            } else {
                System.out.println("Aucun resultat trouve pour : " + search);
            }
            System.out.println("Voulez-vous rechercher une autre societe ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void displayClient() {
        ArrayList<Client> clients = new ArrayList<>();
        clients = new ClientDAO().getAll();

        System.out.print("------ Affichage des Clients ------\n");
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public static void addClient() {
        ClientDAO clientDAO = new ClientDAO();
        Client client = new Client();
        boolean isInputValid = false;
        String firstname;
        String lastname;
        String response;
        String city;
        String adress;
        String email;
        String sexe;
        String numTel;
        String country;
        int age;

        do {
            displayClient();
            System.out.println("------ Ajout de Client ------");
            while (!isInputValid) {
                System.out.println("Veuillez entrer le nom du client : ");
                lastname = scanner.nextLine();
                if (client.setNom(lastname)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le nom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer le prenom du client : ");
                firstname = scanner.nextLine();
                if (client.setPrenom(firstname)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le prenom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (true) {
                System.out.println("Veuillez entrer l'age uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    if (age <= 0) {
                        System.out.println("Veuillez entrer une valeur positive.");
                    } else {
                        scanner.nextLine();
                        client.setAge(age);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le ville du client : ");
                city = scanner.nextLine();

                if (client.setVille(city)) {
                    isInputValid = true;
                } else {
                    System.out.println("La ville entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'adresse du client , numero de rue, nom de rue, zip code: ");
                adress = scanner.nextLine();

                if (client.setAdress(adress)) {
                    isInputValid = true;
                } else {
                    System.out.println("L'adresse entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'email du client : ");
                email = scanner.nextLine();

                if (client.setEmail(email)) {
                    isInputValid = true;
                } else {
                    System.out.println("L'email entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            do {
                System.out.println("Veuillez entrer le sexe du client (homme / femme): ");
                sexe = scanner.nextLine();
                client.setSexe(sexe);
            } while (!"homme".equalsIgnoreCase(sexe) && !"femme".equalsIgnoreCase(sexe));

            while (!isInputValid) {
                System.out.println("Veuillez entrer le numero de telephone du client : ");
                numTel = scanner.nextLine();

                if (client.setNotel(numTel)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le numero de telephone entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer le pays du client : ");
                country = scanner.nextLine();

                if (client.setCountry(country)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le pays entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            clientDAO.save(client);
            System.out.println("Voulez-vous ajouter un autre client ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void modifyClient() {
        ClientDAO clientDAO = new ClientDAO();
        Client client = new Client();
        boolean isInputValid = false;
        String firstname;
        String lastname;
        String response;
        String city;
        String adress;
        String email;
        String sexe;
        String numTel;
        String country;
        int age;
        int userChoice;

        do {
            System.out.println("------ Modifier un Client ------");
            while (true) {
                displayClient();
                System.out.println("Veuillez entrer l'id de la societe à modifier, uniquement en valeur numérique : ");
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    scanner.nextLine();
                    client = clientDAO.getById(userChoice);
                    if (client != null) {
                        break;
                    } else {
                        System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le nom du client : ");
                lastname = scanner.nextLine();
                if (client.setNom(lastname)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le nom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer le prenom du client : ");
                firstname = scanner.nextLine();
                if (client.setPrenom(firstname)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le prenom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (true) {
                System.out.println("Veuillez entrer l'age uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    if (age <= 0) {
                        System.out.println("Veuillez entrer une valeur positive.");
                    } else {
                        scanner.nextLine();
                        client.setAge(age);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le ville du client : ");
                city = scanner.nextLine();

                if (client.setVille(city)) {
                    isInputValid = true;
                } else {
                    System.out.println("La ville entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'adresse du client , numero de rue, nom de rue, zip code: ");
                adress = scanner.nextLine();

                if (client.setAdress(adress)) {
                    isInputValid = true;
                } else {
                    System.out.println("L'adresse entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'email du client : ");
                email = scanner.nextLine();

                if (client.setEmail(email)) {
                    isInputValid = true;
                } else {
                    System.out.println("L'email entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            do {
                System.out.println("Veuillez entrer le sexe du client (homme / femme): ");
                sexe = scanner.nextLine();
                client.setSexe(sexe);
            } while (!"homme".equalsIgnoreCase(sexe) && !"femme".equalsIgnoreCase(sexe));

            while (!isInputValid) {
                System.out.println("Veuillez entrer le numero de telephone du client : ");
                numTel = scanner.nextLine();

                if (client.setNotel(numTel)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le numero de telephone entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer le pays du client : ");
                country = scanner.nextLine();

                if (client.setCountry(country)) {
                    isInputValid = true;
                } else {
                    System.out.println("Le pays entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            clientDAO.save(client);
            System.out.println("Voulez-vous modifier un autre client ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void deleteClient() {
        ClientDAO clientDAO = new ClientDAO();
        Client client;
        String response;
        String areYouSure;
        int userChoice;
        do {
            System.out.println("Suppression d'un Client");
            displayClient();
            System.out.println("Veuillez entrer l'id du client à supprimer, uniquement en valeur numérique : ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                client = clientDAO.getById(userChoice);
                if (client != null) {
                    System.out.println("les donnees du client seront TOTALEMENT \uD83D\uDCA5DETRUIT\uD83D\uDCA5, t'es sur de toi (repondre par oui ou non)?");
                    areYouSure = scanner.nextLine();
                    while (!"Oui".equalsIgnoreCase(areYouSure) && !"Non".equalsIgnoreCase(areYouSure)) {
                        System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                        areYouSure = scanner.nextLine();
                    }
                    if ("Oui".equalsIgnoreCase(areYouSure)) {
                        clientDAO.deleteById(userChoice);
                    }
                } else {
                    System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                }
            } else {
                System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }

            System.out.println("Voulez-vous supprimer un autre client ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void searchClient() {
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> searchResults = new ArrayList<>();
        String search;
        String response;

        do {
            System.out.println("------ Recherche de Client ------");
            System.out.println("Veuillez entrer le terme de la recherche ");
            search = scanner.nextLine();
            searchResults = clientDAO.searchClients(search);
            if (searchResults != null) {
                System.out.println("Resultat de la recherche : ");
                for (Client client : searchResults) {
                    System.out.println(client);
                }
            } else {
                System.out.println("Aucun resultat trouve pour : " + search);
            }
            System.out.println("Voulez-vous rechercher un autre client ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void displayHotel() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels = new HotelDAO().getAll();

        System.out.print("------ Affichage des Hotels ------\n");
        for (Hotel hotel : hotels) {
            System.out.println(hotel);
        }
    }

    public static void addHotel() {
        HotelDAO hotelDAO = new HotelDAO();
        SocieteDAO societeDAO = new SocieteDAO();
        Hotel hotel = new Hotel();
        boolean isInputValid = false;
        String name;
        String adress;
        String city;
        String desc;
        String parking;
        String wifi;
        String checkIn;
        String checkOut;
        String pool;
        String shuttle;
        String animal;
        String response;
        int stars;
        int id_company;
        LocalTime checkInTime = null;
        LocalTime checkOutTime = null;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        do {
            System.out.println("------ Ajout de Hotel ------");
            displayCompany();
            while (true) {
                System.out.println("Veuillez choisir à quelle societe appartiendra l'hotel en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_company = scanner.nextInt();
                    scanner.nextLine();
                    if (societeDAO.getById(id_company) != null) {
                        hotel.setId_societe(id_company);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le nom de l'hotel : ");
                name = scanner.nextLine();
                if (isStringValid(name)) {
                    hotel.setNom(name);
                    isInputValid = true;
                } else {
                    System.out.println("Le nom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'adresse de l'hotel : ");
                adress = scanner.nextLine();
                if (isStringValid(adress)) {
                    hotel.setAdress(adress);
                    isInputValid = true;
                } else {
                    System.out.println("L'adresse entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (true) {
                System.out.println("Veuillez entrer le nombre d'etoile uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    stars = scanner.nextInt();
                    if (stars <= 0 || stars > 5) {
                        System.out.println("Veuillez entrer une valeur positive. Qui ne depasse pas 5");
                    } else {
                        scanner.nextLine();
                        hotel.setStar(stars);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le ville du hotel : ");
                city = scanner.nextLine();

                if (isStringValid(city)) {
                    hotel.setCity(city);
                    isInputValid = true;
                } else {
                    System.out.println("La ville entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer une description de l'hotel en 50 lettres maximum ");
                desc = scanner.nextLine();

                if (isStringValid(desc)) {
                    hotel.setAdress(desc);
                    isInputValid = true;
                } else {
                    System.out.println("La description entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'un parking ? : ");
                parking = scanner.nextLine();
                if ("oui".equalsIgnoreCase(parking) || "non".equalsIgnoreCase(parking)) {
                    hotel.setWifi(parking);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'un wifi ? : ");
                wifi = scanner.nextLine();
                if ("oui".equalsIgnoreCase(wifi) || "non".equalsIgnoreCase(wifi)) {
                    hotel.setWifi(wifi);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (checkInTime == null) {
                System.out.println("Veuillez entrer l'heure du check-in au format (HH:mm) : ");
                checkIn = scanner.nextLine();
                try {
                    checkInTime = LocalTime.parse(checkIn, timeFormatter);
                    java.sql.Time checkInTimeSql = java.sql.Time.valueOf(checkInTime);
                    hotel.setCheckIn(checkInTimeSql);
                } catch (DateTimeParseException e) {
                    System.out.println("Heure invalide, veuillez réessayer.");
                }
            }

            while (checkOutTime == null) {
                System.out.println("Veuillez entrer l'heure du check-out au format (HH:mm) : ");
                checkOut = scanner.nextLine();
                try {
                    checkOutTime = LocalTime.parse(checkOut, timeFormatter);
                    java.sql.Time checkOutTimeSql = java.sql.Time.valueOf(checkOutTime);
                    hotel.setCheckIn(checkOutTimeSql);
                } catch (DateTimeParseException e) {
                    System.out.println("Heure invalide, veuillez réessayer.");
                }
            }

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'un piscine ? : ");
                pool = scanner.nextLine();
                if ("oui".equalsIgnoreCase(pool) || "non".equalsIgnoreCase(pool)) {
                    hotel.setPool(pool);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'une navette ? : ");
                shuttle = scanner.nextLine();
                if ("oui".equalsIgnoreCase(shuttle) || "non".equalsIgnoreCase(shuttle)) {
                    hotel.setShuttle(shuttle);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel accepte t'il les animaux ? : ");
                animal = scanner.nextLine();
                if ("oui".equalsIgnoreCase(animal) || "non".equalsIgnoreCase(animal)) {
                    hotel.setAnimals(animal);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            hotelDAO.save(hotel);
            System.out.println("Voulez-vous ajouter un autre hotel ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void modifyHotel() {
        int userChoice;
        HotelDAO hotelDAO = new HotelDAO();
        SocieteDAO societeDAO = new SocieteDAO();
        Hotel hotel = new Hotel();
        boolean isInputValid = false;
        String name;
        String adress;
        String city;
        String desc;
        String parking;
        String wifi;
        String checkIn;
        String checkOut;
        String pool;
        String shuttle;
        String animal;
        String response;
        int stars;
        int id_company;
        LocalTime checkInTime = null;
        LocalTime checkOutTime = null;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        do {
            System.out.println("------ Modifier un Hotel ------");
            while (true) {
                displayHotel();
                System.out.println("Veuillez entrer l'id de l'hotel à modifier, uniquement en valeur numérique : ");
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    scanner.nextLine();
                    hotel = hotelDAO.getById(userChoice);
                    if (hotel != null) {
                        break;
                    } else {
                        System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }
            displayCompany();
            while (true) {
                System.out.println("Veuillez choisir à quelle societe appartiendra l'hotel en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_company = scanner.nextInt();
                    scanner.nextLine();
                    if (societeDAO.getById(id_company) != null) {
                        hotel.setId_societe(id_company);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le nom de l'hotel : ");
                name = scanner.nextLine();
                if (isStringValid(name)) {
                    hotel.setNom(name);
                    isInputValid = true;
                } else {
                    System.out.println("Le nom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer l'adresse de l'hotel : ");
                adress = scanner.nextLine();
                if (isStringValid(adress)) {
                    hotel.setAdress(adress);
                    isInputValid = true;
                } else {
                    System.out.println("L'adresse entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (true) {
                System.out.println("Veuillez entrer le nombre d'etoile uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    stars = scanner.nextInt();
                    if (stars <= 0 || stars > 5) {
                        System.out.println("Veuillez entrer une valeur positive. Qui ne depasse pas 5");
                    } else {
                        scanner.nextLine();
                        hotel.setStar(stars);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Veuillez entrer le ville du hotel : ");
                city = scanner.nextLine();

                if (isStringValid(city)) {
                    hotel.setCity(city);
                    isInputValid = true;
                } else {
                    System.out.println("La ville entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("Veuillez entrer une description de l'hotel en 50 lettres maximum ");
                desc = scanner.nextLine();

                if (isStringValid(desc)) {
                    hotel.setAdress(desc);
                    isInputValid = true;
                } else {
                    System.out.println("La description entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'un parking ? : ");
                parking = scanner.nextLine();
                if ("oui".equalsIgnoreCase(parking) || "non".equalsIgnoreCase(parking)) {
                    hotel.setWifi(parking);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'un wifi ? : ");
                wifi = scanner.nextLine();
                if ("oui".equalsIgnoreCase(wifi) || "non".equalsIgnoreCase(wifi)) {
                    hotel.setWifi(wifi);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (checkInTime == null) {
                System.out.println("Veuillez entrer l'heure du check-in au format (HH:mm) : ");
                checkIn = scanner.nextLine();
                try {
                    checkInTime = LocalTime.parse(checkIn, timeFormatter);
                    java.sql.Time checkInTimeSql = java.sql.Time.valueOf(checkInTime);
                    hotel.setCheckIn(checkInTimeSql);
                } catch (DateTimeParseException e) {
                    System.out.println("Heure invalide, veuillez réessayer.");
                }
            }

            while (checkOutTime == null) {
                System.out.println("Veuillez entrer l'heure du check-out au format (HH:mm) : ");
                checkOut = scanner.nextLine();
                try {
                    checkOutTime = LocalTime.parse(checkOut, timeFormatter);
                    java.sql.Time checkOutTimeSql = java.sql.Time.valueOf(checkOutTime);
                    hotel.setCheckIn(checkOutTimeSql);
                } catch (DateTimeParseException e) {
                    System.out.println("Heure invalide, veuillez réessayer.");
                }
            }

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'un piscine ? : ");
                pool = scanner.nextLine();
                if ("oui".equalsIgnoreCase(pool) || "non".equalsIgnoreCase(pool)) {
                    hotel.setPool(pool);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel dispose t'il d'une navette ? : ");
                shuttle = scanner.nextLine();
                if ("oui".equalsIgnoreCase(shuttle) || "non".equalsIgnoreCase(shuttle)) {
                    hotel.setShuttle(shuttle);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("l'hotel accepte t'il les animaux ? : ");
                animal = scanner.nextLine();
                if ("oui".equalsIgnoreCase(animal) || "non".equalsIgnoreCase(animal)) {
                    hotel.setAnimals(animal);
                    isInputValid = true;
                } else {
                    System.out.println("Veuillez repondre par oui ou par non uniquement. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            hotelDAO.save(hotel);
            System.out.println("Voulez-vous ajouter un autre hotel ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void deleteHotel() {
        HotelDAO hotelDAO = new HotelDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        Hotel hotel;
        String response;
        String areYouSure;
        int userChoice;
        do {
            System.out.println("Suppression d'un Hotel");
            displayHotel();
            System.out.println("Veuillez entrer l'id du hotel à supprimer, uniquement en valeur numérique : ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                hotel = hotelDAO.getById(userChoice);
                if (hotel != null) {
                    System.out.println("les hotel seront TOTALEMENT \uD83D\uDCA5DETRUIT\uD83D\uDCA5, t'es sur de toi (repondre par oui ou non)?");
                    areYouSure = scanner.nextLine();
                    while (!"Oui".equalsIgnoreCase(areYouSure) && !"Non".equalsIgnoreCase(areYouSure)) {
                        System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                        areYouSure = scanner.nextLine();
                    }
                    if ("Oui".equalsIgnoreCase(areYouSure)) {
                        hotelDAO.deleteById(userChoice);
                        chambreDAO.deleteByIdHotel(userChoice);
                    } else {
                        System.out.println("Suppression annulee");
                    }
                } else {
                    System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                }
            } else {
                System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }

            System.out.println("Voulez-vous supprimer un autre hotel ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void searchHotel() {
        HotelDAO hotelDAO = new HotelDAO();
        ArrayList<Hotel> searchResults = new ArrayList<>();
        String search;
        String response;

        do {
            System.out.println("------ Recherche d'un Hotel ------");
            System.out.println("Veuillez entrer le terme de la recherche ");
            search = scanner.nextLine();
            searchResults = hotelDAO.searchHotels(search);
            if (searchResults != null) {
                System.out.println("Resultat de la recherche : ");
                for (Hotel hotel : searchResults) {
                    System.out.println(hotel);
                }
            } else {
                System.out.println("Aucun resultat trouve pour : " + search);
            }
            System.out.println("Voulez-vous rechercher un autre hotel ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void addOrder() {

    }

    public static void deleteOrder() {

    }

    public static void displaySupplier() {

    }

    public static void addSupplier() {

    }

    public static void modifySupplier() {

    }

    public static void deleteSupplier() {

    }

    public static void searchSupplier() {

    }

    public static void displayStock() {

    }

    public static void addStock() {

    }

    public static void deleteStock() {

    }

    /**
     * Affiche les détails de tous les paiements enregistrés. Pour chaque paiement,
     * récupère et affiche les informations associées de la commande et du client correspondant.
     * Utilise PaiementDAO pour obtenir tous les paiements, et CommandeDAO et ClientDAO pour
     * obtenir les détails supplémentaires nécessaires.
     **/
    public static void displayPaiement() {

    }

    /**
     * Gère l'ajout de paiements pour des commandes spécifiques. La méthode guide l'utilisateur à travers
     * plusieurs étapes : sélection d'une commande par ID, vérification du montant restant à payer, saisie du montant
     * du paiement et de la date de paiement, et enregistrement du paiement dans la base de données. La saisie de l'utilisateur
     * est validée à chaque étape. La méthode permet l'ajout de paiements multiples par une boucle do while.
     **/
    public static void addPaiement() {

    }

    /**
     * Permet à l'utilisateur de modifier les détails d'un paiement existant.
     * Les étapes comprennent la sélection du paiement par ID, la modification de l'ID de la commande associée,
     * la saisie d'un nouveau montant de paiement et la mise à jour de la date du paiement.
     * Les entrées sont validées à chaque étape. La méthode permet également de modifier plusieurs paiements
     * dans une session grâce à une boucle do while.
     **/
    public static void modifyPaiement() {

    }

    /**
     * Permet la suppression de paiements enregistrés. L'utilisateur est invité à choisir un paiement à supprimer
     * par son ID après l'affichage de tous les paiements existants. La validation de l'ID est effectuée avant la suppression.
     * Offre la possibilité de supprimer plusieurs paiements dans une même session grâce à une boucle do while.
     **/
    public static void deletePaiement() {

    }

    public static int menu() {
        int userChoice;
        do {
            System.out.println("\n------ Menu de Gestion ------");
            System.out.println("1- Liste des Societes");
            System.out.println("2- Ajouter une Societe");
            System.out.println("3- Modifier une Societe");
            System.out.println("4- Supprimer une Societe");
            System.out.println("5- Rechercher une Societe");
            System.out.println("6- Liste des Clients");
            System.out.println("7- Ajouter un Client");
            System.out.println("8- Modifier un Client");
            System.out.println("9- Supprimer un Client");
            System.out.println("10- Rechercher un Client");
            System.out.println("11- Liste des Hotels");
            System.out.println("12- Ajouter un Hotel");
            System.out.println("13- Modifier un Hotel");
            System.out.println("14- Supprimer un Hotel");
            System.out.println("15- Rechercher un Hotel");
            System.out.println("16- Liste des Reservations");
            System.out.println("17- ajouter une Reservation");
            System.out.println("18- Modifier une Reservation");
            System.out.println("18- Supprimer une Reservation");
            System.out.println("19- Rechercher une Reservation");
            System.out.println("20- Ajouter une chambre");
            System.out.println("21- Modifier une chambre");
            System.out.println("22- Supprimer une chambre");
            System.out.println("23- Rechercher une chambre");
            System.out.println("24- Liste des Paiements");
            System.out.println("25- Effectuer un Paiement");
            System.out.println("26- Modifier un Paiement");
            System.out.println("27- Supprimer un Paiement");
            System.out.println("28- Rechercher un Paiement");
            System.out.println("0- Quitter");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }
            userChoice = scanner.nextInt();
            scanner.nextLine();
        } while (userChoice < 0 || userChoice > 28);
        return userChoice;
    }
}