import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import repository.CoursRepository;
import repository.ModuleRepository;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ModuleRepository moduleDAO = new ModuleRepository() {
        
    }();
    private static final CoursRepository coursDAO = new CoursRepository() {
        
    }();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Ajouter un module");
            System.out.println("2. Lister les modules");
            System.out.println("3. Créer un cours");
            System.out.println("4. Lister tous les cours");
            System.out.println("5. Lister les cours d’un module et d’un professeur");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne

            try {
                switch (choix) {
                    case 1:
                        ajouterModule();
                        break;
                    case 2:
                        listerModules();
                        break;
                    case 3:
                        creerCours();
                        break;
                    case 4:
                        listerTousLesCours();
                        break;
                    case 5:
                        listerCoursParModuleEtProfesseur();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Option invalide.");
                }
            } catch (SQLException e) {
                System.err.println("Erreur de base de données : " + e.getMessage());
            }
        }
    }

    private static void listerCoursParModuleEtProfesseur() {
        
        throw new UnsupportedOperationException("Unimplemented method 'listerCoursParModuleEtProfesseur'");
    }

    private static void listerTousLesCours() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerTousLesCours'");
    }

    private static void creerCours() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'creerCours'");
    }

    private static void listerModules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerModules'");
    }

    private static void ajouterModule() throws SQLException {
        System.out.print("Nom du module : ");
        String nom = scanner.nextLine();
        ModuleRepository.ajouterModule(nom);
        System.out.println("Module ajouté avec succès.");
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static ModuleRepository getModuledao() {
        return moduleDAO;
    }

    /**
     * @return
     */
    public static CoursR getCoursdao() {
        return coursDAO;
    }
    private static 

    

    

