package org.example.dao;

import org.example.util.Conexao;
import org.example.model.historicoEntrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HistoricoDAO {



        public void registrarEvento(historicoEntrega evento) {
            String query = "INSERT INTO HistoricoEntrega (entrega_id, data_evento, descricao) VALUES (?,?,?)";

            try (Connection conn = Conexao.conectar();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, evento.getEntregaId());
                stmt.setTimestamp(2, new Timestamp(evento.getDataEvento().getTime()));
                stmt.setString(3, evento.getDescricao());

                stmt.executeUpdate();
                System.out.println("Evento registrado no histórico com sucesso!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
