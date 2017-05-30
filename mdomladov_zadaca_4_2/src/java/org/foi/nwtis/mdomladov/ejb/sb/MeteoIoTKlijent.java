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
 * Klasa za dohvat meteo podataka
 * i za dohvat lokacije
 * 
 * @author Marko Domladovac
 */
@Stateless
@LocalBean
public class MeteoIoTKlijent {
    
    private String apiKey;

    /**
     *
     * @param apiKey
     */
    public void postaviKorisnickePodatke(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Dohvat koordinata za adresu
     * 
     * @param adresa
     * @return 
     */
    public Lokacija dajLokaciju(String adresa) {
        GMKlijent gmklijent = new GMKlijent();
        
        return gmklijent.getGeoLocation(adresa);
    }

    /**
     * Dohvat prognoza za adresu
     * 
     * @param id
     * @param adresa
     * @return 
     */
    public MeteoPrognoza[] dajMeteoPrognoze(int id, String adresa) {
        GMKlijent gmklijent = new GMKlijent();
        Lokacija lokacija = gmklijent.getGeoLocation(adresa);
        OWMKlijent owmKlijent = new OWMKlijent(apiKey);
        
        return owmKlijent.getWeatherForecast(id, 
                lokacija.getLatitude(), 
                lokacija.getLongitude());
    }

    /**
     * Dohvat adrese za geolokaciju
     * 
     * @param lokacija
     * @return 
     */
    public String dajAdresu(Lokacija lokacija) {
        GMKlijent gmklijent = new GMKlijent();
        
        return gmklijent.getAdresaByLocation(lokacija);
    }

    /**
     * Dohvat adrese za geolokaciju
     * 
     * @param latitude
     * @param longitude
     * @return 
     */
    public String dajAdresu(double latitude, double longitude) {
        GMKlijent gmklijent = new GMKlijent();
        Lokacija lokacija = new Lokacija(String.valueOf(latitude), String.valueOf(longitude));
        
        return gmklijent.getAdresaByLocation(lokacija);
    }
    
}
