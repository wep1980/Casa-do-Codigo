package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.DAO.LivroDAO;
import br.com.wepdev.casadocodigo.models.Livro;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LivroDetalheBean {

    @Inject
    private LivroDAO dao;

    private Livro livro;

    private Integer id;


    public void carregaDetalhe(){
        this.livro = dao.buscarPorId(id);
    }


    public LivroDAO getDao() {
        return dao;
    }

    public void setDao(LivroDAO dao) {
        this.dao = dao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
