package controle;

import dao.AvaliacaoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Avaliacao;
import modelo.Musica;
import modelo.Usuario;

/**
 *
 * @author Galen Marek
 */
@ManagedBean
@SessionScoped
public class AvaliacaoControle {

    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    private List<Musica> musicas = new ArrayList<Musica>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Avaliacao avaliacao = new Avaliacao();
    private Musica musica = new Musica();
    private Usuario usuario = new Usuario();
    private boolean salvar = false;

    @PostConstruct
    public void atualizarAvaliacaos() {
        try {
            avaliacoes = AvaliacaoDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void preparaIncluir() {
        System.out.println("ta passando pelo incluir");
        salvar = true;
        avaliacao = new Avaliacao();
    }

    public void preparaAlterar() {
        salvar = false;
    }

    public void salvarAvaliacao() {
        if (salvar) {
            try {
                AvaliacaoDAO.inserir(avaliacao);
                System.out.println("avaliacao incluido");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                AvaliacaoDAO.alterar(avaliacao);
                System.out.println("avaliacao alterado");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        atualizarAvaliacaos();
    }

    public void excluir() {
        try {
            AvaliacaoDAO.excluir(avaliacao);
            atualizarAvaliacaos();
            System.out.println("avaliacao excluido");
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

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

}
