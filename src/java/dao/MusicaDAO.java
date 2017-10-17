package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Album;
import modelo.Musica;
import modelo.Genero;
import modelo.Participa;
import util.Conexao;

/**
 *
 * @author Galen Marek
 */
public class MusicaDAO {

    public static void inserir(Musica musica) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`musica` (`nomeMusica`, `letra`, `faixa`, `idAlbumMusica`, `idGeneroMusica`) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, musica.getNomeMusica());
        stmt.setString(2, musica.getLetra());
        stmt.setInt(3, musica.getFaixa());
        stmt.setInt(4, musica.getAlbum().getIdAlbum());
        stmt.setInt(5, musica.getGenero().getIdGenero());
        stmt.execute();
        String sql2
                = "select max(idMusica) as idMusica from musica";
        PreparedStatement stmt2 = con.prepareStatement(sql2);
        ResultSet rs = stmt2.executeQuery();
        rs.first();
        int ultimaMusica = rs.getInt("idMusica");
        System.out.println(ultimaMusica);
        stmt2.execute();
        
        stmt2.close();
        rs.close();
        stmt.close();
        con.close();
    }
    
    public static void alterar(Musica musica) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`musica` SET `nomeMusica`=?, `score`=?, `letra`=?, `faixa`=?, `idAlbumMusica`=?, `idGeneroMusica`=? WHERE  `idMusica`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, musica.getNomeMusica());
        stmt.setFloat(2, musica.getScore());
        stmt.setString(3, musica.getLetra());
        stmt.setInt(4, musica.getFaixa());
        stmt.setInt(5, musica.getAlbum().getIdAlbum());
        stmt.setInt(6, musica.getGenero().getIdGenero());
        stmt.setInt(7, musica.getIdMusica());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Musica musica) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`musica` WHERE  `idMusica`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, musica.getIdMusica());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static List<Musica> getLista() throws SQLException {
        List<Musica> lista = new ArrayList<Musica>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM musica mu, album al, genero ge WHERE mu.idAlbumMusica = al.idAlbum AND mu.idGeneroMusica = idGenero";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Album album = new Album();
            album.setIdAlbum(rs.getInt("idAlbum"));
            album.setNomeAlbum(rs.getString("nomeAlbum"));
            album.setAno(rs.getInt("ano"));

            Genero genero = new Genero();
            genero.setIdGenero(rs.getInt("idGenero"));
            genero.setNome(rs.getString("nomeGenero"));

            Musica musica = new Musica();
            musica.setIdMusica(rs.getInt("idMusica"));
            musica.setNomeMusica(rs.getString("nomeMusica"));
            musica.setScore(rs.getFloat("score"));
            musica.setLetra(rs.getString("letra"));
            musica.setFaixa(rs.getInt("faixa"));

            musica.setAlbum(album);
            musica.setGenero(genero);
            lista.add(musica);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Musica> lista = getLista();

            for (Musica m : lista) {
                System.out.println("ID....: " + m.getIdMusica());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
