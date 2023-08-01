package com.betrybe.models;

import com.betrybe.enuns.Status;
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
  @ManyToOne
  @JoinColumn(name="drone_id")
  private Drone drone;
  @JoinColumn(name = "video_id")
  @OneToOne(fetch = FetchType.LAZY)
  private Video video;

  @JoinColumn(name = "position_id")
  @OneToOne(fetch = FetchType.LAZY)
  private Position position;
}
