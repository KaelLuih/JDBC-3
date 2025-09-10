package org.example.dao;

import org.example.model.Pedido;
import org.example.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PedidoDAO {

    public void criarPedido(Pedido pedido) {
        String query = "INSERT INTO pedido (" +
                "id_cliente," +
                "data_pedido," +
                "volume_m3," +
                "peso_kg," +
                "status)" +
                "VALUES " +
                "(?,?,?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, pedido.getId_cliente());
            stmt.setDate(2, new java.sql.Date(pedido.getData_pedido().getTime()));
            stmt.setString(3, pedido.getVolume_m3());
            stmt.setString(4, pedido.getPeso_kg());
            stmt.setString(5, pedido.getStatus().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static List<Pedido> BuscarPedidoPorCpfCnpj(String cpfcnpj)  {
        List<Pedido> pedidos = new ArrayList<>();

        String query = "SELECT p.id, p.data_pedido,c.nome,c.cpf_cnpj" +
                "FROM pedido p" +
                "INNER JOIN cliente c ON p.id_cliente =c.id" +
                "WHERE c.cpf_cnpj = ?";


        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

stmt.setString(1,cpfcnpj);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                Date datapedido = rs.getDate("data_pedido");
                String volume = rs.getString("volume_m3");
                String peso = rs.getString("peso_kg");
                String status = rs.getString("status");

                var pedido = new Pedido(id,datapedido,volume,peso,status);
                pedidos.add(pedido);
            }

        }catch (SQLException e ){
            e.printStackTrace();
        }
        return pedidos;
    }
    public void CancelarPedido(int idPedido){
        String query = "UPDATE pedido SET STATUS = 'CANCELADO WHERE id =?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,idPedido);
            System.out.println("Pedido alterado com sucesso " );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
