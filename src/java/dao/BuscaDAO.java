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

    public static List<Busca> getListaMusicas() throws SQLException {
        List<Busca> lista = new ArrayList<Busca>();
        Connection con = Conexao.getConnection();
        String sql = "select * from album al, artista ar, composicao co, genero ge, participa pa, musica mu "
                + "where mu.idAlbumMusica=al.idAlbum and mu.idGeneroMusica=ge.idGenero and "
                + "pa.Artista_idArtista=ar.idArtista and co.Artista_idArtista=ar.idArtista and "
                + "pa.Musica_idMusica=mu.idMusica and co.Musica_idMusica=mu.idMusica "
                + "order by mu.score;";
        PreparedStatement stmt = con.prepareStatement(sql);
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

            Composicao composicao = new Composicao();
            composicao.setArtista(artista);
            composicao.setMusica(musica);

            Genero genero = new Genero();
            genero.setIdGenero(rs.getInt("idGenero"));
            genero.setNome(rs.getString("nomeGenero"));

            Busca busca = new Busca();
            busca.setAlbum(album);
            busca.setArtista(artista);
            busca.setComposicao(composicao);
            busca.setGenero(genero);
            busca.setMusica(musica);
            busca.setParticipa(participa);
            lista.add(busca);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Busca> lista = getListaMusicas();

            for (Busca m : lista) {
                System.out.println("ARTISTA....: " + m.getAlbum().getNomeAlbum());
                System.out.println("MUSICA......: " + m.getArtista().getNomeArtista());
                System.out.println("MUSICA......: " + m.getComposicao().getArtista().getNomeArtista());
                System.out.println("MUSICA......: " + m.getComposicao().getMusica().getNomeMusica());
                System.out.println("MUSICA......: " + m.getGenero().getNome());
                System.out.println("MUSICA......: " + m.getMusica().getNomeMusica());
                System.out.println("MUSICA......: " + m.getParticipa().getArtista().getNomeArtista());
                System.out.println("MUSICA......: " + m.getParticipa().getMusica().getNomeMusica());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
