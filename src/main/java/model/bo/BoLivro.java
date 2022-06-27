/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bo;

import dao.DaoLivro;
import exception.LivroException;
import model.vo.VoLivro;
import model.vo.VoConsulta;

/**
 *
 * @author dlnotari
 */
public class BoLivro implements I_BO {

    // atributos
    private VoLivro vo;
    private String erro;
    private DaoLivro dao;

    // construtor
    public BoLivro(VoLivro vo) {
        this.vo = vo;
        this.dao = new DaoLivro(vo);
        this.erro = "";
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean cadastrar() throws LivroException {
        // validar os campos
        if (!this.validar()) {
            return false;
        }
        try {
            // cadastrar
            this.getDao().setVo(this.getVo());
            if (!this.getDao().cadastrar()) {
                this.setErro("Houve um erro ao salvar as informações de cidade no banco de dados");
                return false;
            }
        } catch (LivroException ex) {
            this.setErro("Houve um erro ao salvar as informações de cidade no banco de dados");
            throw ex;
        }

        // return, tudo ocorreu normal
        return true;
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean excluir() throws LivroException {
        // testa o valor do código
        if (!this.validarCodigo()) {
            return false;
        }

        try {
            // set vo
            this.getDao().setVo(this.getVo());

            // excluir
            if (!this.getDao().excluir()) {
                this.setErro("Houve um erro ao excluir um cidade do banco de dados");
                return false;
            }
        } catch (LivroException ex) {
            this.setErro("Houve um erro ao excluir um cidade do banco de dados\n" + ex.getMessage());
            throw ex;
        }

        // return, tudo ocorreu normal
        return true;
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean consultar() throws LivroException {
        // testa o valor do código
        if (!this.validarCodigo()) {
            return false;
        }
        // consultar
        try {
            this.getDao().setVo(this.getVo());
            if (this.getDao().consultar()) {
                this.setVo(this.getDao().getVo());
                return true;
            }
        } catch (LivroException ex) {
            this.setErro("Houve um erro ao consultar um cidade do banco de dados\n" + ex.getMessage());
            throw ex;
        }

        // erro 
        this.setErro("Codigo de cidade não cadastrado!");

        // return
        return false;
    }

    /**
     * *
     *
     * @return
     */
    public VoConsulta obterLista() throws LivroException {
        try {
            return this.getDao().obterLista();
        } catch (LivroException ex) {
            this.setErro("Não foi possível obter a lista de livros cadastradas!");
            throw ex;
        }
    }

    /**
     * *
     *
     * @return
     */
    public int proximoCodigoLivre() throws LivroException {
        try {
            return this.getDao().proximoCodigoLivre();
        } catch (LivroException ex) {
            this.setErro("Erro ao obter o próximo código do Livro!");
            throw ex;
        }
    }

    /**
     * *
     *
     * @return
     */
    public boolean validarCodigo() {
        // testa o valor do código
        if (this.getVo().getCodigo() <= 0) {
            this.setErro("O Código do Livro deve ser informado!");
            return false;
        }

        // return, sem erros
        return true;
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean validar() {
        boolean error = true;
        String msg = "";
        // testa o valor do código
        if (!this.validarCodigo()) {
            error = false;
            msg = this.getErro() + "\n";
        }

        // testa o valor do descrição
        if (this.getVo().getNome().isEmpty()) {
            msg += "O nome da Livro deve ser informada!\n";
            error = false;
        }

        // testa o valor do nome
        if (this.getVo().getNome().length() > 40) {
            msg += "O nome da Livro deve ter no máximo 40 caracteres!\n";
            error = false;
        }

        // testa o valor da uf
        if ((this.getVo().getAutor().length() < 2) || (this.getVo().getAutor().length() > 2)) {
            msg += "O nome do autor deve ser informado e ter até N caracteres!\n";
            error = false;
        }

        // return....
        this.setErro(msg);
        return error;
    }

    /**
     * *
     *
     * getttes and set
     *
     * @return ters
     */
    public VoLivro getVo() {
        return vo;
    }

    public void setVo(VoLivro vo) {
        this.vo = vo;
    }

    public String getErro() {
        return this.erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public DaoLivro getDao() {
        return dao;
    }

    public void setDao(DaoLivro dao) {
        this.dao = dao;
    }
}
