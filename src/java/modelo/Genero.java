package modelo;

/**
 *
 * @author Galen Marek
 */
public class Genero {
    private int idGenero;
    private String nome;

    public int getIdGenero() {
        System.out.println("pegou idgenero");
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        System.out.println("setou idgenero");
        this.idGenero = idGenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
