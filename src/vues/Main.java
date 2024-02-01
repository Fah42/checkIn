package vues;

import dao.*;
import entites.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
                displayReservation();
            } else if (userChoice == 17) {
                addReservation();
            } else if (userChoice == 18) {
                modifyReservation();
            } else if (userChoice == 19) {
                deleteReservation();
            } else if (userChoice == 20) {
                searchReservation();
            } else if (userChoice == 21) {
                displayRoom();
            } else if (userChoice == 22) {
                addRoom();
            } else if (userChoice == 23) {
                modifyRoom();
            } else if (userChoice == 24) {
                deleteRoom();
            } else if (userChoice == 25) {
                searchRoom();
            } else if (userChoice == 26) {
                displayPaiement();
            } else if (userChoice == 27) {
                addPaiement();
            } else if (userChoice == 28) {
                modifyPaiement();
            } else if (userChoice == 29) {
                deletePaiement();
            } else if (userChoice == 30) {
                searchPaiement();
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

    public static boolean isDateFormatValid(String userInput) {
        try {
            LocalDate.parse(userInput);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Double alreadyPaid(int id_reservation) {
        ArrayList<Paiement> paiements = new ArrayList<>();
        PaiementDAO paiementDAO = new PaiementDAO();
        paiements = paiementDAO.getAll();
        Double alreadyPaid = 0.0;

        for (Paiement paiement : paiements) {
            if (paiement.getId_reservation() == id_reservation) {
                alreadyPaid += paiement.getMontant();
            }
        }

        return alreadyPaid;
    }

    public static boolean isStringValid(String stringToCheck) {
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
                    System.out.println("Si La societe dispose d'un ou plusieurs hotels qui seront aussi TOTALEMENT \uD83D\uDCA5DETRUIT\uD83D\uDCA5, t'es sur de toi (repondre par oui ou non)?");
                    areYouSure = scanner.nextLine();
                    while (!"Oui".equalsIgnoreCase(areYouSure) && !"Non".equalsIgnoreCase(areYouSure)) {
                        System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                        areYouSure = scanner.nextLine();
                    }
                    if ("Oui".equalsIgnoreCase(areYouSure)) {
                        if (hotelDAO.getByIdCompany(userChoice) != null) {
                            chambreDAO.deleteByIdHotel(hotelDAO.getByIdCompany(userChoice).getId());
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
                System.out.println("Veuillez entrer l'id du client à modifier, uniquement en valeur numérique : ");
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
                System.out.println("Veuillez entrer la ville ou se situe l'hotel : ");
                city = scanner.nextLine();

                if (isStringValid(city)) {
                    hotel.setCity(city);
                    isInputValid = true;
                } else {
                    System.out.println("La ville entrée n'est pas valide. Veuillez réessayer.");
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
                    System.out.println("La description entrée n'est pas valide. Veuillez réessayer.");
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
                System.out.println("l'hotel dispose t'il d'une piscine ? : ");
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
                    hotel.setDesc(desc);
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
                    hotel.setParking(parking);
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
                    hotel.setCheckOut(checkOutTimeSql);
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

    public static void displayRoom() {
        ArrayList<Chambre> chambres = new ArrayList<>();
        chambres = new ChambreDAO().getAll();

        System.out.print("------ Affichage des Chambres ------\n");
        for (Chambre chambre : chambres) {
            System.out.println(chambre);
        }
    }

    public static void addRoom() {
        ChambreDAO chambreDAO = new ChambreDAO();
        HotelDAO hotelDAO = new HotelDAO();
        Chambre chambre = new Chambre();
        int id_hotel;
        int roomNumber;
        int roomArea;
        double pricePerNight;
        boolean isInputValid = false;
        String simpleOrDouble;
        String bathroom;
        String bathtub;
        String tv;
        String balcony;
        String fridge;
        String soundproof;
        String response;

        do {
            System.out.println("------ Ajout d'une chambre ------");
            displayHotel();
            while (true) {
                System.out.println("Veuillez choisir à quel hotel appartiendra la chambre en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_hotel = scanner.nextInt();
                    scanner.nextLine();
                    if (hotelDAO.getById(id_hotel) != null) {
                        chambre.setId_hotel(id_hotel);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                System.out.println("Veuillez entrer le numero de la chambre uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    roomNumber = scanner.nextInt();
                    if (roomNumber < 0) {
                        System.out.println("Veuillez entrer une valeur positive. ");
                    } else {
                        scanner.nextLine();
                        chambre.setChamberNumber(roomNumber);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (true) {
                System.out.println("Veuillez entrer la taille de la chambre en m2, uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    roomArea = scanner.nextInt();
                    if (roomArea <= 0) {
                        System.out.println("Veuillez entrer une valeur positive.");
                    } else {
                        scanner.nextLine();
                        chambre.setChamberArea(roomArea);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Est-ce une chambre simple ? (repondre par oui ou non) : ");
                simpleOrDouble = scanner.nextLine();
                if (isStringValid(simpleOrDouble)) {
                    while (!"oui".equalsIgnoreCase(simpleOrDouble) && !"non".equalsIgnoreCase(simpleOrDouble)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("Est-ce une chambre simple ? : ");
                        simpleOrDouble = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(simpleOrDouble)) {
                        chambre.setIsSimple("oui");
                        chambre.setisDouble("non");
                    } else {
                        chambre.setIsSimple("non");
                        chambre.setisDouble("oui");
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'une salle de bain ? (repondre par oui ou non) : ");
                bathroom = scanner.nextLine();
                if (isStringValid(bathroom)) {
                    while (!"oui".equalsIgnoreCase(bathroom) && !"non".equalsIgnoreCase(bathroom)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'une salle de bain ? : ");
                        bathroom = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(bathroom)) {
                        chambre.setBathroom(bathroom);
                        System.out.println("La salle de bain dispose t'elle d'une baignoire ?");
                        bathtub = scanner.nextLine();
                        while (!"oui".equalsIgnoreCase(bathtub) && !"non".equalsIgnoreCase(bathtub)) {
                            System.out.println("Veuillez repondre uniquement par Oui ou Non");
                            System.out.println("La chambre dispose t'elle d'une baignoire ? : ");
                            bathtub = scanner.nextLine();
                        }
                        if ("oui".equalsIgnoreCase(bathtub)) {
                            chambre.setBathtub(bathtub);
                        }
                    } else {
                        chambre.setBathroom(bathroom);
                        chambre.setBathtub(bathroom);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'une TV ? (repondre par oui ou non) : ");
                tv = scanner.nextLine();
                if (isStringValid(tv)) {
                    while (!"oui".equalsIgnoreCase(tv) && !"non".equalsIgnoreCase(tv)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'une TV ? : ");
                        tv = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(tv)) {
                        chambre.setTv(tv);
                    } else {
                        chambre.setTv(tv);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'un balcon ? (repondre par oui ou non) : ");
                balcony = scanner.nextLine();
                if (isStringValid(balcony)) {
                    while (!"oui".equalsIgnoreCase(balcony) && !"non".equalsIgnoreCase(balcony)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'un balcon ? : ");
                        balcony = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(balcony)) {
                        chambre.setBalcony(balcony);
                    } else {
                        chambre.setBalcony(balcony);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'un frigo ? (repondre par oui ou non) : ");
                fridge = scanner.nextLine();
                if (isStringValid(fridge)) {
                    while (!"oui".equalsIgnoreCase(fridge) && !"non".equalsIgnoreCase(fridge)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'un frigo ? : ");
                        fridge = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(fridge)) {
                        chambre.setFridge(fridge);
                    } else {
                        chambre.setFridge(fridge);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'une insonorisation ? (repondre par oui ou non) : ");
                soundproof = scanner.nextLine();
                if (isStringValid(soundproof)) {
                    while (!"oui".equalsIgnoreCase(soundproof) && !"non".equalsIgnoreCase(soundproof)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'une insonorisation ? : ");
                        soundproof = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(soundproof)) {
                        chambre.setSoundproof(soundproof);
                    } else {
                        chambre.setSoundproof(soundproof);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (true) {
                System.out.println("Veuillez entrer le prix de la chambre uniquement en valeur numerique : ");
                if (scanner.hasNextDouble()) {
                    pricePerNight = scanner.nextDouble();
                    if (pricePerNight < 0) {
                        System.out.println("Veuillez entrer une valeur positive. ");
                    } else {
                        scanner.nextLine();
                        chambre.setPricePerNight(pricePerNight);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            chambreDAO.save(chambre);
            System.out.println("Voulez-vous ajouter une autre chambre ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void modifyRoom() {
        ChambreDAO chambreDAO = new ChambreDAO();
        HotelDAO hotelDAO = new HotelDAO();
        Chambre chambre = new Chambre();
        int id_hotel;
        int roomNumber;
        int roomArea;
        double pricePerNight;
        boolean isInputValid = false;
        String simpleOrDouble;
        String bathroom;
        String bathtub;
        String tv;
        String balcony;
        String fridge;
        String soundproof;
        String response;
        int userChoice;

        do {
            System.out.println("------ Modifier une chambre ------");
            while (true) {
                displayRoom();
                System.out.println("Veuillez entrer l'id de la chambre à modifier, uniquement en valeur numérique : ");
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    scanner.nextLine();
                    chambre = chambreDAO.getById(userChoice);
                    if (chambreDAO != null) {
                        break;
                    } else {
                        System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }

            while (true) {
                System.out.println("Veuillez choisir à quelle hotel appartiendra la chambre en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_hotel = scanner.nextInt();
                    scanner.nextLine();
                    if (hotelDAO.getById(id_hotel) != null) {
                        chambre.setId_hotel(id_hotel);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                System.out.println("Veuillez entrer le numero de la chambre uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    roomNumber = scanner.nextInt();
                    if (roomNumber < 0) {
                        System.out.println("Veuillez entrer une valeur positive. ");
                    } else {
                        scanner.nextLine();
                        chambre.setChamberNumber(roomNumber);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (true) {
                System.out.println("Veuillez entrer la taille de la chambre en m2, uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    roomArea = scanner.nextInt();
                    if (roomArea <= 0) {
                        System.out.println("Veuillez entrer une valeur positive.");
                    } else {
                        scanner.nextLine();
                        chambre.setChamberArea(roomArea);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            while (!isInputValid) {
                System.out.println("Est-ce une chambre simple ? (repondre par oui ou non) : ");
                simpleOrDouble = scanner.nextLine();
                if (isStringValid(simpleOrDouble)) {
                    while (!"oui".equalsIgnoreCase(simpleOrDouble) || !"non".equalsIgnoreCase(simpleOrDouble)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("Est-ce une chambre simple ? : ");
                        simpleOrDouble = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(simpleOrDouble)) {
                        chambre.setIsSimple(simpleOrDouble);
                    } else {
                        chambre.setisDouble(simpleOrDouble);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'une salle de bain ? (repondre par oui ou non) : ");
                bathroom = scanner.nextLine();
                if (isStringValid(bathroom)) {
                    while (!"oui".equalsIgnoreCase(bathroom) || !"non".equalsIgnoreCase(bathroom)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'une salle de bain ? : ");
                        bathroom = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(bathroom)) {
                        chambre.setBathroom(bathroom);
                        System.out.println("La salle de bain dispose t'elle d'une baignoire ?");
                        bathtub = scanner.nextLine();
                        while (!"oui".equalsIgnoreCase(bathtub) || !"non".equalsIgnoreCase(bathtub)) {
                            System.out.println("Veuillez repondre uniquement par Oui ou Non");
                            System.out.println("La chambre dispose t'elle d'une baignoire ? : ");
                            bathtub = scanner.nextLine();
                        }
                        if ("oui".equalsIgnoreCase(bathtub)) {
                            chambre.setBathtub(bathtub);
                        }
                    } else {
                        chambre.setBathroom(bathroom);
                        chambre.setBathtub(bathroom);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'une TV ? (repondre par oui ou non) : ");
                tv = scanner.nextLine();
                if (isStringValid(tv)) {
                    while (!"oui".equalsIgnoreCase(tv) || !"non".equalsIgnoreCase(tv)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'une TV ? : ");
                        tv = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(tv)) {
                        chambre.setTv(tv);
                    } else {
                        chambre.setTv(tv);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'un balcon ? (repondre par oui ou non) : ");
                balcony = scanner.nextLine();
                if (isStringValid(balcony)) {
                    while (!"oui".equalsIgnoreCase(balcony) || !"non".equalsIgnoreCase(balcony)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'un balcon ? : ");
                        balcony = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(balcony)) {
                        chambre.setBalcony(balcony);
                    } else {
                        chambre.setBalcony(balcony);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'un frigo ? (repondre par oui ou non) : ");
                fridge = scanner.nextLine();
                if (isStringValid(fridge)) {
                    while (!"oui".equalsIgnoreCase(fridge) || !"non".equalsIgnoreCase(fridge)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'un frigo ? : ");
                        fridge = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(fridge)) {
                        chambre.setFridge(fridge);
                    } else {
                        chambre.setFridge(fridge);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (!isInputValid) {
                System.out.println("La chambre dispose t'elle d'une insonorisation ? (repondre par oui ou non) : ");
                soundproof = scanner.nextLine();
                if (isStringValid(soundproof)) {
                    while (!"oui".equalsIgnoreCase(soundproof) || !"non".equalsIgnoreCase(soundproof)) {
                        System.out.println("Veuillez repondre uniquement par Oui ou Non");
                        System.out.println("La chambre dispose t'elle d'une insonorisation ? : ");
                        soundproof = scanner.nextLine();
                    }
                    if ("oui".equalsIgnoreCase(soundproof)) {
                        chambre.setSoundproof(soundproof);
                    } else {
                        chambre.setSoundproof(soundproof);
                    }
                    isInputValid = true;
                } else {
                    System.out.println("Nombre de characters trop eleve. Veuillez repondre par oui ou non.");
                }
            }
            isInputValid = false;

            while (true) {
                System.out.println("Veuillez entrer le prix de la chambre uniquement en valeur numerique : ");
                if (scanner.hasNextDouble()) {
                    pricePerNight = scanner.nextDouble();
                    if (pricePerNight < 0) {
                        System.out.println("Veuillez entrer une valeur positive. ");
                    } else {
                        scanner.nextLine();
                        chambre.setPricePerNight(pricePerNight);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            chambreDAO.save(chambre);
            System.out.println("Voulez-vous modifier une autre chambre ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void deleteRoom() {
        ReservationDAO reservationDAO = new ReservationDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        Chambre chambre;
        String response;
        String areYouSure;
        int userChoice;
        do {
            System.out.println("Suppression d'une chambre");
            displayRoom();
            System.out.println("Veuillez entrer l'id de la chambre à supprimer, uniquement en valeur numérique : ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                chambre = chambreDAO.getById(userChoice);
                if (chambre != null) {
                    System.out.println("la chambre sera TOTALEMENT \uD83D\uDCA5DETRUITE\uD83D\uDCA5 ainsi que les reservations lie a cette chambre, t'es sur de toi (repondre par oui ou non)?");
                    areYouSure = scanner.nextLine();
                    while (!"Oui".equalsIgnoreCase(areYouSure) && !"Non".equalsIgnoreCase(areYouSure)) {
                        System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                        areYouSure = scanner.nextLine();
                    }
                    if ("Oui".equalsIgnoreCase(areYouSure)) {
                        reservationDAO.deleteByIdChambre(userChoice);
                        chambreDAO.deleteById(userChoice);
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

    public static void searchRoom() {
        ChambreDAO chambreDAO = new ChambreDAO();
        ArrayList<Chambre> searchResults = new ArrayList<>();
        String search;
        String response;

        do {
            System.out.println("------ Recherche d'un Hotel ------");
            System.out.println("Veuillez entrer le terme de la recherche ");
            search = scanner.nextLine();
            searchResults = chambreDAO.searchChambres(search);
            if (searchResults != null) {
                System.out.println("Resultat de la recherche : ");
                for (Chambre chambre : searchResults) {
                    System.out.println(chambre);
                }
            } else {
                System.out.println("Aucun resultat trouve pour : " + search);
            }
            System.out.println("Voulez-vous rechercher une autre chambre ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void displayReservation() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        ClientDAO clientDAO = new ClientDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        HotelDAO hotelDAO = new HotelDAO();
        Client client = new Client();
        Chambre chambre = new Chambre();
        Hotel hotel = new Hotel();

        reservations = new ReservationDAO().getAll();
        System.out.print("------ Affichage des Resevations ------\n");
        for (Reservation reservation : reservations) {
            client = clientDAO.getById(reservation.getId_client());
            chambre = chambreDAO.getById(reservation.getId_chambre());
            hotel = hotelDAO.getById(chambre.getId_hotel());
            System.out.println("Au nom de : " + client.getNom() + " "+ client.getPrenom() + " ,Chambre numero : " + chambre.getChamberNumber() + ", dans l'hotel : " + hotel.getNom() + ", Pour " + reservation.getNbNight() + " nuits, pour le prix par nuit de : " + chambre.getPricePerNight() + ", au prix total de : " + reservation.getNbNight() + chambre.getPricePerNight() + " euros");
        }
    }

    public static void addReservation() {
        ClientDAO clientDAO = new ClientDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        Reservation reservation = new Reservation();
        int id_chambre;
        int id_client;
        int nb_personne;
        long nb_nuit;
        int nb_nuitsInt;
        String jour_arrive;
        String jour_depart;
        String response;

        do {
            System.out.println("------ Ajout d'une reservation ------");
            while (true) {
                displayClient();
                System.out.println("Veuillez choisir à quelle client appartiendra la reservation en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_client = scanner.nextInt();
                    scanner.nextLine();
                    if (clientDAO.getById(id_client) != null) {
                        reservation.setId_client(id_client);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                displayRoom();
                System.out.println("Veuillez choisir la chambre pour la reservation en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_chambre = scanner.nextInt();
                    scanner.nextLine();
                    if (chambreDAO.getById(id_chambre) != null) {
                        reservation.setId_chambre(id_chambre);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                System.out.println("Veuillez entrer la date d'arrivée au format YYYY-MM-DD : ");
                jour_arrive = scanner.nextLine();
                if (isDateFormatValid(jour_arrive)) {
                    LocalDate localDate = LocalDate.parse(jour_arrive);
                    LocalDate today = LocalDate.now(); // obtenir la date actuelle

                    if (!localDate.isBefore(today)) { // Vérifiez si la date d'arrivée n'est pas antérieure à aujourd'hui
                        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                        reservation.setJour_arrive(sqlDate);
                        break;
                    } else {
                        System.out.println("La date d'arrivée ne peut pas être antérieure à la date actuelle. Veuillez choisir une date valide.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas une date valide. Veuillez entrer une date au format YYYY-MM-DD.");
                }
            }

            while (true) {
                System.out.println("Veuillez entrer la date de départ au format YYYY-MM-DD : ");
                jour_depart = scanner.nextLine();
                if (isDateFormatValid(jour_depart)) {
                    LocalDate localDateDepart = LocalDate.parse(jour_depart);
                    java.sql.Date sqlDateDepart = java.sql.Date.valueOf(localDateDepart);
                    LocalDate localDateArrivee = reservation.getJour_arrive().toLocalDate();
                    if (!localDateDepart.isBefore(localDateArrivee)) {
                        reservation.setJour_depart(sqlDateDepart);
                        break;
                    } else {
                        System.out.println("La date de départ doit être postérieure à la date d'arrivée.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas une date valide. Veuillez entrer une date au format YYYY-MM-DD.");
                }
            }

            LocalDate localDateArrivee = reservation.getJour_arrive().toLocalDate();
            LocalDate localDateDepart = reservation.getJour_depart().toLocalDate();
            // Calculer le nombre de nuits
            nb_nuit = java.time.temporal.ChronoUnit.DAYS.between(localDateArrivee, localDateDepart);
            // Afficher le nombre de nuits ou le stocker selon vos besoins
            System.out.println("Nombre de nuits : " + nb_nuit);
            nb_nuitsInt = (int) nb_nuit;
            reservation.setNbNight(nb_nuitsInt);

            while (true) {
                System.out.println("Veuillez entrer le nombre de personne uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    nb_personne = scanner.nextInt();
                    if (nb_personne <= 0) {
                        System.out.println("Veuillez entrer une valeur positive.");
                    } else {
                        scanner.nextLine();
                        reservation.setNb_personne(nb_personne);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            reservationDAO.save(reservation);

            System.out.println("Voulez-vous ajouter une autre reservation ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void modifyReservation() {
        ClientDAO clientDAO = new ClientDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        Reservation reservation = new Reservation();
        int id_chambre;
        int id_client;
        int nb_personne;
        String jour_arrive;
        String jour_depart;
        String response;
        int userChoice;

        do {
            System.out.println("------ modification d'une reservation ------");

            while (true) {
                displayReservation();
                System.out.println("Veuillez choisir à quelle reservation vous souhaitez modifier en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (reservationDAO.getById(userChoice) != null) {
                        reservation.setId_client(userChoice);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                displayClient();
                System.out.println("Veuillez choisir à quelle client appartiendra la reservation en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_client = scanner.nextInt();
                    scanner.nextLine();
                    if (clientDAO.getById(id_client) != null) {
                        reservation.setId_client(id_client);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                displayRoom();
                System.out.println("Veuillez choisir la chambre pour la reservation en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    id_chambre = scanner.nextInt();
                    scanner.nextLine();
                    if (chambreDAO.getById(id_chambre) != null) {
                        reservation.setId_chambre(id_chambre);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                System.out.println("Veuillez entrer la date d'arrivee au format YYYY-MM-DD : ");
                jour_arrive = scanner.nextLine();
                if (isDateFormatValid(jour_arrive)) {
                    LocalDate localDate = LocalDate.parse(jour_arrive);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                    reservation.setJour_arrive(sqlDate);
                    break;
                } else {
                    System.out.println("L'entrée n'est pas une date valide. Veuillez entrer une date au format YYYY-MM-DD.");
                }
            }

            while (true) {
                System.out.println("Veuillez entrer la date de départ au format YYYY-MM-DD : ");
                jour_depart = scanner.nextLine();
                if (isDateFormatValid(jour_depart)) {
                    LocalDate localDateDepart = LocalDate.parse(jour_depart);
                    java.sql.Date sqlDateDepart = java.sql.Date.valueOf(localDateDepart);
                    LocalDate localDateArrivee = reservation.getJour_depart().toLocalDate();
                    if (!localDateDepart.isBefore(localDateArrivee)) {
                        reservation.setJour_depart(sqlDateDepart);
                        break;
                    } else {
                        System.out.println("La date de départ doit être postérieure à la date d'arrivée.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas une date valide. Veuillez entrer une date au format YYYY-MM-DD.");
                }
            }

            while (true) {
                System.out.println("Veuillez entrer le nombre d'etoile uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    nb_personne = scanner.nextInt();
                    if (nb_personne <= 0) {
                        System.out.println("Veuillez entrer une valeur positive. Qui ne depasse pas 5");
                    } else {
                        scanner.nextLine();
                        reservation.setNb_personne(nb_personne);
                        break;
                    }
                } else {
                    System.out.println("Valeur invalide.");
                    scanner.next();
                }
            }

            reservationDAO.save(reservation);
            System.out.println("Voulez-vous ajouter une autre reservation ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void deleteReservation() {
        ReservationDAO reservationDAO = new ReservationDAO();
        PaiementDAO paiementDAO = new PaiementDAO();
        Reservation reservation;
        String response;
        String areYouSure;
        int userChoice;

        do {
            System.out.println("Suppression d'une reservation");
            displayRoom();
            System.out.println("Veuillez entrer l'id de la reservation à supprimer, uniquement en valeur numérique : ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                reservation = reservationDAO.getById(userChoice);
                if (reservation != null) {
                    System.out.println("la reservation sera TOTALEMENT \uD83D\uDCA5DETRUITE\uD83D\uDCA5 ainsi que les paiements lie a cette reservation, t'es sur de toi (repondre par oui ou non)?");
                    areYouSure = scanner.nextLine();
                    while (!"Oui".equalsIgnoreCase(areYouSure) && !"Non".equalsIgnoreCase(areYouSure)) {
                        System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                        areYouSure = scanner.nextLine();
                    }
                    if ("Oui".equalsIgnoreCase(areYouSure)) {
                        paiementDAO.deleteByIdReservation(userChoice);
                        reservationDAO.deleteById(userChoice);
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

            System.out.println("Voulez-vous supprimer une autre reservation ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void searchReservation() {
        ReservationDAO reservationDAO = new ReservationDAO();
        ArrayList<Reservation> searchResults = new ArrayList<>();
        String search;
        String response;

        do {
            System.out.println("------ Recherche d'une reservation ------");
            System.out.println("Veuillez entrer le terme de la recherche ");
            search = scanner.nextLine();
            searchResults = reservationDAO.searchReservations(search);
            if (searchResults != null) {
                System.out.println("Resultat de la recherche : ");
                for (Reservation reservation : searchResults) {
                    System.out.println(reservation);
                }
            } else {
                System.out.println("Aucun resultat trouve pour : " + search);
            }
            System.out.println("Voulez-vous rechercher une autre reservation ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    /**
     * Affiche les détails de tous les paiements enregistrés. Pour chaque paiement,
     * récupère et affiche les informations associées a la reservation et du client correspondant.
     * Utilise PaiementDAO pour obtenir tous les paiements, et CommandeDAO et ClientDAO pour
     * obtenir les détails supplémentaires nécessaires.
     **/
    public static void displayPaiement() {
        ArrayList<Paiement> paiements = new ArrayList<>();
        paiements = new PaiementDAO().getAll();

        System.out.print("------ Affichage des Paiements ------\n");
        for (Paiement paiement : paiements) {
            System.out.println(paiement);
        }
    }

    /**
     * Gère l'ajout de paiements pour des commandes spécifiques. La méthode guide l'utilisateur à travers
     * plusieurs étapes : sélection d'une commande par ID, vérification du montant restant à payer, saisie du montant
     * du paiement et de la date de paiement, et enregistrement du paiement dans la base de données. La saisie de l'utilisateur
     * est validée à chaque étape. La méthode permet l'ajout de paiements multiples par une boucle do while.
     **/
    public static void addPaiement() {
        ReservationDAO reservationDAO = new ReservationDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        Paiement paiement = new Paiement();
        String response;
        String methode;
        boolean isLeftToPay;
        boolean isInputValid = false;
        double montant;
        double pricePerNight;
        double alreadyPaid;
        double total;
        int id_reservation;

        do {
            isLeftToPay = true;
            System.out.println("------ Paiement ------");
            displayReservation();
            while (true) {
                System.out.println("Veuillez choisir a quel reservation appartient le paiement en selectionnant l'id correspond en utilisant uniquement des caracteres numerique : ");
                if (scanner.hasNextInt()){
                    id_reservation = scanner.nextInt();
                    scanner.nextLine();
                    if(reservationDAO.getById(id_reservation) != null) {
                        paiement.setId_reservation(id_reservation);
                        break;
                    } else {
                        System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                    scanner.next();
                }
            }
            pricePerNight = chambreDAO.getById(reservationDAO.getByIdChambre(id_reservation).getId()).getPricePerNight();
            alreadyPaid = alreadyPaid(id_reservation);
            total = pricePerNight * reservationDAO.getByIdChambre(id_reservation).getNbNight();

            if (alreadyPaid == total){
                System.out.println("La commande est deja regle.");
                isLeftToPay = false;
            }

            if (isLeftToPay) {
                while(true) {
                    System.out.println("Veuillez entrer le paiement uniquement en valeur numerique : ");
                    if (scanner.hasNextDouble()) {
                        montant = scanner.nextDouble();
                        scanner.nextLine();
                        while (montant <= 0) {
                            System.out.println("Veuillez entrer une valeur positive svp : ");
                            montant = scanner.nextDouble();
                            scanner.nextLine();
                        }
                        if (alreadyPaid + montant > total){
                            System.out.print("Vous avez entre une somme trop eleve qui depasse le prix total de la commande.");
                        } else {
                            paiement.setMontant(montant);
                            break;
                        }
                    } else {
                        System.out.print("La somme entré n'est pas valide. Veuillez réessayer.");
                        scanner.next();
                    }
                }

                while (true) {
                    System.out.println("Veuillez entrer la date du paiement au format YYYY-MM-DD : ");
                    String date = scanner.nextLine();
                    if (isDateFormatValid(date)) {
                        LocalDate localDate = LocalDate.parse(date);
                        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                        paiement.setDateP(sqlDate);
                        break;
                    } else {
                        System.out.println("L'entrée n'est pas une date valide. Veuillez entrer une date au format YYYY-MM-DD.");
                    }
                }

                while (!isInputValid) {
                    System.out.println("Quelle est la methode de paiement ? (Carte, Espece, Cheque ?) : ");
                    methode = scanner.nextLine();
                    if (isStringValid(methode)) {
                        while (!"carte".equalsIgnoreCase(methode) || !"espece".equalsIgnoreCase(methode) || !"cheque".equalsIgnoreCase(methode)) {
                            System.out.println("Veuillez repondre uniquement par Carte, Espece ou Cheque.");
                            methode = scanner.nextLine();
                        }
                        isInputValid = true;
                    } else {
                        System.out.println("Nombre de characters trop eleve.");
                    }
                }
            PaiementDAO.save(paiement);
            }
            System.out.println("Voulez-vous ajouter un autre paiement ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    /**
     * Permet à l'utilisateur de modifier les détails d'un paiement existant.
     * Les étapes comprennent la sélection du paiement par ID, la modification de l'ID de la commande associée,
     * la saisie d'un nouveau montant de paiement et la mise à jour de la date du paiement.
     * Les entrées sont validées à chaque étape. La méthode permet également de modifier plusieurs paiements
     * dans une session grâce à une boucle do while.
     **/
    public static void modifyPaiement() {
        ReservationDAO reservationDAO = new ReservationDAO();
        ChambreDAO chambreDAO = new ChambreDAO();
        PaiementDAO paiementDAO = new PaiementDAO();
        Paiement paiement = new Paiement();
        String response;
        String methode;
        boolean isLeftToPay = true;
        boolean isInputValid = false;
        double montant;
        double pricePerNight;
        double alreadyPaid;
        double total;
        int id_reservation;
        int userChoice;

        do {
            System.out.println("------ Modifer Paiement ------");
            while (true) {
                displayPaiement();
                System.out.println("Veuillez choisir quel paiement vous souhaitez modifier en sélectionnant l'id correspondant, en utilisant uniquement des caractères numériques : ");
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (paiementDAO.getById(userChoice) != null) {
                        paiement.setId(userChoice);
                        break;
                    }
                    System.out.println("ID inexistant.\n");
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                    scanner.next();
                }
            }

            while (true) {
                System.out.println("Veuillez choisir a quel reservation appartient le paiement en selectionnant l'id correspond en utilisant uniquement des caracteres numerique : ");
                if (scanner.hasNextInt()){
                    id_reservation = scanner.nextInt();
                    scanner.nextLine();
                    if(reservationDAO.getById(id_reservation) != null) {
                        paiement.setId_reservation(id_reservation);
                        break;
                    } else {
                        System.out.println("L'ID entré n'est pas valide. Veuillez réessayer.");
                    }
                } else {
                    System.out.println("L'entrée n'est pas un nombre valide. Veuillez entrer un nombre entier.");
                    scanner.next();
                }
            }
            pricePerNight = chambreDAO.getById(reservationDAO.getByIdChambre(id_reservation).getId()).getPricePerNight();
            alreadyPaid = alreadyPaid(id_reservation);
            total = pricePerNight * reservationDAO.getByIdChambre(id_reservation).getNbNight();

            if (alreadyPaid == total){
                System.out.println("La commande est deja regle.");
                isLeftToPay = false;
            }

            if (isLeftToPay) {
                while(true) {
                    System.out.println("Veuillez entrer le paiement uniquement en valeur numerique : ");
                    if (scanner.hasNextDouble()) {
                        montant = scanner.nextDouble();
                        scanner.nextLine();
                        while (montant <= 0) {
                            System.out.println("Veuillez entrer une valeur positive svp : ");
                            montant = scanner.nextDouble();
                            scanner.nextLine();
                        }
                        if (alreadyPaid + montant > total){
                            System.out.print("Vous avez entre une somme trop eleve qui depasse le prix total de la commande.");
                        } else {
                            paiement.setMontant(montant);
                            break;
                        }
                    } else {
                        System.out.print("La somme entré n'est pas valide. Veuillez réessayer.");
                        scanner.next();
                    }
                }

                while (true) {
                    System.out.println("Veuillez entrer la date du paiement au format YYYY-MM-DD : ");
                    String date = scanner.nextLine();
                    if (isDateFormatValid(date)) {
                        LocalDate localDate = LocalDate.parse(date);
                        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                        paiement.setDateP(sqlDate);
                        break;
                    } else {
                        System.out.println("L'entrée n'est pas une date valide. Veuillez entrer une date au format YYYY-MM-DD.");
                    }
                }

                while (!isInputValid) {
                    System.out.println("Quelle est la methode de paiement ? (Carte, Espece, Cheque ?) : ");
                    methode = scanner.nextLine();
                    if (isStringValid(methode)) {
                        while (!"carte".equalsIgnoreCase(methode) || !"espece".equalsIgnoreCase(methode) || !"cheque".equalsIgnoreCase(methode)) {
                            System.out.println("Veuillez repondre uniquement par Carte, Espece ou Cheque.");
                            methode = scanner.nextLine();
                        }
                        isInputValid = true;
                    } else {
                        System.out.println("Nombre de characters trop eleve.");
                    }
                }
                PaiementDAO.save(paiement);
            }
            System.out.println("Voulez-vous ajouter un autre paiement ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    /**
     * Permet la suppression de paiements enregistrés. L'utilisateur est invité à choisir un paiement à supprimer
     * par son ID après l'affichage de tous les paiements existants. La validation de l'ID est effectuée avant la suppression.
     * Offre la possibilité de supprimer plusieurs paiements dans une même session grâce à une boucle do while.
     **/
    public static void deletePaiement() {
        PaiementDAO paiementDAO = new PaiementDAO();
        Paiement paiement = new Paiement();
        String response;
        String areYouSure;
        int userChoice;

        do {
            System.out.println("Suppression d'une reservation");
            displayPaiement();
            System.out.println("Veuillez entrer l'id du paiement à supprimer, uniquement en valeur numérique : ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                paiement = paiementDAO.getById(userChoice);
                if (paiement != null) {
                    System.out.println("la reservation sera TOTALEMENT \uD83D\uDCA5DETRUITE\uD83D\uDCA5 ainsi que les paiements lie a cette reservation, t'es sur de toi (repondre par oui ou non)?");
                    areYouSure = scanner.nextLine();
                    while (!"Oui".equalsIgnoreCase(areYouSure) && !"Non".equalsIgnoreCase(areYouSure)) {
                        System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                        areYouSure = scanner.nextLine();
                    }
                    if ("Oui".equalsIgnoreCase(areYouSure)) {
                        paiementDAO.deleteById(userChoice);
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

            System.out.println("Voulez-vous supprimer une autre reservation ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
    }

    public static void searchPaiement() {
        PaiementDAO paiementDAO = new PaiementDAO();
        ArrayList<Paiement> searchResults = new ArrayList<>();
        String search;
        String response;

        do {
            System.out.println("------ Recherche d'une reservation ------");
            System.out.println("Veuillez entrer le terme de la recherche ");
            search = scanner.nextLine();
            searchResults = paiementDAO.searchPaiements(search);
            if (searchResults != null) {
                System.out.println("Resultat de la recherche : ");
                for (Paiement paiement : searchResults) {
                    System.out.println(paiement);
                }
            } else {
                System.out.println("Aucun resultat trouve pour : " + search);
            }
            System.out.println("Voulez-vous rechercher une autre reservation ? (Oui/Non)");
            response = scanner.nextLine();
            while (!"Oui".equalsIgnoreCase(response) && !"Non".equalsIgnoreCase(response)) {
                System.out.println("Veuillez repondre uniquement par (Oui/Non).");
                response = scanner.nextLine();
            }
        } while ("Oui".equalsIgnoreCase(response));
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
            System.out.println("17- Ajouter une Reservation");
            System.out.println("18- Modifier une Reservation");
            System.out.println("19- Supprimer une Reservation");
            System.out.println("20- Rechercher une Reservation");
            System.out.println("21- Liste des chambres");
            System.out.println("22- Ajouter une chambre");
            System.out.println("23- Modifier une chambre");
            System.out.println("24- Supprimer une chambre");
            System.out.println("25- Rechercher une chambre");
            System.out.println("26- Liste des Paiements");
            System.out.println("27- Effectuer un Paiement");
            System.out.println("28- Modifier un Paiement");
            System.out.println("29- Supprimer un Paiement");
            System.out.println("30- Rechercher un Paiement");
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