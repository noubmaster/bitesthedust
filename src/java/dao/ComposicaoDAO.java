package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Composicao;
import modelo.Artista;
import modelo.Musica;
import util.Conexao;

/**
 *
 * @author Galen Marek
 */
public class ComposicaoDAO {

    public static void inserir(Composicao composicao) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`composicao` (`Artista_idArtista`, `Musica_idMusica`) VALUES (?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, composicao.getArtista().getIdArtista());
        stmt.setInt(2, composicao.getMusica().getIdMusica());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void alterar(Composicao composicao) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`composicao` SET `Artista_idArtista`=?, `Musica_idMusica`=? WHERE  `Artista_idArtista`=? AND `Musica_idMusica`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, composicao.getArtista().getIdArtista());
        stmt.setInt(2, composicao.getMusica().getIdMusica());
        stmt.setInt(3, composicao.getArtista().getIdArtista());
        stmt.setInt(4, composicao.getMusica().getIdMusica());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Composicao composicao) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`composicao` WHERE  `Artista_idArtista`=? AND `Musica_idMusica`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, composicao.getArtista().getIdArtista());
        stmt.setInt(2, composicao.getMusica().getIdMusica());
        System.out.println("id A:" + composicao.getArtista().getIdArtista() + "id M:" + composicao.getMusica().getIdMusica());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static List<Composicao> getLista() throws SQLException {
        List<Composicao> lista = new ArrayList<Composicao>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM composicao co, musica mu, artista al WHERE co.Artista_idArtista = al.idArtista AND co.Musica_idMusica = mu.idMusica;";
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
            
            Composicao composicao = new Composicao();
            composicao.setArtista(artista);
            composicao.setMusica(musica);
            lista.add(composicao);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Composicao> lista = getLista();

            for (Composicao m : lista) {
                System.out.println("ARTISTA....: " + m.getArtista().getNomeArtista());
                System.out.println("MUSICA......: " + m.getMusica().getNomeMusica());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
