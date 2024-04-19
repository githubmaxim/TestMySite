package api.workingWithFieldPage.pojo.saveMethod;

public class UniversityRequest {
    public String name;
    public CityRequest cities;

    public UniversityRequest() {
    }

    public UniversityRequest(String name, CityRequest cities) {
        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public CityRequest getCities() {
        return cities;
    }
}
