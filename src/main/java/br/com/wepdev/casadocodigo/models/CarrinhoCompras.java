package br.com.wepdev.casadocodigo.models;

import br.com.wepdev.casadocodigo.DAO.CompraDao;
import br.com.wepdev.casadocodigo.DAO.UsuarioDao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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

    //@Inject
    //private UsuarioDao usuarioDao;

    @Inject
    private CompraDao compraDao;

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

    public void remover(CarrinhoItem item) {
        this.itens.remove(item);
    }

    /**
     * Metodo que soma a quantidade total de todos os itens q estiverem mo carrinho de compras,
     * que e vizualizado na pagina do carrinho, dentro cesta do carrinho de compras
     * @return
     */
    public Integer getQuantidadeTotal(){
        // Mapeia para int, onde cada item dentro do Set faz um getQuantidade() e soma o resultado do getQuantidade()
        return itens.stream().mapToInt(item -> item.getQuantidade()).sum();
    }


    public void finalizar(Usuario usuario) {

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setItens(this.toJson()); // vai permitir transforma o carrinhoItem para Json
        //usuarioDao.salvar(usuario); // Como ja esta sendo salvo em cascata, nao e necessario salvar o usuario aqui.
        compraDao.salvar(compra);
    }

    /**
     * Metodo que transforma os itens em JSON
     * @return
     */
    private String toJson() {
        return "{}";
    }
}
