package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.DAO.LivroDAO;
import br.com.wepdev.casadocodigo.models.CarrinhoCompras;
import br.com.wepdev.casadocodigo.models.CarrinhoItem;
import br.com.wepdev.casadocodigo.models.Livro;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class CarrinhoComprasBean {

    @Inject
    private LivroDAO livroDAO;

    @Inject
    private CarrinhoCompras carrinho;


    /**
     *
     * @param id - RECEBE O ID DO LIVRO
     * @return
     */
    public String add(Integer id){
        Livro livro = livroDAO.buscarPorId(id); //Buscando o livro
        CarrinhoItem item = new CarrinhoItem(livro); // Foi criando um construtor no CarrinhoItem que recebe um livro
        carrinho.add(item); // adicionando o item ao carrinho

        return "carrinho?faces-redirect=true"; // retorna o usuario para a mesma tela, a URL muda

    }

    public List<CarrinhoItem> getItens(){
       return carrinho.getItens();
    }

    public void remover(CarrinhoItem item){
        carrinho.remover(item);
    }

}
