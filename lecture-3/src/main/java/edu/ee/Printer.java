package edu.ee;

public class Printer {

  private PrintableSource source;

  public Printer() {

  }

  public void setSource(PrintableSource source) {
    this.source = source;
  }

  public void print(){
    String content = source.getContent();
    System.out.println(content);
  }
}
