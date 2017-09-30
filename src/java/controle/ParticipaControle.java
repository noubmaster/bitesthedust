package controle;

import dao.ParticipaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Artista;
import modelo.Participa;
import modelo.Musica;

@ManagedBean
@SessionScoped
public class ParticipaControle {

    private List<Participa> participacoes = new ArrayList<Participa>();
    private Participa participa = new Participa();
    private boolean salvar = false;
    private int idArtista = 0;
    private int idMusica = 0;

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }
    
    @PostConstruct
    public void atualizaParticipacoes() {
        try {
            participacoes = ParticipaDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void preparaIncluir() {
        salvar = true;
        participa = new Participa();
        idArtista = 0;
        idMusica = 0;
    }

    public void preparaAlterar() {
        salvar = false;
        idArtista = participa.getArtista().getIdArtista();
        idMusica = participa.getMusica().getIdMusica();
        
    }

    public void salvar() {
        Artista artista = new Artista();
        Musica musica = new Musica();
        artista.setIdArtista(idArtista);
        musica.setIdMusica(idMusica);
        
        participa.setArtista(artista);
        participa.setMusica(musica);
        
        if (salvar) {
            try {
                ParticipaDAO.inserir(participa);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ParticipaDAO.alterar(participa);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     
        atualizaParticipacoes();
    }

    public void excluir() {
        try {
            ParticipaDAO.excluir(participa);
            atualizaParticipacoes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Participa> getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(List<Participa> participacoes) {
        this.participacoes = participacoes;
    }

    public Participa getParticipa() {
        return participa;
    }

    public void setParticipa(Participa participa) {
        this.participa = participa;
    }
}