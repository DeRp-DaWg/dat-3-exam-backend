package entities;

import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.deleteAllRows", query = "DELETE from User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String password;
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
            @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private List<Role> roleList = new ArrayList<>();
    private String address;
    private String phone;
    private String email;
    private int birthYear;
    // TODO: Change gender to enum
    private String gender;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )
    private List<Trip> trips;

    public List<String> getRolesAsStrings() {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roleList.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

    public User() {}

    public boolean verifyPassword(String password){
        return(BCrypt.checkpw(password, this.password));
    }

    public User(String username, String password) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        return this;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public User setRoleList(List<Role> roleList) {
        this.roleList = roleList;
        return this;
    }

    public User addRole(Role userRole) {
        roleList.add(userRole);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public User setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public User setTrips(List<Trip> trips) {
        this.trips = trips;
        return this;
    }

    public User addTrip(Trip trip) {
        this.trips.add(trip);
        trip.getUsers().add(this);
        return this;
    }
}
