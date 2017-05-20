/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.mdomladov.ejb.eb.Meteo;

/**
 *
 * @author Zeus
 */
@Stateless
public class MeteoFacade extends AbstractFacade<Meteo> {

    @PersistenceContext(unitName = "mdomladov_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeteoFacade() {
        super(Meteo.class);
    }
    
}
