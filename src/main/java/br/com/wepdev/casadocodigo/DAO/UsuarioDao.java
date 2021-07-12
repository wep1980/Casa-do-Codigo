package br.com.wepdev.casadocodigo.DAO;


import br.com.wepdev.casadocodigo.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class UsuarioDao implements Serializable {


    @PersistenceContext
    private EntityManager entityManager;


    public void salvar(Usuario usuario){
       entityManager.persist(usuario);
    }
}
