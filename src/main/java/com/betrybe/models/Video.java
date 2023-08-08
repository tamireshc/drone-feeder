package com.betrybe.models;

import jakarta.persistence.*;

@Entity
public class Video {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String link;

  @OneToOne(mappedBy = "video")
  private Delivery delivery;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

}