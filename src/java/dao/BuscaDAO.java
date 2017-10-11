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
                + "	musica m\n"
                + "LEFT JOIN\n"
                + "	participa p ON p.Musica_idMusica = m.idMusica\n"
                + "LEFT JOIN\n"
                + "	artista a ON p.Artista_idArtista = a.idArtista\n"
                + "WHERE\n"
                + "	m.nomeMusica like CONCAT('%', ?, '%') or\n"
                + "	m.letra like CONCAT('%', ?, '%') or\n"
                + "	a.nomeArtista like CONCAT('%', ?, '%')\n"
                + "GROUP BY\n"
                + "	m.idMusica";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, webInput);
        stmt.setString(2, webInput);
        stmt.setString(3, webInput);
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

            Busca busca = new Busca();
            busca.setArtista(artista);
            busca.setMusica(musica);
            busca.setParticipa(participa);
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
        System.out.println(musica.getNomeMusica());
        return musica;
    }
}
