package controle;

import dao.GeneroDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Genero;

/**
 *
 * @author Galen Marek
 */
@ManagedBean
@SessionScoped
public class GeneroControle {
    private List<Genero> generos = new ArrayList<Genero>();
    private Genero genero = new Genero();
    private boolean salvar = false;
    
    @PostConstruct
    public void atualizarGeneros() {
        try {
            generos = GeneroDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void preparaIncluir() {
        System.out.println("ta passando pelo incluir");
        salvar = true;
        genero = new Genero();
    }
    
    public void preparaAlterar() {
        salvar = false;
    }
    
    public void salvar() {
        if (salvar) {
           try{
               GeneroDAO.inserir(genero);
               System.out.println("genero incluido");
           } catch (SQLException e) {
               e.printStackTrace();
           }
        }else{
            try{
                GeneroDAO.alterar(genero);
                System.out.println("genero alterado");
            } catch (SQLException e) {
               e.printStackTrace();
           }
        }
        
        atualizarGeneros();
    }
    
    public void excluir() {
        try {
            GeneroDAO.excluir(genero);
            atualizarGeneros();
            System.out.println("genero excluido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

}
