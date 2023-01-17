package facades;

import dtos.GuideDTO;
import dtos.TripDTO;
import entities.Guide;
import entities.Trip;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import errorhandling.NotFoundException;

public class TripFacade {
    private static TripFacade instance;
    private static EntityManagerFactory emf;
    
    private TripFacade() {}

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

    public TripDTO create(TripDTO tripDTO){
        Trip trip = new Trip();
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            updateEntity(em, trip, tripDTO);
            em.persist(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDTO(trip);
    }

    public TripDTO update(TripDTO tripDTO) {
        EntityManager em = getEntityManager();
        Trip trip = em.find(Trip.class, tripDTO.getId());
        updateEntity(em, trip, tripDTO);
        try {
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDTO(trip);
    }

    public TripDTO deleteByID(Long tripID) {
        EntityManager em = getEntityManager();
        Trip trip = em.find(Trip.class, tripID);
        try {
            em.getTransaction().begin();
            em.remove(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDTO(trip);
    }

    public static void updateEntity(EntityManager em, Trip trip, TripDTO tripDTO) {
        for (GuideDTO guideDTO : tripDTO.getGuides()) {
            Long guideID = guideDTO.getId();
            boolean isNew = guideID == null;
            if (isNew) {
                guideID = GuideFacade.getFacade(emf).create(guideDTO).getId();
            }
            em.flush();
            Guide guide = em.find(Guide.class, guideID);
            if (isNew) {
                guide.setTrip(trip);
            } else {
                GuideFacade.updateEntity(guide, guideDTO);
            }
            System.out.println(guide);
        }
        trip
                .setName(tripDTO.getName())
                .setDateTime(tripDTO.getDateTime())
                .setLocation(tripDTO.getLocation())
                .setDuration(tripDTO.getDuration())
                .setPackingList(tripDTO.getPackingList());
        System.out.println(trip);
    }
}
