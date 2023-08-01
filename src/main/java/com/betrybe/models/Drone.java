package com.betrybe.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Drone {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String model;
  @Column
  private String brand;
  @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Drone> drones;

}
