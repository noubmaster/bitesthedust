package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Participa;
import modelo.Artista;
import modelo.Musica;
import util.Conexao;

/**
 *
 * @author Galen Marek
 */
public class ParticipaDAO {

    public static void inserir(Participa participa) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`participa` (`Musica_idMusica`, `Artista_idArtista`, `papel`) VALUES (?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, participa.getMusica().getIdMusica());
        stmt.setInt(2, participa.getArtista().getIdArtista());
        stmt.setString(3, participa.getPapel());

        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void alterar(Participa participa) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`participa` SET `Musica_idMusica`=?, `Artista_idArtista`=?, `papel`=? WHERE  `Musica_idMusica`=? AND `Artista_idArtista`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, participa.getMusica().getIdMusica());
        stmt.setInt(2, participa.getArtista().getIdArtista());
        stmt.setString(3, participa.getPapel());
        stmt.setInt(4, participa.getMusica().getIdMusica());
        stmt.setInt(5, participa.getArtista().getIdArtista());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Participa participa) throws SQLException {
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`participa` WHERE  `Musica_idMusica`=? AND `Artista_idArtista`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, participa.getMusica().getIdMusica());
        stmt.setInt(2, participa.getArtista().getIdArtista());
        System.out.println("id A:" + participa.getArtista().getIdArtista() + "id M:" + participa.getMusica().getIdMusica());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static List<Artista> getIdArtistaLista() throws SQLException {
        List<Artista> lista = new ArrayList<Artista>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT al.idArtista FROM artista al;";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Artista artista = new Artista();
            artista.setIdArtista(rs.getInt("idArtista"));
            
            lista.add(artista);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }
    public static List<Participa> getLista() throws SQLException {
        List<Participa> lista = new ArrayList<Participa>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM participa pa, musica mu, artista al WHERE pa.Artista_idArtista = al.idArtista AND pa.Musica_idMusica = mu.idMusica;";
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
            lista.add(participa);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Participa> lista = getLista();

            for (Participa m : lista) {
                System.out.println("ARTISTA....: " + m.getArtista().getNomeArtista());
                System.out.println("MUSICA......: " + m.getMusica().getNomeMusica());
                System.out.println("MUSICA......: " + m.getPapel());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
