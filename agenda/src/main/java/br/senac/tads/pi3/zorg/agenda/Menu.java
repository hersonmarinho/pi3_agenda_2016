/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.zorg.agenda;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    agenda.listarContatos();
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
                    nomeContato = lerString();
                    System.out.println("");

                    System.out.println("Telefone: ");
                    telefone = lerString();
                    System.out.println("");

                    System.out.println("E-mail: ");
                    email = lerString();
                    System.out.println("");
                    
                    DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Data de Nascimento: ");
                    dataNasc = lerString();
                    try{
                        // Pega a string digitada e converte para data
                        Date dataFormatada = formatadorData.parse(dataNasc);
                        System.out.println("");
                        agenda.cadastrarContato(telefone, nomeContato, email, dataFormatada);
                    }catch(ParseException ex){
                        System.out.println("Data inválida");
                    }
                    
                    break;
                }
                case 3: {
                    System.out.println("Qual ID do contato que você deseja alterar?");
                    // Recebe contato
                    int id = leitor.nextInt();
                    DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
                    // Exibe os dados do contato
                    Contatos contato = agenda.listarContato(id);
                    System.out.println("Nome: "+ contato.getNome());
                    System.out.println("Telefone: "+ contato.getTelefone());
                    System.out.println("E-mail: "+ contato.getEmail());
                    System.out.println("Data de nascimento: "+ formatadorData.format(contato.getDataNascimento()));
                    
                    String resposta = null;
                    System.out.println("Deseja alterar o Nome? (S/N)");
                    resposta = lerString();
                    if(resposta.equalsIgnoreCase("S")){
                        contato.setNome(lerString());
                    }
                    System.out.println("Deseja alterar o Telefone? (S/N)");
                    resposta = lerString();
                    if(resposta.equalsIgnoreCase("S")){
                        contato.setTelefone(lerString());
                    }
                    System.out.println("Deseja alterar o E-mail? (S/N)");
                    resposta = lerString();
                    if(resposta.equalsIgnoreCase("S")){
                        contato.setEmail(lerString());
                    }
                    System.out.println("Deseja alterar a Data de nascimento? (S/N)");
                    resposta = lerString();
                    if(resposta.equalsIgnoreCase("S")){
                        String dataNova = lerString();
                        try{
                            contato.setDataNascimento(formatadorData.parse(dataNova));
                        }catch(ParseException ex){
                            System.err.println("Por favor digite uma data válida");
                        }
                    }
                    // Salva
                    agenda.alterarContato(contato);
                    break;
                }
                case 4: {
                    System.out.println("Digite o ID do contato que deseja excluir: ");
                    int idExcluir = leitor.nextInt();
                    System.out.println("");
                    agenda.excluirContato(idExcluir);
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
    
    public static String lerString(){
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }
}
