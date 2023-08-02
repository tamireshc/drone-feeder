package com.betrybe.service;

import com.betrybe.entities.DeliveryRequest;
import com.betrybe.entities.StatusRequest;
import com.betrybe.enuns.Status;
import com.betrybe.models.Delivery;
import com.betrybe.models.Drone;
import com.betrybe.models.Position;
import com.betrybe.models.Video;
import com.betrybe.repository.DeliveryRepository;
import com.betrybe.repository.DroneRepository;
import com.betrybe.repository.PositionRepository;
import com.betrybe.repository.VideoRepository;
import com.betrybe.util.FormaterForLocalDateTime;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.annotations.Pos;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class DeliveryService {
  @Inject
  DeliveryRepository deliveryRepository;
  @Inject
  DroneRepository droneRepository;

  @Inject
  PositionRepository positionRepository;

  @Inject
  VideoRepository videoRepository;

  public List<Delivery> getAll() {
    return deliveryRepository.listAll();
  }

  public Delivery findById(Integer id) {
    return deliveryRepository.findById(id);
  }

  @Transactional
  public Delivery created(DeliveryRequest deliveryRequest) {
    Delivery delivery = new Delivery();

    Drone drone = droneRepository.findById(deliveryRequest.getDroneId());

    delivery.setDrone(drone);

    Position position = deliveryRequest.getPosition();
    delivery.setPosition(position);
    positionRepository.persist(position);

    LocalDateTime dateTimeSchedule = FormaterForLocalDateTime.conversor(deliveryRequest.getSchedule_delivery());
    delivery.setSchedule_delivery(dateTimeSchedule);

    delivery.setStatus(deliveryRequest.getStatus());

    deliveryRepository.persist(delivery);
    return delivery;
  }

  @Transactional
  public Status updateStatus(Integer id, StatusRequest statusRequest) {
    Delivery delivery = deliveryRepository.findById(id);
    Status status = null;
    switch (statusRequest.getStatus()) {
      case "ONROUTE":
        status = Status.ONROUTE;
        break;
      case "FINISHED":
        status = Status.FINISHED;
        break;
      case "CANCELED":
        status = Status.CANCELED;
        break;
    }
    delivery.setStatus(status);
    deliveryRepository.persist(delivery);
    return status;
  }

  @Transactional
  public Delivery update(Integer id, DeliveryRequest deliveryRequest) {
    Delivery delivery = deliveryRepository.findById(id);
    Video video = new Video();
    video.setLink(deliveryRequest.getVideo().getLink());
    videoRepository.persist(video); // colocar fora na classe video service
    System.out.println("delivery" + delivery.getId());
//    Drone drone = droneRepository.findById(deliveryRequest.getDroneId());
//    Position position = deliveryRequest.getPosition();
//    positionRepository.persist(position);
    delivery.setSchedule_delivery(LocalDateTime.now());
    delivery.setDelivery_date(LocalDateTime.now());
//    delivery.setDrone(drone);
//    delivery.setPosition(position);
    delivery.setStatus(deliveryRequest.getStatus());
    delivery.setVideo(video);
    deliveryRepository.persist(delivery);
    return null;
  }
}
