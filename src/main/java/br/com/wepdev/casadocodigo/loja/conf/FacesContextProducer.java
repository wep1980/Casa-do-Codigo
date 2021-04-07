package br.com.wepdev.casadocodigo.loja.conf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {


    @RequestScoped
    @Produces // Faz o CDi produzir um FacesContext(@Inject -> injeta o FacesContext)
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();

    }
}
