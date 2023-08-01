package com.betrybe.repository;

import com.betrybe.models.Position;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PositionRepository implements PanacheRepositoryBase<Position, Integer> {
}
