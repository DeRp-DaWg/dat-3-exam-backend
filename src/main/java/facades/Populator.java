/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.TripDTO;
import entities.Trip;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        TripFacade fe = TripFacade.getFacadeExample(emf);
        fe.create(new TripDTO(new Trip("Trip to Denmark", "Denmark", 1000*60*60*24L)));
        fe.create(new TripDTO(new Trip("Trip to Italy", "Italy", 1000*60*60*24*2L)));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
