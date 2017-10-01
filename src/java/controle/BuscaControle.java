/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BuscaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Busca;

/**
 *
 * @author Dendi
 */
@ManagedBean
@SessionScoped
public class BuscaControle {

    private List<Busca> data = null;
    private List<Busca> result = null;
    private String display;

    @PostConstruct
    public void init() {
        try {
            data = BuscaDAO.getListaMusicas();
            result = BuscaDAO.getListaMusicas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
