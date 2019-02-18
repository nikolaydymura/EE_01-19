package edu.ee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest {

  @Test
  void checkSumFor1And1() {
    int expected = 2;
    int result = Main.add(1, 1);
    Assertions.assertEquals(expected, result);
  }

  @Test
  void checkInvalidFirstArgument() {
    Assertions.assertThrows(ArithmeticException.class, () -> {
      Main.add(-1, 1);
    });
  }
}
