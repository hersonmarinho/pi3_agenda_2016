/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.zorg.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fabricio
 */
public class DBConnection {

    private Connection conn;
    
    // Método que inicia a conexão com o banco
    public Connection obterConexao() throws SQLException, ClassNotFoundException {
        try {
            // Passo 1: Registrar driver JDBC.
            Class.forName("org.apache.derby.jdbc.ClientDataSource");

            // Passo 2: Abrir a conexÃ£o
            conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/agendabd;SecurityMechanism=3",
                    "app", // usuario
                    "app"); // senha
        } catch (SQLException ex) {
            System.err.println("Não foi possível conectar ao banco de dados");
        }
        return conn;
    }
    
    public void fecharConexao(){
        try{
            conn.close();
        }catch(SQLException ex){
            System.err.println("Não foi possível fechar a conexão\n" + ex);
        }
    }
}
