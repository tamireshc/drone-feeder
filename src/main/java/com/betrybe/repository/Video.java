package com.betrybe.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Video implements PanacheRepositoryBase<Video, Integer> {
}
