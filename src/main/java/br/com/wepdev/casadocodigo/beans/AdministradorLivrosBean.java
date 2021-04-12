package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.DAO.AutorDAO;
import br.com.wepdev.casadocodigo.DAO.LivroDAO;
import br.com.wepdev.casadocodigo.models.Autor;
import br.com.wepdev.casadocodigo.models.Livro;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Named
@RequestScoped
public class AdministradorLivrosBean {


    @Inject
    private LivroDAO livroDAO;

    @Inject
    private AutorDAO autorDAO;

    private Livro livro = new Livro();

    @Inject
    private FacesContext context;


    private Part capaLivro; // Objeto para trabalhar com arquivos






    @Transactional
    public String salvar() throws IOException {

        livroDAO.salvar(livro);

        capaLivro.write("C:/Users/WePDev/Desktop/casadocodigo/livros/" + capaLivro.getSubmittedFileName());

        context.getExternalContext().getFlash().setKeepMessages(true); // Deixa a mensagem atica durante o contexto de flash, coloca os dados no sessão do usuario que dura ate a ultima requisição
        context.addMessage(null , new FacesMessage("Livro cadastrado com sucesso"));

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

    public void setAutorDAO(AutorDAO autorDAO) {
        this.autorDAO = autorDAO;
    }

    public Part getCapaLivro() {
        return capaLivro;
    }

    public void setCapaLivro(Part capaLivro) {
        this.capaLivro = capaLivro;
    }
}
