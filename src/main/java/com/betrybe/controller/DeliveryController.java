package com.betrybe.controller;

import com.betrybe.models.Delivery;
import com.betrybe.service.DeliveryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/delivery")
public class DeliveryController {
  @Inject
  DeliveryService deliveryService;

  @GET
  public Response getAll(){
    List<Delivery> deliveries = deliveryService.getAll();
    return Response.ok(deliveries).build();
  }
}
