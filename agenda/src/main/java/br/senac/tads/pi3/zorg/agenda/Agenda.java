/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.zorg.agenda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herso
 */
public class Agenda {

    // Obtem os contatos do banco
    public void listarContatos() {
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL FROM TB_CONTATO";
        try {
            DBConnection banco = new DBConnection();
            conn = banco.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

            while (resultados.next()) {
                Long id = resultados.getLong("ID_CONTATO");
                String nome = resultados.getString("NM_CONTATO");
                Date dataNasc = resultados.getDate("DT_NASCIMENTO");
                String email = resultados.getString("VL_EMAIL");
                String telefone = resultados.getString("VL_TELEFONE");
                //List<Contatos> contatos = new ArrayList<>();
                
                System.out.println("ID: " + String.valueOf(id) + ", NOME: " + nome + ", DT NASCIMENTO: " + formatadorData.format(dataNasc) + ", E-MAIL: " + email + ", TELEFONE: " + telefone);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Contatos listarContato(int id) {
        String sql = "SELECT * FROM TB_CONTATO WHERE ID_CONTATO = ?";
        Contatos contato = new Contatos();
        try {
            DBConnection banco = new DBConnection();
            Connection conn = banco.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                contato.setId(rs.getInt("ID_CONTATO"));
                contato.setNome(rs.getString("NM_CONTATO"));
                contato.setDataNascimento(rs.getDate("DT_NASCIMENTO"));
                contato.setEmail(rs.getString("VL_EMAIL"));
                contato.setTelefone(rs.getString("VL_TELEFONE"));
            }
        }catch (ClassNotFoundException ex){
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contato;
    }

    //Método para cadastrar contatos
    public void cadastrarContato(String telefone, String nomeContato, String email, java.util.Date dataNasc) {
        Connection conn = null;

        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
                + "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            DBConnection banco = new DBConnection();
            conn = banco.obterConexao();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nomeContato);
            java.sql.Date dataBanco = new java.sql.Date(dataNasc.getTime());
            ps.setDate(2, dataBanco);
            ps.setString(3, telefone);
            ps.setString(4, email);
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("O contato foi incluído com sucesso!");
                System.out.println("");
            } else {
                System.out.println("O contato não foi incluído!");
                System.out.println("");
            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Altera os dados do contato
    public void alterarContato(Contatos contato) {
        Connection conn = null;
        String sql = "UPDATE TB_CONTATO SET NM_CONTATO = ?, DT_NASCIMENTO = ?, VL_TELEFONE = ?, VL_EMAIL = ? WHERE ID_CONTATO = ?";

        try {
            DBConnection banco = new DBConnection();
            conn = banco.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, contato.getNome());
            // Passa a data do java para data do banco
            java.sql.Date dataBanco = new java.sql.Date(contato.getDataNascimento().getTime());
            ps.setDate(2, dataBanco);
            ps.setString(3, contato.getTelefone());
            ps.setString(4, contato.getEmail());
            // Seta qual contato será alterado
            ps.setInt(5, contato.getId());
            
            int retornoQuery = ps.executeUpdate();
            if(retornoQuery > 0){
                System.out.println("Cadastro atualizado com sucesso");
            }else{
                System.out.println("Não foi possível realizar a operação desejada");
            }
        }catch(ClassNotFoundException ex){
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.err.println("Não foi possível alterar os dados do contato");
            System.out.println(ex);
        }
    }

    // Remove o contato do banco
    public void excluirContato(int idExcluir) {
        Connection conn = null;
        String sql = "DELETE FROM TB_CONTATO WHERE ID_CONTATO = ?";
        try {
            DBConnection banco = new DBConnection();
            conn = banco.obterConexao();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idExcluir);
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("O contato foi excluído com sucesso!");
                System.out.println("");
            } else {
                System.out.println("O contato não excluído!");
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
