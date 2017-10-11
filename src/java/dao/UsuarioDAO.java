package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import controle.UsuarioControle;
import util.Conexao;

/**
 *
 * @author Galen Marek
 */
public class UsuarioDAO {

    public static void inserir(Usuario usuario) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`usuario` (`email`, `idR`, `perfil`) VALUES (?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getIdR());
        stmt.setString(3, usuario.getPerfil());

        System.out.println("Login: " + usuario.getEmail());
        System.out.println("Perfil: " + usuario.getIdR());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static List<Usuario> getTipo(Usuario usuario) throws SQLException {
        List<Usuario> lista = new ArrayList<Usuario>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT `tipo` FROM `memes`.`usuario` WHERE  `idR` = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getIdR());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            usuario.setTipo(rs.getInt("tipo"));
            lista.add(usuario);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }
    
    public static List<Usuario> getId(Usuario usuario) throws SQLException {
        List<Usuario> lista = new ArrayList<Usuario>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT us.idUsuario FROM usuario us WHERE  idR = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getIdR());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            lista.add(usuario);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }
    
    public static void alterar(Usuario usuario) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`usuario` SET `email`=?, `idR`=?, `tipo`=?, `perfil`=? WHERE  `idUsuario`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getIdR());
        stmt.setInt(3, usuario.getTipo());
        stmt.setString(4, usuario.getPerfil());
        stmt.setInt(5, usuario.getIdUsuario());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Usuario usuario) throws SQLException {
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
            usuario.setEmail(rs.getString("email"));
            usuario.setIdR(rs.getString("idR"));
            usuario.setTipo(rs.getInt("tipo"));
            usuario.setPerfil(rs.getString("perfil"));
            lista.add(usuario);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

}
