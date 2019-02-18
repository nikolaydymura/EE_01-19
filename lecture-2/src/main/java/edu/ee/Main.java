package edu.ee;

public class Main {

  public static int add(int a, int b) throws ArithmeticException {
    if (a < 0) {
      throw new ArithmeticException();
    }
    return a + b;
  }

  public static void main(String[] args) {
    System.out.println("Hello");
  }
}
