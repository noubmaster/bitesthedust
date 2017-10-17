package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Artista;
import modelo.Musica;
import modelo.Participa;
import modelo.Album;
import modelo.Avaliacao;
import modelo.Composicao;
import modelo.Genero;
import modelo.Busca;
import modelo.Usuario;

import util.Conexao;

public class BuscaDAO {

    public static List<Busca> searchMain(String webInput) throws SQLException {
        List<Busca> lista = new ArrayList<Busca>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT\n"
                + "*\n"
                + "FROM\n"
                + "musica m\n"
                + "LEFT JOIN\n"
                + "participa p ON p.Musica_idMusica = m.idMusica\n"
                + "LEFT JOIN\n"
                + "artista a ON p.Artista_idArtista = a.idArtista\n"
                + "LEFT JOIN\n"
                + "album al ON al.idAlbum = m.idAlbumMusica\n"
                + "LEFT JOIN\n"
                + "genero ge ON ge.idGenero = m.idGeneroMusica\n"
                + "WHERE\n"
                + "m.nomeMusica like CONCAT('%', ?, '%') or\n"
                + "m.letra like CONCAT('%', ?, '%') or\n"
                + "a.nomeArtista like CONCAT('%', ?, '%') or\n"
                + "ge.nomeGenero like CONCAT('%', ?, '%') or\n"
                + "al.nomeAlbum like CONCAT('%', ?, '%')\n"
                + "GROUP BY\n"
                + "m.idMusica";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, webInput);
        stmt.setString(2, webInput);
        stmt.setString(3, webInput);
        stmt.setString(4, webInput);
        stmt.setString(5, webInput);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Musica musica = new Musica();
            musica.setIdMusica(rs.getInt("idMusica"));
            musica.setNomeMusica(rs.getString("nomeMusica"));
            musica.setScore(rs.getFloat("score"));
            musica.setLetra(rs.getString("letra"));

            Artista artista = new Artista();
            artista.setIdArtista(rs.getInt("idArtista"));
            artista.setNomeArtista(rs.getString("nomeArtista"));

            Participa participa = new Participa();
            participa.setPapel(rs.getString("papel"));
            participa.setArtista(artista);
            participa.setMusica(musica);

            Album album = new Album();
            album.setIdAlbum(rs.getInt("idAlbum"));
            album.setNomeAlbum(rs.getString("nomeAlbum"));
            album.setAno(rs.getInt("ano"));

            Genero genero = new Genero();
            genero.setIdGenero(rs.getInt("idGenero"));
            genero.setNome(rs.getString("nomeGenero"));

            Busca busca = new Busca();
            busca.setArtista(artista);
            busca.setMusica(musica);
            busca.setParticipa(participa);
            busca.setAlbum(album);
            busca.setGenero(genero);
            lista.add(busca);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static Musica getMusicaByID(int idMusica) throws SQLException {
        Musica musica = new Musica();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM musica m, album a, genero g WHERE m.idMusica = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idMusica);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Album album = new Album();
            album.setIdAlbum(rs.getInt("idAlbum"));
            album.setNomeAlbum(rs.getString("nomeAlbum"));
            album.setAno(rs.getInt("ano"));

            Genero genero = new Genero();
            genero.setIdGenero(rs.getInt("idGenero"));
            genero.setNome(rs.getString("nomeGenero"));

            musica.setIdMusica(rs.getInt("idMusica"));
            musica.setNomeMusica(rs.getString("nomeMusica"));
            musica.setScore(rs.getFloat("score"));
            musica.setLetra(rs.getString("letra"));

            musica.setAlbum(album);
            musica.setGenero(genero);

        }
        stmt.close();
        rs.close();
        con.close();
        return musica;
    }

    public static Artista getArtistByID(int idMusica) throws SQLException {
        Musica musica = new Musica();
        Artista artista = new Artista();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM album al, musica m, participa p, artista a WHERE m.idMusica = p.Musica_idMusica AND p.Artista_idArtista = a.idArtista AND m.idMusica = ? group by m.idMusica";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idMusica);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Album album = new Album();
            album.setIdAlbum(rs.getInt("idAlbum"));
            album.setNomeAlbum(rs.getString("nomeAlbum"));
            album.setAno(rs.getInt("ano"));

            musica.setIdMusica(rs.getInt("idMusica"));
            musica.setNomeMusica(rs.getString("nomeMusica"));
            musica.setScore(rs.getFloat("score"));
            musica.setLetra(rs.getString("letra"));

            musica.setAlbum(album);

            artista.setIdArtista(rs.getInt("idArtista"));
            artista.setNomeArtista(rs.getString("nomeArtista"));

            Participa participa = new Participa();
            participa.setPapel(rs.getString("papel"));

            participa.setArtista(artista);
            participa.setMusica(musica);

        }
        stmt.close();
        rs.close();
        con.close();
        return artista;
    }

    public static List<Album> getArtistAlbumsByID(int idArtista) throws SQLException {
        List<Album> lista = new ArrayList<Album>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT al.idAlbum, al.nomeAlbum, al.ano FROM artista ar, participa pa, musica mu, album al WHERE mu.idAlbumMusica = al.idAlbum AND ar.idArtista = pa.Artista_idArtista AND pa.Musica_idMusica = mu.idMusica AND ar.idArtista = ? GROUP BY al.idAlbum";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idArtista);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Album album = new Album();
            album.setIdAlbum(rs.getInt("idAlbum"));
            album.setNomeAlbum(rs.getString("nomeAlbum"));
            album.setAno(rs.getInt("ano"));
            lista.add(album);
        }
        stmt.close();
        rs.close();
        con.close();
        return lista;
    }

    public static Album getAlbumByID(int idMusica) throws SQLException {
        Musica musica = new Musica();
        Album album = new Album();
        Connection con = Conexao.getConnection();
        String sql = "select * from musica m, album a where m.idAlbumMusica = a.idAlbum and m.idAlbumMusica = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idMusica);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            album.setIdAlbum(rs.getInt("idAlbum"));
            album.setNomeAlbum(rs.getString("nomeAlbum"));
            album.setAno(rs.getInt("ano"));

            musica.setIdMusica(rs.getInt("idMusica"));
            musica.setNomeMusica(rs.getString("nomeMusica"));
            musica.setScore(rs.getFloat("score"));
            musica.setLetra(rs.getString("letra"));

            musica.setAlbum(album);
        }
        stmt.close();
        rs.close();
        con.close();
        System.out.println(album.getNomeAlbum());
        return album;
    }

    public static List<Musica> getListaMusicaByAlbum(int idAlbum) throws SQLException {
        List<Musica> lista = new ArrayList<Musica>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM musica mu, album al, genero ge WHERE mu.idAlbumMusica = al.idAlbum AND mu.idGeneroMusica = idGenero AND al.idAlbum = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idAlbum);
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
}
