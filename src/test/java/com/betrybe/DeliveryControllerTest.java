package com.betrybe;

import com.betrybe.entities.DeliveryRequest;
import com.betrybe.entities.StatusRequest;
import com.betrybe.models.Drone;
import com.betrybe.models.Position;
import com.betrybe.models.Video;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@Testcontainers
public class DeliveryControllerTest {

  @Inject
  DroneRepository droneRepository;

  @Container
  private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
    .withDatabaseName("testdrone_feeder")
    .withUsername("root")
    .withPassword("root");

  @DisplayName("1 - Deve cadastrar uma delivery com sucesso.")
  @Test
  @Transactional
  void DeveCadastrarNovaDelivery() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(1);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode())
      .body("status", equalTo("CREATED"))
      .body("schedule_delivery", equalTo("2023-05-25T16:34:00"));
  }

  @DisplayName("2 - Ao cadastrar uma delivery com drone inexistente deve retornar status 404.")
  @Test
  @Transactional
  void TentaCadastrarUmaDeliveryComDroneInexistente() {
    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(99);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Drone not Found"));
  }

  @DisplayName("3 - Atualiza o status de uma delivery com sucesso")
  @Test
  @Transactional
  void AtualizaStatusDeUmaDelivery() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(1);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    StatusRequest statusRequest = new StatusRequest();
    statusRequest.setStatus("CANCELED");

    given()
      .contentType("application/json")
      .body(statusRequest)
      .when()
      .put("delivery/status/1")
      .then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("status", equalTo("CANCELED"));
  }

  @DisplayName("4 - Tenta atualizar o status de uma delivery inexistente")
  @Test
  @Transactional
  void TentaAtualizarStatusDeUmaDeliveryComIdInexistente() {

    StatusRequest statusRequest = new StatusRequest();
    statusRequest.setStatus("CANCELED");

    given()
      .contentType("application/json")
      .body(statusRequest)
      .when()
      .put("delivery/status/99")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Delivery not found"));
  }

  @DisplayName("5 - Tenta atualizar uma delivery com um status não permitido e retorna status 400")
  @Test
  @Transactional
  void TentaAtualizarUmaDeliveryComStatusNaoPermitido() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(1);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    StatusRequest statusRequest = new StatusRequest();
    statusRequest.setStatus("XXXX");

    given()
      .contentType("application/json")
      .body(statusRequest)
      .when()
      .put("delivery/status/1")
      .then()
      .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
      .body("status", equalTo("status not allowed"));
  }

  @DisplayName("6 - Deve listar todos as deliveries com sucesso.")
  @Test
  @Transactional
  void DeveListarTodasOsDeliveries() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(1);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    given()
      .when()
      .get("/delivery")
      .then()
      .statusCode(Response.Status.OK.getStatusCode());
  }


  @DisplayName("7 - Deleta uma delivery pelo seu id com sucesso.")
  @Test
  @Transactional
  void DeveDeletarUmDeliveryPorId() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(1);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    given()
      .when()
      .delete("/delivery/1")
      .then()
      .statusCode(Response.Status.OK.getStatusCode());
  }

  @DisplayName("8 - Tenta deletar uma delivery com id inexistente e retornar o Status 404 ")
  @Test
  @Transactional
  void TentaDeletarUmaDeliveryComIdInexistente() {

    given()
      .when()
      .delete("/delivery/99")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Delivery not found"));
  }

  @DisplayName("8 - Deve atualizar uma delivery pelo id com sucesso.")
  @Test
  @Transactional
  void DeveAtualizarUmaDeliveryPorId() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(1);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    DeliveryRequest deliveryRequest2  = new DeliveryRequest();

    Video newVideo = new Video();
    newVideo.setLink("http://video.com");

    deliveryRequest2.setStatus("FINISHED");
    deliveryRequest.setVideo(newVideo);


    given()
      .contentType("application/json")
      .body(deliveryRequest2)
      .when()
      .put("delivery/1")
      .then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("status", equalTo("FINISHED"));

  }

  @DisplayName("9 - Tenta atualizar uma delivery com um drone inexistente e retornar  o Status 404")
  @Test
  @Transactional
  void TentaAtualizarUmaDeliveryComDroneInexistente() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");

    given()
      .contentType("application/json")
      .body(newDrone)
      .when()
      .post("/drone")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    Position position = new Position();
    position.setLatitude("38° N");
    position.setLongitude("0° O");

    DeliveryRequest deliveryRequest  = new DeliveryRequest();

    deliveryRequest.setStatus("CREATED");
    deliveryRequest.setDroneId(1);
    deliveryRequest.setSchedule_delivery("25/05/2023-16:34");
    deliveryRequest.setPosition(position);

    given()
      .contentType("application/json")
      .body(deliveryRequest)
      .when()
      .post("/delivery")
      .then()
      .statusCode(Response.Status.CREATED.getStatusCode());

    DeliveryRequest deliveryRequest2  = new DeliveryRequest();

    Video newVideo = new Video();
    newVideo.setLink("http://video.com");

    deliveryRequest2.setStatus("FINISHED");
    deliveryRequest.setVideo(newVideo);
    deliveryRequest2.setDroneId(99);


    given()
      .contentType("application/json")
      .body(deliveryRequest2)
      .when()
      .put("delivery/1")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Drone not Found"));
  }

  @DisplayName("10 - Tenta atualizar uma delivery inexistente e retorna o Status 404")
  @Test
  @Transactional
  void TentaAtualizarUmaDeliveryInexistente() {
    DeliveryRequest deliveryRequest2  = new DeliveryRequest();

    Video newVideo = new Video();
    newVideo.setLink("http://video.com");

    deliveryRequest2.setStatus("FINISHED");
    deliveryRequest2.setVideo(newVideo);

    given()
      .contentType("application/json")
      .body(deliveryRequest2)
      .when()
      .put("delivery/99")
      .then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode())
      .body("message", equalTo("Delivery not found"));
  }

}
