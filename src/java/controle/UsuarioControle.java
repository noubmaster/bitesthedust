package controle;

import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;

/**
 *
 * @author Galen Marek
 */
@ManagedBean
@SessionScoped
public class UsuarioControle {

    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Usuario usuario = new Usuario();
    private boolean salvar = false;

    @PostConstruct
    public void atualizarUsuarios() {
        try {
            usuarios = UsuarioDAO.getLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void preparaIncluir() {
        System.out.println("ta passando pelo incluir");
        salvar = true;
        usuario = new Usuario();
    }

    public void preparaAlterar() {
        salvar = false;
    }

    public void salvar() {
        if (salvar) {
            try {
                UsuarioDAO.inserir(usuario);
                System.out.println("usuario incluido");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                UsuarioDAO.alterar(usuario);
                System.out.println("usuario alterado");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        atualizarUsuarios();
    }

    public void excluir() {
        try {
            UsuarioDAO.excluir(usuario);
            atualizarUsuarios();
            System.out.println("usuario excluido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
