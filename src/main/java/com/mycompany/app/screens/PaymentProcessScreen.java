package com.mycompany.app.screens;

import java.util.Scanner;

import com.mycompany.app.services.PaymentProcessService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentProcessScreen implements IScreen {
  private final PaymentProcessService paymentProcessService;

  @Override
  public void start(Scanner scan) {
    String cardNumber, cardName, cardYearExpiration, cardMonthExpiration;
    System.out.println("Card Info");
    exit:
    {
      System.out.println("Enter x at anytime to exit");
      System.out.print("Enter card name: ");
      cardName = scan.nextLine();
      if (cardName.equalsIgnoreCase("x")) break exit;
      do {
        System.out.print("\n Enter Card Number: ");
        cardNumber = scan.nextLine();
        if (cardNumber.equalsIgnoreCase("x")) break exit;
      } while (!paymentProcessService.cardNumberValid(cardNumber));
      do {
        System.out.print("\n Enter year expiration date: ");
        cardYearExpiration = scan.nextLine();
        if (cardYearExpiration.equalsIgnoreCase("x")) break exit;
        System.out.print("\n Enter month expiration date: ");
        cardMonthExpiration = scan.nextLine();
        if (cardMonthExpiration.equalsIgnoreCase("x")) break exit;
      } while (!paymentProcessService.cardExpirationDateValid(
          cardMonthExpiration, cardYearExpiration));
      if (paymentProcessService.paymentProcessedSuccessfully(
          cardName, cardNumber, cardYearExpiration, cardMonthExpiration)) {
        System.out.println("payment processed successfully!");
      } else {
        System.out.println("payment NOT processed Successfully");
      }
    }
  }
}
