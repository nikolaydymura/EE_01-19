package edu.ee;

public class Book  implements  PrintableSource{

  private String content;

  public Book() {
    System.out.println(getClass().getSimpleName());
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
