package edu.ee;

public class Mail implements PrintableSource {

  private String content;

  public Mail() {
    System.out.println(getClass().getSimpleName());
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
