package com.betrybe.service;

import com.betrybe.exceptions.NotFoundException;
import com.betrybe.models.Video;
import com.betrybe.repository.VideoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VideoService {
  @Inject
  VideoRepository videoRepository;

  @Transactional
  public Video created(Video videoRequest) {
    Video video = new Video();
    video.setLink(videoRequest.getLink());
    videoRepository.persist(video);
    return video;
  }

  public List<Video> getAll() {
    return videoRepository.listAll();
  }

  public Video findById(Integer id) {
    Optional<Video> video = Optional.ofNullable(videoRepository.findById(id));
    return video.orElseThrow(() -> new NotFoundException("Video not Found"));
  }
}
