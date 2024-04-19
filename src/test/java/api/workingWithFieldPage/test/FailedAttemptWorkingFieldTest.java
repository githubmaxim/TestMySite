package api.workingWithFieldPage.test;

//import static io.restassured.RestAssured.given;
//
//public class WorkingFieldTest {
//    private static String URL = "https://localhost:443/";


////  !!!!Разные неудачные попытки пробиться через логин+пароль на моем сайте, чтобы добраться до нужной мне страницы.
//// Получилось только пробраться до получения от сервера ответа с html-страницей в которой вводится логин/пароль - при этом
//// используется ".relaxedHTTPSValidation()" без "auth()" и всех ее доп.настроек!!!!


//    @Test
//    public void checkMailContainLogin() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

////Попытка 1 - прописываю ключ сертификации:
//        KeyStore keyStore = null;
//        SSLConfig config = null;
//        String password = "humlo21";
//
//        try {
//            keyStore = KeyStore.getInstance("PKCS12");
//            keyStore.load(
//                    new FileInputStream("D:\\Java\\Project IDEA\\TestMySite\\src\\test\\java\\api\\workingWithFieldPage\\test\\keystore.p12"),
//                    password.toCharArray());
//        } catch (Exception ex) {
//            System.out.println("Error while loading keystore >>>>>>>>>");
//            ex.printStackTrace();
//        }
//
//        if (keyStore != null) {
//
//            org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(keyStore, password);
//
//            // set the config in rest assured
//            config = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
//
//            RestAssured.config = RestAssured.config().sslConfig(config);
//
////        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));
//
//            UserDTOResponse userDTOResponse = RestAssured.given()
//            ResponseBodyExtractionOptions userDTOResponse = given()
//Попытки 2.1, 2.2, 2.3, 2.4, 2.5, 2.6
//                .auth()
//                .basic("a", "a")
//                .preemptive().basic("a", "a")
//                .digest("a", "a")
//                .form("a", "a")
//                .form("a", "a", new FormAuthConfig("/login", "username", "password"))
//                .relaxedHTTPSValidation()
//
//                    .when()
////                    .get("https://localhost:443/")
//                    .get("https://localhost:443/users/findAll")
////                    .get("https://localhost:443/users/findByLogin?param1=max")
////                .get("users/findByLogin?param1=max")
//                    .then()
//                    .log().all()
//                    .extract().body();
////                    .extract().as(UserDTOResponse.class);
//        }}
//
//
// Попытки 3.1, 3.2
//    @Test
//    public void verify_get() {
//        String responseString= given()
//                .config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
//                .baseUri("https://localhost:443/users/findByLogin?param1=max")
////                .port(serverConfig.endpointsSslPort())
////                .basePath(SSLTestEndpoint.MATCHING_PATH)
//                .log().all()
//                .when()
//                .get("https://localhost:443/users/findByLogin?param1=max")
//                .then()
//                .log().all()
//                .statusCode(200)
//                .extract().asString();
////        assertThat(responseString).isEqualTo(RESPONSE_STRING);
//    }
//
//        String getCookie = RestAssured
//                .given().relaxedHTTPSValidation()
//                .contentType(ContentType.URLENC)
//                .formParam("username", "a")
//                .formParam("password", "a")
//                .post("https://localhost:443/users/findByLogin?param1=max")
//                .then()
//                .log().all()
//                .statusCode(302)
//                .extract().asString();
//    }
//}
