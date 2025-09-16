package org.example.controller;

import org.example.service.Gerenciamento;
import org.example.view.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class Executar {

    Menu menu = new Menu();
    Gerenciamento executador = new Gerenciamento();
    Scanner input = new Scanner(System.in);

    public void ExecutarSistema() throws SQLException {

        while (true) {

            System.out.println("--- SISTEMA DE LOGISTICA ---");
            int escolha = menu.MenuPrincipal();

            switch (escolha) {
                case 1: {
                    executador.cadastrarCliente();
                    break;
                }
                case 2: {
                    executador.cadastrarMotorista();
                    break;
                }
                case 3: {
                    executador.criarPedido();
                    break;
                }
                case 4: {
                    executador.gerarEntrega();
                }
                case 5: {
                    executador.GerarHistorico();
                }
                case 6: {
                    executador.AtualizarEntrega();
                }
                case 7: {
                    executador.listarEntregaClienteMotorista();
                }
                case 8: {
                    executador.TotalEntregas();
                }
                case 9: {
                    executador.ClientesMaiorVolume();
                }
                case 10: {
                    executador.PedidosPEndentes();
                }
                case 11: {
                    executador.EntregasAtrasadas();
                }
                case 12: {
                    executador.BuscarCpf();
                }
                case 13: {
                    executador.cancelarPedido();
                }
                case 14: {
                    executador.ExcluirEntrega();
                }
                case 15: {
                    executador.ExcluirMotorista();
                }
                case 16: {
                    executador.ExcluirCliente();
                }

            }
        }
    }

}
