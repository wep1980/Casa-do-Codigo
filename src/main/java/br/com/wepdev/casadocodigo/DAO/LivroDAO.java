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
         DISTINCT (l) -> Como pode exister mais de um autor para cada livro, destingue o livro independente da quantidade de autores.
         join fetch -> Para cada livro o join tem que trazer os autores
         fetch -> faz a junção de cada livro trazer os autores relacionados a ele
         */
        String jpql = "SELECT DISTINCT (l) FROM Livro l join fetch l.autores";
        return entityManager.createQuery(jpql , Livro.class).getResultList();
    }


    public List<Livro> ultimosLancamentos() {
        String jpql = "select l from Livro l order by l.dataPublicacao desc "; // Ordena pela data de publicacao de maneira decrescente
        return entityManager.createQuery(jpql , Livro.class)
                .setMaxResults(5) // Traz os ultimos 5 resultados
                .getResultList();
    }


    public List<Livro> demaisLivros() {
        String jpql = "select l from Livro l order by l.dataPublicacao desc "; // Ordena pela data de publicacao de maneira decrescente
        return entityManager.createQuery(jpql , Livro.class)
                .setFirstResult(6) // O primeiro resultado começa a partir do sexto livro
                .getResultList();
    }

    public Livro buscarPorId(Integer id) {
        return entityManager.find(Livro.class , id);
    }
}
