/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.web.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.foi.nwtis.mdomladov.ejb.eb.Promjene;
import org.foi.nwtis.mdomladov.ejb.sb.DnevnikFacade;
import org.foi.nwtis.mdomladov.ejb.sb.PromjeneFacade;
import org.foi.nwtis.mdomladov.web.kontrole.LogerHelper;

/**
 *
 * @author Zeus
 */
@Named(value = "pregledPromjena")
@SessionScoped
public class PregledPromjena implements Serializable {

    @EJB
    private DnevnikFacade dnevnikFacade;

    @EJB
    private PromjeneFacade promjeneFacade;
    
    private String searchId;
    
    private String searchNaziv;
    
    private List<Promjene> promjene;

    private LogerHelper loger;

    /**
     * Creates a new instance of PregledPromjena
     */
    public PregledPromjena() {
    }
    
    @PostConstruct
    private void init() {
        promjene = promjeneFacade.findAll();
        loger = new LogerHelper(dnevnikFacade);
    }
    
    public void filtrirajPodatke(){
        loger.setStartTime((int)System.currentTimeMillis());
        promjene = promjeneFacade.filter(searchId, searchNaziv); 
        loger.logiraj("filtriranje dnevnika");
        loger.setStartTime(null);
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getSearchNaziv() {
        return searchNaziv;
    }

    public void setSearchNaziv(String searchNaziv) {
        this.searchNaziv = searchNaziv;
    }

    public List<Promjene> getPromjene() {
        return promjene;
    }

    public void setPromjene(List<Promjene> promjene) {
        this.promjene = promjene;
    }
}
