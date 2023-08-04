package com.betrybe;

import com.betrybe.models.Drone;
import com.betrybe.models.Video;
import com.betrybe.repository.DroneRepository;
import com.betrybe.repository.VideoRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@Testcontainers
public class VideoControllerTest {

  @Inject
  VideoRepository videoRepository;

  @BeforeEach
  @Transactional
  public void setupDatabase() {
    Video newVideo = new Video();
    newVideo.setLink("http://video.com");
    videoRepository.persist(newVideo);
  }
  @AfterEach
  @Transactional
  public void cleanUp() {
    videoRepository.deleteAll();
  }

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

    given()
      .when()
      .get("/video")
      .then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body(containsString("http://video.com"));
  }

  @DisplayName("3 - Deve buscar um video pelo id com sucesso..")
  @Test
  @Transactional
  void DeveListarUmVideoPorId() {
    List<Video> video = videoRepository.listAll();

    given()
      .when()
      .get("/video/" + video.get(0).getId())
      .then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body(containsString("http://video.com"));
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
