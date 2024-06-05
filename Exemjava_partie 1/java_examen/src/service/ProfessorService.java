package service;

import java.sql.SQLException;
import java.util.List;

import entities.Professor;

// Exception personnalisée pour les erreurs de service
public class ProfessorService extends Exception {
    public void professorService(String message, Throwable cause) {
        
    }

    @Override
    public String toString() {
        return "professorService []";
    }
}

public interface ProfessorService {
    class ProfessorServiceImpl implements professorService {
        private final ProfessorRepository professorRepository;

        public ProfessorServiceImpl(ProfessorRepository professorRepository) {
            this.professorRepository = professorRepository;
        }

        @Override
        public void addProfessor(Professor professor) throws professorService {
            try {
                professorRepository.save(professor);
            } catch (SQLException e) {
                throw new professorservice("Erreur lors de l'ajout du professeur", e);
            }
        }

        @Override
        public List<Professor> getAllProfessors() throws professorservice {
            try {
                return professorRepository.findAll();
            } catch (SQLException e) {
                throw new professorservice("Erreur lors de la récupération de tous les professeurs", e);
            }
        }

        @Override
        public Professor getProfessorById(int id) throws professorservice {
            try {
                return professorRepository.findById(id);
            } catch (SQLException e) {
                throw new professorservice("Erreur lors de la récupération du professeur par ID", e);
            }
        }

        @Override
        public void updateProfessor(Professor professor) throws professorservice {
            try {
                professorRepository.update(professor);
            } catch (SQLException e) {
                throw new professorservice("Erreur lors de la mise à jour du professeur", e);
            }
        }

        @Override
        public void deleteProfessor(int id) throws professorservice {
            try {
                professorRepository.deleteById(id);
            } catch (SQLException e) {
                throw new professorservice("Erreur lors de la suppression du professeur", e);
            }
        }

        // Méthode d'accès à la repository
        public ProfessorRepository getProfessorRepository() {
            return professorRepository;
        }
    }

    /**
     * Ajoute un professeur.
     *
     * @param professor le professeur à ajouter
     * @throws professorservice si une erreur survient lors de l'ajout du professeur
     */
    void addProfessor(Professor professor) throws professorservice;

    /**
     * Récupère tous les professeurs.
     *
     * @return une liste de tous les professeurs
     * @throws professorservice si une erreur survient lors de la récupération des professeurs
     */
    List<Professor> getAllProfessors() throws professorservice;

    /**
     * Récupère un professeur par son ID.
     *
     * @param id l'ID du professeur à récupérer
     * @return le professeur correspondant à l'ID
     * @throws professorservice si une erreur survient lors de la récupération du professeur
     */
    Professor getProfessorById(int id) throws professorservice;

    /**
     * Met à jour un professeur.
     *
     * @param professor le professeur à mettre à jour
     * @throws professorservice si une erreur survient lors de la mise à jour du professeur
     */
    void updateProfessor(Professor professor) throws professorservice;

    /**
     * Supprime un professeur par son ID.
     *
     * @param id l'ID du professeur à supprimer
     * @throws professorservice si une erreur survient lors de la suppression du professeur
     */
    void deleteProfessor(int id) throws professorservice;
}
