package com.betrybe.exceptions;

import com.betrybe.entities.Error;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider

public class StatusNotAllowedExceptionHandler implements ExceptionMapper<StatusNotAllowedException> {
  @Override
  public Response toResponse(StatusNotAllowedException exception) {
    var mensagemErro = exception.getMessage();
    var erro = new Error();
    erro.setMessage(mensagemErro);
    return Response.status(405).entity(erro).build();
  }
}
