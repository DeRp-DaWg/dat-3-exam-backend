package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Guide.deleteAllRows", query = "DELETE from Guide")
public class Guide implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private int birthYear;
    private String profile;
        private String imageURL;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Guide() {
    }

    public Guide(String name, String gender, int birthYear, String profile, String imageURL) {
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public Guide setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Guide setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Guide setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Guide setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public Guide setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Guide setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public Trip getTrip() {
        return trip;
    }

    public Guide setTrip(Trip trip) {
        this.trip = trip;
        trip.getGuides().add(this);
        return this;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", profile='" + profile + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", trip=" + trip.getName() +
                '}';
    }
}
