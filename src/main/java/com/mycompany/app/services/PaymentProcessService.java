package com.mycompany.app.services;

import java.time.LocalDate;

public class PaymentProcessService {
  public boolean cardNumberValid(String cardNumber) {
    if (!digitsOnly(cardNumber)) return false;
    if (cardNumber.length() == 16) {
      return true;
    } else {
      System.out.println("card number has to be 16 digits");
      return false;
    }
  }

  public boolean cardExpirationDateValid(String month, String year) {
    if (!digitsOnly(month)) return false;
    if (!digitsOnly(year)) return false;
    int monthInt = Integer.parseInt(month);
    int yearInt = Integer.parseInt(year);
    LocalDate localDate = LocalDate.now();
    if (yearInt < localDate.getYear()) {
      System.out.println("year needs to be today's year or future year");
      return false;
    }
    if (monthInt > 12) {
      System.out.println("month needs to be between 0 and 12");
      return false;
    }
    if (yearInt == localDate.getYear() && (monthInt < localDate.getMonthValue())) {
      System.out.println("month needs to be today's month or future month");
      return false;
    }
    return true;
  }

  public boolean digitsOnly(String str) {
    if (!str.matches("\\d+")) { // if string is NOT only numbers
      System.out.println("enter only numbers");
      return false;
    } else {
      return true;
    }
  }

  public boolean paymentProcessedSuccessfully(
      String name, String cardNumber, String cardYear, String cardMonth) {
    // TODO verify card
    return true;
  }
}
