package org.example.dao;

import org.example.model.Motorista;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoristaDAO {


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
        String query = "SELECT COUNT(*) FROM Entregas WHERE motorista_id = ?";
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




}
