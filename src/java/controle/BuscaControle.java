/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BuscaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Busca;
import modelo.Musica;

/**
 *
 * @author Dendi
 */
@ManagedBean
@SessionScoped
public class BuscaControle {

    private List<Busca> result = new ArrayList<Busca>();
    private String webInput = "";
    private Musica musica = new Musica();    

    @PostConstruct
    public void buscar() {
        try {
            result = BuscaDAO.searchMain(webInput);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getMusicaRedirect(int idMusica) {
        try {
            musica = BuscaDAO.getMusicaByID(idMusica);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "musica.xhtml?faces-redirect=true";
    }

    public List<Busca> getResult() {
        return result;
    }

    public void setResult(List<Busca> result) {
        this.result = result;
    }

    public String getWebInput() {
        return webInput;
    }

    public void setWebInput(String webInput) {
        this.webInput = webInput;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    
}
