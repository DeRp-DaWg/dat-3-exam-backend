package facades;

import dtos.TripDTO;
import entities.Trip;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import errorhandling.NotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class TripFacade {

    private static TripFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private TripFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static TripFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TripFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TripDTO create(TripDTO tripDTO){
        Trip trip = new Trip(tripDTO.getName(), tripDTO.getLocation(), tripDTO.getDuration());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDTO(trip);
    }
    public TripDTO getById(long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Trip trip = em.find(Trip.class, id);
        if (trip == null) {
            throw new NotFoundException("The Trip entity with ID: " + id + " Was not found");
        }
        return new TripDTO(trip);
    }
    
    public List<TripDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
        List<Trip> trips = query.getResultList();
        return TripDTO.getDtos(trips);
    }
}
