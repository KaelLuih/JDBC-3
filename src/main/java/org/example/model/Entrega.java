package org.example.model;

import java.util.Date;

public class Entrega {

    private int id ;
    private int pedido_id;
    private int motorista_id;
    private Date dataSaida;
    private Date data_entrega;
    private StatusEntrega status;

    public Entrega(int pedido_id, int motorista_id, Date data_saida) {
        this.pedido_id = pedido_id;
        this.motorista_id = motorista_id;
        this.dataSaida = data_saida;
        this.status = StatusEntrega.EM_ROTA;
    }

    public Entrega(int id, int pedido_id, int motorista_id, Date dataSaida, StatusEntrega status) {
        this.id = id;
        this.pedido_id = pedido_id;
        this.motorista_id = motorista_id;
        this.dataSaida = dataSaida;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(int motorista_id) {
        this.motorista_id = motorista_id;
    }

    public Date getData_saida() {
        return dataSaida;
    }

    public void setData_saida(Date data_saida) {
        this.dataSaida = data_saida;
    }

    public Date getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }
}
