package com.betrybe;

import com.betrybe.models.Video;
import com.betrybe.repository.DroneRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@Testcontainers
public class VideoControllerTest {

  @Inject
  DroneRepository droneRepository;

  @Container
  private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
    .withDatabaseName("testdrone_feeder")
    .withUsername("root")
    .withPassword("root");

  @DisplayName("1 - Deve cadastrar um video com sucesso.")
  @Test
  @Transactional
  void DeveCadastrarNovoVideo() {
    Video newVideo = new Video();
    newVideo.setLink("http://video.com");

    given()
      .contentType("application/json")
      .body(newVideo)
      .when()
      .post("/video")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode())
      .body("link", equalTo("http://video.com"));
  }

  @DisplayName("2 - Deve listar todos os videos com sucesso.")
  @Test
  @Transactional
  void DeveListarTodosOsVideos() {
    Video newVideo = new Video();
    newVideo.setLink("http://video.com");

    given()
      .contentType("application/json")
      .body(newVideo)
      .when()
      .post("/video")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    given()
      .when()
      .get("/video")
      .then()
      .statusCode(Response.Status.OK.getStatusCode());
  }

  @DisplayName("3 - Deve buscar um video pelo id com sucesso..")
  @Test
  @Transactional
  void DeveListarUmVideoPorId() {
    Video newVideo = new Video();
    newVideo.setLink("http://video.com");

    given()
      .contentType("application/json")
      .body(newVideo)
      .when()
      .post("/video")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    given()
      .when()
      .get("/video/1")
      .then()
      .statusCode(Response.Status.OK.getStatusCode());
  }

  @DisplayName("4 - Tenta buscar um video com id inexistente e retorna o Status 404 ")
  @Test
  @Transactional
  void TentaBuscarUmVideoComIdInexistente() {

    given()
      .when()
      .get("/video/99")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Video not Found"));
  }

}
