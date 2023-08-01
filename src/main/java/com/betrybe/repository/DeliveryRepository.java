package com.betrybe.repository;

import com.betrybe.models.Delivery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public class DeliveryRepository implements PanacheRepositoryBase<Delivery, Integer> {
}
