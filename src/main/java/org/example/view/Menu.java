package org.example.view;

import java.util.Scanner;

public class Menu {
static Scanner input = new Scanner(System.in);
    public static int MenuPrincipal(){
        System.out.println("""
                1 - Cadastrar Cliente
                2 - Cadastrar Motorista
                3 - Criar Pedido
                4 - Atribuir Pedido a Motorista (Gerar Entrega)
                5 - Registrar Evento de Entrega (Histórico)
                6 - Atualizar Status da Entrega
                7 - Listar Todas as Entregas com Cliente e Motorista
                8 - Relatório: Total de Entregas por Motorista
                9 - Relatório: Clientes com Maior Volume Entregue
                10 - Relatório: Pedidos Pendentes por Estado
                11 - Relatório: Entregas Atrasadas por Cidade
                12 - Buscar Pedido por CPF/CNPJ do Cliente
                13 - Cancelar Pedido
                14 - Excluir Entrega (com validação)
                15 - Excluir Cliente (com verificação de dependência)
                16 - Excluir Motorista (com verificação de dependência)
                0 - Sair""");
        int escolha = input.nextInt();
        return escolha;
    }

}
