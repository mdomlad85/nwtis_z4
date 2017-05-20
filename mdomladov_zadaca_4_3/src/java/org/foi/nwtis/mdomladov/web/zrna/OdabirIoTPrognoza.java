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
import org.foi.nwtis.mdomladov.web.podaci.Izbornik;

/**
 *
 * @author Zeus
 */
@Named(value = "odabirIoTPrognoza")
@SessionScoped
public class OdabirIoTPrognoza implements Serializable {
    
    private int id;
    
    private String naziv;
    
    private String adresa;
    
    private List<Izbornik> sviUredjaji;
    
    private List<String> raspoloziviUredjaji;
    
    private List<String> odabraniUredjaji;
    
    private boolean azuriranjeOmoguceno;

    /**
     * Creates a new instance of OdabirIoTPrognoza
     */
    public OdabirIoTPrognoza() {
    }
    
    public void dodajIotUredjaj(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Izbornik> getSviUredjaji() {
        return sviUredjaji;
    }

    public void setSviUredjaji(List<Izbornik> sviUredjaji) {
        this.sviUredjaji = sviUredjaji;
    }

    public List<String> getRaspoloziviUredjaji() {
        return raspoloziviUredjaji;
    }

    public void setRaspoloziviUredjaji(List<String> raspoloziviUredjaji) {
        this.raspoloziviUredjaji = raspoloziviUredjaji;
    }

    public List<String> getOdabraniUredjaji() {
        return odabraniUredjaji;
    }

    public void setOdabraniUredjaji(List<String> odabraniUredjaji) {
        this.odabraniUredjaji = odabraniUredjaji;
    }

    public boolean isAzuriranjeOmoguceno() {
        return azuriranjeOmoguceno;
    }

    public void setAzuriranjeOmoguceno(boolean azuriranjeOmoguceno) {
        this.azuriranjeOmoguceno = azuriranjeOmoguceno;
    }
    
    
}
