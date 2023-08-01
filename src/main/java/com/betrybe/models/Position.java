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

  @OneToOne(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Delivery delivery;

}
