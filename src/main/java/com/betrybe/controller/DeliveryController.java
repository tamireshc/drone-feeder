package com.betrybe.controller;

import com.betrybe.entities.DeliveryRequest;
import com.betrybe.models.Delivery;
import com.betrybe.service.DeliveryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/delivery")
public class DeliveryController {
  @Inject
  DeliveryService deliveryService;

  @GET
  public Response getAll() {
    List<Delivery> deliveries = deliveryService.getAll();
    return Response.ok(deliveries).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(DeliveryRequest deliveryRequest) {
    Delivery delivery = deliveryService.created(deliveryRequest);
    return Response.status(201).entity(delivery).build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response update(@PathParam("id") Integer id, DeliveryRequest deliveryRequest) {
    System.out.println(id);
    Delivery delivery = deliveryService.update(id, deliveryRequest);
    return Response.status(200).entity(delivery).build();
  }
}
