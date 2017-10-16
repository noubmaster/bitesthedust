package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Musica;
import modelo.Avaliacao;
import modelo.Usuario;
import util.Conexao;

/**
 *
 * @author Galen Marek
 */
public class AvaliacaoDAO {

    public static void inserir(Avaliacao avaliacao) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`avaliacao` (`nota`, `comentario`, `revisao`, `idMusicaAvaliacao`, `idUsuarioAvaliacao`) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, avaliacao.getNota());
        stmt.setString(2, avaliacao.getComentario());
        stmt.setInt(3, avaliacao.getRevisao());
        stmt.setInt(4, avaliacao.getMusica().getIdMusica());
        stmt.setInt(5, avaliacao.getUsuario().getIdUsuario());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void alterar(Avaliacao avaliacao) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`avaliacao` SET `nota`=?, `comentario`=?, `revisao`=?, `idMusicaAvaliacao`=?, `idUsuarioAvaliacao`=? WHERE  `idAvaliacao`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, avaliacao.getNota());
        stmt.setString(2, avaliacao.getComentario());
        stmt.setInt(3, avaliacao.getRevisao());
        stmt.setInt(4, avaliacao.getMusica().getIdMusica());
        stmt.setInt(5, avaliacao.getUsuario().getIdUsuario());
        stmt.setInt(6, avaliacao.getIdAvaliacao());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Avaliacao avaliacao) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`avaliacao` WHERE  `idAvaliacao`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, avaliacao.getIdAvaliacao());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static List<Avaliacao> getListaAsd(int idMusica) throws SQLException {
        List<Avaliacao> lista = new ArrayList<Avaliacao>();
        Musica musica = new Musica();
        Connection con = Conexao.getConnection();
        String sql = "select\n"
                + "*\n"
                + "from\n"
                + "avaliacao av, \n"
                + "musica mu, \n"
                + "usuario us \n"
                + "WHERE\n"
                + "av.idMusicaAvaliacao = mu.idMusica AND \n"
                + "av.idUsuarioAvaliacao = us.idUsuario AND\n"
                + "mu.idMusica = ?\n"
                + "ORDER BY\n"
                + "av.idAvaliacao DESC";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idMusica);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            musica.setIdMusica(rs.getInt("idMusica"));
            musica.setNomeMusica(rs.getString("nomeMusica"));
            musica.setScore(rs.getFloat("score"));
            musica.setLetra(rs.getString("letra"));

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setEmail(rs.getString("email"));
            usuario.setIdR(rs.getString("idR"));
            usuario.setTipo(rs.getInt("tipo"));
            usuario.setPerfil(rs.getString("perfil"));

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setIdAvaliacao(rs.getInt("idAvaliacao"));
            avaliacao.setNota(rs.getInt("nota"));
            avaliacao.setComentario(rs.getString("comentario"));
            avaliacao.setRevisao(rs.getInt("revisao"));

            avaliacao.setMusica(musica);
            avaliacao.setUsuario(usuario);
            lista.add(avaliacao);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static List<Avaliacao> getLista() throws SQLException {
        List<Avaliacao> lista = new ArrayList<Avaliacao>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM avaliacao av, musica mu, usuario us WHERE av.idMusicaAvaliacao = mu.idMusica AND av.idUsuarioAvaliacao = idUsuario";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Musica musica = new Musica();
            musica.setIdMusica(rs.getInt("idMusica"));
            musica.setNomeMusica(rs.getString("nomeMusica"));
            musica.setScore(rs.getFloat("score"));
            musica.setLetra(rs.getString("letra"));

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setEmail(rs.getString("email"));
            usuario.setIdR(rs.getString("idR"));
            usuario.setTipo(rs.getInt("tipo"));
            usuario.setPerfil(rs.getString("perfil"));

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setIdAvaliacao(rs.getInt("idAvaliacao"));
            avaliacao.setNota(rs.getInt("nota"));
            avaliacao.setComentario(rs.getString("comentario"));
            avaliacao.setRevisao(rs.getInt("revisao"));

            avaliacao.setMusica(musica);
            avaliacao.setUsuario(usuario);
            lista.add(avaliacao);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Avaliacao> lista = getLista();

            for (Avaliacao m : lista) {
                System.out.println("ID....: " + m.getIdAvaliacao());
                System.out.println("COMENTARIO......: " + m.getComentario());
                System.out.println("NOME MUSICA......: " + m.getMusica().getNomeMusica());
                System.out.println("NOME USUARIO......: " + m.getUsuario().getEmail());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
