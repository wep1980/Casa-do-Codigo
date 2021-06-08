package br.com.wepdev.casadocodigo.beans;

import br.com.wepdev.casadocodigo.DAO.AutorDAO;
import br.com.wepdev.casadocodigo.DAO.LivroDAO;
import br.com.wepdev.casadocodigo.loja.infra.FileSaver;
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
@RequestScoped // mantem o arquivo vivo durante o processo de uso do usuario
public class AdministradorLivrosBean {

    @Inject
    private LivroDAO livroDAO;

    @Inject
    private AutorDAO autorDAO;

    @Inject
    private FacesContext context;

    private Livro livro = new Livro(); // Evita o erro de livro nulo

    private Part capaLivro; // Objeto para trabalhar com arquivos


    @Transactional // Faz parte do JTA
    public String salvar() throws IOException {

        livroDAO.salvar(livro);

        // ------------------------ INICIO arquivos --------------------------------//

        FileSaver fileSaver = new FileSaver();

        // Nesse metodo e informado o arquivo que nesse caso e do tipo capaLivro e o local de armazenamento
        livro.setCapaPath(fileSaver.write(capaLivro , "livros"));

        // ------------------------ FIM arquivos --------------------------------//

        context.getExternalContext().getFlash().setKeepMessages(true); // Deixa a mensagem ativa durante o contexto de flash, coloca os dados no sessão do usuario que dura ate a ultima requisição
        context.addMessage(null , new FacesMessage("Livro cadastrado com sucesso"));

        // Manda a tela redirecionar ( UTILIZAR SEMPRE QUE FOR REALIZADO UM POST ) senão ao atualizar a tela e feito um novo cadastro com os mesmos dados. E feito um novo request
        return "/livros/lista?faces-redirect=true";
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
