package vues;

import dao.*;
import entites.*;

import java.time.LocalDate;
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
                displayProduct();
            } else if (userChoice == 2) {
                addProduct();
            } else if (userChoice == 3) {
                modifyProduct();
            } else if (userChoice == 4) {
                deleteProduct();
            } else if (userChoice == 5) {
                searchProduct();
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
                displayCategorie();
            } else if (userChoice == 12) {
                addCategorie();
            } else if (userChoice == 13) {
                modifyCategorie();
            } else if (userChoice == 14) {
                deleteCategorie(); 
            } else if (userChoice == 15) {
                displayOrder();
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

    public static boolean isDateFormatValid(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static Double alreadyPaid(int id_commande) {
        return 0.0;
    }

    public static void displayProduct() {
        
    }

    public static void addProduct() {
        
    }

    public static void modifyProduct() {
        
    }

    public static void deleteProduct() {
        
    }

    public static void searchProduct() {
        
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
            //displayClient();
            System.out.println("------ Ajout de Client ------");
            while(!isInputValid){
                System.out.println("Veuillez entrer le nom du client : ");
                lastname = scanner.nextLine();
                if (client.setNom(lastname)){
                    isInputValid = true;
                } else {
                    System.out.println("Le nom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;
    
            while(!isInputValid){
                System.out.println("Veuillez entrer le prenom du client : ");
                firstname = scanner.nextLine();
                if (client.setPrenom(firstname)){
                    isInputValid = true;
                } else {
                    System.out.println("Le prenom entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while(true) {
                System.out.println("Veuillez entrer l'age uniquement en valeur numerique : ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    if (age <= 0 ) {
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
    
            while(!isInputValid){
                System.out.println("Veuillez entrer le ville du client : ");
                city = scanner.nextLine();
    
                if (client.setVille(city)){
                    isInputValid = true;
                } else {
                    System.out.println("La ville entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while(!isInputValid){
                System.out.println("Veuillez entrer l'adresse du client , numero de rue, nom de rue, zip code: ");
                adress = scanner.nextLine();
    
                if (client.setAdress(adress)){
                    isInputValid = true;
                } else {
                    System.out.println("L'adresse entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while(!isInputValid){
                System.out.println("Veuillez entrer l'email du client : ");
                email = scanner.nextLine();
    
                if (client.setEmail(email)){
                    isInputValid = true;
                } else {
                    System.out.println("L'email entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while(!isInputValid){
                System.out.println("Veuillez entrer le sexe du client (homme / femme): ");
                sexe = scanner.nextLine();
    
                if (client.setSexe(sexe)){
                    isInputValid = true;
                } else {
                    System.out.println("L'email entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while(!isInputValid){
                System.out.println("Veuillez entrer le numero de telephone du client : ");
                numTel = scanner.nextLine();

                if (client.setNotel(numTel)){
                    isInputValid = true;
                } else {
                    System.out.println("Le numero de telephone entré n'est pas valide. Veuillez réessayer.");
                }
            }
            isInputValid = false;

            while(!isInputValid){
                System.out.println("Veuillez entrer le pays du client : ");
                country = scanner.nextLine();

                if (client.setCountry(country)){
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
        
    }

    public static void deleteClient() {
        
    }

    public static void searchClient() {
        
    }

    public static void displayCategorie() {
       
    }

    public static void addCategorie() {
        
    }
    
    public static void modifyCategorie() {
        
    }

    public static void deleteCategorie() {
        
    }

    public static void displayOrder() {
       
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
        } while (userChoice < 0 || userChoice > 29);
        return userChoice;
    }
}