package facades;

import entities.Trip;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class TripFacadeTest {

    private static EntityManagerFactory emf;
    private static TripFacade facade;

    public TripFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = TripFacade.getFacadeExample(emf);
    }

//    @AfterAll
//    public static void tearDownClass() {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.persist(new Trip("Trip to Denmark", "Denmark", 1000*60*60*24L));
            em.persist(new Trip("Trip to Italy", "Italy", 1000*60*60*24*2L));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

//    @AfterEach
//    public void tearDown() {
////        Remove any data after each test was run
//    }
}
