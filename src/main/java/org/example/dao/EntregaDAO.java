package org.example.dao;

import org.example.model.Entrega;
import org.example.model.StatusEntrega;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EntregaDAO {


    public void atribuirEntrega(Entrega entrega) {
        String query = "INSERT INTO Entrega (" +
                "pedido_id," +
                "motorista_id," +
                "data_saida," +
                "status)" +
                "VALUES (?,?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, entrega.getPedido_id());
            stmt.setInt(2, entrega.getMotorista_id());
            stmt.setTimestamp(3, new java.sql.Timestamp(entrega.getData_saida().getTime()));
            stmt.setString(4, StatusEntrega.EM_ROTA.toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarHistorico(int entregaID) {
        String query = "SELECT " +
                "data_saida, " +
                "status" +
                "FROM Entrega " +
                "WHERE entrega_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, entregaID);
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void AtualizarStatus(int entregaID, StatusEntrega novoStatus) {
            String query = "UPDATE Entrega" +
                    "           SET status = ? " +
                    "           WHERE id = ?";
            try (Connection conn = Conexao.conectar();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, novoStatus.toString());
                stmt.setInt(2, entregaID);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    public void LIstarEntregaCLienteMotorista() {
        String query = "SELECT " +
                "e.id AS entrega_id, " +
                "c.nome AS nome_cliente, " +
                "m.nome AS nome_motorista, " +
                "e.status " +
                "FROM Entrega e " +
                "INNER JOIN pedido p ON e.pedido_id = p.id " +
                "INNER JOIN cliente c ON p.id_cliente = c.id " +
                "INNER JOIN Motorista m ON e.Motorista_id = m.id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            System.out.println("---Registro de Entrega---");
            while (rs.next()) {
                System.out.printf("Entrega %d: Cliente %s | Motorista %s | Status %s%n",
                        rs.getInt("entrega_id"),
                        rs.getString("nome_cliente"),
                        rs.getString("nome_motorista"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Integer> AtrasadasPorCidade() {
        String query = "SELECT c.cidade, COUNT(e.id) AS total " +
                "FROM Entrega e " +
                "INNER JOIN pedido p ON e.pedido_id = p.id " +
                "INNER JOIN cliente c ON p.id_cliente = c.id " +
                "WHERE e.status = 'ATRASADA' " +
                "GROUP BY c.cidade " +
                "ORDER BY total DESC";
        Map<String, Integer> relatorio = new LinkedHashMap<>();
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String cidade = rs.getString("cidade");
                int total = rs.getInt("total");
                relatorio.put("cidade: " + cidade , total);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorio;
    }

    public List<Entrega> DeletarEntrega(int idEntrega) {
        String query = "DELETE FROM Entrega " +
                "WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idEntrega);
            int validacao = stmt.executeUpdate();

            if (validacao == 0) {
                System.out.println("Entrega não encontrada");

            } else {
                System.out.println("Excluida com sucesso");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
