package br.com.wepdev.casadocodigo.models;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named // Permite que a classe seja utilizada na pagina, mo xhtml
@SessionScoped // Enquanto a sessao nao for encerrada os itens adicionados no carrinho permaneceram dentro dele. E OBRIGATORIO O USO DO SERIALIZABLE
public class CarrinhoCompras implements Serializable {

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


    /**
     * Metodo que calcula o valor total do carrinho de compras
     * @return
     */
    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (CarrinhoItem carrinhoItem : itens) {
            total = total.add(carrinhoItem.getLivro().getPreco().multiply( new BigDecimal(carrinhoItem.getQuantidade()))); // E necessario pegar o retorno do BigDecimal
        }
        return total;
    }

    /**
     * Metodo que soma total por itens adicionados no carrinho
     * @param item
     * @return
     */
    public BigDecimal getTotal(CarrinhoItem item){
       return item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade()));
    }
}
