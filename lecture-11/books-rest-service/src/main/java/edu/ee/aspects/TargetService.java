package edu.ee.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TargetService {

  private static final Logger logger = LoggerFactory.getLogger(TargetService.class);

  public void doSomething() {
    logger.info("do something");
  }

  public void doSomething1() {
    logger.info("do something 1");
  }

  public Long doSomething2(long id) {
    if (id < 0) throw new RuntimeException("Less that zero");
    logger.info("do something 2 " + id);
    return id + 200;
  }

  private void doSomething3() {
    logger.info("do something 3");
  }

  public int doSomething4(int id) {
    logger.info("do something 4 " + id);
    return id + 100;
  }
}
