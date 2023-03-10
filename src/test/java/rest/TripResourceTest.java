package rest;

import entities.Role;
import entities.Trip;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class TripResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Trip trip1, trip2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        trip1 = new Trip("Trip to Denmark", "Denmark", 1000*60*60*24L);
        trip2 = new Trip("Trip to Italy", "Italy", 1000*60*60*24*2L);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.createNamedQuery("Guide.deleteAllRows").executeUpdate();
            Role teacherRole = new Role("teacher");
            User user1 = new User("user1", "1");
            User user2 = new User("user2", "12");
            trip1 = new Trip("Trip to Denmark", "Denmark", 1000*60*60*24L);
            trip2 = new Trip("Trip to Italy", "Italy", 1000*60*60*24*2L);
            em.persist(trip1);
            em.persist(trip2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Disabled
    @Test
    public void testServerIsUp() {
        given().when().get("/trip").then().statusCode(200);
    }

    @Disabled
    @Test
    public void testGetAllTrips() throws Exception {
        given()
                .contentType("application/json")
                .get("/trip").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("", contains(""));
    }
}
