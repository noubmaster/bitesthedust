package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Artista;
import util.Conexao;

/**
 *
 * @author Galen Marek
 */
public class ArtistaDAO {
     public static void inserir(Artista artista) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`artista` (`nomeArtista`) VALUES (?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, artista.getNomeArtista());
        
        stmt.execute();
        stmt.close();
        con.close();
    }
    
        
    public static void alterar(Artista artista) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`artista` SET `nomeArtista`=? WHERE  `idArtista`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, artista.getNomeArtista());
        stmt.setInt(2, artista.getIdArtista());
        
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Artista artista) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`artista` WHERE  `idArtista`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, artista.getIdArtista());
        System.out.println("id:" + artista.getIdArtista());
        stmt.execute();
        stmt.close();
        con.close();
    }
    public static List<Artista> getLista() throws SQLException {
        List<Artista> lista = new ArrayList<Artista>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM artista ORDER BY idArtista";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Artista artista = new Artista();
            artista.setIdArtista(rs.getInt("idArtista"));
            artista.setNomeArtista(rs.getString("nomeArtista"));
            lista.add(artista);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Artista> lista = getLista();
            
            for (Artista m : lista) {
                System.out.println("ID....: "+m.getIdArtista());
                System.out.println("NOME......: "+m.getNomeArtista());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
}
