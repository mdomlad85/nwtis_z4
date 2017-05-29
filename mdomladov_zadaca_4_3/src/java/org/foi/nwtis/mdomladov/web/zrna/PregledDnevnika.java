/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.web.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.foi.nwtis.mdomladov.ejb.eb.Dnevnik;
import org.foi.nwtis.mdomladov.ejb.sb.DnevnikFacade;
import org.foi.nwtis.mdomladov.web.kontrole.LogerHelper;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Zeus
 */
@Named(value = "pregledDnevnika")
@SessionScoped
public class PregledDnevnika implements Serializable {

    @EJB
    private DnevnikFacade dnevnikFacade;
    
    private String korisnik;
    
    private String ipAdresa;
    
    private String trajanje;
    
    private String status;
    
    private String url;
    
    private Date datumOd;
    
    private Date datumDo;
    
    private List<Dnevnik> dnevnici;

    private LogerHelper loger;

    /**
     * Creates a new instance of PregledDnevnika
     */
    public PregledDnevnika() {
        dnevnici = new ArrayList<>();
    }
    
    public void filtriraj(){
        loger.setStartTime((int)System.currentTimeMillis());
        dnevnici = dnevnikFacade
                .filtriraj(korisnik, ipAdresa, trajanje, status, url, datumOd, datumDo);
        loger.logiraj("filtriranje dnevnika");
        loger.setStartTime(null);
        
    }
    @PostConstruct
    private void init() {
        loger = new LogerHelper(dnevnikFacade);
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public String getIpAdresa() {
        return ipAdresa;
    }

    public void setIpAdresa(String ipAdresa) {
        this.ipAdresa = ipAdresa;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public List<Dnevnik> getDnevnici() {
        return dnevnici;
    }

    public void setDnevnici(List<Dnevnik> dnevnici) {
        this.dnevnici = dnevnici;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat(PomocnoZrno.FORMAT_DATUMA);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
}
