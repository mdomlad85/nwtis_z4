/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.ejb.sb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.foi.nwtis.mdomladov.rest.klijenti.GMKlijent;
import org.foi.nwtis.mdomladov.rest.klijenti.OWMKlijent;
import org.foi.nwtis.mdomladov.web.podaci.Lokacija;
import org.foi.nwtis.mdomladov.web.podaci.MeteoPrognoza;

/**
 *
 * @author Zeus
 */
@Stateless
@LocalBean
public class MeteoIoTKlijent {
    
    private String apiKey;

    public void postaviKorisnickePodatke(String apiKey) {
        this.apiKey = apiKey;
    }

    public Lokacija dajLokaciju(String adresa) {
        GMKlijent gmklijent = new GMKlijent();
        
        return gmklijent.getGeoLocation(adresa);
    }

    public MeteoPrognoza[] dajMeteoPrognoze(int id, String adresa) {
        GMKlijent gmklijent = new GMKlijent();
        Lokacija lokacija = gmklijent.getGeoLocation(adresa);
        OWMKlijent owmKlijent = new OWMKlijent(apiKey);
        
        return owmKlijent.getWeatherForecast(id, 
                lokacija.getLatitude(), 
                lokacija.getLongitude());
    }
    
}
