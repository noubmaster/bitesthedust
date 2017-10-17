package controle;

import dao.MusicaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Genero;
import modelo.Musica;
import modelo.Album;
import modelo.Participa;

@ManagedBean
@SessionScoped
public class MusicaControle {

    private List<Musica> musicas = new ArrayList<Musica>();
    private Musica musica = new Musica();
    private Participa participa = new Participa();
    private boolean salvar = false;
    private int idGenero = 0;
    private int idAlbum = 0;

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }
    
    @PostConstruct
    public void atualizaMusicas() {
        try {
            musicas = MusicaDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void preparaIncluir() {
        salvar = true;
        musica = new Musica();
        idGenero = 0;
        idAlbum = 0;
    }

    public void preparaAlterar() {
        salvar = false;
        idGenero = musica.getGenero().getIdGenero();
        idAlbum = musica.getAlbum().getIdAlbum();
        System.out.println(musica.getNomeMusica());
    }

    public void salvar() {
        Genero genero = new Genero();
        Album album = new Album();
        genero.setIdGenero(idGenero);
        album.setIdAlbum(idAlbum);
        
        musica.setGenero(genero);
        musica.setAlbum(album);
        
        if (salvar) {
            try {
                MusicaDAO.inserir(musica);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                MusicaDAO.alterar(musica);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     
        atualizaMusicas();
    }

    public void excluir() {
        try {
            MusicaDAO.excluir(musica);
            atualizaMusicas();
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
}