package com.betrybe.repository;

import com.betrybe.models.Drone;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DroneRepository implements PanacheRepositoryBase<Drone, Integer> {
}
