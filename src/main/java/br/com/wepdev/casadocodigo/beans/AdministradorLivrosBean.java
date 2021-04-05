package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.DAO.AutorDAO;
import br.com.wepdev.casadocodigo.DAO.LivroDAO;
import br.com.wepdev.casadocodigo.models.Autor;
import br.com.wepdev.casadocodigo.models.Livro;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AdministradorLivrosBean {


    @Inject
    private LivroDAO livroDAO;

    @Inject
    private AutorDAO autorDAO;

    private Livro livro = new Livro();

    private List<Integer> autoresId = new ArrayList<>();



    @Transactional
    public String salvar(){
        for (Integer outorId: autoresId) {
            livro.getAutores().add(new Autor(outorId));
        }
        livroDAO.salvar(livro);
        System.out.println("Livro cadastrado : " + livro);

        //this.livro = new Livro(); // Instancia um objeto vazio, assim se limpa a tela e PERMANECE NA MESMA TELA
        //this.autoresId = new ArrayList<>(); // Instancia um objeto vazio, e desseleciona o autor selecionado anteriormente e PERMANECE NA MESMA TELA

        //return "/livros/lista"; // Ao atualizar essa tela , ele resubmete essa tela, e as informações são gravadas novamente no banco de dados ( NÃO FAZER )

        return "/livros/lista?faces-redirect=true"; // Manda a tela redirecionar ( UTILIZAR SEMPRE QUE FOR REALIZADO UM POST )
    }


    public List<Autor> getAutores() {
       return autorDAO.listar();
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Integer> getAutoresId() {
        return autoresId;
    }

    public void setAutoresId(List<Integer> autoresId) {
        this.autoresId = autoresId;
    }
}
