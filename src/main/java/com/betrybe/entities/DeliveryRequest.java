package com.betrybe.entities;

import com.betrybe.models.Position;
import com.betrybe.models.Video;

import java.time.LocalDateTime;

public class DeliveryRequest {
  private String schedule_delivery;
  private LocalDateTime delivery_date;
  private String status;
  private Integer droneId;
  private Video video;
  private Position position;

  public String getSchedule_delivery() {
    return schedule_delivery;
  }

  public void setSchedule_delivery(String schedule_delivery) {
    this.schedule_delivery = schedule_delivery;
  }

  public LocalDateTime getDelivery_date() {
    return delivery_date;
  }

  public void setDelivery_date(LocalDateTime delivery_date) {
    this.delivery_date = delivery_date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getDroneId() {
    return droneId;
  }

  public void setDroneId(Integer droneId) {
    this.droneId = droneId;
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
