package org.example.service;

import org.example.dao.ClienteDAO;
import org.example.model.Cliente;

import java.sql.SQLException;
import java.util.Scanner;

public class Gerenciamento {

static Scanner input = new Scanner(System.in);
public static void cadastrarCliente(){
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

    var cliente = new Cliente(nome,cpf_cnpj,endereco,cidade,estado);
var dao = new ClienteDAO();

try {
    dao.inserir(cliente);

}catch (SQLException erro ){
    erro.printStackTrace();
}catch (IllegalArgumentException erro){
System.out.println("Voce nao pode deixar espaços vazios" + erro.getMessage());
}
}


}
