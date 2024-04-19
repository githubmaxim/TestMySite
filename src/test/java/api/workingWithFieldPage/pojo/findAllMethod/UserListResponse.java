package api.workingWithFieldPage.pojo.findAllMethod;

public class UserListResponse {
    public int id;
    public String name;
    public String login;
    public String email;

    public UserListResponse() {
    }

    public UserListResponse(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public int getId() {
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
}
