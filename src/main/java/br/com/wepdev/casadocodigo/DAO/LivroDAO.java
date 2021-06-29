package br.com.wepdev.casadocodigo.DAO;


import br.com.wepdev.casadocodigo.models.Livro;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateful // E necessario ao utilizar o EXTENDED quando se ultiliza no @PersistenceContext(type = PersistenceContextType.EXTENDED) (EJB)
public class LivroDAO {

    // O tempo de vida do entityManager dura não so durante uma transação, mas durante o escopo. mas utilizada para evitar querys complexas
    @PersistenceContext(type = PersistenceContextType.EXTENDED) // SOLUÇÃO 1
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
                .setFirstResult(5) // O primeiro resultado começa a partir do sexto livro
                .getResultList();
    }

    /**
     * JOIN FETCH -> FAZ A BUSCA , A RELAÇÃO COM OS AUTORES LIGADOS AO LIVRO
     * @param id
     * @return
     */
    public Livro buscarPorId(Integer id) {
        // SOLUÇÃO 1 -> Acontecia um erro apos buscar os ids, pq a conexão se encerrava e n buscava os autores
        return entityManager.find(Livro.class, id);

        // SOLUÇÃO 2 -> para querys menos complexas, solucão mais performatica
        //String jpql = "select l from Livro l join fetch l.autores where l.id = :id";
        //return entityManager.createQuery(jpql , Livro.class)
               // .setParameter("id" , id)
               // .getSingleResult(); // Busca o id e apenas um resultado que é o livro
    }
}
