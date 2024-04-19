package api.workingWithFieldPage.pojo.findAllMethod;

import java.util.ArrayList;

public class UniversityResponse {
    public int id;
    public String name;
    public ArrayList<UserListResponse> usersList;
    public CitiesResponse cities;

    public UniversityResponse() {
    }

    public UniversityResponse(int id, String name, ArrayList<UserListResponse> usersList, CitiesResponse cities) {
        this.id = id;
        this.name = name;
        this.usersList = usersList;
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<UserListResponse> getUsersList() {
        return usersList;
    }

    public CitiesResponse getCities() {
        return cities;
    }
}
