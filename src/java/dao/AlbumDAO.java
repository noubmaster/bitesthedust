package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Album;
import util.Conexao;


/**
 *
 * @author Galen Marek
 */
public class AlbumDAO {
    public static void inserir(Album album) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO album (`nomeAlbum`, `ano`) VALUES (?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, album.getNomeAlbum());
        stmt.setInt(2, album.getAno());
        
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void alterar(Album album) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`album` SET `nomeAlbum`=?, `ano`=? WHERE  `idAlbum`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, album.getNomeAlbum());
        stmt.setInt(2, album.getAno());
        stmt.setInt(3, album.getIdAlbum());
        
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Album album) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`album` WHERE  `idAlbum`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, album.getIdAlbum());
        
        stmt.execute();
        stmt.close();
        con.close();
    }
    public static List<Album> getLista() throws SQLException {
        List<Album> lista = new ArrayList<Album>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM album ORDER BY idAlbum";
        PreparedStatement stmt = con.prepareStatement(sql);
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

    public static void main(String[] args) {

        try {
            List<Album> lista = getLista();
            
            for (Album m : lista) {
                System.out.println("ID....: "+m.getIdAlbum());
                System.out.println("NOME......: "+m.getNomeAlbum());
                System.out.println("ANO......: "+m.getAno());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
}
