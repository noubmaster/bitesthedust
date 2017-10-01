package controle;

import dao.AlbumDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Album;

/**
 *
 * @author Galen Marek
 */
@ManagedBean
@SessionScoped
public class AlbumControle {

    private List<Album> albums = new ArrayList<Album>();
    private Album album = new Album();
    private boolean salvar = false;

    @PostConstruct
    public void atualizarAlbums() {
        try {
            albums = AlbumDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void preparaIncluir() {
        System.out.println("passando pelo incluir");
        salvar = true;
        album = new Album();
    }

    public void preparaAlterar() {
        System.out.println("passando pelo alterar");
        salvar = false;
    }

    public void salvar() {
        if (salvar) {
            try {
                AlbumDAO.inserir(album);
                System.out.println("album incluido");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                AlbumDAO.alterar(album);
                System.out.println("album alterado");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        atualizarAlbums();
        for(Album a : albums) {
            System.out.println(a.getNomeAlbum());
        }
    }

    public void excluir() {
        try {
            AlbumDAO.excluir(album);
            atualizarAlbums();
            System.out.println("album excluido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
