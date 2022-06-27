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
public interface DaoStringConexao {

    public String getStringConexao(VoConexao vo);
    public VoConexao getConfiguracaoDefault();
    public VoConexao getConfiguracaoAlternativa();
}
