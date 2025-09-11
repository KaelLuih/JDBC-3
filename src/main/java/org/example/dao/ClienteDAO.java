package org.example.dao;

import org.example.model.Cliente;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClienteDAO {

    public void inserir(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente( " +
                "                    nome," +
                "                    cpf_cnpj," +
                "                    endereco," +
                "                    cidade," +
                "                    estado)   " +
                "VALUES " +
                "(?,?,?,?,?) ";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf_cnp());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String> clientescommaiorEntregas() {
        String query = "SELECT c.nome, COUNT(e.id) AS total_entregas " +
                "FROM cliente c " +
                "JOIN pedido p ON c.id = p.id_cliente " +
                "JOIN Entrega e ON p.id = e.pedido_id " +
                "GROUP BY c.id, c.nome " +
                "ORDER BY total_entregas DESC";

        List<String> resultado = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            var rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int total = rs.getInt("total_entregas");
                resultado.add("nome:" +nome + " - " + "total: " + total + " De Entregas Recebidas  ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;

    }

    public Map<String, Integer> PedidosPEndentesPorEstado() {
        String query = "SELECT c.estado, COUNT(p.id) AS total " +
                "FROM cliente c " +
                "JOIN pedido p ON c.id = p.id_cliente " +
                "WHERE p.status = 'PENDENTE' " +
                "GROUP BY c.estado " +
                "ORDER BY total DESC";

        Map<String, Integer> relatorio = new LinkedHashMap<>();
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String estado = rs.getString("estado");
                int total = rs.getInt("total");
                relatorio.put("Estado:" + estado ,   total );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatorio;
    }

    public boolean DeletarCliente(int idCLiente) {
        String query = "DELETE FROM cliente " +
                "WHERE id = ?";




        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);

            if(temPedido(conn,idCLiente)){
                System.out.println("Clientes tem alguma entrega vinculada não é possivel realizar a ação");
                conn.rollback();
                return false;
            }

            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1,idCLiente);
                int verificacao = stmt.executeUpdate();

                if(verificacao > 0){
                    conn.commit();
                    System.out.println("Cliente deletado com sucesso");
                    return true;
                }else {
                    conn.rollback();
                    System.out.println("Cliente não encontrado");
                    return false;
                }


            }



        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

    public boolean temPedido(Connection conn, int idCliente) throws SQLException {
        String query = "SELECT e.id " +
                "FROM Entrega e " +
                "INNER JOIN pedido p ON e.pedido_id = p.id " +
                "WHERE p.id_cliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
    }


}
