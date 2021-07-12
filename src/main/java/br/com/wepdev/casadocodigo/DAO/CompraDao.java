package br.com.wepdev.casadocodigo.DAO;


import br.com.wepdev.casadocodigo.models.Compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class CompraDao implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;


    public void salvar(Compra compra){
        entityManager.persist(compra);
    }
}
