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

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(DeliveryRequest deliveryRequest) throws NotFoundException {
    Drone droneSearch = droneService.findById(deliveryRequest.getDroneId());
    if (droneSearch == null) {
      throw new NotFoundException("Drone not Found");
    }
    Delivery delivery = deliveryService.created(deliveryRequest);
    return Response.status(201).entity(delivery).build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/status/{id}")
  public Response updateStatus(@PathParam("id") Integer id, StatusRequest statusRequest) throws NotFoundException {
    Delivery delivery = deliveryService.findById(id);
    if (delivery == null) {
      throw new NotFoundException("Delivery not found");
    }
    Status newStatus = deliveryService.updateStatus(id, statusRequest.getStatus());
    StatusResponse statusResponse = new StatusResponse();
    statusResponse.setStatus(statusRequest.getStatus());
    if (newStatus == null) {
      statusResponse.setStatus("status not allowed");
      return Response.status(400).entity(statusResponse).build();
    }
    return Response.status(200).entity(statusResponse).build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response update(@PathParam("id") Integer id, DeliveryRequest deliveryRequest) {
    Delivery deliverySearch = deliveryService.findById(id);
    if (deliverySearch == null) {
      throw new NotFoundException("Delivery not found");
    }
    if (deliveryRequest.getDroneId() != null) {
      Drone droneSearch = droneService.findById(deliveryRequest.getDroneId());
      if (droneSearch == null) {
        throw new NotFoundException("Drone not Found");
      }
    }
    Delivery delivery = deliveryService.update(id, deliveryRequest);
    return Response.status(200).entity(delivery).build();
  }
}
