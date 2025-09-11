package org.example.model;

import java.util.Date;

public class historicoEntrega {

        private int id;
        private int entregaId;
        private Date dataEvento;
        private String descricao;

        public historicoEntrega(int entregaId, Date dataEvento, String descricao) {
            this.entregaId = entregaId;
            this.dataEvento = dataEvento;
            this.descricao = descricao;
        }

        public int getId() { return id; }
        public int getEntregaId() { return entregaId; }
        public Date getDataEvento() { return dataEvento; }
        public String getDescricao() { return descricao; }

}
