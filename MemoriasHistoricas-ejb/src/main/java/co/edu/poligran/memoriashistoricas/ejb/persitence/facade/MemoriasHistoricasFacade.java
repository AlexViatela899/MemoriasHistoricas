/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poligran.memoriashistoricas.ejb.persitence.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peraltace
 */
@Named
@Stateless
public class MemoriasHistoricasFacade implements MemoriasHistoricasFacadeLocal {

    @PersistenceContext(unitName = "MemoriasHistoricasPU")
    private EntityManager em;
    
    
}
