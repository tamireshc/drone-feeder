package com.betrybe.service;

import com.betrybe.models.Delivery;
import com.betrybe.repository.DeliveryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DeliveryService {
  @Inject
  DeliveryRepository deliveryRepository;

  public List<Delivery> getAll(){
    return deliveryRepository.listAll();
  }
}
