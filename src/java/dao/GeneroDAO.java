package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Genero;
import util.Conexao;

/**
 *
 * @author Galen Marek
 */
public class GeneroDAO {
     public static void inserir(Genero genero) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "INSERT INTO `memes`.`genero` (`nomeGenero`) VALUES (?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, genero.getNome());
        
        stmt.execute();
        stmt.close();
        con.close();
    }
    
        
    public static void alterar(Genero genero) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "UPDATE `memes`.`genero` SET `nomeGenero`=? WHERE  `idGenero`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, genero.getNome());
        stmt.setInt(2, genero.getIdGenero());
        
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static void excluir(Genero genero) throws SQLException{
        Connection con = Conexao.getConnection();
        String sql
                = "DELETE FROM `memes`.`genero` WHERE  `idGenero`=?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, genero.getIdGenero());
        System.out.println("id:" + genero.getIdGenero());
        stmt.execute();
        stmt.close();
        con.close();
    }
    public static List<Genero> getLista() throws SQLException {
        List<Genero> lista = new ArrayList<Genero>();
        Connection con = Conexao.getConnection();
        String sql = "SELECT * FROM genero ORDER BY idGenero";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Genero genero = new Genero();
            genero.setIdGenero(rs.getInt("idGenero"));
            genero.setNome(rs.getString("nomeGenero"));
            lista.add(genero);
        }
        stmt.close();
        rs.close();
        con.close();

        return lista;
    }

    public static void main(String[] args) {

        try {
            List<Genero> lista = getLista();
            
            for (Genero m : lista) {
                System.out.println("ID....: "+m.getIdGenero());
                System.out.println("NOME......: "+m.getNome());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
}
