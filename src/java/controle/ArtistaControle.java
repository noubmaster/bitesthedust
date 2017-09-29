package controle;

import dao.ArtistaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Artista;

/**
 *
 * @author Galen Marek
 */
@ManagedBean
@SessionScoped
public class ArtistaControle {
    private List<Artista> artistas = new ArrayList<Artista>();
    private Artista artista = new Artista();
    private boolean salvar = false;
    
    @PostConstruct
    public void atualizarArtistas() {
        try {
            artistas = ArtistaDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void preparaIncluir() {
        System.out.println("ta passando pelo incluir");
        salvar = true;
        artista = new Artista();
    }
    
    public void preparaAlterar() {
        salvar = false;
    }
    
    public void salvar() {
        if (salvar) {
           try{
               ArtistaDAO.inserir(artista);
               System.out.println("artista incluido");
           } catch (SQLException e) {
               e.printStackTrace();
           }
        }else{
            try{
                ArtistaDAO.alterar(artista);
                System.out.println("artista alterado");
            } catch (SQLException e) {
               e.printStackTrace();
           }
        }
        
        atualizarArtistas();
    }
    
    public void excluir() {
        try {
            ArtistaDAO.excluir(artista);
            atualizarArtistas();
            System.out.println("artista excluido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

}
