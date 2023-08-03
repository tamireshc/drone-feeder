package com.betrybe;

import com.betrybe.models.Drone;
import com.betrybe.repository.DroneRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@Testcontainers
public class DroneControllerTest {

  @Inject
  DroneRepository droneRepository;

  @Container
  private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
    .withDatabaseName("testdrone_feeder")
    .withUsername("root")
    .withPassword("root");

//  @AfterEach
//  @Transactional
//  void cleanUp() {
//    this.droneRepository.deleteAll();
//  }

  @DisplayName("1 - Deve cadastrar um drone com sucesso.")
  @Test
  @Transactional
  void DeveCadastrarNovoDrone() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode())
      .body("model", equalTo("Mavic 3 Pro"))
      .body("brand", equalTo("DJI"));
  }
  @DisplayName("2 - Deve listar todos os drones com sucesso.")
  @Test
  @Transactional
  void DeveListarTodosOsDrone() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone1)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Drone newDrone2 = new Drone();
    newDrone2.setBrand("Xiaomi");
    newDrone2.setModel("Fimi X8 Mini");

    given()
      .contentType("application/json")
      .body(newDrone2)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    given()
      .when()
      .get("/drone")
      .then()
      .statusCode(Response.Status.OK.getStatusCode());
  }

  @DisplayName("3 - Deve buscar um drone pelo id com sucesso..")
  @Test
  @Transactional
  void DeveListarUmDronePorId() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone1)
      .when()
      .post("/drone");

    given()
      .when()
      .get("/drone/1")
      .then()
      .statusCode(Response.Status.OK.getStatusCode());
  }

  @DisplayName("4 - Deve buscar um drone com id inexistente e retornar  o Status 404 ")
  @Test
  @Transactional
  void DeveBuscarUmDronePorIdInexistente() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone1)
      .when()
      .post("/drone");

    given()
      .when()
      .get("/drone/99")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Drone not Found"));
  }

  @DisplayName("5 - Deletar um drone pelo id com sucesso.")
  @Test
  @Transactional
  void DeveDeletarUmDronePorId() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone1)
      .when()
      .post("/drone");

    given()
      .when()
      .delete("/drone/1")
      .then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body(is("Drone Deleted"));
  }

  @DisplayName("6 - Deve deletar um drone com id inexistente e retornar  o Status 404 ")
  @Test
  @Transactional
  void DeveDeletarUmDronePorIdInexistente() {

    given()
      .when()
      .delete("/drone/99")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Drone not Found"));
  }

  @DisplayName("7 - Deve atualizar um drone pelo id com sucesso.")
  @Test
  @Transactional
  void DeveAtualizarUmDronePorId() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode())
      .body("model", equalTo("Mavic 3 Pro"))
      .body("brand", equalTo("DJI"));

    Drone newDrone2 = new Drone();
    newDrone2.setBrand("Xiaomi");
    newDrone2.setModel("Fimi X8 Mini");

    given()
      .contentType("application/json")
      .body(newDrone2)
      .when()
      .put("/drone/1")
      .then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("model", equalTo("Fimi X8 Mini"))
      .body("brand", equalTo("Xiaomi"));
  }

  @DisplayName("8 - Deve atualizar um drone com id inexistente e retornar  o Status 404 ")
  @Test
  @Transactional
  void DeveAtualizarUmDronePorIdInexistente() {
    Drone newDrone2 = new Drone();
    newDrone2.setBrand("Xiaomi");
    newDrone2.setModel("Fimi X8 Mini");

    given()
      .contentType("application/json")
      .body(newDrone2)
      .when()
      .put("/drone/99")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Drone not found"));
  }

}
