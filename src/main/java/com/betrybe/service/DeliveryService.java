package com.betrybe.service;

import com.betrybe.entities.DeliveryRequest;
import com.betrybe.models.Delivery;
import com.betrybe.models.Drone;
import com.betrybe.models.Position;
import com.betrybe.models.Video;
import com.betrybe.repository.DeliveryRepository;
import com.betrybe.repository.DroneRepository;
import com.betrybe.repository.PositionRepository;
import com.betrybe.repository.VideoRepository;
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

  @Transactional
  public Delivery created(DeliveryRequest deliveryRequest) {
    Delivery delivery = new Delivery();
    Drone drone = droneRepository.findById(deliveryRequest.getDroneId());

    Position position = deliveryRequest.getPosition();
    positionRepository.persist(position);

    delivery.setSchedule_delivery(LocalDateTime.now());
    delivery.setDrone(drone);
    delivery.setPosition(position);
    delivery.setStatus(deliveryRequest.getStatus());
    deliveryRepository.persist(delivery);
    return delivery;
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
