package com.betrybe.exceptions;

import com.betrybe.entities.Error;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider

public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
  @Override
  public Response toResponse(NotFoundException exception) {
    var mensagemErro = exception.getMessage();
    var erro = new Error();
    erro.setMessage(mensagemErro);
    return Response.status(404).entity(erro).build();
  }
}
