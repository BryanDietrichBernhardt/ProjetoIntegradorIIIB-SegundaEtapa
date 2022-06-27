package dao;

import exception.LivroException;

/**
 *
 * @author dlnotari
 */
public interface I_DAO {
    public boolean cadastrar() throws LivroException;
    public boolean excluir() throws LivroException;
    public boolean consultar() throws LivroException;    
}
