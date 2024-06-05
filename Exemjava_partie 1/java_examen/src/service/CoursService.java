package service;

import java.sql.SQLException;
import java.util.List;

import entities.Cours;
import repository.CoursRepository;

// Exception personnalisée pour les erreurs de service
public class CoursService extends Exception {
    public CoursService(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "ServiceException []";
    }
}

public interface CoursService {
    /**
     * Ajoute un cours.
     *
     * @param cours le cours à ajouter
     * @throws ServiceException si une erreur survient lors de l'ajout du cours
     */
    void addCours(Cours cours) throws ServiceException;

    /**
     * Récupère tous les cours.
     *
     * @return une liste de tous les cours
     * @throws ServiceException si une erreur survient lors de la récupération des cours
     */
    List<Cours> getAllCours() throws ServiceException;

    /**
     * Récupère les cours d'un module donné enseignés par un professeur donné.
     *
     * @param moduleId    l'ID du module
     * @param professorId l'ID du professeur
     * @return une liste des cours correspondants
     * @throws ServiceException si une erreur survient lors de la récupération des cours
     */
    List<Cours> getCoursByModuleAndProfessor(int moduleId, int professorId) throws ServiceException;

    class CoursServiceImpl implements CoursService {
        private final CoursRepository coursRepository;

        public CoursServiceImpl(CoursRepository coursRepository) {
            this.coursRepository = coursRepository;
        }

        @Override
        public void addCours(Cours cours) throws ServiceException {
            try {
                coursRepository.save(cours);
            } catch (SQLException e) {
                throw new ServiceException("Erreur lors de l'ajout du cours", e);
            }
        }

        @Override
        public List<Cours> getAllCours() throws ServiceException {
            try {
                return coursRepository.findAll();
            } catch (SQLException e) {
                throw new ServiceException("Erreur lors de la récupération de tous les cours", e);
            }
        }

        @Override
        public List<Cours> getCoursByModuleAndProfessor(int moduleId, int professorId) throws ServiceException {
            try {
                return coursRepository.findByModuleAndProfessor(moduleId, professorId);
            } catch (SQLException e) {
                throw new ServiceException("Erreur lors de la récupération des cours par module et professeur", e);
            }
        }
    }
}


