package br.com.wepdev.casadocodigo.loja.converters;

import br.com.wepdev.casadocodigo.models.Autor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("autorConverter") // E colococado apenas o nome para ser referenciado apenas em locais especificos
public class AutorConverter implements Converter {



    /**
     * Cuidado sempre com os erros de conversão, implementar sempre o metodo equals e hashcode. Objeto reternado n e o mesmo que esta na lista
     * Conversão do id(String) da tela para manegedBean(Object)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String id) {
        if(id == null || id.trim().isEmpty()) return null;
            System.out.println("Convertendo para o Objeto: " + id);

        Autor autor = new Autor();
        autor.setId(Integer.valueOf(id)); // Pegando o valor inteiro da String(id)
        return autor;
    }

    /**
     * Conversão do autor(Object) manegedBean para tela(String)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object autorObject) {
        if(autorObject == null )  return null;
            System.out.println("Convertendo para String: " + autorObject);

        Autor autor = (Autor) autorObject;
        return autor.getId().toString();
    }
}
