/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.bd.conexao;

import model.vo.VoConexao;

/**
 *
 * @author dlnotari
 */
public class DaoStringConexaoPostgreSQL implements DaoStringConexao {

    @Override
    public String getStringConexao(VoConexao vo) {
        // monta conexão
        String url = "jdbc:postgresql://" + vo.getHost() +
                ":" + vo.getPorta() + "/" + vo.getBaseDados();

        //mostra conexao
        System.out.println(url);

        // retorna
        return url;
    }

    @Override
    public VoConexao getConfiguracaoDefault() {
        // cria objeto
        VoConexao vo = new VoConexao();

        // seta valores
        vo.setSgbd("PostgreSQL");
        vo.setHost("slinf30.ucs.br");
        vo.setPorta("5432");
        vo.setBaseDados("inf0211");
        vo.setUsuario("alunos");
        vo.setSenha("postgres");
        vo.setClassDriver("org.postgresql.Driver");

        // returns
        return vo;
    }

    @Override
    public VoConexao getConfiguracaoAlternativa() {
        // cria objeto
        VoConexao vo = new VoConexao();

        // seta valores
        vo.setSgbd("PostgreSQL");
        vo.setHost("localhost");
        vo.setPorta("5432");
        vo.setBaseDados("postgres");
        vo.setUsuario("postgres");
        vo.setSenha("postgres");
        vo.setClassDriver("org.postgresql.Driver");

        // returns
        return vo;
    }

}