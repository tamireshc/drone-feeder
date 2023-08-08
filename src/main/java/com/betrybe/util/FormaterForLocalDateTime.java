package com.betrybe.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormaterForLocalDateTime {
  public static LocalDateTime conversor(String dataHoraStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
    return dataHora;
  }
}
