package com.betrybe.repository;

import com.betrybe.models.Video;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VideoRepository implements PanacheRepositoryBase<Video, Integer> {
}
