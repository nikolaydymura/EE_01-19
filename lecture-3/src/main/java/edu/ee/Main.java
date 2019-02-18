package edu.ee;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {

  public static void main(String[] args) throws Exception {
    Properties properties = new Properties();

    properties.load(new FileInputStream("/Users/nikolaydymura/IdeaProjects/DIiOC/src/main/resources/config.properties"));

    DependencyRegistry registry = new DependencyRegistry(properties);
    Printer printer = registry.getDependency("printer");
    printer.print();
  }
}
