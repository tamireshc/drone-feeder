package com.betrybe;

import com.betrybe.models.Drone;
import com.betrybe.repository.DroneRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import jakarta.inject.Inject;
import java.util.List;

@QuarkusTest
@Testcontainers
public class DroneRepositoryTest {

  @Inject
  DroneRepository droneRepository;

  @Container
  private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
    .withDatabaseName("testdrone_feeder")
    .withUsername("root")
    .withPassword("root");

  @AfterEach
  @Transactional
  void cleanUp() {
    this.droneRepository.deleteAll();
  }

  @DisplayName("1 - Deve cadastrar um drone com sucesso.")
  @Test
  @Transactional
  void DeveCadastrarNovoDrone() {
    Drone newDrone = new Drone();
    newDrone.setBrand("DJI");
    newDrone.setModel("Mavic 3 Pro");
    droneRepository.persist(newDrone);
    List<Drone> result = droneRepository.listAll();
    Assertions.assertNotNull(result.get(0));
    Assertions.assertEquals(result.get(0).getBrand(), "DJI");
    Assertions.assertEquals(result.get(0).getModel(), "Mavic 3 Pro");
  }

  @DisplayName("2 - Deve listar todos os drones com sucesso.")
  @Test
  @Transactional
  void DeveListarTodosOsDrone() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");
    droneRepository.persist(newDrone1);

    Drone newDrone2 = new Drone();
    newDrone2.setBrand("Xiaomi");
    newDrone2.setModel("Fimi X8 Mini");
    droneRepository.persist(newDrone2);

    List<Drone> result = droneRepository.listAll();

    Assertions.assertEquals(result.size(), 2);

    Assertions.assertNotNull(result.get(0));
    Assertions.assertEquals(result.get(0).getBrand(), "DJI");
    Assertions.assertEquals(result.get(0).getModel(), "Mavic 3 Pro");

    Assertions.assertNotNull(result.get(1));
    Assertions.assertEquals(result.get(1).getBrand(), "Xiaomi");
    Assertions.assertEquals(result.get(1).getModel(), "Fimi X8 Mini");
  }

  @DisplayName("3 - Deve buscar um drone pelo id com sucesso..")
  @Test
  @Transactional
  void DeveListarUmDronePorId() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");
    droneRepository.persist(newDrone1);

    Drone result = droneRepository.findById(1);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getBrand(), "DJI");
    Assertions.assertEquals(result.getModel(), "Mavic 3 Pro");
  }

  @DisplayName("4 - Deve atualizar um drone pelo id com sucesso.")
  @Test
  @Transactional
  void DeveAtualizarUmDronePorId() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");
    droneRepository.persist(newDrone1);

    Drone result = droneRepository.findById(1);
    result.setBrand("Xiaomi");
    result.setModel("Fimi X8 Mini");

    Assertions.assertEquals(result.getBrand(), "Xiaomi");
    Assertions.assertEquals(result.getModel(), "Fimi X8 Mini");
  }

  @DisplayName("5 - Deletar um drone pelo id com sucesso.")
  @Test
  @Transactional
  void DeveDeletarUmDronePorId() {
    Drone newDrone1 = new Drone();
    newDrone1.setBrand("DJI");
    newDrone1.setModel("Mavic 3 Pro");
    droneRepository.persist(newDrone1);

    List<Drone> result1 = droneRepository.listAll();
    Assertions.assertEquals(result1.size(), 1);

    droneRepository.deleteById(1);

    List<Drone> result2 = droneRepository.listAll();
    Assertions.assertEquals(result2.size(), 0);
  }
}
