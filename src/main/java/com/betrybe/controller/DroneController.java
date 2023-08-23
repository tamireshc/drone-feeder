package com.betrybe.controller;

import com.betrybe.exceptions.NotFoundException;
import com.betrybe.models.Drone;
import com.betrybe.service.DroneService;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/drone")
public class DroneController {
  @Inject
  DroneService droneService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(Drone droneRequest) {
    Drone drone = droneService.create(droneRequest);
    return Response.status(201).entity(drone).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll() {
    List<Drone> drones = droneService.getall();
    return Response.ok(drones).build();
  }

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") Integer id, Drone drone) throws NotFoundException {
    Drone droneSearch = droneService.findById(id);
    if (droneSearch == null) {
      throw new NotFoundException("Drone not found");
    }
    Drone droneAtt = droneService.update(id, drone);
    return Response.ok(droneAtt).build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Integer id) {
    Drone droneSearch = droneService.findById(id);
    if (droneSearch == null) {
      throw new NotFoundException("Drone not Found");
    }
    droneService.delete(id);
    return Response.ok("Drone Deleted").build();
  }

  @GET
  @Path("/{id}")
  public Response findById(@PathParam("id") Integer id) {
    Drone droneSearch = droneService.findById(id);
    return Response.ok(droneSearch).build();
  }
}
