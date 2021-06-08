package br.com.wepdev.casadocodigo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
    (Bean Validator) Hibernate validation
     */
    @NotBlank
    private String titulo;

    @Lob // Aceita um valor grande de texto
    @Size(min = 10)
    @NotBlank
    private String descricao;

    @DecimalMin("20") // Valor minimo de 20,00
    private BigDecimal preco;

    @Min(50) // No minimo 50 paginas
    private  Integer numeroPaginas;

    @Temporal(TemporalType.DATE) // Gravando apenas a data
    //private Calendar dataPublicacao = Calendar.getInstance(); -> Evita erro de nullPointerException
    private Calendar dataPublicacao;

    private String capaPath; // O arquivo e salvo no disco e o caminho no banco de dados

    @ManyToMany // Muitos para muitos
    @Size(min = 1) // No minimo 1 autor
    @NotNull
    private List<Autor> autores = new ArrayList<Autor>(); // Evita que o autor fique nulo e evita erros



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Calendar getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Calendar dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getCapaPath() {
        return capaPath;
    }

    public void setCapaPath(String capaPath) {
        this.capaPath = capaPath;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", numeroPaginas=" + numeroPaginas +
                ", autores=" + autores +
                '}';
    }
}
