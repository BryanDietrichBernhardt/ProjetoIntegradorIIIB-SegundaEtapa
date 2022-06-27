/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vo;

/**
 *
 * @author dlnotari
 */
public class VoLivro implements I_VO{
    // atributos
    private int codigo;
    private String nome;
    private String autor;

    // construtor
    public VoLivro(int codigo, String nome, String autor) {
        this.codigo = codigo;
        this.nome = nome;
        this.autor = autor;
    }

    // construtor
    public VoLivro() {
        this.codigo = 0;
        this.autor = "";
        this.nome = "";
    }

    // construtor
    public VoLivro(int codigo) {
        this.codigo = codigo;
    }

    // getters and setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "VoCidade{" + "codigo=" + codigo + ", nome=" + nome + ", autor=" + autor + '}';
    }
}
