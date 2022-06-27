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
 * uf varchar not null );
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
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean cadastrar() throws LivroException {
        try {
            // testa se existe
            String sql;
            if (this.existeCodigo()) {
                // regravar
                sql = "update cidade "
                        + "set nome      = ?, "
                        + "    uf        = ? "
                        + "where codigo  = ?";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // atribui valores
                ps.setInt(3, vo.getCodigo());
                ps.setString(1, vo.getNome());
                ps.setString(2, vo.getAutor());

                // regrava no bd
                this.getConexao().getBd().executaSQL(ps);
            } else {
                // gravar
                sql = "insert into cidade (codigo, nome, uf)"
                        + " values (?, ?, ?)";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // gerar o código
                vo.setCodigo(this.proximoCodigoLivre());

                // atribui valores            
                ps.setInt(1, vo.getCodigo());
                ps.setString(2, vo.getNome());
                ps.setString(3, vo.getAutor());

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
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean excluir() throws LivroException {
        try {
            // testa se existe
            String sql;
            if (this.existeCodigo()) {
                // regravar
                sql = "delete from cidade where codigo = ?";

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
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public VoConsulta obterLista() throws LivroException {
        try {
            // consultar o código
            String sql = "select * from cidade where codigo > 0 order by nome";

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
     * @return @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean consultar() throws LivroException {
        try {
            // consultar o código
            String sql = "select * from cidade where codigo = " + this.getVo().getCodigo();

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                this.vo = new VoLivro(rs.getInt("codigo"), rs.getString("nome"),
                        rs.getString("uf"));
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
     * @return @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */
    public boolean existeCodigo() throws LivroException {
        try {
            // consultar o código
            String sql = "select * from cidade where codigo = " + this.getVo().getCodigo();

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
     * @return @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */
    public int proximoCodigoLivre() throws LivroException {
        try {
            // consultar o código
            String sql = "select max(codigo) from cidade";

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
