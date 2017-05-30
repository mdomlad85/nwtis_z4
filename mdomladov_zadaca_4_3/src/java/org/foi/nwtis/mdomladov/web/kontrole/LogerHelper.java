/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.web.kontrole;

import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.foi.nwtis.mdomladov.ejb.eb.Dnevnik;
import org.foi.nwtis.mdomladov.ejb.sb.DnevnikFacade;

/**
 * Klasa koja pomaže prilikom logiranja 
 * akcija korisnika
 * 
 * @author Marko Domladovac
 */
public class LogerHelper {

    private final DnevnikFacade dnevnikFacade;

    private Integer startTime;

    public LogerHelper(DnevnikFacade dnevnikFacade) {
        this.dnevnikFacade = dnevnikFacade;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * metoda koja kreira potrebne podatke i logira ih
     * 
     * @param metoda
     * @return 
     */
    public boolean logiraj(String metoda) {
        if (startTime != null) {
            Dnevnik dnevnik = new Dnevnik();
            dnevnik.setTrajanje((int) System.currentTimeMillis() - startTime);
            dnevnik.setKorisnik("mdomladov");
            dnevnik.setIpadresa(getIpAdresa());
            dnevnik.setUrl(getUrl(metoda));
            dnevnik.setVrijeme(new Date());

            try {
                dnevnikFacade.create(dnevnik);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    private String getIpAdresa() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        String ipAddresa = request.getHeader("X-FORWARDED-FOR");
        if (ipAddresa == null) {
            ipAddresa = request.getRemoteAddr();
        }
        return ipAddresa;
    }

    private String getUrl(String metoda) {
        return String.format("%s(%s)", FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestContextPath(),
                metoda);
    }

}
