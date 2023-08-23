package com.betrybe.controller;

import com.betrybe.entities.DeliveryRequest;
import com.betrybe.entities.StatusRequest;
import com.betrybe.entities.StatusResponse;
import com.betrybe.enuns.Status;
import com.betrybe.exceptions.NotFoundException;
import com.betrybe.models.Delivery;
import com.betrybe.models.Drone;
import com.betrybe.service.DeliveryService;
import com.betrybe.service.DroneService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/delivery")
public class DeliveryController {
  @Inject
  DeliveryService deliveryService;
  @Inject
  DroneService droneService;

  @GET
  public Response getAll() {
    List<Delivery> deliveries = deliveryService.getAll();
    return Response.ok(deliveries).build();
  }

  @GET
  @Path("/{id}")
  public Response findById(@PathParam("id") Integer id) {
    Delivery delivery = deliveryService.findById(id);
    return Response.ok(delivery).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(DeliveryRequest deliveryRequest) throws NotFoundException {
    Delivery delivery = deliveryService.created(deliveryRequest);
    return Response.status(201).entity(delivery).build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/status/{id}")
  public Response updateStatus(@PathParam("id") Integer id, StatusRequest statusRequest) throws NotFoundException {
    Status newStatus = deliveryService.updateStatus(id, statusRequest.getStatus());
    StatusResponse statusResponse = new StatusResponse();
    statusResponse.setStatus(statusRequest.getStatus());
    return Response.status(200).entity(statusResponse).build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response update(@PathParam("id") Integer id, DeliveryRequest deliveryRequest) {
    Delivery delivery = deliveryService.update(id, deliveryRequest);
    return Response.status(200).entity(delivery).build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Integer id) {
    deliveryService.delete(id);
    return Response.ok().build();
  }
}
