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
                    break;
                }
                case 5: {
                    executador.GerarHistorico();
                    break;
                }
                case 6: {
                    executador.AtualizarEntrega();
                    break;
                }
                case 7: {
                    executador.listarEntregaClienteMotorista();
                    break;
                }
                case 8: {
                    executador.TotalEntregas();
                    break;
                }
                case 9: {
                    executador.ClientesMaiorVolume();
                    break;
                }
                case 10: {
                    executador.PedidosPEndentes();
                    break;
                }
                case 11: {
                    executador.EntregasAtrasadas();
                    break;
                }
                case 12: {
                    executador.BuscarCpf();
                    break;
                }
                case 13: {
                    executador.cancelarPedido();
                    break;
                }
                case 14: {
                    executador.ExcluirEntrega();
                    break;
                }
                case 15: {
                    executador.ExcluirMotorista();
                    break;
                }
                case 16: {
                    executador.ExcluirCliente();
                    break;
                }

            }
        }
    }

}
