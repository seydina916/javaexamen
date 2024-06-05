package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Déclaration de l'exception RepositoryException
public class ModuleRepository extends Exception {
    public ModuleRepository(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "RepositoryException []";
    }
}

public interface ModuleRepository {
    class ModuleRepositoryImpl implements ModuleRepository {
        @Override
        public void save(Module module) throws RepositoryException {
            String query = "INSERT INTO Module (nom) VALUES (?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, module.getNom());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RepositoryException("Erreur lors de l'ajout du module", e);
            }
        }

        @Override
        public List<Module> findAll() throws RepositoryException {
            String query = "SELECT * FROM Module";
            List<Module> modules = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Module module = new Module(
                            rs.getInt("id"),
                            rs.getString("nom")
                    );
                    modules.add(module);
                }
                return modules;
            } catch (SQLException e) {
                throw new RepositoryException("Erreur lors de la récupération des modules", e);
            }
        }
    }
    void save(Module module) throws RepositoryException;

    List<Module> findAll() throws RepositoryException;
}
