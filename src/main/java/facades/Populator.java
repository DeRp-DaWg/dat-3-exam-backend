/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.GuideDTO;
import dtos.TripDTO;
import dtos.UserDTO;
import entities.Guide;
import entities.Trip;
import javax.persistence.EntityManagerFactory;

import entities.User;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        TripFacade tf = TripFacade.getFacade(emf);
        GuideFacade gf = GuideFacade.getFacade(emf);
        UserFacade uf = UserFacade.getFacade(emf);
        Trip trip1 = new Trip("Trip to Denmark", "Denmark", 1000*60*60*24L);
        Trip trip2 = new Trip("Trip to Italy", "Italy", 1000*60*60*24*2L);
        Guide guide1 = new Guide("John Doe", "Man", 1995, "Hello :)", "urlofimage");
        Guide guide2 = new Guide("Jane Doe", "Woman", 1997, "Hi :D", "urlofimage");
        Guide guide3 = new Guide("John Fossil", "Dinosaur", 1901, "I'm very old", "urlofimage");
        trip1
                .addGuide(guide1)
                .addGuide(guide2);
        trip2
                .addGuide(guide3);
        User user1 = new User("tripaddict", "1234");
        User user2 = new User("triplover4", "4321");
        tf.create(new TripDTO(trip1));
        tf.create(new TripDTO(trip2));
        gf.create(new GuideDTO(guide1));
        gf.create(new GuideDTO(guide2));
        gf.create(new GuideDTO(guide3));
        uf.createUser(new UserDTO(user1));
        uf.createUser(new UserDTO(user2));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
