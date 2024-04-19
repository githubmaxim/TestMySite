package api.workingWithFieldPage.pojo.saveMethod;

import java.util.ArrayList;

public class UserRequest {
    public String name;
    public String login;
    public String email;
    public ArrayList<UniversityRequest> universities;

    public UserRequest() {
    }

    public UserRequest(String name, String login, String email, ArrayList<UniversityRequest> universities) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.universities = universities;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<UniversityRequest> getUniversities() {
        return universities;
    }
}
