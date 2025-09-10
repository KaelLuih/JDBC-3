package org.example.model;

import java.util.Date;

public class Pedido {

private int id;
private int id_cliente;
private Date data_pedido;
private String volume_m3;
private String peso_kg;
private  Status status;

    public Pedido(int id, java.sql.Date datapedido, String volume, String peso, String status) {
        this.id = this.id;
        this.id_cliente = id_cliente;
        this.data_pedido = data_pedido;
        this.volume_m3 = volume_m3;
        this.peso_kg = peso_kg;
        this.status = this.status;
    }

    public Pedido(Date data_pedido, String volume_m3, String peso_kg, Status status) {
        this.data_pedido = data_pedido;
        this.volume_m3 = volume_m3;
        this.peso_kg = peso_kg;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }

    public String getVolume_m3() {
        return volume_m3;
    }

    public void setVolume_m3(String volume_m3) {
        this.volume_m3 = volume_m3;
    }

    public String getPeso_kg() {
        return peso_kg;
    }

    public void setPeso_kg(String peso_kg) {
        this.peso_kg = peso_kg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

