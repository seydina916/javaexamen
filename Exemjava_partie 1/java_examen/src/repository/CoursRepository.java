package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Cours;
import entities.Professor;
import entities.Module;

public interface CoursRepository {
    void save(Cours cours) throws SQLException;
    List<Cours> findAll() throws SQLException;
    List<Cours> findByModuleAndProfessor(int moduleId, int professorId) throws SQLException;

    class CoursRepositoryImpl implements CoursRepository {
        @Override
        public void save(Cours cours) throws SQLException {
            String query = "INSERT INTO Cours (date, heureDebut, heureFin, professor_id, module_id) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setDate(1, java.sql.Date.valueOf(cours.getDate()));
                stmt.setTime(2, java.sql.Time.valueOf(cours.getHeureDebut()));
                stmt.setTime(3, java.sql.Time.valueOf(cours.getHeureFin()));
                stmt.setInt(4, cours.getProfessor().getId());
                stmt.setInt(5, cours.getModule().getId());
                stmt.executeUpdate();
            }
        }

        @Override
        public List<Cours> findAll() throws SQLException {
            String query = "SELECT * FROM Cours";
            List<Cours> coursList = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Professor professor = findProfessorById(rs.getInt("professor_id"), conn);
                    Module module = findModuleById(rs.getInt("module_id"), conn);
                    Cours cours = new Cours(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("heureDebut").toLocalTime(),
                        rs.getTime("heureFin").toLocalTime(),
                        professor,
                        module
                    );
                    coursList.add(cours);
                }
            }
            return coursList;
        }

        @Override
        public List<Cours> findByModuleAndProfessor(int moduleId, int professorId) throws SQLException {
            String query = "SELECT * FROM Cours WHERE module_id = ? AND professor_id = ?";
            List<Cours> coursList = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, moduleId);
                stmt.setInt(2, professorId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Professor professor = findProfessorById(rs.getInt("professor_id"), conn);
                        Module module = findModuleById(rs.getInt("module_id"), conn);
                        Cours cours = new Cours(
                            rs.getInt("id"),
                            rs.getDate("date").toLocalDate(),
                            rs.getTime("heureDebut").toLocalTime(),
                            rs.getTime("heureFin").toLocalTime(),
                            professor,
                            module
                        );
                        coursList.add(cours);
                    }
                }
            }
            return coursList;
        }

        private Professor findProfessorById(int id, Connection conn) throws SQLException {
            String query = "SELECT * FROM Professor WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Professor(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("tel")
                        );
                    }
                }
            }
            return null;
        }

        private Module findModuleById(int id, Connection conn) throws SQLException {
            String query = "SELECT * FROM Module WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Module(
                            rs.getInt("id"),
                            rs.getString("nom")
                        );
                    }
                }
            }
            return null;
        }
    }
}
