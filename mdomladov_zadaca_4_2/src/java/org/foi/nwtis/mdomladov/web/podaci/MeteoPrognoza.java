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
public class MeteoPrognoza {

    private int id;
    private int dan;
    private MeteoPodaci prognoza;

    /**
     *
     */
    public MeteoPrognoza() {
    }

    /**
     *
     * @param id
     * @param dan
     * @param prognoza
     */
    public MeteoPrognoza(int id, int dan, MeteoPodaci prognoza) {
        this.id = id;
        this.dan = dan;
        this.prognoza = prognoza;
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
    public int getDan() {
        return dan;
    }

    /**
     *
     * @param dan
     */
    public void setDan(int dan) {
        this.dan = dan;
    }

    /**
     *
     * @return
     */
    public MeteoPodaci getPrognoza() {
        return prognoza;
    }

    /**
     *
     * @param prognoza
     */
    public void setPrognoza(MeteoPodaci prognoza) {
        this.prognoza = prognoza;
    }


}