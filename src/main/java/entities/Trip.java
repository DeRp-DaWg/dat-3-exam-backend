package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Trip.deleteAllRows", query = "DELETE from Trip")
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime dateTime = LocalDateTime.now();
    private String location;
    private Long duration;
    @ElementCollection
    private List<String> packingList = new ArrayList<>();
    @ManyToMany(targetEntity = User.class)
    private List<User> users;
    @OneToMany(targetEntity = Guide.class)
    private List<Guide> guides;
    
    public Trip() {
    }

    public Trip(String name, String location, Long duration) {
        this.name = name;
        this.location = location;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public Trip setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Trip setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Trip setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Trip setLocation(String location) {
        this.location = location;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Trip setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public List<String> getPackingList() {
        return packingList;
    }

    public Trip setPackingList(List<String> packingList) {
        this.packingList = packingList;
        return this;
    }

    public Trip addPackingListItem(String item) {
        this.packingList.add(item);
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Trip setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Trip addUser(User user) {
        this.users.add(user);
        return this;
    }

    public List<Guide> getGuides() {
        return guides;
    }

    public Trip setGuides(List<Guide> guides) {
        this.guides = guides;
        return this;
    }

    public Trip addGuide(Guide guide) {
        this.guides.add(guide);
        return this;
    }
}
