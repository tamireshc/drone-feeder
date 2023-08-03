package com.betrybe.models;

import com.betrybe.enuns.Status;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private LocalDateTime schedule_delivery;
  @Column
  private LocalDateTime delivery_date;
  @Column
  private Status status;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "drone_id")
  private Drone drone;

  @JoinColumn(name = "video_id")
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private Video video;

  @JoinColumn(name = "position_id")
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private Position position;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDateTime getSchedule_delivery() {
    return schedule_delivery;
  }

  public void setSchedule_delivery(LocalDateTime schedule_delivery) {
    this.schedule_delivery = schedule_delivery;
  }

  public LocalDateTime getDelivery_date() {
    return delivery_date;
  }

  public void setDelivery_date(LocalDateTime delivery_date) {
    this.delivery_date = delivery_date;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
}
