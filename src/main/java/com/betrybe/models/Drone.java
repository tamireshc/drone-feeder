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
  private List<Delivery> deliveries;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void addDelivery(Delivery delivery) {
    deliveries.add(delivery);
  }
}
