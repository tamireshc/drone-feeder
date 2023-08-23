package com.betrybe.controller;

import com.betrybe.exceptions.NotFoundException;
import com.betrybe.models.Video;
import com.betrybe.service.VideoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/video")
public class VideoController {
  @Inject
  VideoService videoService;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(Video videoRequest) {
    Video video = videoService.created(videoRequest);
    return Response.status(201).entity(video).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll() {
    List<Video> videos = videoService.getAll();
    return Response.ok(videos).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response findById(@PathParam("id") Integer id) throws NotFoundException {
    Video video = videoService.findById(id);
    return Response.ok(video).build();
  }
}
