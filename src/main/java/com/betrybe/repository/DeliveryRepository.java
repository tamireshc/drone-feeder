package com.betrybe.repository;

import com.betrybe.models.Delivery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeliveryRepository implements PanacheRepositoryBase<Delivery, Integer> {
}
