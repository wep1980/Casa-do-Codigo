package br.com.wepdev.casadocodigo.models;

import javax.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Uma compra tem 1 usuario, um usuario tem varias compras.
     * Informa ao JPA que toda vez que for salva uma compra, salve tambem o usuario
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;


    /**
     * Itens da compra.
     * Ao salvar os itens como texto(em String) podemos utilizar xml/Json ,
     * facilita em transportar dados de um sistema para outro.
     */
    private String itens;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getItens() {
        return itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }
}
