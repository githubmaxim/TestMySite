package api.workingWithFieldPage.pojo.findAllMethod;

import java.util.ArrayList;

public class UserResponse {
    public String id;
    public String name;
    public String login;
    public String email;
    public ArrayList<UniversityResponse> universities;

    public UserResponse() {
    }

    public UserResponse(String id, String name, String login, String email, ArrayList<UniversityResponse> universities) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.universities = universities;
    }

    public String getId() {
        return id;
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

    public ArrayList<UniversityResponse> getUniversities() {
        return universities;
    }
}
