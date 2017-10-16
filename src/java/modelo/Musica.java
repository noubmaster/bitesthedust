package modelo;

/**
 *
 * @author Galen Marek
 */
public class Musica {
    private int idMusica, faixa;
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

    public int getFaixa() {
        return faixa;
    }

    public void setFaixa(int faixa) {
        this.faixa = faixa;
    }

}
