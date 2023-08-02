package com.betrybe.service;

import com.betrybe.models.Video;
import com.betrybe.repository.VideoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class VideoService {
  @Inject
  VideoRepository videoRepository;

  @Transactional
  public Video Created(Video videoRequest) {
    Video video = new Video();
    video.setLink(videoRequest.getLink());
    videoRepository.persist(video);
    return video;
  }

  public List<Video> getAll() {
    return videoRepository.listAll();
  }

  public Video findById(Integer id) {
    return videoRepository.findById(id);
  }
}
