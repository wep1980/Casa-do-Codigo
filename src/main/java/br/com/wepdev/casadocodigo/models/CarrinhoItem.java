package br.com.wepdev.casadocodigo.models;

import java.util.Objects;

public class CarrinhoItem {


    private Livro livro;
    private Integer quantidade;

    // Cobstrutor que recebe um livro , utilizado no CarrinhoComprasBean
    public CarrinhoItem(Livro livro) {
        this.livro = livro;
        this.quantidade = 1; // aSSIM QUE ADICIONAR UM LIVRO JA SET 1 NA QUANTIDADE, SOLUCAO TEMPORARIA
    }


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * COMO EXISTE UMA LISTA DO TIPO SET COM HASHSET SENDO UTILIZADA NO CARRINHO DE COMPRAS E NECESSARIO A IMPLEMENTAÇÃO DO EQUALS E HASHCODE
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoItem that = (CarrinhoItem) o;
        return Objects.equals(livro, that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro);
    }
}
