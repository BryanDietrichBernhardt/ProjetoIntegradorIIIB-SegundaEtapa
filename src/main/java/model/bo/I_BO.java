package model.bo;

import exception.LivroException;
import java.io.Serializable;

/**
 *
 * @author dlnotari
 */
public interface I_BO extends Serializable{
    public boolean cadastrar() throws LivroException;
    public boolean excluir() throws LivroException;
    public boolean consultar() throws LivroException;
    public boolean validar() throws LivroException;
}
