package com.goal.restservice.util;

import org.junit.jupiter.api.Test;

public class PasswordEncodingTest {

  @Test
  public void test() {
    PasswordEncoding passwordEncoding = new PasswordEncoding();

    String rawPassword1 = "12345678";
    String rawPassword2 = "12345678";

    String newPassword1 = passwordEncoding.encode(rawPassword1);
    String newPassword2 = passwordEncoding.encode(rawPassword2);

    System.out.println("newPassword1 : " + newPassword1);
    System.out.println("newPassword2 : " + newPassword2);

    System.out.println("\nmatches : " + passwordEncoding.matches(rawPassword1, newPassword1));
    System.out.println("matches : " + passwordEncoding.matches(rawPassword2, newPassword1));
  }
}
