package br.com.wepdev.casadocodigo.DAO;


import br.com.wepdev.casadocodigo.models.Livro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LivroDAO {


    @PersistenceContext
    private EntityManager entityManager;


    public void salvar(Livro livro){
        entityManager.persist(livro);
    }


    public List<Livro> listar() {
        /*
         DISTINCT (l) -> Como pode exister maios de um autor para cada livro, destingue o livro independente da quantidade de autores.
         join fetch -> Para cada livro o join tem que trazer os autores
         */
        String jpql = "SELECT DISTINCT (l) FROM Livro l" + " join fetch l.autores";
        return entityManager.createQuery(jpql , Livro.class).getResultList();
    }
}
