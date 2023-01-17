package dtos;

import entities.Trip;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripDTO {
    private Long id;
    private String name;
    private LocalDateTime dateTime = LocalDateTime.now();
    private String location;
    private Long duration;
    private List<String> packingList = new ArrayList<>();
    private List<GuideDTO> guides = new ArrayList<>();

    public TripDTO(Long id, String name, String location, Long duration) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.duration = duration;
    }

    public TripDTO(String name, String location, Long duration) {
        this.name = name;
        this.location = location;
        this.duration = duration;
    }

    public static List<TripDTO> getDtos(List<Trip> trips){
        List<TripDTO> tripDTOs = new ArrayList();
        trips.forEach(trip->tripDTOs.add(new TripDTO(trip)));
        return tripDTOs;
    }


    public TripDTO(Trip trip) {
        if(trip.getId() != null)
            this.id = trip.getId();
        this.name = trip.getName();
        this.dateTime = trip.getDateTime();
        this.location = trip.getLocation();
        this.duration = trip.getDuration();
        this.packingList = trip.getPackingList();
        this.guides = GuideDTO.getDtos(trip.getGuides());
    }

    public Long getId() {
        return id;
    }

    public TripDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TripDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TripDTO setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public TripDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public TripDTO setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public List<String> getPackingList() {
        return packingList;
    }

    public TripDTO setPackingList(List<String> packingList) {
        this.packingList = packingList;
        return this;
    }

    public TripDTO addPackingListItem(List<String> packingList) {
        this.packingList = packingList;
        return this;
    }

    public List<GuideDTO> getGuides() {
        return guides;
    }

    public TripDTO setGuides(List<GuideDTO> guides) {
        this.guides = guides;
        return this;
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", location='" + location + '\'' +
                ", duration=" + duration +
                ", packingList=" + packingList +
                '}';
    }
}
