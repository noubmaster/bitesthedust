package modelo;

/**
 *
 * @author Dendi
 */
public class Participa {
    private int papel, ordem;
    private Artista artista;
    private Musica musica;

    public int getPapel() {
        return papel;
    }

    public void setPapel(int papel) {
        this.papel = papel;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
}
