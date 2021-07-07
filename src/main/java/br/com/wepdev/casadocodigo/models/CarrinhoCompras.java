package br.com.wepdev.casadocodigo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarrinhoCompras {

    // Lista que guarda os itens.
    // Nao permite repetições do mesmo item dentro da lista.
    // QUANDO SE UTILIZA UM ELEMENTO QUE E BASEADO EM SET E HASHSET E RECOMENDADO TER OS METODOS EQUALS E HASHCODE
    private Set<CarrinhoItem> itens = new HashSet<>();



    /**
     * Adiciona os itens no carrinho de compras
     * @param item
     */
    public void add(CarrinhoItem item){
       itens.add(item);
    }


    /**
     * Como o Set<CarrinhoItem> nao conversa com o List diretamente,
     * foi criado new ArrayList de CarrinhoItem que recebe dentro do construtor
     * o Set de itens.
     * @return
     */
    public List<CarrinhoItem> getItens() {
        return new ArrayList<CarrinhoItem>(itens);
    }
}
