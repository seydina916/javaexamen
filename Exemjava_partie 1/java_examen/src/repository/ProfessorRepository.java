package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Professor;

public interface ProfessorRepository {
    void save(Professor professor) throws SQLException;
    List<Professor> findAll() throws SQLException;
    Professor findById(int id) throws SQLException;
    void update(Professor professor) throws SQLException;
    void deleteById(int id) throws SQLException;

    class ProfessorRepositoryImpl implements ProfessorRepository {
      
        public void save(Professor professor) throws SQLException {
            String sql = "INSERT INTO Professor(id, nom, prenom, tel) VALUES(?, ?, ?, ?)";
            try (Connection db = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = db.prepareStatement(sql)) {
                preparedStatement.setInt(1, professor.getId());
                preparedStatement.setString(2, professor.getNom());
                preparedStatement.setString(3, professor.getPrenom());
                preparedStatement.setString(4, professor.getTel());
                int count = preparedStatement.executeUpdate();
                if (count == 0) {
                    System.out.println("Une erreur est survenue, le professeur n'a pas été créé");
                }
            }
        }

        @Override
        public List<Professor> findAll() throws SQLException {
            String sql = "SELECT id, nom, prenom, tel FROM Professor";
            try (Connection db = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = db.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Professor> professors = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String tel = resultSet.getString("tel");
                    Professor professor = new Professor(id, nom, prenom, tel);
                    professors.add(professor);
                }
                return professors;
            }
        }

        @Override
        public Professor findById(int id) throws SQLException {
            String sql = "SELECT id, nom, prenom, tel FROM Professor WHERE id = ?";
            Professor professor = null;
            try (Connection db = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = db.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        professor = new Professor(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("tel")
                        );
                    }
                }
            }
            return professor;
        }

        @Override
        public void update(Professor professor) throws SQLException {
            String sql = "UPDATE Professor SET nom = ?, prenom = ?, tel = ? WHERE id = ?";
            try (Connection db = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = db.prepareStatement(sql)) {
                preparedStatement.setString(1, professor.getNom());
                preparedStatement.setString(2, professor.getPrenom());
                preparedStatement.setString(3, professor.getTel());
                preparedStatement.setInt(4, professor.getId());
                preparedStatement.executeUpdate();
            }
        }

        @Override
        public void deleteById(int id) throws SQLException {
            String sql = "DELETE FROM Professor WHERE id = ?";
            try (Connection db = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = db.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        }
    }
}
