/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.zorg.agenda;

import java.util.Scanner;

/**
 *
 * @author herso
 */
public class Menu {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("Escolha a opção desejada:");
            System.out.println("1. Consultar Contato");
            System.out.println("2. Cadastrar Contato");
            System.out.println("3. Alterar Contato");
            System.out.println("4. Excluir Contato");
            System.out.println("5. Sair da Agenda");
            System.out.println("");

            opcao = leitor.nextInt();
            switch (opcao) {
                case 1: {
                    agenda.listarPessoas();
                    System.out.println("");
                    break;
                }
                case 2: {
                    String nomeContato = null;
                    String dataNasc = null;
                    String email = null;
                    String telefone = null;
                    System.out.println("Informe os dados do contato:");

                    System.out.println("Nome: ");
                    nomeContato = leitor.nextLine();
                    nomeContato = leitor.nextLine();
                    System.out.println("");

                    System.out.println("Telefone: ");
                    telefone = leitor.nextLine();
                    System.out.println("");

                    System.out.println("E-mail: ");
                    email = leitor.nextLine();
                    System.out.println("");

                    System.out.println("Data de Nascimento: ");
                    dataNasc = leitor.nextLine();
                    System.out.println("");
                    agenda.cadastrarPessoas(telefone, nomeContato, email, dataNasc);
                    break;
                }
                case 3: {
                    agenda.alterarPessoas();
                    break;
                }
                case 4: {
                    System.out.println("Digite o ID do contato que deseja excluir: ");
                    int idExcluir = leitor.nextInt();
                    System.out.println("");
                    agenda.excluirPessoas(idExcluir);
                    break;
                }
                case 5: {
                    System.out.println("Agenda encerrada!");
                    break;
                }
                default:
                    System.out.println("Opção inválida, digite novamente!");
            }
        }
    }

}
