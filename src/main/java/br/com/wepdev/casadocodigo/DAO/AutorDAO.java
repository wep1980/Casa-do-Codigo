package br.com.wepdev.casadocodigo.DAO;


import br.com.wepdev.casadocodigo.models.Autor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AutorDAO {


    @PersistenceContext
    private EntityManager entityManager;


    public List<Autor> listar(){
        return entityManager.createQuery("SELECT a FROM Autor a" , Autor.class)
                .getResultList();
    }
}
