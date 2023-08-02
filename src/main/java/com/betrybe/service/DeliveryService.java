package com.betrybe.service;

import com.betrybe.entities.DeliveryRequest;
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

    delivery.setStatus(Status.CREATED);

    deliveryRepository.persist(delivery);
    return delivery;
  }

  @Transactional
  public Status updateStatus(Integer id, String statusRequest) {
    Delivery delivery = deliveryRepository.findById(id);
    Status status = null;
    switch (statusRequest) {
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

    if (deliveryRequest.getSchedule_delivery() != null) {
      LocalDateTime scheduleDate = FormaterForLocalDateTime.conversor(deliveryRequest.getSchedule_delivery());
      delivery.setSchedule_delivery(scheduleDate);
    }

    if (deliveryRequest.getStatus().equals("FINISHED")) {
      delivery.setDelivery_date(LocalDateTime.now());
    }

    if (deliveryRequest.getStatus() != null) {
      this.updateStatus(id, deliveryRequest.getStatus());
    }

    if (deliveryRequest.getVideo() != null) {
      Video video = new Video();
      video.setLink(deliveryRequest.getVideo().getLink());
      videoRepository.persist(video);
      delivery.setVideo(video);
    }

    if (deliveryRequest.getDroneId() != null) {
      Drone drone = droneRepository.findById(deliveryRequest.getDroneId());
      delivery.setDrone(drone);
    }

    if (deliveryRequest.getPosition() != null) {
      Position position = deliveryRequest.getPosition();
      positionRepository.persist(position);
      delivery.setPosition(position);
    }

    deliveryRepository.persist(delivery);
    return delivery;
  }
}
