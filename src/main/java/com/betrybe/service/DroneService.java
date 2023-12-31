package com.betrybe.service;

import com.betrybe.exceptions.NotFoundException;
import com.betrybe.models.Drone;
import com.betrybe.repository.DroneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DroneService {
  @Inject
  DroneRepository droneRepository;

  @Transactional
  public Drone create(Drone droneRequest) {
    Drone drone = new Drone();
    drone.setBrand(droneRequest.getBrand());
    drone.setModel(droneRequest.getModel());
    droneRepository.persist(drone);
    return drone;
  }

  @Transactional
  public Drone update(Integer id, Drone droneRequest) {
    Drone drone = this.findById(id);
    drone.setBrand(droneRequest.getBrand());
    drone.setModel(droneRequest.getModel());
    droneRepository.persist(drone);
    return drone;
  }

  @Transactional
  public void delete(Integer id) {
    Drone drone = this.findById(id);
    droneRepository.delete(drone);
  }

  public List<Drone> getall() {
    return droneRepository.listAll();
  }

  public Drone findById(Integer id) {
    Optional<Drone> drone = Optional.ofNullable(droneRepository.findById(id));
    return drone.orElseThrow(() -> new NotFoundException("Drone not Found"));
  }
}
