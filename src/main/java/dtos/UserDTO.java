package dtos;

import entities.Trip;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static List<UserDTO> getDtos(List<User> users){
        List<UserDTO> userDTOs = new ArrayList();
        users.forEach(user->userDTOs.add(new UserDTO(user)));
        return userDTOs;
    }


    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRolesAsStrings();
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public UserDTO addRole(String role) {
        this.roles.add(role);
        return this;
    }
}
