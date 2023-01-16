package facades;

import dtos.GuideDTO;
import entities.Guide;
import errorhandling.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class GuideFacade {

    private static GuideFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private GuideFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GuideFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GuideFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public GuideDTO create(GuideDTO guideDTO){
        Guide guide = new Guide(guideDTO.getName(), guideDTO.getGender(), guideDTO.getBirthYear(), guideDTO.getProfile(), guideDTO.getImageURL());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new GuideDTO(guide);
    }
    public GuideDTO getById(long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Guide guide = em.find(Guide.class, id);
        if (guide == null) {
            throw new NotFoundException("The Guide entity with ID: " + id + " Was not found");
        }
        return new GuideDTO(guide);
    }
    
    public List<GuideDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Guide> query = em.createQuery("SELECT g FROM Guide g", Guide.class);
        List<Guide> guides = query.getResultList();
        return GuideDTO.getDtos(guides);
    }
}
