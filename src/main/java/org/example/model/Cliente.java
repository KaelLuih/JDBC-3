package org.example.model;

public class Cliente {
    private int id;
    private String nome;
    private String cpf_cnp;
    private String endereco;
    private String cidade;
    private String estado;

    public Cliente(int id, String nome, String cpf_cnp, String endereco, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.cpf_cnp = cpf_cnp;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cliente(String nome, String cpf_cnp, String endereco, String cidade, String estado) {
        this.nome = nome;
        this.cpf_cnp = cpf_cnp;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnp() {
        return cpf_cnp;
    }

    public void setCpf_cnp(String cpf_cnp) {
        this.cpf_cnp = cpf_cnp;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
