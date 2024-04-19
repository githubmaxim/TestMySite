package api.workingWithFieldPage.test;

import api.specification.Specifications;
import api.workingWithFieldPage.pojo.findAllMethod.UniversityResponse;
import api.workingWithFieldPage.pojo.findAllMethod.UserResponse;
import api.workingWithFieldPage.pojo.saveMethod.CityRequest;
import api.workingWithFieldPage.pojo.saveMethod.UniversityRequest;
import api.workingWithFieldPage.pojo.saveMethod.UserRequest;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.testcontainers.shaded.org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //для возможности использования аннотаций @Order()
public class WorkingFieldTest {
    private static String URL = "http://localhost:8080";

    @Test
    public void checkCreateUserAlreadyExist(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(444));

        CityRequest cityRequest = new CityRequest("Kiev");
        UniversityRequest universityRequest = new UniversityRequest("KPI", cityRequest);
        ArrayList<UniversityRequest> universityRequestArrayList = new ArrayList<>();
        universityRequestArrayList.add(universityRequest);
        UserRequest userRequest = new UserRequest("Max", "max", "max@ukr.net", universityRequestArrayList);

        String  response = given()
                .accept("application/json, text/plain, */*")
                .body(userRequest)
                .when()
                .post("users/save")
                .then().log().all()
                .extract().asPrettyString();
        Assertions.assertTrue(response.equals("Such a login exists"));
    }

    @Order(1)
    @Test
    public void checkCreateUserOk() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));

        CityRequest cityRequest = new CityRequest("Charkiv");
        UniversityRequest universityRequest = new UniversityRequest("KNMU", cityRequest);
        ArrayList<UniversityRequest> universityRequestArrayList = new ArrayList<>();
        universityRequestArrayList.add(universityRequest);
        UserRequest userRequest = new UserRequest("Vlad", "vlad", "vlad@ukr.net", universityRequestArrayList);

        String  response = given()
                .accept("application/json, text/plain, */*")
                .body(userRequest)
                .when()
                .post("users/save")
                .then().log().all()
                .extract().asPrettyString();
        Assertions.assertTrue(response.equals("User created"));
    }

    @Order(2)
    @Test
    //Перед запуском этого теста нужно, чтобы вначале сработал тест "checkCreateUserOk()" (который записывает эти данные в БД)
    public void checkDeleteUser() {

        String userName = "Vlad";
        String userId = "";

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));

        //Thread.sleep(3000); //Если запускать в многопоточке без "@Order()" ждем, чтобы сработал тест "checkCreateUserOk()" (который записывает эти данные в БД)

        //Находим "id", созданного тестом "checkCreateUserOk()" user-a
        List<UserResponse> userResponseList = given()
                .when()
                .get("users/findAll")
                .then()
                .log().all()
                .extract().body().jsonPath().getList(".", UserResponse.class);

        for (int j = 0; j < userResponseList.size(); j++) {
            if (userResponseList.get(j).name.equals(userName)) {
                userId = userResponseList.get(j).id;
            }
        }

        //Удаляем user-a по найденому ранее "id"
        given()
                .when()
                .delete("http://localhost:8080/users/delete/" + userId)
                .then()
                .log().all();
    }


    // !!! "StatusCode:415:Unsupported Media Type" - Не знаю как правильно создать Request, когда информация на сервер отправляется методом-form "!!!
//    @Test
//    public void checkForm() {
////        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));
//
//        FormRequest formRequest = new FormRequest("1", "a");
//        List<FormRequest> formRequests = new ArrayList<>();
//        formRequests.add(formRequest);
//
//        String  response = given()
//                .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/x-www-form-urlencodedl", ContentType.JSON)))
////                .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json, application/xml", ContentType.JSON)))
//                .accept("application/json, text/plain, */*")
////                .contentType("application/json, application/xml")
//                .contentType("application/x-www-form-urlencodedl")
//                .body(formRequests)
//                .when()
//                .post("http://localhost:8080/users/form")
//                .then().log().all()
//                .extract().asPrettyString();
//        int i = 0;
////        Assertions.assertTrue(response.equals("User created"));
//    }

    @Test
    public void checkFindByLogin() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));

        JsonPath userDTOResponse = given()
                .when()
                .get("users/findByLogin?param1=max")
                .then()
                .log().all()
                .extract().body().jsonPath();

        //"email" contain "max@"
        Assertions.assertTrue(userDTOResponse.get("email").toString().contains("max@"));
    }

    @Test
    public void checkFindAll() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));

//      //Получение полей не напрямую из Json-ответа, а через DTO-классы
//        (!!!!!!При этом связь между сущностями в моей БД на сайте не oneToOne (БД в appication.properties называется "springbeginmenytomeny"), a manyToMany/manyToOne (БД в appication.properties называется "springbegin"))
//

        List<UserResponse> userResponseList = given()
                .when()
                .get("users/findAll")
                .then()
                .log().all()
                .extract().body().jsonPath().getList(".", UserResponse.class);

        //All "email" equal "Information is not available"
        List<String> emails = new ArrayList<>();
        for (int j = 0; j < userResponseList.size(); j++){
            emails.add(userResponseList.get(j).email);
            Assertions.assertTrue(emails.stream().allMatch(x->x.equals("Information is not available")));
        }

        //Ira live in Chernigiv
        String name = "Ira";
        String city = "Chernigiv";
        String haveCity = "";
        for (int i = 0; i < userResponseList.size(); i++) {
            if (userResponseList.get(i).getName().equals(name)) {
                List<UniversityResponse> universityResponseList = userResponseList.get(i).getUniversities();
                for (int j = 1; j < universityResponseList.size(); j = j + 2) {
                    haveCity = universityResponseList.get(j).getCities().city;
                }
            }
        }
        Assertions.assertTrue(haveCity.equals(city));

//      //Получение полей не через DTO-классы, а напрямую из Json-ответа
//        (!!!!!!При этом связь между сущностями в моей БД на сайте не manyToMany/manyToOne (БД в appication.properties называется "springbegin"), a oneToOne (БД в appication.properties называется "springbeginmenytomeny"))
//                JsonPath userDTOResponse = given()
//                .when()
//                .get("users/findAll")
//                .then()
//                .log().all()
//                .extract().body().jsonPath();
//
//        //All "email" equal "Information is not available"
//        List<String> email = userDTOResponse.get("email");
//        Assertions.assertTrue(email.stream().allMatch(x -> x.equals("Information is not available")));
//
//
//        //Ira live in Chernigiv
//        String name = "Ira";
//        String city = "Chernigiv";
//        List<Map<Object, Object>> users = userDTOResponse.get(".");
//        Map<Object, Object> universityMap = new HashMap<>();
//        Object haveCity = null;
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).get("name").equals(name)){
//
//                //Строка заменяющая следующие 4 строки. Тут get-ом по ключам и "кастом" получившегося в "Map<Object, Object>", получаю доступ к ключам нижнего "Map" и т.д.
//                universityMap = (Map<Object, Object>) ((Map<Object, Object>) users.get(i).get("universities")).get("cities");
////                Map<Object, Object> universityMap = (Map<Object, Object>) users.get(i).get("universities");
////                for (Map.Entry<Object, Object> entry : universityMap.entrySet()) {
////                    if (entry.getKey().equals("cities")) {
////                        Map<Object, Object> cityMap = (Map<Object, Object>) entry.getValue();
//                        for (Map.Entry<Object, Object> entry2 : universityMap.entrySet()) {
//                            if (entry2.getKey().equals("city")) {
//                                haveCity = entry2.getValue();
//                            }
//                        }
////                    }
////                }
//            }
//        }
//        Assertions.assertTrue(haveCity.toString().equals(city));
    }
}