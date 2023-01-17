package facades;

import dtos.TripDTO;
import dtos.UserDTO;
import entities.Role;
import entities.Trip;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.errorhandling.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    public static UserFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public String createRole(String role) {
        Role newRole = new Role(role);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newRole);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return newRole.getRoleName();
    }

    public UserDTO createUser(UserDTO userDTO) {
        EntityManager em = getEntityManager();
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        List<Role> roles = new ArrayList<>();
        for (String roleAsString : userDTO.getRoles()) {
            Role role = em.find(Role.class, roleAsString);
            roles.add(role);
        }
        user.setRoleList(roles);
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }

}
