package org.example.controller;

import org.example.service.Gerenciamento;
import org.example.view.Menu;

import java.util.Scanner;

public class Executar {

    Menu menu = new Menu();
    Gerenciamento executador = new Gerenciamento();
    Scanner input = new Scanner(System.in);

    public void ExecutarSistema() {

        while (true) {

            System.out.println("--- SISTEMA DE LOGISTICA ---");
            int escolha = menu.MenuPrincipal();

            switch (escolha) {
                case 1: {
                    executador.cadastrarCliente();

                }
                case 2:{

                }
                case 15:{
                    //Aplicar rollback
                }

            }
        }
    }

}
