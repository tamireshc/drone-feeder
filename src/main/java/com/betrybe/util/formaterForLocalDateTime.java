package com.betrybe.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class formaterForLocalDateTime {
  public static  LocalDateTime conversos(String dataHoraStr){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
    return dataHora;
  }
}
