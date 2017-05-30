/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.web.podaci;

/**
 *
 * @author dkermek
 */
public class Lokacija {

    private String latitude;
    private String longitude;
    private String adresa;    

    /**
     *
     */
    public Lokacija() {
    }

    /**
     *
     * @param latitude
     * @param longitude
     */
    public Lokacija(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @param adresa
     */
    public Lokacija(String latitude, String longitude, String adresa) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adresa = adresa;
    }
    
    /**
     *
     * @param latitude
     * @param longitude
     */
    public void postavi(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", latitude, longitude);
    }   

    /**
     *
     * @return
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     *
     * @param adresa
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
}
