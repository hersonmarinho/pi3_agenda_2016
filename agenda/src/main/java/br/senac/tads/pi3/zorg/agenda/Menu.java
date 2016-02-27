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
                    break;
                }
                case 2: {
                    agenda.cadastrarPessoas();
                    break;
                }
                case 3: {
                    agenda.alterarPessoas();
                    break;
                }
                case 4: {
                    agenda.excluirPessoas();
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
