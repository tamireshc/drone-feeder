package com.betrybe.models;

import jakarta.persistence.*;

@Entity
public class Position {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String latitude;
  @Column
  private String longitude;

//  @OneToOne(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//  private Delivery delivery;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

//  public Delivery getDelivery() {
//    return delivery;
//  }
//
//  public void setDelivery(Delivery delivery) {
//    this.delivery = delivery;
//  }
}
