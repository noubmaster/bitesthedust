package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import util.Conexao;


/**
 *
 * @author Galen Marek
 */
public class UsuarioDAO {
    public static void inserir(Usuario usuario) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`usuario` (`login`, `senha`, `perfil`) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getLogin());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getPerfil());
        
        System.out.println("Login: "+usuario.getLogin());
        System.out.println("Perfil: "+usuario.getPerfil());
        
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void alterar(Usuario usuario) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`usuario` SET `login`=?, `senha`=?, `perfil`=? WHERE  `idUsuario`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getLogin());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getPerfil());
        stmt.setInt(4, usuario.getIdUsuario());
        
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Usuario usuario) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`usuario` WHERE  `idUsuario`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, usuario.getIdUsuario());
        
        stmt.execute();
        stmt.close();
        con.close();
    }
    public static List<Usuario> getLista() throws SQLException {
        List<Usuario> lista = new ArrayList<Usuario>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM usuario ORDER BY idUsuario";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setPerfil(rs.getString("perfil"));
            lista.add(usuario);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Usuario> lista = getLista();
            
            for (Usuario m : lista) {
                System.out.println("ID....: "+m.getIdUsuario());
                System.out.println("LOGIN......: "+m.getLogin());
                System.out.println("PERFIL......: "+m.getPerfil());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
}
