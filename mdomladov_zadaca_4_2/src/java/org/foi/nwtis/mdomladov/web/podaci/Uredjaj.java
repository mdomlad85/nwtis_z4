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
public class Uredjaj {
    private int id;
    private String naziv;
    private Lokacija geoloc;

    /**
     *
     */
    public Uredjaj() {
    }

    /**
     *
     * @param id
     * @param naziv
     * @param geoloc
     */
    public Uredjaj(int id, String naziv, Lokacija geoloc) {
        this.id = id;
        this.naziv = naziv;
        this.geoloc = geoloc;
    }

    /**
     *
     * @return
     */
    public Lokacija getGeoloc() {
        return geoloc;
    }

    /**
     *
     * @param geoloc
     */
    public void setGeoloc(Lokacija geoloc) {
        this.geoloc = geoloc;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     *
     * @param naziv
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }        
}
