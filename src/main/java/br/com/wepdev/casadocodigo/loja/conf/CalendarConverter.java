package br.com.wepdev.casadocodigo.loja.conf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*
Classe de conversão do JSF de texto que vem da tela
 */
@FacesConverter(forClass = Calendar.class) // Converter para a Classe Calendar.class
public class CalendarConverter implements Converter {


    private DateTimeConverter converter = new DateTimeConverter();

    // Construtor que ja formata a data
    public CalendarConverter() {
        converter.setPattern("dd/MM/yyyy");
        converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    /**
     * Conversão de data da tela para manegedBean
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String dataTexto) {
        Date data = (Date) converter.getAsObject(context , component , dataTexto);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar;

    }

    /**
     * Conversão de data manegedBean para tela
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object dataObject) {
        if(dataObject == null){
            return null;
        }
        Calendar calendar = (Calendar) dataObject; // O dataObject ja sai do manegedBean como calendar
        String dataTexto = converter.getAsString(context , component , calendar.getTime());
           return dataTexto;
    }
}
