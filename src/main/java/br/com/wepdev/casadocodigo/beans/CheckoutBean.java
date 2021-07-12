package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.models.CarrinhoCompras;
import br.com.wepdev.casadocodigo.models.Usuario;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class CheckoutBean {

    private Usuario usuario = new Usuario();

    @Inject
    private CarrinhoCompras carrinho;



    @Transactional // Toda vez que for feita uma operação que altera o estado do banco de dados
    public void finalizar(){
        carrinho.finalizar(usuario);
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
