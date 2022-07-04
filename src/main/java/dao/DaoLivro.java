/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.bd.conexao.Conexao;
import exception.LivroException;
import exception.SGBDException;
import model.vo.VoConsulta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.vo.VoLivro;
import projetosoftware.ProjetoSoftware;

/**
 * create table cidade ( codigo int not null primary key, nome varchar not null,
 * autor varchar not null );
 */
/**
 *
 * @author dlnotari
 */
public class DaoLivro implements I_DAO {

    // atributos
    private VoLivro vo;

    // construtor
    public DaoLivro(VoLivro vo) {
        this.vo = vo;
    }

    /**
     * *
     *
     * @return
     * @throws exception.LivroException @throws SQLException
     */
    @Override
    public boolean cadastrar() throws LivroException {
        try {
            // testa se existe
            String sql;
            if (this.existeCodigo()) {
                // regravar
                sql = "update livro  "
                        + "set titulo      = ?"
                        + "where codigo  = ?;" 
                        + "update autor  "
                        + "set nome = ?"
                        + "where codigo = ?;";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // atribui valores
                ps.setInt(2, vo.getCodigo());
                ps.setString(1, vo.getNome());
                ps.setString(3, vo.getAutor());
                ps.setInt(4, vo.getCodigo());

                // regrava no bd
                this.getConexao().getBd().executaSQL(ps);
            } else {
                // gravar
                sql = "insert into livro (codigo, titulo)"
                        + " values (?, ?);"
                        + "insert into Autor (codigo, nome)"
                        + "values(?, ?);";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // gerar o código
                vo.setCodigo(this.proximoCodigoLivre());

                // atribui valores            
                ps.setInt(1, vo.getCodigo());
                ps.setString(2, vo.getNome());
                ps.setInt(3, vo.getCodigo());
                ps.setString(4, vo.getAutor());

                // regrava no bd
                this.getConexao().getBd().executaSQL(ps);
            }
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new LivroException(e.getMessage());
        }

        // return, tudo certo ao salvar
        return true;
    }

    /**
     *
     * @return
     * @throws exception.LivroException @throws SQLException
     */
    @Override
    public boolean excluir() throws LivroException {
        try {
            // testa se existe
            String sql;
            if (this.existeCodigo()) {
                // regravar
                sql = "delete from livro where codigo = ?";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // atribui valores
                ps.setInt(1, vo.getCodigo());

                // exclui no bd
                this.getConexao().getBd().executaSQL(ps);
                // return, tudo certo ao excluir
                return true;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new LivroException(e.getMessage());
        }

        // return
        return false;
    }

    /**
     *
     * @return
     * @throws exception.LivroException @throws SQLException
     */
    public VoConsulta obterLista() throws LivroException {
        try {
            // consultar o código
            String sql = "select livro.codigo, livro.titulo, autor.nome from livro join autor on livro.codigo = autor.codigo where livro.codigo > 0 order by livro.titulo";

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // cria lista de cidades
            return new VoConsulta(rs);
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new LivroException(e.getMessage());
        }
    }

    /**
     * *
     *
     * @return
     * @throws exception.LivroException @throws SQLException
     */
    @Override
    public boolean consultar() throws LivroException {
        try {
            // consultar o código
            String sql = "select livro.codigo, livro.titulo, autor.nome from livro join autor on livro.codigo = autor.codigo where livro.codigo = " + this.getVo().getCodigo();

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                this.vo = new VoLivro(rs.getInt("codigo"), rs.getString("titulo"),
                        rs.getString("autor"));
                return true;
            }
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new LivroException(e.getMessage());
        }

        // return, não existe o código
        return false;
    }

    /**
     * *
     *
     * @return
     * @throws exception.LivroException @throws SQLException
     */
    public boolean existeCodigo() throws LivroException {
        try {
            // consultar o código
            String sql = "select * from livro where codigo = " + this.getVo().getCodigo();

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                return true;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new LivroException(e.getMessage());
        }

        // return, não existe o código
        return false;
    }

    /**
     * *
     *
     * @return
     * @throws exception.LivroException @throws SQLException
     */
    public int proximoCodigoLivre() throws LivroException {
        try {
            // consultar o código
            String sql = "select max(codigo) from livro";

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                int codigo = rs.getInt(1) + 1;
                return codigo;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new LivroException(e.getMessage());
        }

        // return, não existe o código
        return 1;
    }

    /**
     * GETTERS & SETTERS
     *
     * @return
     */
    public VoLivro getVo() {
        return vo;
    }

    public void setVo(VoLivro vo) {
        this.vo = vo;
    }

    public Conexao getConexao() {
        return ProjetoSoftware.getConexao();
    }
}
