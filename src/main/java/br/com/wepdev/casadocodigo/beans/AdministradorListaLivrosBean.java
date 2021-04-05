package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.DAO.LivroDAO;
import br.com.wepdev.casadocodigo.models.Livro;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;



//@Named
//@RequestScoped
@Model // Anotação que junta o @RequestScoped e o @Named
public class AdministradorListaLivrosBean {

    @Inject
    private LivroDAO livroDAO;

    private List<Livro> livros = new ArrayList<>();



    public List<Livro> getLivros() {
        this.livros = livroDAO.listar();
        return livros;
    }
}
