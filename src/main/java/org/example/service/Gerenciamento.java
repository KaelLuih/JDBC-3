package org.example.service;

import org.example.dao.*;
import org.example.model.*;
import org.example.view.Menu;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Gerenciamento {

    static Scanner input = new Scanner(System.in);

    public static void cadastrarCliente() {
        System.out.println("Digite o nome do cliente");
        String nome = input.nextLine();
        System.out.println("Digite o cnpj ou cpf do cliente");
        String cpf_cnpj = input.nextLine();
        System.out.println("Digite o endereço do cliente");
        String endereco = input.nextLine();
        System.out.println("Digite a cidade do cliente ");
        String cidade = input.nextLine();
        System.out.println("Digite o estado do cliente");
        String estado = input.nextLine();

        var cliente = new Cliente(nome, cpf_cnpj, endereco, cidade, estado);
        var dao = new ClienteDAO();

        try {
            dao.inserir(cliente);
            Menu.MensagemDeSucesso();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } catch (IllegalArgumentException erro) {
            System.out.println("Voce nao pode deixar espaços vazios" + erro.getMessage());
        }
    }

    public static void cadastrarMotorista() {

        System.out.println("Digite o nome do motorista");
        String nome = input.nextLine();
        System.out.println("Digite a cnh do motorista");
        String cnh = input.nextLine();
        System.out.println("Digite a cidade Base");
        String cidade_base = input.nextLine();


        var motorista = new Motorista(nome, cnh, cidade_base);
        var dao = new MotoristaDAO();

        try {
            dao.InserirMotorista(motorista);
            Menu.MensagemDeSucesso();
        } catch (IllegalArgumentException erro) {
            System.out.println("Voce nao pode deixar espaços vazios" + erro.getMessage());
        }
    }

    public static void criarPedido() {
        System.out.println("Digite o id do cliente  ");
        int id_cliente = input.nextInt();
        input.nextLine();

        System.out.println("Digite a data do pedido (yyyy-MM-dd):");
        String dataStr = input.nextLine();
        java.sql.Date data_pedido = java.sql.Date.valueOf(dataStr);

        System.out.println("Digite o volume em m3 do pedido");
        String volume_m3 = input.nextLine();

        System.out.println("Digite o peso em kg do pedido");
        String peso_kg = input.nextLine();

        var pedido = new Pedido(id_cliente, data_pedido, volume_m3, peso_kg, Status.PENDENTE);
        var dao = new PedidoDAO();

        try {
            dao.criarPedido(pedido);
            Menu.MensagemDeSucesso();
        } catch (IllegalArgumentException erro) {
            System.out.println("Voce não pode deixar espaços em branco " + erro.getMessage());
        }
    }

    public static void gerarEntrega() {
        System.out.println("digite o id do pedido");
        int id_pedido = input.nextInt();
        input.nextLine();
        System.out.println("Digite do id do motorista");
        int id_motorista = input.nextInt();
        input.nextLine();
        Date data_saida = new Date();

        var entrega = new Entrega(id_pedido, id_motorista, data_saida);
        var dao = new EntregaDAO();

        try {
            dao.atribuirEntrega(entrega);
            Menu.MensagemDeSucesso();
        } catch (IllegalArgumentException erro) {
            System.out.println("Não pode deixar em branco " + erro.getMessage());
        }
    }

    public static void GerarHistorico() {
        System.out.println("Digite o id da entrega");
        int id_entrega = input.nextInt();
        input.nextLine();

        Date dataEvento = new Date();

        System.out.println("Digite a descrição ");
        String descricao = input.nextLine();

        var historico = new historicoEntrega(id_entrega, dataEvento, descricao);
        var dao = new HistoricoDAO();

        try {
            dao.registrarEvento(historico);
            Menu.MensagemDeSucesso();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void AtualizarEntrega() {
        System.out.println("Digite o id da entrega");
        int id_entrega = input.nextInt();
        input.nextLine();

        System.out.println("Digite o status que deseja (1)EM ROTA (2)ENTREGUE (3)ATRASADA");
        int escolha = input.nextInt();
        input.nextLine();

        StatusEntrega status;
        switch (escolha) {
            case 1 -> status = StatusEntrega.EM_ROTA;
            case 2 -> status = StatusEntrega.ENTREGUE;
            case 3 -> status = StatusEntrega.ATRASADA;
            default -> throw new IllegalStateException("Unexpected value: " + escolha);
        }

        var dao = new EntregaDAO();

        try {
            dao.AtualizarStatus(id_entrega, status);
            Menu.MensagemDeSucesso();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public static void listarEntregaClienteMotorista() {
        var dao = new EntregaDAO();
      dao.LIstarEntregaCLienteMotorista();
        }
        public static void TotalEntregas(){
        System.out.println("Digite o id do motorista");
        int id_motorista = input.nextInt();
        input.nextLine();

        var dao = new MotoristaDAO();

        try {
           int total = dao.TotalEntregas(id_motorista);
            System.out.println("O total do motorista" + id_motorista + "é de :" + total);
        }catch (Exception e){
            e.printStackTrace();
        }



        }

        public static void ClientesMaiorVolume(){
        var dao = new ClienteDAO();

        List <String> resultado = dao.clientescommaiorEntregas();

            System.out.println(resultado);


        }
        public static void PedidosPEndentes(){
        var dao = new ClienteDAO();
            Map<String, Integer> resultado = dao.PedidosPEndentesPorEstado();

            System.out.println(resultado);
        }
public static void EntregasAtrasadas(){
        var dao = new EntregaDAO();
        Map<String,Integer> resultado = dao.AtrasadasPorCidade();

        System.out.println(resultado);
}

    public static void BuscarCpf(){

        System.out.println("Digite o cpf ou o cnpj");
        String cpf_cnpj = input.nextLine();


        var dao = new PedidoDAO();
List<Pedido> resultado = dao.BuscarPedidoPorCpfCnpj(cpf_cnpj);

        System.out.println(resultado);

    }

    }


