/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.web.podaci;

/**
 * Klasa koja se koristi
 * za popunjavanje select liste (key, value)
 * 
 * @author Marko Domladovac
 */
public class Izbornik {
    
    private String vrijednost;
    
    private String labela;

    public Izbornik() {
    }

    public Izbornik(String vrijednost, String labela) {
        this.vrijednost = vrijednost;
        this.labela = labela;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getLabela() {
        return labela;
    }

    public void setLabela(String labela) {
        this.labela = labela;
    }
    
    
}
