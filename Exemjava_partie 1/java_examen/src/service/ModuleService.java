package service;

import java.sql.SQLException;
import java.util.List;

import repository.ModuleRepository;

// Exception personnalisée pour les erreurs de service
public class ModuleService extends Exception {
    public void Moduleservice(String message, Throwable cause) {

    }

    @Override
    public String toString() {
        return "ModuleService []";
    }
}

public interface ModuleService{
    class ModuleServiceImpl implements ModuleService {
        private final ModuleRepository moduleRepository;

        public ModuleServiceImpl(ModuleRepository moduleRepository) {
            this.moduleRepository = moduleRepository;
        }

        @Override
        public void addModule(Module module) throws Moduleservice {
            try {
                moduleRepository.save(module);
            } catch (SQLException e) {
                throw new Moduleservice("Erreur lors de l'ajout du module", e);
            }
        }

        @Override
        public List<Module> getAllModules() throws Moduleservice {
            try {
                return moduleRepository.findAll();
            } catch (SQLException e) {
                throw new Moduleservice("Erreur lors de la récupération de tous les modules", e);
            }
        }
    }

    /**
     * Ajoute un module.
     *
     * @param module le module à ajouter
     * @throws Moduleservice si une erreur survient lors de l'ajout du module
     */
    void addModule(Module module) throws Moduleservice;

    /**
     * Récupère tous les modules.
     *
     * @return une liste de tous les modules
     * @throws Moduleservice si une erreur survient lors de la récupération des
     *                       modules
     */
    List<Module> getAllModules() throws Moduleservice;
}
