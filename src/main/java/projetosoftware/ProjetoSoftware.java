/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosoftware;

import exception.SGBDException;
import gui.view.GuiLivro;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dao.bd.conexao.Conexao;

/**
 *
 * @author TESTE
 */
public class ProjetoSoftware {

    // atributos de conexão com o Banco de Dados
    public static Conexao conexao;
    private GuiLivro guiLivro;

    // construtor
    public ProjetoSoftware() {
        // criar objeto interface
        guiLivro = new GuiLivro(new JFrame(), true);

        // criar objeto banco de dados
        conexao = new Conexao();
    }

    /**
     * *
     * conectar
     */
    private void conectar() {
        try {
            conexao.conectar();
        } catch (SGBDException | ClassNotFoundException | SQLException ex) {
            // houve erro
            JOptionPane.showMessageDialog(this.getGuiLivro(),
                    "Não foi possível estabelecer uma conexão com o banco de dados!"
                    + "\nO sistema será encerrado" + ex,
                    "Conexão com o Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    /**
     * desconectar
     */
    public void desconectar() {
        try {
            // encerrar conexão
            conexao.desconectar();
            JOptionPane.showConfirmDialog(this.getGuiLivro(),
                    "Conexão com o banco de dados foi encerrada com sucesso",
                    "Conexão com o SGBD", JOptionPane.DEFAULT_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(this.getGuiLivro(),
                    "Conexão com o banco de dados não foi encerrada com sucesso",
                    "Conexão com o SGBD", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * chama tela de cidade
     */
    public void mostrarTelaLivro() {
        this.getGuiLivro().getCo().limpar();
        this.getGuiLivro().setVisible(true);
    }

    public static Conexao getConexao() {
        return conexao;
    }

    public GuiLivro getGuiLivro() {
        return guiLivro;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProjetoSoftware ps = new ProjetoSoftware();

        // conectar
        ps.conectar();

        // mostrar tela cidade
        ps.mostrarTelaLivro();
        
        // desconectar
        ps.desconectar();
    }

}
