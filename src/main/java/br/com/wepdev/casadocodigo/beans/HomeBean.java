package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.DAO.LivroDAO;
import br.com.wepdev.casadocodigo.models.Livro;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class HomeBean {


    @Inject
    private LivroDAO dao;


    public List<Livro> ultimosLancamentos(){
        return dao.ultimosLancamentos();
    }

    public List<Livro> demaisLivros(){
        return dao.demaisLivros();
    }
}
