package dtos;

import entities.Guide;
import entities.Trip;

import java.util.ArrayList;
import java.util.List;

public class GuideDTO {
    private Long id;
    private String name;
    private String gender;
    private int birthYear;
    private String profile;
    private String imageURL;

    public GuideDTO(Long id, String name, String gender, int birthYear, String profile, String imageURL) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
        this.imageURL = imageURL;
    }

    public GuideDTO(String name, String gender, int birthYear, String profile, String imageURL) {
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
        this.imageURL = imageURL;
    }

    public static List<GuideDTO> getDtos(List<Guide> guides){
        List<GuideDTO> guideDTOs = new ArrayList();
        guides.forEach(guide->guideDTOs.add(new GuideDTO(guide)));
        return guideDTOs;
    }

    public GuideDTO(Guide guide) {
        if(guide.getId() != null)
            this.id = guide.getId();
        this.name = guide.getName();
        this.gender = guide.getGender();
        this.birthYear = guide.getBirthYear();
        this.profile = guide.getProfile();
        this.imageURL = guide.getImageURL();
    }

    public Long getId() {
        return id;
    }

    public GuideDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GuideDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public GuideDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public GuideDTO setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public GuideDTO setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public GuideDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    @Override
    public String toString() {
        return "GuideDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", profile='" + profile + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
