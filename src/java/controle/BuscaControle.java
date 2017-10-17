/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BuscaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Artista;
import modelo.Album;
import modelo.Busca;
import modelo.Musica;

/**
 *
 * @author Dendi
 */
@ManagedBean
@SessionScoped
public class BuscaControle {

    private List<Busca> result = new ArrayList<Busca>();
    private List<Album> albumsArtist = new ArrayList<Album>();
    private List<Musica> musicasAlbum = new ArrayList<Musica>();
    private String webInput = "";
    private Musica musica = new Musica();
    private Artista artista = new Artista();
    private Album album = new Album();

    @PostConstruct
    public void buscar() {
        try {
            result = BuscaDAO.searchMain(webInput);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMusicaRedirect(int idMusica) {
        try {
            musica = BuscaDAO.getMusicaByID(idMusica);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "musica.xhtml?faces-redirect=true";
    }

    public String getArtistaRedirect(int idMusica) {
        try {
            artista = BuscaDAO.getArtistByID(idMusica);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "artista.xhtml?faces-redirect=true";
    }

    public void getListaMusicasByAlbum(int idAlbum) {
        try {
            musicasAlbum = BuscaDAO.getListaMusicaByAlbum(idAlbum);
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }

    public String getAlbumRedirect(int idMusica) {
        try {
            album = BuscaDAO.getAlbumByID(idMusica);
            System.out.println(idMusica);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "album.xhtml?faces-redirect=true";
    }

    public void getArtistsAlbum(int idArtista) {
        try {
            albumsArtist = BuscaDAO.getArtistAlbumsByID(idArtista);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Busca> getResult() {
        return result;
    }

    public void setResult(List<Busca> result) {
        this.result = result;
    }

    public String getWebInput() {
        return webInput;
    }

    public void setWebInput(String webInput) {
        this.webInput = webInput;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Album> getAlbumsArtist() {
        return albumsArtist;
    }

    public void setAlbumsArtist(List<Album> albumsArtist) {
        this.albumsArtist = albumsArtist;
    }

    public List<Musica> getMusicasAlbum() {
        return musicasAlbum;
    }

    public void setMusicasAlbum(List<Musica> musicasAlbum) {
        this.musicasAlbum = musicasAlbum;
    }
    
    
}
