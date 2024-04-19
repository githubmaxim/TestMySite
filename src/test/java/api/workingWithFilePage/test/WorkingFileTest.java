package api.workingWithFilePage.test;

import api.specification.Specifications;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.testcontainers.shaded.org.apache.commons.io.FileUtils.getFile;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //для возможности использования аннотаций @Order()
public class WorkingFileTest {

    private static String URL = "http://localhost:8080";

    @Test
    @Order(1)
    public void checkUploadFile() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));

        String response = given()
//                .multiPart("file", getFile("src\\test\\java\\api\\workingWithFilePage\\test\\FileForUploadDownload.txt"))
                .multiPart("file", getFile("src\\test\\java\\api\\workingWithFilePage\\test\\FileForUploadDownload.jpg"))
                .contentType("multipart/form-data")
                .when()
                .post("filesInfo/upload")
                .then()
                .log().all()
                .log().all()
                .extract().asString();
        Assertions.assertEquals(response, "file uploaded");
    }

    @Test
    @Order(2)
    public void checkDownloadFileInfo() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));
        String fileName = "FileForUploadDownload.jpg";
        String fileNameNew = "FileForUploadDownloadNew.jpg";
        String MY_DIRECTORY = "src//test//java//api//workingWithFilePage//test//";

        ResponseBodyExtractionOptions file = given()
                .when()
                .get("filesInfo/download?param1=" + fileName)
                .then()
                .log().all()
                .extract().body();

        try {
            //Сохранение полученного файла перед проверкой
            byte[] bytes = file.asByteArray(); //разбили полученный файл на последовательность байт
            Path path = Paths.get(MY_DIRECTORY + fileNameNew);
            Files.write(path, bytes);

            Path filePath1 = Paths.get(MY_DIRECTORY + fileName);
            Path filePath2 = Paths.get(MY_DIRECTORY + fileNameNew);

            long mismatch = Files.mismatch(filePath1, filePath2); //Побайтовая проверка на совпадение 2-х файлов любого типа, если совпали возвращает "-1"
            Assertions.assertEquals(mismatch, -1);
            Files.delete(filePath2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void checkDeleteFile() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));
        String fileName = "FileForUploadDownload.jpg";
        given()
                .when()
                .delete("filesInfo/delete/" + fileName)
                .then().log().all();
    }

    @Test
    public void checkEmptyUploadFile() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(444));

        String response = given()
                .multiPart("file", getFile("src\\test\\java\\api\\workingWithFilePage\\test\\EmptyFileForUploadDownload.txt"))
//                .multiPart("file", getFile("src\\test\\java\\api\\workingWithFilePage\\test\\FileForUploadDownload.jpg"))
                .contentType("multipart/form-data")
                .when()
                .post("filesInfo/upload")
                .then()
                .log().all()
                .extract().asString();
        Assertions.assertEquals(response, "Not file for download");
    }

    @Test
    public void checkNOTDownloadFileInfo() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(444));
        String fileName = "xxx.jpg";

        String response = given()
                .when()
                .get("filesInfo/download?param1=" + fileName)
                .then()
                .log().all()
                .extract().asString();
        Assertions.assertEquals(response, "Not file for download");
    }

    @Test
    public void checkLoadAllFilesInfo() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(200));

        List<String> listName = given()
                .when()
                .get("filesInfo/findAllFilesName")
                .then()
                .log().all()
                .extract().body().jsonPath().getList(".", String.class);
        Assertions.assertTrue(!listName.isEmpty());
    }

}

