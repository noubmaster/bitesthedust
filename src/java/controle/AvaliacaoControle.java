package controle;

import dao.AvaliacaoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;
import modelo.Avaliacao;
import modelo.Musica;

@ManagedBean
@SessionScoped
public class AvaliacaoControle {

    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    private Avaliacao avaliacao = new Avaliacao();
    private boolean salvar = false;
    private int idUsuario = 0;
    private int idMusica = 0;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }
    
    @PostConstruct
    public void atualizaAvaliacoes() {
        try {
            avaliacoes = AvaliacaoDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void preparaIncluir() {
        salvar = true;
        avaliacao = new Avaliacao();
        idUsuario = 0;
        idMusica = 0;
    }

    public void preparaAlterar() {
        salvar = false;
        idUsuario = avaliacao.getUsuario().getIdUsuario();
        idMusica = avaliacao.getMusica().getIdMusica();
        
    }

    public void salvar() {
        Usuario usuario = new Usuario();
        Musica musica = new Musica();
        usuario.setIdUsuario(idUsuario);
        musica.setIdMusica(idMusica);
        
        avaliacao.setUsuario(usuario);
        avaliacao.setMusica(musica);
        
        if (salvar) {
            try {
                AvaliacaoDAO.inserir(avaliacao);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                AvaliacaoDAO.alterar(avaliacao);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     
        atualizaAvaliacoes();
    }

    public void excluir() {
        try {
            AvaliacaoDAO.excluir(avaliacao);
            atualizaAvaliacoes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
}