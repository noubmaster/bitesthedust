package modelo;

/**
 *
 * @author Galen Marek
 */
public class Musica {
    private int idMusica, idAlbumMusica, idGeneroMusica;
    private String nomeMusica, letra;
    private float score;
    private Album album;
    private Genero genero;

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

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public int getIdAlbumMusica() {
        return idAlbumMusica;
    }

    public void setIdAlbumMusica(int idAlbumMusica) {
        this.idAlbumMusica = idAlbumMusica;
    }

    public int getIdGeneroMusica() {
        return idGeneroMusica;
    }

    public void setIdGeneroMusica(int idGeneroMusica) {
        this.idGeneroMusica = idGeneroMusica;
    }
    
    
}
