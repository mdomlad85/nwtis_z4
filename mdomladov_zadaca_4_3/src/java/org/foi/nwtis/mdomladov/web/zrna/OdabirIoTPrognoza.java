/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.web.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.foi.nwtis.mdomladov.ejb.eb.Promjene;
import org.foi.nwtis.mdomladov.ejb.eb.Uredaji;
import org.foi.nwtis.mdomladov.ejb.sb.DnevnikFacade;
import org.foi.nwtis.mdomladov.ejb.sb.MeteoIoTKlijent;
import org.foi.nwtis.mdomladov.ejb.sb.PromjeneFacade;
import org.foi.nwtis.mdomladov.ejb.sb.UredajiFacade;
import org.foi.nwtis.mdomladov.konfiguracije.APP_Konfiguracija;
import org.foi.nwtis.mdomladov.web.kontrole.LogerHelper;
import org.foi.nwtis.mdomladov.web.podaci.Izbornik;
import org.foi.nwtis.mdomladov.web.podaci.Lokacija;
import org.foi.nwtis.mdomladov.web.podaci.MeteoPrognoza;
import org.foi.nwtis.mdomladov.web.slusaci.SlusacAplikacije;

/**
 *
 * @author Zeus
 */
@Named(value = "odabirIoTPrognoza")
@SessionScoped
public class OdabirIoTPrognoza implements Serializable {

    @EJB
    private DnevnikFacade dnevnikFacade;

    @EJB
    private PromjeneFacade promjeneFacade;

    @EJB
    private MeteoIoTKlijent meteoIoTKlijent;

    @EJB
    private UredajiFacade uredajiFacade;

    private static final String DISPLAY_NONE = "none";

    private static final String DISPLAY_BLOCK = "block";

    private static final String PROGNOZE_OPEN = "Prognoze";

    private static final String PROGNOZE_CLOSE = "Zatvori prognoze";

    private String id;

    private String naziv;

    private String adresa;

    private String idEdit;

    private String nazivEdit;

    private String adresaEdit;

    private List<Izbornik> raspoloziviUredjaji;

    private List<Izbornik> odabraniUredjaji;

    private List<String> listaRaspolozivihIota;

    private List<String> listaIotaZaVracanje;

    private List<MeteoPrognoza> meteoPrognoza;

    private String porukaPogreska;

    private String porukaUspjeha;

    private String displayAzuriraj = DISPLAY_NONE;

    private String displayAzurirajBtn = DISPLAY_NONE;

    private String displayPrognozeBtn = DISPLAY_NONE;

    private String displayPrognozeBtnText = PROGNOZE_OPEN;

    private String displayPrognoze = DISPLAY_NONE;

    private Uredaji uredjajEdit;

    private LogerHelper loger;

    /**
     * Creates a new instance of OdabirIoTPrognoza
     */
    public OdabirIoTPrognoza() {
        raspoloziviUredjaji = new ArrayList<>();
        odabraniUredjaji = new ArrayList<>();
        listaIotaZaVracanje = new ArrayList<>();
        listaRaspolozivihIota = new ArrayList<>();
        meteoPrognoza = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        loger = new LogerHelper(dnevnikFacade);
        List<Uredaji> uredjaji = uredajiFacade.findActive();
        uredjaji.forEach((uredjaj) -> {
            String idUredjaj = String.valueOf(uredjaj.getId());
            raspoloziviUredjaji.add(new Izbornik(idUredjaj, uredjaj.getNaziv()));
        });
        ServletContext sc = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();
        APP_Konfiguracija konfig = (APP_Konfiguracija) sc.getAttribute(SlusacAplikacije.APP_KONFIG);
        meteoIoTKlijent.postaviKorisnickePodatke(konfig.getOWApiKey());
    }

    public void dodajIotUredjaj() {
        loger.setStartTime((int) System.currentTimeMillis());
        if (createFieldsValid()) {
            dodajIot();
            loger.logiraj("Dodaj IoT");
        }
        loger.setStartTime(null);
    }

    public void urediIotUredjaj() {
        loger.setStartTime((int) System.currentTimeMillis());
        if (editFieldsValid()) {
            urediIot();
            loger.logiraj("Uredi IoT");
        }
        loger.setStartTime(null);
    }

    public void omoguciAzuriranjeForm() {
        displayAzuriraj = odabraniUredjaji.size() == 1 ? DISPLAY_BLOCK : DISPLAY_NONE;

        int idUredjaj = Integer.parseInt(odabraniUredjaji.get(0).getVrijednost());
        uredjajEdit = uredajiFacade.find(idUredjaj);
        idEdit = String.valueOf(uredjajEdit.getId());
        nazivEdit = uredjajEdit.getNaziv();
        adresaEdit = meteoIoTKlijent.dajAdresu(uredjajEdit.getLatitude(), uredjajEdit.getLongitude());
    }

    public void otvoriZatvoriPrognozePregled() {
        loger.setStartTime((int) System.currentTimeMillis());
        
        if (displayPrognoze.equals(DISPLAY_NONE)) {
            setPrognoze();
            loger.logiraj("Dohvati prognoze");
            displayPrognoze = DISPLAY_BLOCK;
            displayPrognozeBtnText = PROGNOZE_CLOSE;
        } else {
            displayPrognoze = DISPLAY_NONE;
            displayPrognozeBtnText = PROGNOZE_OPEN;
        }
        loger.setStartTime(null);
    }

    public void preuzmi() {
        pretoci(listaRaspolozivihIota, raspoloziviUredjaji, odabraniUredjaji);
        setGumbe();
    }

    public void vrati() {
        pretoci(listaIotaZaVracanje, odabraniUredjaji, raspoloziviUredjaji);
        setGumbe();
    }

    private void pretoci(List<String> koji, List<Izbornik> iz, List<Izbornik> u) {
        koji.forEach((idUredjaj) -> {
            Iterator<Izbornik> i = iz.iterator();
            while (i.hasNext()) {
                Izbornik uredjaj = i.next();
                if (uredjaj.getVrijednost().equals(idUredjaj)) {
                    u.add(uredjaj);
                    i.remove();
                }
            }
        });
    }

    private void setPrognoze() {
        meteoPrognoza = new ArrayList<>();
        for (Izbornik u : odabraniUredjaji) {
            Uredaji uredjaj = uredajiFacade
                    .find(Integer.parseInt(u.getVrijednost()));

            String add = meteoIoTKlijent
                    .dajAdresu(uredjaj.getLatitude(), uredjaj.getLongitude());

            meteoPrognoza.addAll(Arrays.asList(meteoIoTKlijent.dajMeteoPrognoze(uredjaj.getId(), add)));
        }
    }

    private void setGumbe() {
        int arrLen = odabraniUredjaji.size();
        displayPrognozeBtnText = PROGNOZE_OPEN;
        if (arrLen == 1) {
            displayAzurirajBtn = DISPLAY_BLOCK;
            displayPrognozeBtn = DISPLAY_NONE;
            displayPrognoze = DISPLAY_NONE;
        } else if (arrLen > 1) {
            displayPrognozeBtn = DISPLAY_BLOCK;
            displayAzurirajBtn = DISPLAY_NONE;
            displayAzuriraj = DISPLAY_NONE;
            displayPrognoze = DISPLAY_NONE;
        } else {
            displayAzurirajBtn = DISPLAY_NONE;
            displayAzuriraj = DISPLAY_NONE;
            displayPrognozeBtn = DISPLAY_NONE;
            displayPrognoze = DISPLAY_NONE;
        }
    }

    private void dodajIot() {
        Uredaji uredaj = new Uredaji();
        try {
            Date now = new Date();
            Lokacija lokacija = meteoIoTKlijent.dajLokaciju(adresa);
            uredaj.setLatitude(Double.parseDouble(lokacija.getLatitude()));
            uredaj.setLongitude(Double.parseDouble(lokacija.getLongitude()));
            uredaj.setNaziv(naziv);
            uredaj.setId(Integer.parseInt(id));
            uredaj.setVrijemeKreiranja(now);
            uredaj.setVrijemePromjene(now);
            uredajiFacade.create(uredaj);
            raspoloziviUredjaji.add(new Izbornik(id, naziv));
            porukaPogreska = uredajiFacade.getErrorMessage();
        } catch (Exception e) {
            porukaPogreska = e.getMessage();
        }

        if (porukaPogreska == null) {
            spremiPromjenu(uredaj);
            porukaUspjeha = String.format("Uređaj %s je uspješno pohranjen.", naziv);
            naziv = "";
            adresa = "";
            id = "";
        }
    }

    private void urediIot() {
        int originId = uredjajEdit.getId();
        try {
            if (!idEdit.equals(String.valueOf(originId))) {
                uredjajEdit.setStatus(1);
                uredajiFacade.edit(uredjajEdit);
                uredjajEdit.setId(Integer.parseInt(idEdit));
            }
            Lokacija lokacija = meteoIoTKlijent.dajLokaciju(adresaEdit);
            uredjajEdit.setLatitude(Double.parseDouble(lokacija.getLatitude()));
            uredjajEdit.setLongitude(Double.parseDouble(lokacija.getLongitude()));
            uredjajEdit.setNaziv(nazivEdit);
            uredjajEdit.setVrijemePromjene(new Date());

            if (!idEdit.equals(String.valueOf(originId))) {
                uredjajEdit.setStatus(0);
                uredajiFacade.create(uredjajEdit);
            } else {
                uredajiFacade.edit(uredjajEdit);
            }
            porukaPogreska = uredajiFacade.getErrorMessage();
        } catch (Exception e) {
            porukaPogreska = "Uređivanje nije uspješno!";
        }

        if (porukaPogreska == null) {
            spremiPromjenu(uredjajEdit);
            uredjajEdit = null;
            odabraniUredjaji = new ArrayList<>();
            raspoloziviUredjaji.add(new Izbornik(idEdit, nazivEdit));
            porukaUspjeha = String.format("Uređaj %s je uspješno modificiran.", nazivEdit);
            naziv = "";
            adresa = "";
            id = "";
            displayAzuriraj = DISPLAY_NONE;
            displayAzurirajBtn = DISPLAY_NONE;
        } else {
            uredjajEdit.setId(originId);
        }
    }

    private boolean createFieldsValid() {
        porukaPogreska = null;
        porukaUspjeha = null;

        if (id == null || id.isEmpty() || !id.matches("\\d+")) {
            porukaPogreska = "Id mora biti cijeli broj";
            return false;
        }

        if (naziv == null || naziv.isEmpty()) {
            porukaPogreska = "Naziv je obavezan";
            return false;
        }

        if (adresa == null || adresa.isEmpty()) {
            porukaPogreska = "Adresa je obavezna";
            return false;
        }

        return true;
    }

    private boolean editFieldsValid() {
        porukaPogreska = null;
        porukaUspjeha = null;

        if (idEdit == null || idEdit.isEmpty() || !idEdit.matches("\\d+")) {
            porukaPogreska = "Id mora biti cijeli broj";
            return false;
        }

        if (nazivEdit == null || nazivEdit.isEmpty()) {
            porukaPogreska = "Naziv je obavezan";
            return false;
        }

        if (adresaEdit == null || adresaEdit.isEmpty()) {
            porukaPogreska = "Adresa je obavezna";
            return false;
        }

        return true;
    }

    private void spremiPromjenu(Uredaji uredjaj) {
        Promjene promjena = new Promjene();
        promjena.setLatitude((float) uredjaj.getLatitude());
        promjena.setLongitude((float) uredjaj.getLongitude());
        promjena.setId(uredjaj.getId());
        promjena.setNaziv(uredjaj.getNaziv());
        promjena.setStatus(uredjaj.getStatus());
        promjena.setVrijemeKreiranja(uredjaj.getVrijemeKreiranja());
        promjena.setVrijemePromjene(uredjaj.getVrijemePromjene());
        promjeneFacade.create(promjena);
    }

    public String getPorukaUspjeha() {
        return porukaUspjeha;
    }

    public void setPorukaUspjeha(String porukaUspjeha) {
        this.porukaUspjeha = porukaUspjeha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Izbornik> getRaspoloziviUredjaji() {
        return raspoloziviUredjaji;
    }

    public void setRaspoloziviUredjaji(List<Izbornik> raspoloziviUredjaji) {
        this.raspoloziviUredjaji = raspoloziviUredjaji;
    }

    public List<Izbornik> getOdabraniUredjaji() {
        return odabraniUredjaji;
    }

    public void setOdabraniUredjaji(List<Izbornik> odabraniUredjaji) {
        this.odabraniUredjaji = odabraniUredjaji;
    }

    public List<String> getListaRaspolozivihIota() {
        return listaRaspolozivihIota;
    }

    public void setListaRaspolozivihIota(List<String> listaRaspolozivihIota) {
        this.listaRaspolozivihIota = listaRaspolozivihIota;
    }

    public List<String> getListaIotaZaVracanje() {
        return listaIotaZaVracanje;
    }

    public void setListaIotaZaVracanje(List<String> listaIotaZaVracanje) {
        this.listaIotaZaVracanje = listaIotaZaVracanje;
    }

    public String getPorukaPogreska() {
        return porukaPogreska;
    }

    public void setPorukaPogreska(String porukaPogreska) {
        this.porukaPogreska = porukaPogreska;
    }

    public String getIdEdit() {
        return idEdit;
    }

    public void setIdEdit(String idEdit) {
        this.idEdit = idEdit;
    }

    public String getNazivEdit() {
        return nazivEdit;
    }

    public void setNazivEdit(String nazivEdit) {
        this.nazivEdit = nazivEdit;
    }

    public String getAdresaEdit() {
        return adresaEdit;
    }

    public void setAdresaEdit(String adresaEdit) {
        this.adresaEdit = adresaEdit;
    }

    public String getDisplayAzuriraj() {
        return displayAzuriraj;
    }

    public void setDisplayAzuriraj(String displayAzuriraj) {
        this.displayAzuriraj = displayAzuriraj;
    }

    public String getDisplayAzurirajBtn() {
        return displayAzurirajBtn;
    }

    public void setDisplayAzurirajBtn(String displayAzurirajBtn) {
        this.displayAzurirajBtn = displayAzurirajBtn;
    }

    public String getDisplayPrognozeBtn() {
        return displayPrognozeBtn;
    }

    public void setDisplayPrognozeBtn(String displayPrognozeBtn) {
        this.displayPrognozeBtn = displayPrognozeBtn;
    }

    public String getDisplayPrognozeBtnText() {
        return displayPrognozeBtnText;
    }

    public void setDisplayPrognozeBtnText(String displayPrognozeBtnText) {
        this.displayPrognozeBtnText = displayPrognozeBtnText;
    }

    public String getDisplayPrognoze() {
        return displayPrognoze;
    }

    public void setDisplayPrognoze(String displayPrognoze) {
        this.displayPrognoze = displayPrognoze;
    }

    public List<MeteoPrognoza> getMeteoPrognoza() {
        return meteoPrognoza;
    }

    public void setMeteoPrognoza(List<MeteoPrognoza> meteoPrognoza) {
        this.meteoPrognoza = meteoPrognoza;
    }

}
