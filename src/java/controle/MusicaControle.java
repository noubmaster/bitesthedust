package controle;

import dao.MusicaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Musica;
import modelo.Genero;
import modelo.Album;

/**
 *
 * @author Galen Marek
 */
@ManagedBean
@SessionScoped
public class MusicaControle {
    private List<Musica> musicas = new ArrayList<Musica>();
    private List<Genero> generos = new ArrayList<Genero>();
    private List<Album> albums = new ArrayList<Album>();
    private Musica musica = new Musica();
    private Genero genero = new Genero();
    private Album album = new Album();
    private boolean salvar = false;
    
    @PostConstruct
    public void atualizarMusicas() {
        try {
            musicas = MusicaDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void preparaIncluir() {
        salvar = true;
        musica = new Musica();
    }
    
    public void preparaAlterar() {
        salvar = false;
    }
    
    
    public void salvarMusica() {
        if (salvar) {
           try{
               MusicaDAO.inserir(musica);
               System.out.println("musica incluido");
           } catch (SQLException e) {
               e.printStackTrace();
           }
        }else{
            try{
                MusicaDAO.alterar(musica);
                System.out.println("musica alterado");
            } catch (SQLException e) {
               e.printStackTrace();
           }
        }
        
        atualizarMusicas();
    }
//    public void salvar() {
//        if (salvar) {
//           try{
//               MusicaDAO.inserir(musica, album, genero);
//               System.out.println("musica incluido");
//           } catch (SQLException e) {
//               e.printStackTrace();
//           }
//        }else{
//            try{
//                MusicaDAO.alterar(musica, album, genero);
//                System.out.println("musica alterado");
//            } catch (SQLException e) {
//               e.printStackTrace();
//           }
//        }
//        
//        atualizarMusicas();
//    }
    
    public void excluir() {
        try {
            MusicaDAO.excluir(musica);
            atualizarMusicas();
            System.out.println("musica excluido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public boolean isSalvart() {
        return salvar;
    }

    public void setSalvart(boolean salvar) {
        this.salvar = salvar;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}
