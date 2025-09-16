package org.example.dao;

import org.example.model.Motorista;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotoristaDAO {


    public void InserirMotorista(Motorista motorista) {
        String query = "INSERT INTO Motorista (" +
                "nome," +
                "cnh," +
                "cidade_base)" +
                "VALUES " +
                "(?,?,?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getCidade_base());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public int TotalEntregas(int idMotorista) {
        String query = "SELECT COUNT(*) FROM Entrega WHERE motorista_id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idMotorista);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean possuiEntregas(Connection conn, int idMotorista) throws SQLException {
        String query = """
                SELECT *
                FROM Entrega e
                WHERE e.motorista_id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idMotorista);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        }
    }

    public boolean DeletarMotorista(int idMotorista) throws SQLException {
        String query = "DELETE FROM Motorista WHERE id = ?  ";

        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);

            if (possuiEntregas(conn, idMotorista)) {
                System.out.println("Motorista possui entregas");
                conn.rollback();
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idMotorista);
                int validacao = stmt.executeUpdate();

                if (validacao > 0) {
                    conn.commit();
                    System.out.println("Motorista excluido com sucesso");
                    return true;
                } else {
                    conn.rollback();
                    System.out.println("Item não encontrado");
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


}
