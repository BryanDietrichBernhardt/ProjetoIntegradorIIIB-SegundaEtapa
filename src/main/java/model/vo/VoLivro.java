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
    private String titulo;
    private String nome;

    // construtor
    public VoLivro(int codigo, String titulo, String nome) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.nome = nome;
    }

    // construtor
    public VoLivro() {
        this.codigo = 0;
        this.titulo = "";
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
        return titulo;
    }

    public void setNome(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return nome;
    }

    public void setAutor(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "VoLivro{" + "codigo=" + codigo + ", nome=" + titulo + ", autor=" + nome + '}';
    }
}
