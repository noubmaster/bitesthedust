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
    private boolean salvar = true;
    private String idR = null;
    private int tipo = 0;
    private int idUsuario = 0;

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

    public void pegerIdR() {
        try {
            UsuarioDAO.getTipo(usuario);
            UsuarioDAO.getId(usuario);
            atualizarUsuarios();
            tipo = usuario.getTipo();
            idUsuario = usuario.getIdUsuario();
            System.out.println(tipo);
            System.out.println(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void nullar() {
        usuario = new Usuario();
        tipo = 0;
        idUsuario = 0;
        idR = null;
    }

    public void salvar() {
        if (salvar) {
            try {
                UsuarioDAO.inserir(usuario);
                System.out.println("usuario incluido");
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
                System.out.println("este usuario ja foi cadastrado :D");
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
    
    public String getIdR() {
        return idR;
    }

    public void setIdR(String idR) {
        this.idR = idR;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
